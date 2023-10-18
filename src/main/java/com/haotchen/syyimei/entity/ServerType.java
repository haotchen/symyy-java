package com.haotchen.syyimei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@TableName("t_server_type")
@ApiModel(value = "t_server_type", description = "实体对象: ")
public class ServerType {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 服务类型名称 */
	@ApiModelProperty(value = "服务类型名称")
    @TableField(value = "type_name")
	private String typeName;
	
}