package com.haotchen.syyimei.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.dao.mapper.ReservationInfoMapper;
import com.haotchen.syyimei.entity.ReservationInfo;
import com.haotchen.syyimei.entity.vo.ActiveTimeVo;
import com.haotchen.syyimei.service.ReservationInfoService;
import com.haotchen.syyimei.service.ServerTypeService;
import com.haotchen.syyimei.utils.ALiYunSms;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationInfoServiceImpl extends ServiceImpl<ReservationInfoMapper, ReservationInfo> implements ReservationInfoService {


    @Resource
    ServerTypeService serverTypeService;

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Override
    public List<ReservationInfo> getMyReservationInfo(String memberId) {
        return this.list(
                new LambdaQueryWrapper<ReservationInfo>()
                        .eq(ReservationInfo::getMiniId, memberId)
                        .orderByDesc(ReservationInfo::getCreateTime)
        );
    }

    @Override
    public Boolean add(ReservationInfo reservationInfo) {
        if (Objects.isNull(reservationInfo.getReservationStartTime())) {
            throw new RuntimeException("预约时间异常,请检查!");
        }
        if (Objects.isNull(reservationInfo.getReservationEndTime())) {
            throw new RuntimeException("预约时间异常,请检查!");
        }
        if (Objects.isNull(reservationInfo.getUserPhone()) || !reservationInfo.getUserPhone().matches("^1[3-9]\\d{9}$")) {
            throw new RuntimeException("预约手机号码异常,请检查!");
        }
//        if (Objects.isNull(reservationInfo.getMiniId())) {
//            throw new RuntimeException("用户信息异常");
//        }
        if (Objects.isNull(reservationInfo.getUserName())) {
            throw new RuntimeException("用户信息异常");
        }
        if (Objects.isNull(reservationInfo.getTypeName())) {
            throw new RuntimeException("预约类型异常,请检查!");
        }
        ValueOperations<String, String> redisValue = redisTemplate.opsForValue();
        String redisKey = reservationInfo.getMiniId() + "_" + reservationInfo.getUserPhone();
        String smsCode = redisValue.get(redisKey);
        System.out.println(smsCode);
        if (Objects.isNull(reservationInfo.getSmsCode()) || !smsCode.equals(reservationInfo.getSmsCode())) {
            throw new RuntimeException("验证码不正确请重试!");
        }

        // 预约状态
        reservationInfo.setReservationStatus("0");
        reservationInfo.setCreateTime(LocalDateTime.now());
        List<ActiveTimeVo> activeTimeVos = queryReservationInfoActive(reservationInfo.getReservationStartTime());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (ActiveTimeVo activeTimeVo : activeTimeVos) {
            LocalDateTime start = LocalDateTime.parse(activeTimeVo.getStartTime(), dateTimeFormatter);
            LocalDateTime end = LocalDateTime.parse(activeTimeVo.getStartTime(), dateTimeFormatter);
            if (
                    (start.isAfter(reservationInfo.getReservationStartTime()) && end.isBefore(reservationInfo.getReservationStartTime())) ||
                            (start.isAfter(reservationInfo.getReservationEndTime()) && end.isBefore(reservationInfo.getReservationEndTime()))
            ) {
                throw new RuntimeException("改时间已存在预约!");
            }
        }

        return this.save(reservationInfo);
    }

    @Override
    public List<ActiveTimeVo> queryReservationInfoActive(LocalDateTime target) {
        // 获取最小时间
        LocalDateTime startOfDay = target.withHour(0).withMinute(0).withSecond(0).withNano(0);
        // 获取最大时间
        LocalDateTime endOfDay = target.withHour(23).withMinute(59).withSecond(59).withNano(999_999_999);
        List<ReservationInfo> activeList = this.list(
                new LambdaQueryWrapper<ReservationInfo>()
                        .ge(ReservationInfo::getCreateTime, startOfDay)
                        .lt(ReservationInfo::getCreateTime, endOfDay)
        );

        ArrayList<ActiveTimeVo> res = getActiveTimeVos(activeList);
        return res;
    }

    /**
     * 取消预约
     *
     * @param reId
     * @return
     */
    @Override
    public Boolean review(Integer reId) {
        return this.update(
                new LambdaUpdateWrapper<ReservationInfo>().eq(ReservationInfo::getId, reId)
                        .set(ReservationInfo::getReservationStatus, "4")
        );
    }

    @Override
    public String messageCode(String openId, String phone) {
        if (Objects.isNull(phone)) {
            throw new RuntimeException("该手机号码不支持发送验证码!");
        }
        // 校验是否频繁调用
        ValueOperations<String, String> redisValue = redisTemplate.opsForValue();
        String redisKey = openId + "_" + phone;
        String smsCode = redisValue.get(redisKey);
        if (!Objects.isNull(smsCode)) {
            throw new RuntimeException("该手机号码已获取到验证码,请5分钟后重试!");
        }

        // 生成4位短信验证码
        smsCode = generateSmsCode();
        // 调用阿里云发送短信
        StringBuilder sb = new StringBuilder("{\"code\":\"");
        sb.append(smsCode).append("\"}");
        try {
            ALiYunSms.sendSms(phone, "SMS_463607690", sb.toString());
        } catch (ClientException e) {
            throw new RuntimeException("验证码发送异常,请稍后重试!");
        }
        // 存储4位短信验证码,redis存储时长5分钟
        redisValue.set(redisKey, smsCode, 300, TimeUnit.SECONDS);

        return "验证码发送成功!";
    }

    /**
     * 生成4位随机短信验证码
     *
     * @return
     */
    public static String generateSmsCode() {
        // 随机生成4位数字验证码
        Random random = new Random();
        int code = random.nextInt(10000);

        // 格式化为4位字符串，不足4位时前面补0
        String smsCode = String.format("%04d", code);

        return smsCode;
    }

    private static ArrayList<ActiveTimeVo> getActiveTimeVos(List<ReservationInfo> activeList) {
        ArrayList<ActiveTimeVo> res = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (ReservationInfo reservationInfo : activeList) {
            if (!reservationInfo.getReservationStatus().equals("3")) {
                ActiveTimeVo activeTimeVo = new ActiveTimeVo();
                LocalDateTime reservationStartTime = reservationInfo.getReservationStartTime();
                activeTimeVo.setStartTime(reservationStartTime.format(dateTimeFormatter));
                LocalDateTime reservationEndTime = reservationInfo.getReservationEndTime();
                activeTimeVo.setEndTime(reservationEndTime.format(dateTimeFormatter));
                res.add(activeTimeVo);
            }
        }
        return res;
    }
}