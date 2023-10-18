package com.haotchen.syyimei.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@ApiModel(value = "t_members", description = "请求ble.comment}")
public class MembersDTO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 用户名称 */
	@ApiModelProperty(value = "用户名称")
	private String memberName;
    
	/** 用户手机号 */
	@ApiModelProperty(value = "用户手机号")
	private String memberPhone;
    
	/** openId */
	@ApiModelProperty(value = "openId")
	private String memberOpenId;
    
	/** 注册时间 */
	@ApiModelProperty(value = "注册时间")
	private LocalDateTime createTime;

	/** 头像 */
	@ApiModelProperty(value = "头像")
	private String imgId;

	/** 头像 */
	@ApiModelProperty(value = "头像链接")
	private String imgUrl;
    
	/** 是否已删除 0 未删除 1 已删除 */
	@ApiModelProperty(value = "是否已删除 0 未删除 1 已删除")
	private Integer deleted;
    
	
}