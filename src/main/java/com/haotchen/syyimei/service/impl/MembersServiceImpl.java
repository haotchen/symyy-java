package com.haotchen.syyimei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.dao.mapper.MembersMapper;
import com.haotchen.syyimei.entity.Img;
import com.haotchen.syyimei.entity.Members;
import com.haotchen.syyimei.entity.dto.MembersDTO;
import com.haotchen.syyimei.entity.vo.JsCodeResult;
import com.haotchen.syyimei.entity.vo.MembersVO;
import com.haotchen.syyimei.entity.vo.QNObjectResult;
import com.haotchen.syyimei.service.ImgService;
import com.haotchen.syyimei.service.MembersService;
import com.haotchen.syyimei.utils.QNObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements MembersService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    ImgService imgService;

    @Override
    public Boolean saveMember(MembersDTO membersDTO) {
        Members members = new Members();
        BeanUtils.copyProperties(membersDTO, members);
        members.setCreateTime(LocalDateTime.now());
        return this.save(members);
    }

    @Override
    public MembersVO getMemberInfo(String openId) {
        Members one = this.getOne(new LambdaQueryWrapper<Members>().eq(Members::getMemberOpenId, openId));
        if (Objects.isNull(one)) {
            throw new RuntimeException("当前用户你不存在相关信息!");
        }
        MembersVO membersVO = new MembersVO();
        BeanUtils.copyProperties(one, membersVO);
        // 手机号码中间四位隐藏
        String memberPhone = one.getMemberPhone();
        String phoneNumber = maskPhoneNumber(memberPhone);
        membersVO.setMemberPhone(phoneNumber);
        return membersVO;
    }

    @Override
    public String getOpenId(String code) {
        // 通过code 获取OpenId
        String getOpenidUrl = "https://api.weixin.qq.com/sns/jscode2session?";
        String appId = "wxf640065f16437b78";
        String secret = "e6b7d2d65352b2c2b489efc7ddaec558";
        JsCodeResult jsCode = restTemplate.getForObject(
                getOpenidUrl + "appid=" + appId + "&secret=" + secret + "&js_code=" + code, JsCodeResult.class);

        String openid = jsCode.getOpenid();
        System.out.println(openid);
        System.out.println(jsCode);
        Members one = this.getOne(new LambdaQueryWrapper<Members>().eq(Members::getMemberOpenId, openid));
        if (Objects.isNull(one)) {
            Members members = new Members();
            members.setCreateTime(LocalDateTime.now());
            members.setMemberOpenId(openid);
            members.setMemberName("未命名");
            members.setMemberPhone("未绑定");
            this.save(members);
        }
        return openid;
    }

    @Override
    public Boolean updateMember(MembersDTO membersDTO) {
        LambdaUpdateWrapper<Members> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(!Objects.isNull(membersDTO.getMemberName()), Members::getMemberName, membersDTO.getMemberName());
        wrapper.set(!Objects.isNull(membersDTO.getMemberPhone()), Members::getMemberPhone, membersDTO.getMemberPhone());
        wrapper.set(!Objects.isNull(membersDTO.getImgId()), Members::getImgId, membersDTO.getImgId());
        return this.update(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveTitleImg(MultipartFile multipartFile) {
        try {
            QNObject qnObject = new QNObject();
            QNObjectResult qnObjectResult = qnObject.saveImage(multipartFile, String.valueOf(System.currentTimeMillis()));
            // 图片信息存入数据库
            long countId = imgService.count() + 1;
            Img img = new Img();
            img.setTitle(qnObjectResult.getKey());
            img.setUrl(qnObjectResult.getDownloadUrl());
            img.setId((int) countId);
            imgService.save(img);
            return (int) countId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密展示手机号
     *
     * @param phoneNumber
     * @return
     */
    public String maskPhoneNumber(String phoneNumber) {
        // 使用正则表达式替换中间四位数字为 * 号
        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}