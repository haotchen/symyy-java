package com.haotchen.syyimei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.haotchen.syyimei.dao.mapper.DoctorInfoMapper;
import com.haotchen.syyimei.entity.DoctorInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.entity.Img;
import com.haotchen.syyimei.entity.vo.DoctorInfoVO;
import com.haotchen.syyimei.service.DoctorInfoService;
import com.haotchen.syyimei.service.ImgService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorInfoService {

    @Resource
    ImgService imgService;

    @Override
    public DoctorInfoVO getDoctorInfo() {
        LambdaQueryWrapper<DoctorInfo> doctor = new LambdaQueryWrapper<DoctorInfo>().eq(DoctorInfo::getId, 1);
        DoctorInfo one = this.getOne(doctor);
        DoctorInfoVO doctorInfoVO = new DoctorInfoVO();
        BeanUtils.copyProperties(one,doctorInfoVO);
        // 根据图片id查询链接
        String studioImg = one.getDoctorImg();
        List<String> list = Arrays.asList(studioImg.split(","));
        List<Img> imgList = imgService.list(
                new LambdaQueryWrapper<Img>()
                        .in(Img::getId, list)
        );
        doctorInfoVO.setDoctorImg(imgList);
        return doctorInfoVO;
    }
}