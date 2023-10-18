package com.haotchen.syyimei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotchen.syyimei.entity.Img;
import com.haotchen.syyimei.entity.StudioInfo;
import com.haotchen.syyimei.entity.vo.StudioInfoVO;

import java.util.List;

/**
 * @author haotchen
 * @date  2023-10-08
 * ===============  > 
 * 
 * 服务接口
 */
public interface StudioInfoService  extends IService<StudioInfo> {

    StudioInfoVO getStudioInfo();

    List<Img> getIndexImgs();
}
