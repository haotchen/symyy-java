package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.service.AboutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/about")
public class AboutController {

    @Resource
    private AboutService aboutService;

    @GetMapping("/info")
    public R getAboutInfo() {
        return R.ok("获取关于工作室描述成功",aboutService.getInfo());
    }

}