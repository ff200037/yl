package com.yile.micro.user.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.user.bean.base.BaseRescueRescues;

/**
 * 救援人员表 -- 实体 rescue_rescues:救援人员表
 */
@SuppressWarnings("serial")
public class RescueRescues extends BaseRescueRescues {

	public String toString() {
		return new ToStringBuilder(this).append("Id", getId()).append("DriverLicense", getDriverLicense())
				.append("DriverType", getDriverType()).append("FkRescueStoreId", getFkRescueStoreId())
				.append("FkTcustomerId", getFkTcustomerId()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getDriverLicense()).append(getDriverType())
				.append(getFkRescueStoreId()).append(getFkTcustomerId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof RescueRescues == false)
			return false;
		if (this == obj)
			return true;
		RescueRescues other = (RescueRescues) obj;
		return new EqualsBuilder().append(getId(), other.getId()).append(getDriverLicense(), other.getDriverLicense())
				.append(getDriverType(), other.getDriverType()).append(getFkRescueStoreId(), other.getFkRescueStoreId())
				.append(getFkTcustomerId(), other.getFkTcustomerId()).isEquals();
	}
}
