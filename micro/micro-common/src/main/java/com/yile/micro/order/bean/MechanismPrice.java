package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BaseMechanismPrice;

/**
 * 机构定价关系表 -- 实体
 * t_mechanism_price:机构定价关系表
 */
@SuppressWarnings("serial")
public class MechanismPrice extends BaseMechanismPrice {
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("PriceId",getPriceId())
			.append("MechanismId",getMechanismId())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getPriceId())
			.append(getMechanismId())
			.append(getCreateTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MechanismPrice == false) return false;
		if(this == obj) return true;
		MechanismPrice other = (MechanismPrice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getPriceId(),other.getPriceId())
			.append(getMechanismId(),other.getMechanismId())
			.append(getCreateTime(),other.getCreateTime())
			.isEquals();
	}
}

