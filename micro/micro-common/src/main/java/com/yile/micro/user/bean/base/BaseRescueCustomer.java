package com.yile.micro.user.bean.base;

@SuppressWarnings("serial")
public class BaseRescueCustomer extends BaseCustomer {

	// alias
	public static final String TABLE_ALIAS = "车主附表";

	// date formats
	public static final String FORMAT_ID_CARD_VLD = DATE_TIME_FORMAT;
	public static final String FORMAT_LICENSE_DATE = DATE_TIME_FORMAT;
	public static final String FORMAT_VALID_LICENSE_DATE = DATE_TIME_FORMAT;

	// columns START
	// 主键ID
	private java.lang.Long id;
	// 车主编号
	private java.lang.String customerNo;
	// 父账号ID
	private java.lang.String pcustomerNo;
	// 昵称
	private java.lang.String accNickname;
	// 车主联系地址
	private java.lang.String customertAddr;
	// 客户分类: 1.个人用户 2.集团用户
	private java.lang.String customerSortCode;
	// 客户类型: 1.普通客户 2.VIP客户
	private java.lang.String customerType;
	// 平台账户支付密码
	private java.lang.String customerPassword;
	// 身份证有效期
	private java.util.Date idCardVld;
	// 驾驶证号
	private java.lang.String driceNo;
	// 驾驶证领证时间
	private java.util.Date licenseDate;
	// 驾照有效期
	private java.util.Date validLicenseDate;
	// 驾照等级
	// ：1.A1、2.A2、3.A3、4.B1、5.B2、6.C1、7.C2、8.C3、9.C4、10.C5、11.D、12.E、13.F、14.M、15.N、16.P
	private java.lang.String driveMode;
	// 备用联系人
	private java.lang.String contact;
	// 备用联系人电话
	private java.lang.String contactNumber;
	// 微信OPENID
	private java.lang.String openid;
	// 车主来源：1.平台 2.微信公众号
	private java.lang.String customerSource;
	// 用户主表ID
	private java.lang.Long fkTcustomerId;
	// columns END

	public void setCustomerNo(java.lang.String customerNo) {
		this.customerNo = customerNo;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getCustomerNo() {
		return this.customerNo;
	}

	public void setPcustomerNo(java.lang.String pcustomerNo) {
		this.pcustomerNo = pcustomerNo;
	}

	public java.lang.String getPcustomerNo() {
		return this.pcustomerNo;
	}

	public void setAccNickname(java.lang.String accNickname) {
		this.accNickname = accNickname;
	}

	public java.lang.String getAccNickname() {
		return this.accNickname;
	}

	public void setCustomertAddr(java.lang.String customertAddr) {
		this.customertAddr = customertAddr;
	}

	public java.lang.String getCustomertAddr() {
		return this.customertAddr;
	}

	public void setCustomerSortCode(java.lang.String customerSortCode) {
		this.customerSortCode = customerSortCode;
	}

	public java.lang.String getCustomerSortCode() {
		return this.customerSortCode;
	}

	public void setCustomerType(java.lang.String customerType) {
		this.customerType = customerType;
	}

	public java.lang.String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerPassword(java.lang.String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public java.lang.String getCustomerPassword() {
		return this.customerPassword;
	}

	public void setIdCardVld(java.util.Date idCardVld) {
		this.idCardVld = idCardVld;
	}

	public java.util.Date getIdCardVld() {
		return this.idCardVld;
	}

	public void setDriceNo(java.lang.String driceNo) {
		this.driceNo = driceNo;
	}

	public java.lang.String getDriceNo() {
		return this.driceNo;
	}

	public void setLicenseDate(java.util.Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public java.util.Date getLicenseDate() {
		return this.licenseDate;
	}

	public void setValidLicenseDate(java.util.Date validLicenseDate) {
		this.validLicenseDate = validLicenseDate;
	}

	public java.util.Date getValidLicenseDate() {
		return this.validLicenseDate;
	}

	public void setDriveMode(java.lang.String driveMode) {
		this.driveMode = driveMode;
	}

	public java.lang.String getDriveMode() {
		return this.driveMode;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public java.lang.String getContact() {
		return this.contact;
	}

	public void setContactNumber(java.lang.String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public java.lang.String getContactNumber() {
		return this.contactNumber;
	}

	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}

	public java.lang.String getOpenid() {
		return this.openid;
	}

	public void setCustomerSource(java.lang.String customerSource) {
		this.customerSource = customerSource;
	}

	public java.lang.String getCustomerSource() {
		return this.customerSource;
	}

	public void setFkTcustomerId(java.lang.Long fkTcustomerId) {
		this.fkTcustomerId = fkTcustomerId;
	}

	public java.lang.Long getFkTcustomerId() {
		return this.fkTcustomerId;
	}

	@Override
	public String toString() {
		return "BaseRescueCustomer [id=" + id + ", customerNo=" + customerNo + ", pcustomerNo=" + pcustomerNo
				+ ", accNickname=" + accNickname + ", customertAddr=" + customertAddr + ", customerSortCode="
				+ customerSortCode + ", customerType=" + customerType + ", customerPassword=" + customerPassword
				+ ", idCardVld=" + idCardVld + ", driceNo=" + driceNo + ", licenseDate=" + licenseDate
				+ ", validLicenseDate=" + validLicenseDate + ", driveMode=" + driveMode + ", contact=" + contact
				+ ", contactNumber=" + contactNumber + ", openid=" + openid + ", customerSource=" + customerSource
				+ ", fkTcustomerId=" + fkTcustomerId + "]";
	}

}