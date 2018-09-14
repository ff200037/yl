package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BaseRescueOrder;

/**
 * 救援订单表 -- 实体
 * t_rescue_order:救援订单表
 */
@SuppressWarnings("serial")
public class RescueOrder extends BaseRescueOrder {
	
	// 关联订单
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("FkOrderId",getFkOrderId())
			.append("BelongedRegion",getBelongedRegion())
			.append("FkCarId",getFkCarId())
			.append("FkRescueMechanismId",getFkRescueMechanismId())
			.append("RescueExplain",getRescueExplain())
			.append("CarPosition",getCarPosition())
			.append("CarLon",getCarLon())
			.append("CarLat",getCarLat())
			.append("WaitTime",getWaitTime())
			.append("RescueMileage",getRescueMileage())
			.append("RepairCharging",getRepairCharging())
			.append("Creater",getCreater())
			.append("CreateTime",getCreateTime())
			.append("Updater",getUpdater())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getFkOrderId())
			.append(getBelongedRegion())
			.append(getFkCarId())
			.append(getFkRescueMechanismId())
			.append(getRescueExplain())
			.append(getCarPosition())
			.append(getCarLon())
			.append(getCarLat())
			.append(getWaitTime())
			.append(getRescueMileage())
			.append(getRepairCharging())
			.append(getCreater())
			.append(getCreateTime())
			.append(getUpdater())
			.append(getUpdateTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueOrder == false) return false;
		if(this == obj) return true;
		RescueOrder other = (RescueOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getFkOrderId(),other.getFkOrderId())
			.append(getBelongedRegion(),other.getBelongedRegion())
			.append(getFkCarId(),other.getFkCarId())
			.append(getFkRescueMechanismId(),other.getFkRescueMechanismId())
			.append(getRescueExplain(),other.getRescueExplain())
			.append(getCarPosition(),other.getCarPosition())
			.append(getCarLon(),other.getCarLon())
			.append(getCarLat(),other.getCarLat())
			.append(getWaitTime(),other.getWaitTime())
			.append(getRescueMileage(),other.getRescueMileage())
			.append(getRepairCharging(),other.getRepairCharging())
			.append(getCreater(),other.getCreater())
			.append(getCreateTime(),other.getCreateTime())
			.append(getUpdater(),other.getUpdater())
			.append(getUpdateTime(),other.getUpdateTime())
			.isEquals();
	}
}

