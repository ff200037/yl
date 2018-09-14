package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseOrder extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "订单主表";
	
	//date formats
	public static final String FORMAT_CREATE_ORDER_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//订单编号
	private java.lang.String orderNo;
	//订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	private java.lang.String orderStatus;
	//车主ID
	private java.lang.Long fkCustomerId;
	//下单方式:1.电话下单2.公众号下单
	private java.lang.Integer orderType;
	//下单时间
	private java.util.Date createOrderTime;
	//订单结束时间
	private java.lang.Integer endTime;
	//订单大业务类型1.救援 2.分时 3.网约 4.商城
	private java.lang.String serviceType;
	//创建人姓名
	private java.lang.String creater;
	//创建时间
	private java.util.Date createTime;
	//修改人姓名
	private java.lang.String updater;
	//修改时间
	private java.util.Date updateTime;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setOrderNo(java.lang.String orderNo) {
		this.orderNo = orderNo;
	}
	
	public java.lang.String getOrderNo() {
		return this.orderNo;
	}
		
	public void setOrderStatus(java.lang.String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public java.lang.String getOrderStatus() {
		return this.orderStatus;
	}
		
	public void setFkCustomerId(java.lang.Long fkCustomerId) {
		this.fkCustomerId = fkCustomerId;
	}
	
	public java.lang.Long getFkCustomerId() {
		return this.fkCustomerId;
	}
		
	public void setOrderType(java.lang.Integer orderType) {
		this.orderType = orderType;
	}
	
	public java.lang.Integer getOrderType() {
		return this.orderType;
	}
		
	public void setCreateOrderTime(java.util.Date createOrderTime) {
		this.createOrderTime = createOrderTime;
	}
	
	public java.util.Date getCreateOrderTime() {
		return this.createOrderTime;
	}
		
	public void setEndTime(java.lang.Integer endTime) {
		this.endTime = endTime;
	}
	
	public java.lang.Integer getEndTime() {
		return this.endTime;
	}
		
	public void setServiceType(java.lang.String serviceType) {
		this.serviceType = serviceType;
	}
	
	public java.lang.String getServiceType() {
		return this.serviceType;
	}
		
	public void setCreater(java.lang.String creater) {
		this.creater = creater;
	}
	
	public java.lang.String getCreater() {
		return this.creater;
	}
		
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		
	public void setUpdater(java.lang.String updater) {
		this.updater = updater;
	}
	
	public java.lang.String getUpdater() {
		return this.updater;
	}
		
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
		


}