package com.yile.micro.coupon.bean.base;

import java.io.Serializable;

import com.yile.micro.common.BaseEntity;


@SuppressWarnings("serial")
public class BaseCoupon extends BaseEntity implements Serializable{
	
	//alias
	public static final String TABLE_ALIAS = "优惠券表";
	
	//date formats
	public static final String FORMAT_EFT_DATE = DATE_TIME_FORMAT;
	public static final String FORMAT_INV_DATE = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//主键ID
	private java.lang.Long id;
	//优惠券名称
	private java.lang.String cpnName;
	//优惠券编号
	private java.lang.String cpnNo;
	//优惠券类别：1.免费券 2.现金券 3.折扣券
	private java.lang.String cpnType;
	//面额
	private Double cpnAmt1;
	//折扣
	private Double cpnAmt2;
	//有效时间类型：1.不限 2.固定日期 3.领用/发放后
	private java.lang.String dateType;
	//生效日期
	private java.util.Date eftDate;
	//失效日期
	private java.util.Date invDate;
	//有效时长（天）
	private java.lang.Integer eftLen;
	//发行数量
	private java.lang.Long cpnNum;
	//每人限领
	private java.lang.Integer limGetNum;
	//已领数量
	private java.lang.Long alrdyGetNum;
	//优惠券描述
	private java.lang.String cpnMarks;
	//优惠券状态，1启用 0停用 2草稿
	private java.lang.String cpnStatus;
	//使用条件 0有  1无
	private java.lang.String useCondType;
	//满减金额
	private Double mjMoney;
	//活动对象范围分类
	private java.lang.String custRangeType;
	//活动产品范围分类
	private java.lang.String prodRangeType;
	//活动区域范围分类
	private java.lang.String areaRangeType;
	//活动区域
	private java.lang.String areaRange;
	//活动对象 1.普通用户  2.VIP用户
	private java.lang.String custRange;
	//业务大类 1.救援 2.分时 3.网约 4.充电 5.洗车
	private java.lang.String pbe;
	//二维码
	private java.lang.String qrCode;

	//所属平台
	private java.lang.String ascriptionPlatform;
	//columns END


	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
		
	public void setCpnName(java.lang.String cpnName) {
		this.cpnName = cpnName;
	}
	
	public java.lang.String getCpnName() {
		return this.cpnName;
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
		
	public void setCpnAmt1(Double cpnAmt1) {
		this.cpnAmt1 = cpnAmt1;
	}
	
	public Double getCpnAmt1() {
		return this.cpnAmt1;
	}
	
	public void setCpnAmt2(Double cpnAmt2) {
		this.cpnAmt2 = cpnAmt2;
	}
	
	public Double getCpnAmt2() {
		return this.cpnAmt2;
	}
	
	public void setDateType(java.lang.String dateType) {
		this.dateType = dateType;
	}
	
	public java.lang.String getDateType() {
		return this.dateType;
	}
		
	public void setEftDate(java.util.Date eftDate) {
		this.eftDate = eftDate;
	}
	
	public java.util.Date getEftDate() {
		return this.eftDate;
	}
		
	public void setInvDate(java.util.Date invDate) {
		this.invDate = invDate;
	}
	
	public java.util.Date getInvDate() {
		return this.invDate;
	}
		
	public void setEftLen(java.lang.Integer eftLen) {
		this.eftLen = eftLen;
	}
	
	public java.lang.Integer getEftLen() {
		return this.eftLen;
	}
		
	public void setCpnNum(java.lang.Long cpnNum) {
		this.cpnNum = cpnNum;
	}
	
	public java.lang.Long getCpnNum() {
		return this.cpnNum;
	}
		
	public void setLimGetNum(java.lang.Integer limGetNum) {
		this.limGetNum = limGetNum;
	}
	
	public java.lang.Integer getLimGetNum() {
		return this.limGetNum;
	}
		
	public void setAlrdyGetNum(java.lang.Long alrdyGetNum) {
		this.alrdyGetNum = alrdyGetNum;
	}
	
	public java.lang.Long getAlrdyGetNum() {
		return this.alrdyGetNum;
	}
		
	public void setCpnMarks(java.lang.String cpnMarks) {
		this.cpnMarks = cpnMarks;
	}
	
	public java.lang.String getCpnMarks() {
		return this.cpnMarks;
	}
		
	public void setCpnStatus(java.lang.String cpnStatus) {
		this.cpnStatus = cpnStatus;
	}
	
	public java.lang.String getCpnStatus() {
		return this.cpnStatus;
	}
		
	public void setUseCondType(java.lang.String useCondType) {
		this.useCondType = useCondType;
	}
	
	public java.lang.String getUseCondType() {
		return this.useCondType;
	}
		
	public void setMjMoney(Double mjMoney) {
		this.mjMoney = mjMoney;
	}
	
	public Double getMjMoney() {
		return this.mjMoney;
	}
		
	public void setCustRangeType(java.lang.String custRangeType) {
		this.custRangeType = custRangeType;
	}
	
	public java.lang.String getCustRangeType() {
		return this.custRangeType;
	}
		
	public void setProdRangeType(java.lang.String prodRangeType) {
		this.prodRangeType = prodRangeType;
	}
	
	public java.lang.String getProdRangeType() {
		return this.prodRangeType;
	}
		
	public void setAreaRangeType(java.lang.String areaRangeType) {
		this.areaRangeType = areaRangeType;
	}
	
	public java.lang.String getAreaRangeType() {
		return this.areaRangeType;
	}
		
	public void setAreaRange(java.lang.String areaRange) {
		this.areaRange = areaRange;
	}
	
	public java.lang.String getAreaRange() {
		return this.areaRange;
	}
		
	public void setCustRange(java.lang.String custRange) {
		this.custRange = custRange;
	}
	
	public java.lang.String getCustRange() {
		return this.custRange;
	}
		
	public void setPbe(java.lang.String pbe) {
		this.pbe = pbe;
	}
	
	public java.lang.String getPbe() {
		return this.pbe;
	}
		
	public void setQrCode(java.lang.String qrCode) {
		this.qrCode = qrCode;
	}
	
	public java.lang.String getQrCode() {
		return this.qrCode;
	}


	public java.lang.String getAscriptionPlatform() {
		return ascriptionPlatform;
	}

	public void setAscriptionPlatform(java.lang.String ascriptionPlatform) {
		this.ascriptionPlatform = ascriptionPlatform;
	}
		
	

}