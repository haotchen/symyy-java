package com.haotchen.syyimei.entity.vo;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 关于我们
 */

@Data
@ApiModel(value = "t_about",  description = "关于我们")
public class AboutVO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 关于内容 */
	@ApiModelProperty(value = "关于内容")
	private String aboutText;
    
	
}