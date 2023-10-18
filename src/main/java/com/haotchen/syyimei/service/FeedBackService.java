package com.haotchen.syyimei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotchen.syyimei.entity.FeedBack;
import com.haotchen.syyimei.entity.dto.FeedBackDTO;

/**
 * @author haotchen
 * @date  2023-10-13
 * ===============  > 
 * 
 * 服务接口
 */
public interface FeedBackService  extends IService<FeedBack> {

    Boolean saveFeedBack(FeedBackDTO feedBackDTO);
}
