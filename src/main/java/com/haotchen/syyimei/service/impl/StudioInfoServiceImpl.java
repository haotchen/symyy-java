package com.haotchen.syyimei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.dao.mapper.StudioInfoMapper;
import com.haotchen.syyimei.entity.Img;
import com.haotchen.syyimei.entity.StudioInfo;
import com.haotchen.syyimei.entity.vo.StudioInfoVO;
import com.haotchen.syyimei.service.ImgService;
import com.haotchen.syyimei.service.StudioInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class StudioInfoServiceImpl extends ServiceImpl<StudioInfoMapper, StudioInfo> implements StudioInfoService {

    @Resource
    ImgService imgService;

    @Override
    public StudioInfoVO getStudioInfo() {
        LambdaQueryWrapper<StudioInfo> studio =
                new LambdaQueryWrapper<StudioInfo>().eq(StudioInfo::getId, 1);
        StudioInfo one = this.getOne(studio);
        StudioInfoVO studioInfoVO = new StudioInfoVO();
        BeanUtils.copyProperties(one, studioInfoVO);
        // 根据图片id查询链接
        String studioImg = one.getStudioImg();
        List<String> list = Arrays.asList(studioImg.split(","));
        List<Img> imgList = imgService.list(
                new LambdaQueryWrapper<Img>()
                        .in(Img::getId, list)
        );
        studioInfoVO.setStudioImg(imgList);
        return studioInfoVO;
    }

    @Override
    public List<Img> getIndexImgs() {
        return imgService.list(new LambdaQueryWrapper<Img>().eq(Img::getTitle,"首页推广轮播图"));
    }
}