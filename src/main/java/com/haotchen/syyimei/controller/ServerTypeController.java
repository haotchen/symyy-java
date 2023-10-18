package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.service.ServerTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;

@RestController
@RequestMapping("/serverType")
public class ServerTypeController {


    @Resource
    ServerTypeService serverTypeService;

    @GetMapping("/list")
    public R getServerTypeList() {
        return R.ok("获取服务类型列表",serverTypeService.list(null));
    }

}
