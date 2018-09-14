package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BasePrice;

/**
 * 定价表 -- 实体
 * t_price:定价表
 */
@SuppressWarnings("serial")
public class Price extends BasePrice {
    private String rescueObjectName;

    public String toString() {
        return new ToStringBuilder(this)
                .append("Id", getId())
                .append("PriceName", getPriceName())
                .append("RescueObject", getRescueObject())
                .append("BasicsCharging", getBasicsCharging())
                .append("AdditionalCharging", getAdditionalCharging())
                .append("ServiceMileage", getServiceMileage())
                .append("ExceedCharging", getExceedCharging())
                .append("ExceedChargingMileage", getExceedChargingMileage())
                .append("ChargingExplain", getChargingExplain())
                .append("Creater", getCreater())
                .append("CreateTime", getCreateTime())
                .append("Updater", getUpdater())
                .append("UpdateTime", getUpdateTime())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getPriceName())
                .append(getRescueObject())
                .append(getBasicsCharging())
                .append(getAdditionalCharging())
                .append(getServiceMileage())
                .append(getExceedCharging())
                .append(getExceedChargingMileage())
                .append(getChargingExplain())
                .append(getCreater())
                .append(getCreateTime())
                .append(getUpdater())
                .append(getUpdateTime())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Price == false) return false;
        if (this == obj) return true;
        Price other = (Price) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .append(getPriceName(), other.getPriceName())
                .append(getRescueObject(), other.getRescueObject())
                .append(getBasicsCharging(), other.getBasicsCharging())
                .append(getAdditionalCharging(), other.getAdditionalCharging())
                .append(getServiceMileage(), other.getServiceMileage())
                .append(getExceedCharging(), other.getExceedCharging())
                .append(getExceedChargingMileage(), other.getExceedChargingMileage())
                .append(getChargingExplain(), other.getChargingExplain())
                .append(getCreater(), other.getCreater())
                .append(getCreateTime(), other.getCreateTime())
                .append(getUpdater(), other.getUpdater())
                .append(getUpdateTime(), other.getUpdateTime())
                .isEquals();
    }

    public String getRescueObjectName() {
        return rescueObjectName;
    }

    public void setRescueObjectName(String rescueObjectName) {
        this.rescueObjectName = rescueObjectName;
    }
}

