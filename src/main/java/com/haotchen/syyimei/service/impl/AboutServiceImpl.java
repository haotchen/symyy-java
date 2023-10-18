package com.haotchen.syyimei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.dao.mapper.AboutMapper;
import com.haotchen.syyimei.entity.About;
import com.haotchen.syyimei.service.AboutService;
import org.springframework.stereotype.Service;

@Service
public class AboutServiceImpl extends ServiceImpl<AboutMapper, About> implements AboutService {

    @Override
    public String getInfo() {
        return this.getOne(new LambdaQueryWrapper<About>().eq(About::getId, 1)).getAboutText();
    }
}