package com.yile.micro.user.bean.base;

import com.yile.micro.common.BaseEntity;

@SuppressWarnings("serial")
public class BaseCustomer extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "用户主表";

	// date formats
	public static final String FORMAT_BIRTHDAY = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;

	// columns START
	// 主键ID
	private java.lang.Long customerId;
	// 用户姓名
	private java.lang.String userName;
	// 性别:1.男2.女
	private java.lang.String sex;
	// 年龄
	private java.lang.Integer age;
	// 生日
	private java.util.Date birthday;
	// 身份证
	private java.lang.String idCard;
	// 手机号
	private java.lang.String userNumber;
	// 登陆账号
	private java.lang.String loginAccount;
	// 登陆密码
	private java.lang.String loginPassword;
	// 职业类型：1、各类专业、技术人员 2、国家机关、党群组织、企事业单位的负责人 3、办事人员和有关人员 4、商业工作人员 5、服务性工作人员
	// 6、农林牧渔劳动者 7、生产工作、运输工作和部分体力劳动者 8、不便分类的其他劳动者
	private java.lang.String workType;
	// 联系地址
	private java.lang.String contactAddress;

	// columns END

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(java.lang.Long customerId) {
		this.customerId = customerId;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public void setAge(java.lang.Integer age) {
		this.age = age;
	}

	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public java.util.Date getBirthday() {
		return this.birthday;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getIdCard() {
		return this.idCard;
	}

	public void setUserNumber(java.lang.String userNumber) {
		this.userNumber = userNumber;
	}

	public java.lang.String getUserNumber() {
		return this.userNumber;
	}

	public void setLoginAccount(java.lang.String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public java.lang.String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginPassword(java.lang.String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public java.lang.String getLoginPassword() {
		return this.loginPassword;
	}

	public void setWorkType(java.lang.String workType) {
		this.workType = workType;
	}

	public java.lang.String getWorkType() {
		return this.workType;
	}

	public void setContactAddress(java.lang.String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public java.lang.String getContactAddress() {
		return this.contactAddress;
	}

	@Override
	public String toString() {
		return "BaseCustomer [customerId=" + customerId + ", userName=" + userName + ", sex=" + sex + ", age=" + age
				+ ", birthday=" + birthday + ", idCard=" + idCard + ", userNumber=" + userNumber + ", loginAccount="
				+ loginAccount + ", loginPassword=" + loginPassword + ", workType=" + workType + ", contactAddress="
				+ contactAddress + "]";
	}

}