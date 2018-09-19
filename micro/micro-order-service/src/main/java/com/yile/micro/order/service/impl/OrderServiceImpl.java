package com.yile.micro.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yile.micro.base.service.BaseService;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.bean.OrderRelRescues;
import com.yile.micro.order.bean.RescueOrder;
import com.yile.micro.order.mapper.OrderMapper;
import com.yile.micro.order.mapper.OrderRelRescuesMapper;
import com.yile.micro.order.mapper.RescueOrderMapper;
import com.yile.micro.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;


import java.util.Date;


import javax.annotation.PostConstruct;


/**
 * 订单业务管理
 * @author YLWL
 *
 */
@Transactional
@Service("orderService")
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private RescueOrderMapper rescueOrderMapper;
	
	@Autowired
	private OrderRelRescuesMapper orderRelRescuesMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(orderMapper);
	}
	
	/**
	 * 修改订单
	 * @return
	 */
	@Override
	public Order getOrderById(Long orderId) throws Exception {
		Order order = this.orderMapper.queryById(orderId);
		if(order != null){
			RescueOrder rescueOrder = this.rescueOrderMapper.queryById(order.getId());
			order.setRescueOrder(rescueOrder);
		}
		return order;
	}
	
	/**
	 * 保存订单
	 * @param RescueOrder
	 * @return
	 */
	@Override
	public MapRet saveOrder(Order order ) throws Exception {
		MapRet ret = MapRet.create();
		this.orderMapper.insert(order);
		Long orderId = order.getId();
		RescueOrder rescueOrder = order.getRescueOrder();
		if(rescueOrder == null){
			rescueOrder = new RescueOrder();
		}
		rescueOrder.setId(orderId);
		rescueOrder.setFkOrderId(orderId);
		this.rescueOrderMapper.insert(rescueOrder);
		return ret.setOk();
	}
	
	/**
	 * 更新订单
	 * @param rescueOrder
	 * @return
	 */
	@Override
	public MapRet updateOrder(Order order ) throws Exception {
		MapRet ret = MapRet.create();
		this.orderMapper.update(order);
		this.rescueOrderMapper.update(order.getRescueOrder());
		return ret.setOk();
	}
	
	/**
	 * 删除订单
	 * @param rescueOrder
	 * @return
	 */
	@Override
	public MapRet deleteOrder(Order order) throws Exception {
		MapRet ret = MapRet.create();
		this.orderMapper.deleteById(order.getId());
		this.rescueOrderMapper.deleteById(order.getId());
		return ret.setOk();
	}
	
	/**
	 * 删除订单
	 * @param rescueOrder
	 * @return
	 */
	@Override
	public MapRet deleteOrderById(Long orderId) throws Exception {
		MapRet ret = MapRet.create();
		this.orderMapper.deleteById(orderId);
		this.rescueOrderMapper.deleteById(orderId);
		return ret.setOk();
	}

	 
	/**
	 * 列表
	 */
	@Override
	public PageInfo<Order> findPage(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Order> list = this.orderMapper.queryList(map);
		PageInfo<Order> orderPage = new PageInfo<Order>(list);
		return orderPage;
	}
	 
	/**
	 * 自动派单
	 * 订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	 */
	@Override
	public MapRet saveAutoSendOrder(Long orderId) throws Exception {
		MapRet ret = MapRet.create();
		Order order = this.orderMapper.queryById(orderId);
		order.setOrderStatus(Order.ORDER_STATUS_SEND);
		order.setUpdater("updater");
		order.setUpdateTime(new Date());
		this.orderMapper.update(order);
		// 接口获取最近救援人员
		Long rescuesId = 1L;
		OrderRelRescues orderRelRescues = new OrderRelRescues();
		orderRelRescues.setOrderId(orderId);
		orderRelRescues.setRescuesId(rescuesId);
		orderRelRescues.setRescuesStatus(OrderRelRescues.RESCUES_ORDER_STATUS_SEND);
		orderRelRescues.setCreateTime(new Date());
		this.orderRelRescuesMapper.insert(orderRelRescues);
		return ret.setOk();
	}
	
	/**
	 * 救援人员接单
	 * 订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	 * rescues_status : 救援人员状态 1:执行中 2.取消任务，增加 3,派遣单
	 */
	@Override
	public MapRet updateDoingOrderByRescues(Long orderId, Long rescuesId) throws Exception {
		MapRet ret = MapRet.create();
		Order order = this.orderMapper.queryById(orderId);
		// 修改订单状态
		order.setOrderStatus(Order.ORDER_STATUS_RECEIVE);
		order.setUpdater("updater");
		order.setUpdateTime(new Date());
		this.orderMapper.update(order);
		// 已派单
		OrderRelRescues orderRelRescues = new OrderRelRescues();
		orderRelRescues.setOrderId(orderId);
		orderRelRescues.setRescuesId(rescuesId);
		orderRelRescues.setRescuesStatus(OrderRelRescues.RESCUES_ORDER_STATUS_DOING);
		orderRelRescues.setCreater("creater");
		orderRelRescues.setCreateTime(new Date());
		this.orderRelRescuesMapper.insert(orderRelRescues);
		
		return ret.setOk();
	}
	
	/**
	 * 取消订单
	 * @param orderId
	 * @param rescuesId
	 * @return
	 * @throws Exception
	 * 订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	 */
	@Override
	public MapRet updateCancelOrderByRescues(Long orderId, Long rescuesId) throws Exception {
		MapRet ret = MapRet.create();
		Order order = this.orderMapper.queryById(orderId);
		// 修改订单状态, 取消订单
		order.setOrderStatus(Order.ORDER_STATUS_CANCEL);
		order.setUpdater("updater");
		order.setUpdateTime(new Date());
		this.orderMapper.update(order);
		// 已派单
		OrderRelRescues orderRelRescues = new OrderRelRescues();
		orderRelRescues.setOrderId(orderId);
		orderRelRescues.setRescuesId(rescuesId);
		orderRelRescues.setRescuesStatus(OrderRelRescues.RESCUES_ORDER_STATUS_CANCEL);
		orderRelRescues.setCreater("creater");
		orderRelRescues.setCreateTime(new Date());
		this.orderRelRescuesMapper.insert(orderRelRescues);
		
		return ret.setOk();
	}
	
	/**
	 * 重新派发订单
	 */
	@Override
	public MapRet updateNewSendOrderByRescues(Long orderId, Long rescuesId) throws Exception {
		MapRet ret = MapRet.create();
		// 从新派单
		OrderRelRescues orderRelRescues = new OrderRelRescues();
		orderRelRescues.setOrderId(orderId);
		// 当前重新派单 救援人员
		orderRelRescues.setRescuesId(rescuesId);
		orderRelRescues.setRescuesStatus(OrderRelRescues.RESCUES_ORDER_STATUS_SEND_NEW);
		orderRelRescues.setCreater("creater");
		orderRelRescues.setCreateTime(new Date());
		this.orderRelRescuesMapper.insert(orderRelRescues);
		// 重新自动派单
		saveAutoSendOrder(orderId);
		
		return ret.setOk();
	}
	
	/**
	 * 订单支付完成
	 */
	@Override
	public MapRet updatFinishOrderByRescues(Long orderId, Long rescuesId) throws Exception {
		MapRet ret = MapRet.create();
		Order order = this.orderMapper.queryById(orderId);
		order.setOrderStatus(Order.ORDER_STATUS_FINISH);
		this.orderMapper.update(order);
		// 并调计费接口进行计费
		return ret.setOk();
	}
}
