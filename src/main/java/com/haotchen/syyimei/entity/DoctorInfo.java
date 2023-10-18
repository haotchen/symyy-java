package com.haotchen.syyimei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@TableName("t_doctor_info")
@ApiModel(value = "t_doctor_info", description = "实体对象: ")
public class DoctorInfo {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 医生名称 */
	@ApiModelProperty(value = "医生名称")
    @TableField(value = "doctor_name")
	private String doctorName;
	/** 医生年龄 */
	@ApiModelProperty(value = "医生年龄")
    @TableField(value = "doctor_age")
	private String doctorAge;
	/** 医生电话 */
	@ApiModelProperty(value = "医生电话")
    @TableField(value = "doctor_phone")
	private String doctorPhone;
	/** 医生介绍描述 */
	@ApiModelProperty(value = "医生介绍描述")
    @TableField(value = "description")
	private String description;
	/** 医生照片 */
	@ApiModelProperty(value = "医生照片")
    @TableField(value = "doctor_img")
	private String doctorImg;
	
}