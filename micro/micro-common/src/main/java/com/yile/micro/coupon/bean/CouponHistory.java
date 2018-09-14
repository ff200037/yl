package com.yile.micro.coupon.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.coupon.bean.base.BaseCouponHistory;

/**
 * 优惠券发布历史表 -- 实体
 * t_coupon_history:优惠券发布历史表
 */
@SuppressWarnings("serial")
public class CouponHistory extends BaseCouponHistory {
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("CpnId",getCpnId())
			.append("CpnNo",getCpnNo())
			.append("CpnType",getCpnType())
			.append("SpreadNum",getSpreadNum())
			.append("SpreadMode",getSpreadMode())
			.append("SpreadChannel",getSpreadChannel())
			.append("SpreadTime",getSpreadTime())
			.append("SpreadPeople",getSpreadPeople())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getCpnId())
			.append(getCpnNo())
			.append(getCpnType())
			.append(getSpreadNum())
			.append(getSpreadMode())
			.append(getSpreadChannel())
			.append(getSpreadTime())
			.append(getSpreadPeople())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CouponHistory == false) return false;
		if(this == obj) return true;
		CouponHistory other = (CouponHistory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getCpnId(),other.getCpnId())
			.append(getCpnNo(),other.getCpnNo())
			.append(getCpnType(),other.getCpnType())
			.append(getSpreadNum(),other.getSpreadNum())
			.append(getSpreadMode(),other.getSpreadMode())
			.append(getSpreadChannel(),other.getSpreadChannel())
			.append(getSpreadTime(),other.getSpreadTime())
			.append(getSpreadPeople(), other.getSpreadPeople())
			.isEquals();
	}
}

