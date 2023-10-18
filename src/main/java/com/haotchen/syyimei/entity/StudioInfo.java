package com.haotchen.syyimei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@TableName("t_studio_info")
@ApiModel(value = "t_studio_info", description = "实体对象: ")
public class StudioInfo {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 工作室介绍描述 */
	@ApiModelProperty(value = "工作室介绍描述")
    @TableField(value = "description")
	private String description;
	/** 工作室照片 */
	@ApiModelProperty(value = "工作室照片")
    @TableField(value = "studio_img")
	private String studioImg;
	
}