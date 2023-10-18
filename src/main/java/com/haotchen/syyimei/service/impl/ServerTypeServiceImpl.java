package com.haotchen.syyimei.service.impl;

import com.haotchen.syyimei.dao.mapper.ServerTypeMapper;
import com.haotchen.syyimei.entity.ServerType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotchen.syyimei.service.ServerTypeService;
import org.springframework.stereotype.Service;

@Service
public class ServerTypeServiceImpl extends ServiceImpl<ServerTypeMapper, ServerType> implements ServerTypeService {

}