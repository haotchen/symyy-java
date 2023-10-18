package com.haotchen.syyimei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotchen.syyimei.entity.About;

/**
 * @author haotchen
 * @date  2023-10-13
 * ===============  > 
 * 关于我们
 * 服务接口
 */
public interface AboutService  extends IService<About> {

    String getInfo();
}
