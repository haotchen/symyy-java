package com.haotchen.syyimei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotchen.syyimei.entity.Members;
import com.haotchen.syyimei.entity.dto.MembersDTO;
import com.haotchen.syyimei.entity.vo.MembersVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author haotchen
 * @date  2023-10-13
 * ===============  > 
 * 
 * 服务接口
 */
public interface MembersService  extends IService<Members> {

    Boolean saveMember(MembersDTO membersDTO);

    MembersVO getMemberInfo(String openId);

    String getOpenId(String code);

    Boolean updateMember(MembersDTO membersDTO);

    Integer saveTitleImg(MultipartFile multipartFile);
}
