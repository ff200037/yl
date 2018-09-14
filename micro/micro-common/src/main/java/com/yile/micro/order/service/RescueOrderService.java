package com.yile.micro.order.service;

import com.yile.micro.base.service.IBaseService;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.order.bean.RescueOrder;

public interface RescueOrderService extends IBaseService<RescueOrder> {
	
	/**
	 * 修改订单
	 * @return
	 */
	public RescueOrder getRescueOrderById(Long rescueOrderId) throws Exception;
	
	/**
	 * 保存保险订单
	 * @param RescueOrder
	 * @return
	 */
	public MapRet saveRescueOrder(RescueOrder rescueOrder ) throws Exception;
	
	/**
	 * 更新保险订单
	 * @param rescueOrder
	 * @return
	 */
	public MapRet updateRescueOrder(RescueOrder rescueOrder ) throws Exception;
	
	/**
	 * 删除保险订单
	 * @param rescueOrder
	 * @return
	 */
	public MapRet deleteRescueOrder(RescueOrder rescueOrder ) throws Exception;
	
}

