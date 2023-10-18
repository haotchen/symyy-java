package com.haotchen.syyimei.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@ApiModel(value = "t_feed_back", description = "请求ble.comment}")
public class FeedBackDTO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 用户id */
	@ApiModelProperty(value = "用户id")
	private Integer memberId;
    
	/** 反馈内容 */
	@ApiModelProperty(value = "反馈内容")
	private String textValue;
    
	/** 反馈时间 */
	@ApiModelProperty(value = "反馈时间")
	private LocalDateTime createTime;
    
	
}