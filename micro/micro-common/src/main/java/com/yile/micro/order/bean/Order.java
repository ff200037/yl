package com.yile.micro.order.bean;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.order.bean.base.BaseOrder;

/**
 * 订单主表 -- 实体
 * t_order:订单主表
 */
@SuppressWarnings("serial")
public class Order extends BaseOrder {
	
	// 1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	/**
	 * 已派单
	 */
	public static final String ORDER_STATUS_SEND = "1";
	
	/**
	 *  已接单
	 */
	public static final String ORDER_STATUS_RECEIVE = "2"; 
	
	/**
	 * 已取消
	 */
	public static final String ORDER_STATUS_CANCEL = "3";
	
	/**
	 * 执行中
	 */
	public static final String ORDER_STATUS_DOING = "4";
	
	/**
	 * 待支付
	 */
	public static final String ORDER_STATUS_NOT_PAY = "5";
	
	
	/**
	 * 已经完成
	 */
	public static final String ORDER_STATUS_FINISH = "6";
	
	
	private RescueOrder rescueOrder;
	
	public RescueOrder getRescueOrder() {
		return rescueOrder;
	}

	public void setRescueOrder(RescueOrder rescueOrder) {
		this.rescueOrder = rescueOrder;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("OrderNo",getOrderNo())
			.append("OrderStatus",getOrderStatus())
			.append("FkCustomerId",getFkCustomerId())
			.append("OrderType",getOrderType())
			.append("CreateOrderTime",getCreateOrderTime())
			.append("EndTime",getEndTime())
			.append("ServiceType",getServiceType())
			.append("Creater",getCreater())
			.append("CreateTime",getCreateTime())
			.append("Updater",getUpdater())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getOrderNo())
			.append(getOrderStatus())
			.append(getFkCustomerId())
			.append(getOrderType())
			.append(getCreateOrderTime())
			.append(getEndTime())
			.append(getServiceType())
			.append(getCreater())
			.append(getCreateTime())
			.append(getUpdater())
			.append(getUpdateTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Order == false) return false;
		if(this == obj) return true;
		Order other = (Order)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getOrderNo(),other.getOrderNo())
			.append(getOrderStatus(),other.getOrderStatus())
			.append(getFkCustomerId(),other.getFkCustomerId())
			.append(getOrderType(),other.getOrderType())
			.append(getCreateOrderTime(),other.getCreateOrderTime())
			.append(getEndTime(),other.getEndTime())
			.append(getServiceType(),other.getServiceType())
			.append(getCreater(),other.getCreater())
			.append(getCreateTime(),other.getCreateTime())
			.append(getUpdater(),other.getUpdater())
			.append(getUpdateTime(),other.getUpdateTime())
			.isEquals();
	}
}

