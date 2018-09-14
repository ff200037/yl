package com.yile.micro.order.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yile.micro.base.service.IBaseService;
import com.yile.micro.common.PageBean;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.order.bean.Order;

/**
 * 订单接口
 * @author YLWL
 *
 */
public interface OrderService extends IBaseService<Order> {
	
	/**
	 * 修改订单
	 * @return
	 */
	public Order getOrderById(Long orderId) throws Exception;
	
	/**
	 * 保存订单
	 * @param RescueOrder
	 * @return
	 */
	public MapRet saveOrder(Order order ) throws Exception;
	
	/**
	 * 更新订单
	 * @param rescueOrder
	 * @return
	 */
	public MapRet updateOrder(Order order ) throws Exception;
	
	/**
	 * 删除订单
	 * @param rescueOrder
	 * @return
	 */
	public MapRet deleteOrder(Order order ) throws Exception;

	
	 
	
	public PageInfo<Order> findPage(Map<String, Object> map, int pageNo, int pageSize);

	
	/**
	 * 自动派单
	 * 订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	 */
	public MapRet saveAutoSendOrder(Long orderId) throws Exception;
	
	/**
	 * 救援人员接单
	 * 订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	 * rescues_status : 救援人员状态 1:执行中 2.取消任务，增加 3,派遣单
	 */
	public MapRet updateDoingOrderByRescues(Long orderId, Long rescuesId) throws Exception;
	
	/**
	 * 取消订单
	 * @param orderId
	 * @param rescuesId
	 * @return
	 * @throws Exception
	 * 订单状态:1.已派单2.已接单3.已取消4.执行中5.待支付6.已完成
	 */
	public MapRet updateCancelOrderByRescues(Long orderId, Long rescuesId) throws Exception;
	
	/**
	 * 重新派发订单
	 */
	public MapRet updateNewSendOrderByRescues(Long orderId, Long rescuesId) throws Exception;
	
	/**
	 * 订单支付完成
	 */
	public MapRet updatFinishOrderByRescues(Long orderId, Long rescuesId) throws Exception;

}

