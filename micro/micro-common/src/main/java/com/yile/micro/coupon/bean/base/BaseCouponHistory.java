package com.yile.micro.coupon.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseCouponHistory extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "优惠券发布历史表";
	
	//date formats
	public static final String FORMAT_SPREAD_TIME = DATE_TIME_FORMAT;
	//columns START
	//主键ID
	private java.lang.Long id;
	//优惠券ID
	private java.lang.Long cpnId;
	//优惠券编号
	private java.lang.String cpnNo;
	//优惠券类别：1.免费券 2.现金券 3.折扣券
	private java.lang.String cpnType;
	//推广数量
	private java.lang.Long spreadNum;
	//推广方式： 1.发放  2.客户领取
	private java.lang.String spreadMode;
	//推广渠道： 1.APP  2.微信公众号  3.手机短信
	private java.lang.String spreadChannel;
	//推广时间
	private java.util.Date spreadTime;
	//推广人姓名
	private String spreadPeople;//推广人姓名
	//推广状态： 1.推广  2.未推广
	private java.lang.String spreadStatus;
	
	//columns END


	public java.lang.String getSpreadStatus() {
		return spreadStatus;
	}

	public void setSpreadStatus(java.lang.String spreadStatus) {
		this.spreadStatus = spreadStatus;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setCpnId(java.lang.Long cpnId) {
		this.cpnId = cpnId;
	}
	
	public java.lang.Long getCpnId() {
		return this.cpnId;
	}
		
	public void setCpnNo(java.lang.String cpnNo) {
		this.cpnNo = cpnNo;
	}
	
	public java.lang.String getCpnNo() {
		return this.cpnNo;
	}
		
	public void setCpnType(java.lang.String cpnType) {
		this.cpnType = cpnType;
	}
	
	public java.lang.String getCpnType() {
		return this.cpnType;
	}
		
	public void setSpreadNum(java.lang.Long spreadNum) {
		this.spreadNum = spreadNum;
	}
	
	public java.lang.Long getSpreadNum() {
		return this.spreadNum;
	}
		
	public void setSpreadMode(java.lang.String spreadMode) {
		this.spreadMode = spreadMode;
	}
	
	public java.lang.String getSpreadMode() {
		return this.spreadMode;
	}
		
	public void setSpreadChannel(java.lang.String spreadChannel) {
		this.spreadChannel = spreadChannel;
	}
	
	public java.lang.String getSpreadChannel() {
		return this.spreadChannel;
	}
		
	public void setSpreadTime(java.util.Date spreadTime) {
		this.spreadTime = spreadTime;
	}
	
	public java.util.Date getSpreadTime() {
		return this.spreadTime;
	}

	public String getSpreadPeople() {
		return spreadPeople;
	}

	public void setSpreadPeople(String spreadPeople) {
		this.spreadPeople = spreadPeople;
	}
		
	

}