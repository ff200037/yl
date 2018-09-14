package com.yile.micro.user.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.user.bean.base.BaseRescueCustomer;

/**
 * 车主表 -- 实体 rescue_customer:车主表
 */
@SuppressWarnings("serial")
public class RescueCustomer extends BaseRescueCustomer {

	public String toString() {
		return new ToStringBuilder(this).append("Id", getId()).append("CustomerNo", getCustomerNo())
				.append("PcustomerNo", getPcustomerNo()).append("AccNickname", getAccNickname())
				.append("CustomertAddr", getCustomertAddr()).append("CustomerSortCode", getCustomerSortCode())
				.append("CustomerType", getCustomerType()).append("CustomerPassword", getCustomerPassword())
				.append("IdCardVld", getIdCardVld()).append("DriceNo", getDriceNo())
				.append("LicenseDate", getLicenseDate()).append("ValidLicenseDate", getValidLicenseDate())
				.append("DriveMode", getDriveMode()).append("Contact", getContact())
				.append("ContactNumber", getContactNumber()).append("Openid", getOpenid())
				.append("CustomerSource", getCustomerSource()).append("FkTcustomerId", getFkTcustomerId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getCustomerNo()).append(getPcustomerNo())
				.append(getAccNickname()).append(getCustomertAddr()).append(getCustomerSortCode())
				.append(getCustomerType()).append(getCustomerPassword()).append(getIdCardVld()).append(getDriceNo())
				.append(getLicenseDate()).append(getValidLicenseDate()).append(getDriveMode()).append(getContact())
				.append(getContactNumber()).append(getOpenid()).append(getCustomerSource()).append(getFkTcustomerId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RescueCustomer == false)
			return false;
		if (this == obj)
			return true;
		RescueCustomer other = (RescueCustomer) obj;
		return new EqualsBuilder().append(getId(), other.getId()).append(getCustomerNo(), other.getCustomerNo())
				.append(getPcustomerNo(), other.getPcustomerNo()).append(getAccNickname(), other.getAccNickname())
				.append(getCustomertAddr(), other.getCustomertAddr())
				.append(getCustomerSortCode(), other.getCustomerSortCode())
				.append(getCustomerType(), other.getCustomerType())
				.append(getCustomerPassword(), other.getCustomerPassword()).append(getIdCardVld(), other.getIdCardVld())
				.append(getDriceNo(), other.getDriceNo()).append(getLicenseDate(), other.getLicenseDate())
				.append(getValidLicenseDate(), other.getValidLicenseDate()).append(getDriveMode(), other.getDriveMode())
				.append(getContact(), other.getContact()).append(getContactNumber(), other.getContactNumber())
				.append(getOpenid(), other.getOpenid()).append(getCustomerSource(), other.getCustomerSource())
				.append(getFkTcustomerId(), other.getFkTcustomerId()).isEquals();
	}
}
