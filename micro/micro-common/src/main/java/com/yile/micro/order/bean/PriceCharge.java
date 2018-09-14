package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BasePriceCharge;

import java.util.List;

/**
 * 计费结果表 -- 实体
 * t_price_charge:计费结果表
 */
@SuppressWarnings("serial")
public class PriceCharge extends BasePriceCharge {
    List<PriceChargeDetail> priceChargeDetails;

    public String toString() {
        return new ToStringBuilder(this)
                .append("Id", getId())
                .append("FkCustId", getFkCustId())
                .append("FkOrderId", getFkOrderId())
                .append("BillingType", getBillingType())
                .append("SettlementAmount", getSettlementAmount())
                .append("PriceStatus", getPriceStatus())
                .append("CalcDate", getCalcDate())
                .append("ServiceType", getServiceType())
                .append("Creater", getCreater())
                .append("CreateTime", getCreateTime())
                .append("Updater", getUpdater())
                .append("UpdateTime", getUpdateTime())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getFkCustId())
                .append(getFkOrderId())
                .append(getBillingType())
                .append(getSettlementAmount())
                .append(getPriceStatus())
                .append(getCalcDate())
                .append(getServiceType())
                .append(getCreater())
                .append(getCreateTime())
                .append(getUpdater())
                .append(getUpdateTime())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PriceCharge == false) return false;
        if (this == obj) return true;
        PriceCharge other = (PriceCharge) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .append(getFkCustId(), other.getFkCustId())
                .append(getFkOrderId(), other.getFkOrderId())
                .append(getBillingType(), other.getBillingType())
                .append(getSettlementAmount(), other.getSettlementAmount())
                .append(getPriceStatus(), other.getPriceStatus())
                .append(getCalcDate(), other.getCalcDate())
                .append(getServiceType(), other.getServiceType())
                .append(getCreater(), other.getCreater())
                .append(getCreateTime(), other.getCreateTime())
                .append(getUpdater(), other.getUpdater())
                .append(getUpdateTime(), other.getUpdateTime())
                .isEquals();
    }

    public List<PriceChargeDetail> getPriceChargeDetails() {
        return priceChargeDetails;
    }

    public void setPriceChargeDetails(List<PriceChargeDetail> priceChargeDetails) {
        this.priceChargeDetails = priceChargeDetails;
    }
}

