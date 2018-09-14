package com.yile.micro.coupon.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.coupon.bean.base.BaseCustomerCoupon;

/**
 * 车主优惠券关系表 -- 实体
 * t_customer_coupon:车主优惠券关系表
 */
@SuppressWarnings("serial")
public class CustomerCoupon extends BaseCustomerCoupon {
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("FkCustomerId",getFkCustomerId())
			.append("FkCpId",getFkCpId())
			.append("CreateTime",getCreateTime())
			.append("UseFlag",getUseFlag())
			.append("FkOrderId",getFkOrderId())
			.append("CouponHistoryId",getFkCouponHistoryId())
			.append("Pbe",getPbe())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getFkCustomerId())
			.append(getFkCpId())
			.append(getCreateTime())
			.append(getUseFlag())
			.append(getFkOrderId())
			.append(getFkCouponHistoryId())
			.append(getPbe())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CustomerCoupon == false) return false;
		if(this == obj) return true;
		CustomerCoupon other = (CustomerCoupon)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getFkCustomerId(),other.getFkCustomerId())
			.append(getFkCpId(),other.getFkCpId())
			.append(getCreateTime(),other.getCreateTime())
			.append(getUseFlag(),other.getUseFlag())
			.append(getFkOrderId(),other.getFkOrderId())
			.append(getFkCouponHistoryId(),other.getFkCouponHistoryId())
			.append(getPbe(),other.getPbe())
			.isEquals();
	}
}

