package com.haotchen.syyimei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotchen.syyimei.entity.DoctorInfo;
import com.haotchen.syyimei.entity.vo.DoctorInfoVO;

/**
 * @author haotchen
 * @date  2023-10-08
 * ===============  > 
 * 
 * 服务接口
 */
public interface DoctorInfoService  extends IService<DoctorInfo> {

    /**
     * 获取医生信息
     */
    DoctorInfoVO getDoctorInfo();
}
