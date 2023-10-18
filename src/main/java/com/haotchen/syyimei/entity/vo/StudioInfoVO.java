package com.haotchen.syyimei.entity.vo;

import com.haotchen.syyimei.entity.Img;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@Data
@ApiModel(value = "t_studio_info",  description = "")
public class StudioInfoVO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 工作室介绍描述 */
	@ApiModelProperty(value = "工作室介绍描述")
	private String description;
    
	/** 工作室照片 */
	@ApiModelProperty(value = "工作室照片")
	private List<Img> studioImg;
    
	
}