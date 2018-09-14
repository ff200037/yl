package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseRescueOrder extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "救援订单表";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//订单主表ID
	private java.lang.Long fkOrderId;
	//所属区域
	private java.lang.String belongedRegion;
	//故障车辆ID
	private java.lang.Long fkCarId;
	//所属救援机构ID
	private java.lang.Long fkRescueMechanismId;
	//救援说明
	private java.lang.String rescueExplain;
	//故障车辆位置
	private java.lang.String carPosition;
	//故障车辆经度
	private java.lang.String carLon;
	//故障车辆纬度
	private java.lang.String carLat;
	//等待时间
	private java.lang.Integer waitTime;
	//救援总里程
	private Double rescueMileage;
	//修理附加费用
	private Double repairCharging;
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
		
	public void setFkOrderId(java.lang.Long fkOrderId) {
		this.fkOrderId = fkOrderId;
	}
	
	public java.lang.Long getFkOrderId() {
		return this.fkOrderId;
	}
		
	public void setBelongedRegion(java.lang.String belongedRegion) {
		this.belongedRegion = belongedRegion;
	}
	
	public java.lang.String getBelongedRegion() {
		return this.belongedRegion;
	}
		
	public void setFkCarId(java.lang.Long fkCarId) {
		this.fkCarId = fkCarId;
	}
	
	public java.lang.Long getFkCarId() {
		return this.fkCarId;
	}
		
	public void setFkRescueMechanismId(java.lang.Long fkRescueMechanismId) {
		this.fkRescueMechanismId = fkRescueMechanismId;
	}
	
	public java.lang.Long getFkRescueMechanismId() {
		return this.fkRescueMechanismId;
	}
		
	public void setRescueExplain(java.lang.String rescueExplain) {
		this.rescueExplain = rescueExplain;
	}
	
	public java.lang.String getRescueExplain() {
		return this.rescueExplain;
	}
		
	public void setCarPosition(java.lang.String carPosition) {
		this.carPosition = carPosition;
	}
	
	public java.lang.String getCarPosition() {
		return this.carPosition;
	}
		
	public void setCarLon(java.lang.String carLon) {
		this.carLon = carLon;
	}
	
	public java.lang.String getCarLon() {
		return this.carLon;
	}
		
	public void setCarLat(java.lang.String carLat) {
		this.carLat = carLat;
	}
	
	public java.lang.String getCarLat() {
		return this.carLat;
	}
		
	public void setWaitTime(java.lang.Integer waitTime) {
		this.waitTime = waitTime;
	}
	
	public java.lang.Integer getWaitTime() {
		return this.waitTime;
	}
		
	public void setRescueMileage(Double rescueMileage) {
		this.rescueMileage = rescueMileage;
	}
	
	public Double getRescueMileage() {
		return this.rescueMileage;
	}
		
	public void setRepairCharging(Double repairCharging) {
		this.repairCharging = repairCharging;
	}
	
	public Double getRepairCharging() {
		return this.repairCharging;
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