package com.yile.micro.user.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.user.bean.base.BaseCustomer;

/**
 * 用户主表 -- 实体 t_customer:用户主表
 */
@SuppressWarnings("serial")
public class Customer extends BaseCustomer {

	public String toString() {
		return new ToStringBuilder(this).append("CustomerId", getCustomerId()).append("UserName", getUserName())
				.append("Sex", getSex()).append("Age", getAge()).append("Birthday", getBirthday())
				.append("IdCard", getIdCard()).append("UserNumber", getUserNumber())
				.append("LoginAccount", getLoginAccount()).append("LoginPassword", getLoginPassword())
				.append("WorkType", getWorkType()).append("ContactAddress", getContactAddress())
				.append("Creater", getCreater()).append("CreateTime", getCreateTime()).append("Updater", getUpdater())
				.append("UpdateTime", getUpdateTime()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getCustomerId()).append(getUserName()).append(getSex()).append(getAge())
				.append(getBirthday()).append(getIdCard()).append(getUserNumber()).append(getLoginAccount())
				.append(getLoginPassword()).append(getWorkType()).append(getContactAddress()).append(getCreater())
				.append(getCreateTime()).append(getUpdater()).append(getUpdateTime()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Customer == false)
			return false;
		if (this == obj)
			return true;
		Customer other = (Customer) obj;
		return new EqualsBuilder().append(getCustomerId(), other.getCustomerId())
				.append(getUserName(), other.getUserName()).append(getSex(), other.getSex())
				.append(getAge(), other.getAge()).append(getBirthday(), other.getBirthday())
				.append(getIdCard(), other.getIdCard()).append(getUserNumber(), other.getUserNumber())
				.append(getLoginAccount(), other.getLoginAccount()).append(getLoginPassword(), other.getLoginPassword())
				.append(getWorkType(), other.getWorkType()).append(getContactAddress(), other.getContactAddress())
				.append(getCreater(), other.getCreater()).append(getCreateTime(), other.getCreateTime())
				.append(getUpdater(), other.getUpdater()).append(getUpdateTime(), other.getUpdateTime()).isEquals();
	}
}
