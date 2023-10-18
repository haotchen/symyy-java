package com.haotchen.syyimei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@TableName("t_img")
@ApiModel(value = "t_img", description = "实体对象: ")
public class Img {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 图片名称 */
	@ApiModelProperty(value = "图片名称")
    @TableField(value = "title")
	private String title;
	/** 图片地址 */
	@ApiModelProperty(value = "图片地址")
    @TableField(value = "url")
	private String url;
	
}