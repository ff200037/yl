package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BasePriceChargeDetail;

/**
 * 计费结果明细表 -- 实体
 * t_price_charge_detail:计费结果明细表
 */
@SuppressWarnings("serial")
public class PriceChargeDetail extends BasePriceChargeDetail {
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("FkPrcId",getFkPrcId())
			.append("ItemType",getItemType())
			.append("PricingAmt",getPricingAmt())
			.append("Amount",getAmount())
			.append("Price",getPrice())
			.append("ItemDesc",getItemDesc())
			.append("DataOpenTime",getDataOpenTime())
			.append("DataOpenType",getDataOpenType())
			.append("ItemCode",getItemCode())
			.append("RescueProject",getRescueProject())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getFkPrcId())
			.append(getItemType())
			.append(getPricingAmt())
			.append(getAmount())
			.append(getPrice())
			.append(getItemDesc())
			.append(getDataOpenTime())
			.append(getDataOpenType())
			.append(getItemCode())
			.append(getRescueProject())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PriceChargeDetail == false) return false;
		if(this == obj) return true;
		PriceChargeDetail other = (PriceChargeDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getFkPrcId(),other.getFkPrcId())
			.append(getItemType(),other.getItemType())
			.append(getPricingAmt(),other.getPricingAmt())
			.append(getAmount(),other.getAmount())
			.append(getPrice(),other.getPrice())
			.append(getItemDesc(),other.getItemDesc())
			.append(getDataOpenTime(),other.getDataOpenTime())
			.append(getDataOpenType(),other.getDataOpenType())
			.append(getItemCode(),other.getItemCode())
			.append(getRescueProject(),other.getRescueProject())
			.isEquals();
	}
}

