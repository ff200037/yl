package com.yile.micro.coupon.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseCustomerCoupon extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "车主优惠券关系表";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	/**
  `fk_order_id` bigint(20) DEFAULT NULL COMMENT '订单主表ID',
  
	 */
	//columns START
	//主键ID
	private java.lang.Long id;
	//车主ID
	private java.lang.Long fkCustomerId;
	//优惠券ID
	private java.lang.Long fkCpId;
	//绑定时间
	private java.util.Date createTime;
	//是否使用1.未使用 2.使用过
	private java.lang.String useFlag;
	//订单主表ID
	private java.lang.Long fkOrderId;
	//推广批次ID
	private java.lang.Long fkCouponHistoryId;
	//救援业务大类 0.全部 1.拖车牵引,2.现场修理,3.派送燃油,4.电瓶搭电,5.更换轮胎,6.地库救援,7困境救援
	private java.lang.String pbe;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setFkCustomerId(java.lang.Long fkCustomerId) {
		this.fkCustomerId = fkCustomerId;
	}
	
	public java.lang.Long getFkCustomerId() {
		return this.fkCustomerId;
	}
		
	public void setFkCpId(java.lang.Long fkCpId) {
		this.fkCpId = fkCpId;
	}
	
	public java.lang.Long getFkCpId() {
		return this.fkCpId;
	}
		
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		
	public void setUseFlag(java.lang.String useFlag) {
		this.useFlag = useFlag;
	}
	
	public java.lang.String getUseFlag() {
		return this.useFlag;
	}

	public java.lang.Long getFkOrderId() {
		return fkOrderId;
	}

	public void setFkOrderId(java.lang.Long fkOrderId) {
		this.fkOrderId = fkOrderId;
	}

	public java.lang.Long getFkCouponHistoryId() {
		return fkCouponHistoryId;
	}

	public void setFkCouponHistoryId(java.lang.Long fkCouponHistoryId) {
		this.fkCouponHistoryId = fkCouponHistoryId;
	}

	public java.lang.String getPbe() {
		return pbe;
	}

	public void setPbe(java.lang.String pbe) {
		this.pbe = pbe;
	}
		
	

}