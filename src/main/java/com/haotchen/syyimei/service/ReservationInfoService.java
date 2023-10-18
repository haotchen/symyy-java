package com.haotchen.syyimei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotchen.syyimei.entity.ReservationInfo;
import com.haotchen.syyimei.entity.vo.ActiveTimeVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author haotchen
 * @date  2023-10-08
 * ===============  > 
 * 
 * 服务接口
 */
public interface ReservationInfoService  extends IService<ReservationInfo> {

    List<ReservationInfo> getMyReservationInfo(String memberId);

    Boolean add(ReservationInfo reservationInfo);

    List<ActiveTimeVo> queryReservationInfoActive(LocalDateTime target);

    Boolean review(Integer reId);

    String messageCode(String openId,String phone);
}
