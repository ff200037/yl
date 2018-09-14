package com.yile.micro.order.service.impl;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.bean.RescueOrder;
import com.yile.micro.order.mapper.OrderMapper;
import com.yile.micro.order.mapper.RescueOrderMapper;
import com.yile.micro.order.service.RescueOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * 保险订单业务管理
 * @author YLWL
 *
 */
@Transactional
@Service("rescueOrderService")
public class RescueOrderServiceImpl extends BaseService<RescueOrder> implements RescueOrderService {

	@Autowired
	private RescueOrderMapper rescueOrderMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(rescueOrderMapper);
	}
	
	/**
	 * 修改订单
	 * @return
	 */
	public RescueOrder getRescueOrderById(Long rescueOrderId) throws Exception {
		RescueOrder rescueOrder = this.rescueOrderMapper.queryById(rescueOrderId);
		if(rescueOrder != null){
			Order order = this.orderMapper.queryById(rescueOrder.getFkOrderId());
			rescueOrder.setOrder(order);
		}
		return rescueOrder;
	}
	
	/**
	 * 保存保险订单
	 * @param RescueOrder
	 * @return
	 */
	public MapRet saveRescueOrder(RescueOrder rescueOrder ) throws Exception {
		MapRet ret = MapRet.create();
		this.orderMapper.insert(rescueOrder.getOrder());
		Long orderId = rescueOrder.getOrder().getId();
		rescueOrder.setId(orderId);
		rescueOrder.setFkOrderId(orderId);
		this.rescueOrderMapper.insert(rescueOrder);
		return ret.setOk();
	}
	
	/**
	 * 更新保险订单
	 * @param rescueOrder
	 * @return
	 */
	public MapRet updateRescueOrder(RescueOrder rescueOrder ) throws Exception {
		MapRet ret = MapRet.create();
		this.rescueOrderMapper.update(rescueOrder);
		this.orderMapper.update(rescueOrder.getOrder());
		return ret.setOk();
	}
	
	/**
	 * 删除保险订单
	 * @param rescueOrder
	 * @return
	 */
	public MapRet deleteRescueOrder(RescueOrder rescueOrder ) throws Exception {
		MapRet ret = MapRet.create();
		this.rescueOrderMapper.deleteById(rescueOrder.getId());
		this.orderMapper.deleteById(rescueOrder.getFkOrderId());
		return ret.setOk();
	}
	
}
