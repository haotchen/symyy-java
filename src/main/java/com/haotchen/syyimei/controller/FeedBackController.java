package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.entity.dto.FeedBackDTO;
import com.haotchen.syyimei.service.FeedBackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedBack")
public class FeedBackController {

    @Resource
    private FeedBackService feedBackService;


    @PostMapping("/add")
    public R addFeedBack(@RequestBody FeedBackDTO feedBackDTO) {
        return R.ok("提交反馈意见成功!",feedBackService.saveFeedBack(feedBackDTO));
    }
}