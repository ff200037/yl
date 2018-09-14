package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BaseOrderRelRescues;

/**
 * 订单救援人员关系表 -- 实体
 * t_order_rel_rescues:订单救援人员关系表
 */
@SuppressWarnings("serial")
public class OrderRelRescues extends BaseOrderRelRescues {
	
	// 1:执行中 2.取消任务 3,派遣单 4, 重新派单
	
	/**
	 * 执行中
	 */
	public static final String RESCUES_ORDER_STATUS_DOING = "1";
	
	/**
	 *  取消
	 */
	public static final String RESCUES_ORDER_STATUS_CANCEL = "2"; 
	
	/**
	 * 派遣中
	 */
	public static final String RESCUES_ORDER_STATUS_SEND = "3";
	
	/**
	 * 重新派单
	 */
	public static final String RESCUES_ORDER_STATUS_SEND_NEW = "4";
	
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("OrderId",getOrderId())
			.append("RescuesId",getRescuesId())
			.append("RescuesStatus",getRescuesStatus())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getOrderId())
			.append(getRescuesId())
			.append(getRescuesStatus())
			.append(getCreateTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OrderRelRescues == false) return false;
		if(this == obj) return true;
		OrderRelRescues other = (OrderRelRescues)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getOrderId(),other.getOrderId())
			.append(getRescuesId(),other.getRescuesId())
			.append(getRescuesStatus(),other.getRescuesStatus())
			.append(getCreateTime(),other.getCreateTime())
			.isEquals();
	}
}

