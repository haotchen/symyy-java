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
@TableName("t_feed_back")
@ApiModel(value = "t_feed_back", description = "实体对象: ")
public class FeedBack {
	/** 主键 */
	@ApiModelProperty(value = "主键")
	@TableId(type = IdType.AUTO)
	private Integer id;
	/** 用户id */
	@ApiModelProperty(value = "用户id")
    @TableField(value = "member_id")
	private Integer memberId;
	/** 反馈内容 */
	@ApiModelProperty(value = "反馈内容")
    @TableField(value = "text_value")
	private String textValue;
	/** 反馈时间 */
	@ApiModelProperty(value = "反馈时间")
    @TableField(value = "create_time")
	private LocalDateTime createTime;
	
}