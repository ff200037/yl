package com.yile.micro.order.bean.base;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BasePriceChargeDetail extends BaseEntity {
	
	//alias
	public static final String TABLE_ALIAS = "计费结果明细表";
	
	//date formats
	public static final String FORMAT_DATA_OPEN_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//计费结果表ID
	private java.lang.Long fkPrcId;
	//费用项目类型1.基本收费,2超距费用,3.修理附加费,4.优惠券
	private java.lang.String itemType;
	//费用金额
	private Double pricingAmt;
	//计费项量值
	private java.lang.String amount;
	//计费单价
	private Double price;
	//费用项目说明
	private java.lang.String itemDesc;
	//数据操作时间
	private java.util.Date dataOpenTime;
	//数据操作类型(I:新增 U:修改 D:删除)
	private java.lang.String dataOpenType;
	//费用项编码
	private java.lang.String itemCode;
	//救援项目:1.拖车牵引,2.现场修理,3.派送燃油,4.电瓶搭电,5.更换轮胎,6.地库救援,7困境救援
	private java.lang.String rescueProject;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setFkPrcId(java.lang.Long fkPrcId) {
		this.fkPrcId = fkPrcId;
	}
	
	public java.lang.Long getFkPrcId() {
		return this.fkPrcId;
	}
		
	public void setItemType(java.lang.String itemType) {
		this.itemType = itemType;
	}
	
	public java.lang.String getItemType() {
		return this.itemType;
	}
		
	public void setPricingAmt(Double pricingAmt) {
		this.pricingAmt = pricingAmt;
	}
	
	public Double getPricingAmt() {
		return this.pricingAmt;
	}
		
	public void setAmount(java.lang.String amount) {
		this.amount = amount;
	}
	
	public java.lang.String getAmount() {
		return this.amount;
	}
		
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return this.price;
	}
		
	public void setItemDesc(java.lang.String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	public java.lang.String getItemDesc() {
		return this.itemDesc;
	}
		
	public void setDataOpenTime(java.util.Date dataOpenTime) {
		this.dataOpenTime = dataOpenTime;
	}
	
	public java.util.Date getDataOpenTime() {
		return this.dataOpenTime;
	}
		
	public void setDataOpenType(java.lang.String dataOpenType) {
		this.dataOpenType = dataOpenType;
	}
	
	public java.lang.String getDataOpenType() {
		return this.dataOpenType;
	}
		
	public void setItemCode(java.lang.String itemCode) {
		this.itemCode = itemCode;
	}
	
	public java.lang.String getItemCode() {
		return this.itemCode;
	}
		
	public void setRescueProject(java.lang.String rescueProject) {
		this.rescueProject = rescueProject;
	}
	
	public java.lang.String getRescueProject() {
		return this.rescueProject;
	}
		


}