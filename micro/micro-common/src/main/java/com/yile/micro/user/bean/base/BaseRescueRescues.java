package com.yile.micro.user.bean.base;

@SuppressWarnings("serial")
public class BaseRescueRescues extends BaseCustomer {
	
	//alias
	public static final String TABLE_ALIAS = "救援人员附表";
	
	//date formats
	
	//columns START
	//主键ID
	// date formats

	// columns START
	// 主键ID
	private java.lang.Long id;
	// 救援人员驾驶证
	private java.lang.String driverLicense;
	// 可驾车型: 1.B1 2.B2 3.C1 4.C2 5.C3 6.C4 7.M
	private java.lang.String driverType;
	// 所属救援门店ID
	private java.lang.Long fkRescueStoreId;
	// 用户主表ID
	private java.lang.Long fkTcustomerId;
	// columns END

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setDriverLicense(java.lang.String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public java.lang.String getDriverLicense() {
		return this.driverLicense;
	}

	public void setDriverType(java.lang.String driverType) {
		this.driverType = driverType;
	}

	public java.lang.String getDriverType() {
		return this.driverType;
	}

	public void setFkRescueStoreId(java.lang.Long fkRescueStoreId) {
		this.fkRescueStoreId = fkRescueStoreId;
	}

	public java.lang.Long getFkRescueStoreId() {
		return this.fkRescueStoreId;
	}

	public void setFkTcustomerId(java.lang.Long fkTcustomerId) {
		this.fkTcustomerId = fkTcustomerId;
	}

	public java.lang.Long getFkTcustomerId() {
		return this.fkTcustomerId;
	}

}