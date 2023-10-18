package com.haotchen.syyimei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 关于我们
 */

@Data
@TableName("t_about")
@ApiModel(value = "t_about", description = "实体对象: 关于我们")
public class About {
	/** 主键 */
	@ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
	private Integer id;
	/** 关于内容 */
	@ApiModelProperty(value = "关于内容")
    @TableField(value = "about_text")
	private String aboutText;
	
}