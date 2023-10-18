package com.haotchen.syyimei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("t_members")
@ApiModel(value = "t_members", description = "实体对象: ")
public class Members {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 用户名称 */
	@ApiModelProperty(value = "用户名称")
    @TableField(value = "member_name")
	private String memberName;
	/** 用户手机号 */
	@ApiModelProperty(value = "用户手机号")
    @TableField(value = "member_phone")
	private String memberPhone;
	/** 头像 */
	@ApiModelProperty(value = "头像")
    @TableField(value = "img_id")
	private String imgId;
	/** openId */
	@ApiModelProperty(value = "openId")
    @TableField(value = "member_open_id")
	private String memberOpenId;
	/** 注册时间 */
	@ApiModelProperty(value = "注册时间")
    @TableField(value = "create_time")
	private LocalDateTime createTime;
	/** 是否已删除 0 未删除 1 已删除 */
	@ApiModelProperty(value = "是否已删除 0 未删除 1 已删除")
    @TableField(value = "deleted")
	private Integer deleted;
	
}