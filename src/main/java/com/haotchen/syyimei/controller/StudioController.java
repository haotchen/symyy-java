package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.service.StudioInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Resource
    StudioInfoService studioInfoService;

    @GetMapping("/info")
    public R getStudioInfo(){
        return R.ok("获取工作室信息成功",studioInfoService.getStudioInfo());
    }

    @GetMapping("/index/imgs")
    public R getIndexImgs() {
        return R.ok("获取首页轮播图成功",studioInfoService.getIndexImgs());
    }
}
