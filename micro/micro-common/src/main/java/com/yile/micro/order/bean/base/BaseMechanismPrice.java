package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseMechanismPrice extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "机构定价关系表";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//定价ID
	private java.lang.Long priceId;
	//机构ID
	private java.lang.Long mechanismId;
	//创建时间
	private java.util.Date createTime;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setPriceId(java.lang.Long priceId) {
		this.priceId = priceId;
	}
	
	public java.lang.Long getPriceId() {
		return this.priceId;
	}
		
	public void setMechanismId(java.lang.Long mechanismId) {
		this.mechanismId = mechanismId;
	}
	
	public java.lang.Long getMechanismId() {
		return this.mechanismId;
	}
		
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		


}