package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BaseOrderProject;

/**
 * 订单救援项目关系表 -- 实体
 * t_order_project:订单救援项目关系表
 */
@SuppressWarnings("serial")
public class OrderProject extends BaseOrderProject {
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("FkOrderRescueId",getFkOrderRescueId())
			.append("RescueProject",getRescueProject())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getFkOrderRescueId())
			.append(getRescueProject())
			.append(getCreateTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OrderProject == false) return false;
		if(this == obj) return true;
		OrderProject other = (OrderProject)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getFkOrderRescueId(),other.getFkOrderRescueId())
			.append(getRescueProject(),other.getRescueProject())
			.append(getCreateTime(),other.getCreateTime())
			.isEquals();
	}
}

