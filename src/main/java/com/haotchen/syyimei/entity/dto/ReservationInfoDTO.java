package com.haotchen.syyimei.entity.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Data
@ApiModel(value = "t_reservation_info", description = "请求ble.comment}")
public class ReservationInfoDTO {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Integer id;
    
	/** 预约服务类型 */
	@ApiModelProperty(value = "预约服务类型")
	private Integer typeId;
    
	/** openId */
	@ApiModelProperty(value = "openId")
	private String miniId;
    
	/** 用户名称 */
	@ApiModelProperty(value = "用户名称")
	private String userName;
    
	/** 用户电话 */
	@ApiModelProperty(value = "用户电话")
	private String userPhone;
    
	/** 预约开始时间 */
	@ApiModelProperty(value = "预约开始时间")
	private Date reservationStartTime;
    
	/** 预约结束时间 */
	@ApiModelProperty(value = "预约结束时间")
	private Date reservationEndTime;
    
	/** 预约状态 0 待审批, 1 审批通过, 2 预约失败 */
	@ApiModelProperty(value = "预约状态 0 待审批, 1 审批通过, 2 预约失败")
	private String reservationStatus;
    
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String notes;

	/** 备注 */
	@ApiModelProperty(value = "预约提交时间")
	private LocalDateTime createTime;
}