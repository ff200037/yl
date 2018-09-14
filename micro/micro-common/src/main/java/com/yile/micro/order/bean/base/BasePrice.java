package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BasePrice extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "定价表";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//定价名称
	private java.lang.String priceName;
	//救援项目:1.拖车牵引,2.现场修理,3.派送燃油,4.电瓶搭电,5.更换轮胎,6.地库救援,7困境救援
	private java.lang.String rescueObject;
	//基础计费
	private Double basicsCharging;
	//是否涵盖附加费用: 1.是,2.否
	private java.lang.String additionalCharging;
	//规定服务里程
	private java.lang.Integer serviceMileage;
	//超出计费价格
	private Double exceedCharging;
	//超出计费公里数
	private java.lang.String exceedChargingMileage;
	//计费说明
	private java.lang.String chargingExplain;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setPriceName(java.lang.String priceName) {
		this.priceName = priceName;
	}
	
	public java.lang.String getPriceName() {
		return this.priceName;
	}
		
	public void setRescueObject(java.lang.String rescueObject) {
		this.rescueObject = rescueObject;
	}
	
	public java.lang.String getRescueObject() {
		return this.rescueObject;
	}
		
	public void setBasicsCharging(Double basicsCharging) {
		this.basicsCharging = basicsCharging;
	}
	
	public Double getBasicsCharging() {
		return this.basicsCharging;
	}
		
	public void setAdditionalCharging(java.lang.String additionalCharging) {
		this.additionalCharging = additionalCharging;
	}
	
	public java.lang.String getAdditionalCharging() {
		return this.additionalCharging;
	}
		
	public void setServiceMileage(java.lang.Integer serviceMileage) {
		this.serviceMileage = serviceMileage;
	}
	
	public java.lang.Integer getServiceMileage() {
		return this.serviceMileage;
	}
		
	public void setExceedCharging(Double exceedCharging) {
		this.exceedCharging = exceedCharging;
	}
	
	public Double getExceedCharging() {
		return this.exceedCharging;
	}
		
	public void setExceedChargingMileage(java.lang.String exceedChargingMileage) {
		this.exceedChargingMileage = exceedChargingMileage;
	}
	
	public java.lang.String getExceedChargingMileage() {
		return this.exceedChargingMileage;
	}
		
	public void setChargingExplain(java.lang.String chargingExplain) {
		this.chargingExplain = chargingExplain;
	}
	
	public java.lang.String getChargingExplain() {
		return this.chargingExplain;
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