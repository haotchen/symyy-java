package com.haotchen.syyimei.entity.dto;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Data
@ApiModel(value = "t_doctor_info", description = "请求ble.comment}")
public class DoctorInfoDTO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 医生名称 */
	@ApiModelProperty(value = "医生名称")
	private String doctorName;
    
	/** 医生年龄 */
	@ApiModelProperty(value = "医生年龄")
	private String doctorAge;
    
	/** 医生电话 */
	@ApiModelProperty(value = "医生电话")
	private String doctorPhone;
    
	/** 医生介绍描述 */
	@ApiModelProperty(value = "医生介绍描述")
	private String description;
    
	/** 医生照片 */
	@ApiModelProperty(value = "医生照片")
	private String doctorImg;
    
	
}