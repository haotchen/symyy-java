package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.service.DoctorInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    DoctorInfoService doctorInfoService;

    @GetMapping("/info")
    public R getDoctorInfo(){
        return R.ok("获取医生信息成功",doctorInfoService.getDoctorInfo());
    }
}
