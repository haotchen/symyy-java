package com.haotchen.syyimei.controller;

import com.haotchen.syyimei.common.R;
import com.haotchen.syyimei.entity.ReservationInfo;
import com.haotchen.syyimei.service.ReservationInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservationInfo")
public class ReservationInfoController {

    @Resource
    ReservationInfoService reservationInfoService;

    /**
     * 查看我的预约
     */
    @GetMapping("/my")
    public R getMyReservationInfo(String memberId) {
        return R.ok("获取我的预约列表", reservationInfoService.getMyReservationInfo(memberId));
    }

    /**
     * 提交预约
     */
    @PostMapping("/add")
    public R postMyReservationInfo(@RequestBody ReservationInfo reservationInfo) {
        return R.ok("提交预约", reservationInfoService.add(reservationInfo));
    }

    /**
     * 查询可以预约时间
     */

    @PostMapping("/active")
    public R queryReservationInfoActive(LocalDateTime target) {
        return R.ok("查询指定时间的可预约时间", reservationInfoService.queryReservationInfoActive(target));
    }

    /**
     * 取消预约
     */

    @PostMapping("/update")
    public R review(Integer reId) {
        return R.ok("取消预约", reservationInfoService.review(reId));
    }

    /**
     * 获取短信验证码
     */
    @GetMapping("/smsCode")
    public R smsCode(String openId, String phone) {
        return R.ok("获取短信验证码", reservationInfoService.messageCode(openId, phone));
    }
}
