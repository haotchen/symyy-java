package com.haotchen.syyimei.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.haotchen.syyimei.common.LocalDateTimeDeserializer;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("t_reservation_info")
@ApiModel(value = "t_reservation_info", description = "实体对象: ")
public class ReservationInfo {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 预约服务类型 */
	@ApiModelProperty(value = "预约服务类型")
    @TableField(value = "type_name")
	private String typeName;
	/** openId */
	@ApiModelProperty(value = "openId")
    @TableField(value = "mini_id")
	private String miniId;
	/** 用户名称 */
	@ApiModelProperty(value = "用户名称")
    @TableField(value = "user_name")
	private String userName;
	/** 用户电话 */
	@ApiModelProperty(value = "用户电话")
    @TableField(value = "user_phone")
	private String userPhone;
	/** 预约开始时间 */
	@ApiModelProperty(value = "预约开始时间")
    @TableField(value = "reservation_start_time")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime reservationStartTime;
	/** 预约结束时间 */
	@ApiModelProperty(value = "预约结束时间")
    @TableField(value = "reservation_end_time")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime reservationEndTime;
	/** 预约状态 0 待审批, 1 审批通过, 2 预约失败 */
	@ApiModelProperty(value = "预约状态 0 待审批, 1 审批通过, 2 预约失败")
    @TableField(value = "reservation_status")
	private String reservationStatus;
	/** 备注 */
	@ApiModelProperty(value = "备注")
    @TableField(value = "notes")
	private String notes;
	/** 备注 */
	@ApiModelProperty(value = "验证码")
    @TableField(value = "notes",exist = false)
	private String smsCode;
	/** 备注 */
	@ApiModelProperty(value = "预约提交时间")
	@TableField(value = "create_time")
	private LocalDateTime createTime;
	
}