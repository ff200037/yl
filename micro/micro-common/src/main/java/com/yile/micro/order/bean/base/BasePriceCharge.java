package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BasePriceCharge extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "计费结果表";
	
	//date formats
	public static final String FORMAT_CALC_DATE = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//用户表ID
	private java.lang.Long fkCustId;
	//订单主表ID
	private java.lang.Long fkOrderId;
	//计费类型：01预收 02结算
	private java.lang.String billingType;
	//结算费用
	private Double settlementAmount;
	//费用状态(1.正常 2.撤销)
	private java.lang.String priceStatus;
	//计费日期
	private java.util.Date calcDate;
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
		
	public void setFkCustId(java.lang.Long fkCustId) {
		this.fkCustId = fkCustId;
	}
	
	public java.lang.Long getFkCustId() {
		return this.fkCustId;
	}
		
	public void setFkOrderId(java.lang.Long fkOrderId) {
		this.fkOrderId = fkOrderId;
	}
	
	public java.lang.Long getFkOrderId() {
		return this.fkOrderId;
	}
		
	public void setBillingType(java.lang.String billingType) {
		this.billingType = billingType;
	}
	
	public java.lang.String getBillingType() {
		return this.billingType;
	}
		
	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	
	public Double getSettlementAmount() {
		return this.settlementAmount;
	}
		
	public void setPriceStatus(java.lang.String priceStatus) {
		this.priceStatus = priceStatus;
	}
	
	public java.lang.String getPriceStatus() {
		return this.priceStatus;
	}
		
	public void setCalcDate(java.util.Date calcDate) {
		this.calcDate = calcDate;
	}
	
	public java.util.Date getCalcDate() {
		return this.calcDate;
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