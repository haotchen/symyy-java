package com.haotchen.syyimei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.dao.mapper.FeedBackMapper;
import com.haotchen.syyimei.entity.FeedBack;
import com.haotchen.syyimei.entity.dto.FeedBackDTO;
import com.haotchen.syyimei.service.FeedBackService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedBackServiceImpl extends ServiceImpl<FeedBackMapper, FeedBack> implements FeedBackService {

    @Override
    public Boolean saveFeedBack(FeedBackDTO feedBackDTO) {
        // 转换对象
        FeedBack feedBack = new FeedBack();
        BeanUtils.copyProperties(feedBackDTO,feedBack);
        feedBack.setCreateTime(LocalDateTime.now());
        return this.save(feedBack);
    }
}