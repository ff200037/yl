package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseOrderProject extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "订单救援项目关系表";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//救援订单表ID
	private java.lang.Long fkOrderRescueId;
	//救援项目:1.拖车牵引,2.现场修理,3.派送燃油,4.电瓶搭电,5.更换轮胎,6.地库救援,7困境救援
	private java.lang.String rescueProject;
	//创建时间
	private java.util.Date createTime;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setFkOrderRescueId(java.lang.Long fkOrderRescueId) {
		this.fkOrderRescueId = fkOrderRescueId;
	}
	
	public java.lang.Long getFkOrderRescueId() {
		return this.fkOrderRescueId;
	}
		
	public void setRescueProject(java.lang.String rescueProject) {
		this.rescueProject = rescueProject;
	}
	
	public java.lang.String getRescueProject() {
		return this.rescueProject;
	}
		
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		


}