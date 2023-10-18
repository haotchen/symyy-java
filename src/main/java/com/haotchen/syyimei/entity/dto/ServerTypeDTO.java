package com.haotchen.syyimei.entity.dto;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Data
@ApiModel(value = "t_server_type", description = "请求ble.comment}")
public class ServerTypeDTO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 服务类型名称 */
	@ApiModelProperty(value = "服务类型名称")
	private String typeName;
    
	
}