package com.haotchen.syyimei.entity.dto;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 关于我们
 */

@Data
@ApiModel(value = "t_about", description = "请求ble.comment}")
public class AboutDTO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 关于内容 */
	@ApiModelProperty(value = "关于内容")
	private String aboutText;
    
	
}