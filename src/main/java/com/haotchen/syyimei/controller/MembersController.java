package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.entity.dto.MembersDTO;
import com.haotchen.syyimei.service.MembersService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/members")
public class MembersController {

    @Resource
    private MembersService membersService;

    @PostMapping("/saveMember")
    public R saveMember(@RequestBody MembersDTO membersDTO){
        return R.ok("新增用户成功",membersService.saveMember(membersDTO));
    }
    @GetMapping("/getMember")
    public R getMember(String openId){
        return R.ok("查询用户成功",membersService.getMemberInfo(openId));
    }

    @GetMapping("/open")
    public R getOpenId(String code) {
        return R.ok("获取用户OpenId",membersService.getOpenId(code));
    }

    @PostMapping("/update")
    public R updateMember(@RequestBody MembersDTO membersDTO) {
        return R.ok("修改用户信息",membersService.updateMember(membersDTO));
    }

    @GetMapping("/uploadFile")
    public R uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return R.ok("上传头像成功",membersService.saveTitleImg(multipartFile));
    }
}