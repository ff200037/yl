package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseOrderRelRescues extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "订单救援人员关系表";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//订单ID
	private java.lang.Long orderId;
	//救援人员ID
	private java.lang.Long rescuesId;
	//救援人员状态 1:执行中 2.取消任务
	private java.lang.String rescuesStatus;
	//创建时间
	private java.util.Date createTime;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setOrderId(java.lang.Long orderId) {
		this.orderId = orderId;
	}
	
	public java.lang.Long getOrderId() {
		return this.orderId;
	}
		
	public void setRescuesId(java.lang.Long rescuesId) {
		this.rescuesId = rescuesId;
	}
	
	public java.lang.Long getRescuesId() {
		return this.rescuesId;
	}
		
	public void setRescuesStatus(java.lang.String rescuesStatus) {
		this.rescuesStatus = rescuesStatus;
	}
	
	public java.lang.String getRescuesStatus() {
		return this.rescuesStatus;
	}
		
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		


}