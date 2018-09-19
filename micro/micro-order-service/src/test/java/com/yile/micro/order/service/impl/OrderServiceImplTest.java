package com.yile.micro.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yile.micro.OrderApplication;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.bean.RescueOrder;
import com.yile.micro.order.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderServiceImplTest {

	@Autowired
	OrderService orderService;
	
	//@Test
	public void pageTest() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// orderService.findList(map);
	}

	//@Test
	public void saveRescueOrder() throws Exception {
		
		RescueOrder rescueOrder = new RescueOrder();
		rescueOrder.setCarLon("12");
		rescueOrder.setCarLat("11");
		rescueOrder.setBelongedRegion("new");
		rescueOrder.setCarPosition("1");
		rescueOrder.setFkCarId(1L);
		rescueOrder.setFkRescueMechanismId(1L);
		
		Order order = new Order();
		order.setCreateOrderTime(new Date());
		order.setOrderNo("new");
		order.setOrderStatus("1");
		order.setFkCustomerId(1L);
		order.setOrderType(1);
		order.setCreateTime(new Date());
		order.setServiceType("1");
		
		order.setRescueOrder(rescueOrder);
		MapRet ret = this.orderService.saveOrder(order);
		System.out.println(ret);
	}

	//@Test
	public void updateRescueOrder() throws Exception {
		Order order = new Order();
		order.setId(7L);
		order.setCreateOrderTime(new Date());
		order.setOrderNo("5555");
		order.setOrderStatus("1");
		order.setFkCustomerId(1L);
		order.setOrderType(1);
		order.setCreateTime(new Date());
		order.setServiceType("1");
		
		RescueOrder rescueOrder = new RescueOrder();
		rescueOrder.setId(7L);
		rescueOrder.setCarLon("12");
		rescueOrder.setCarLat("11");
		rescueOrder.setFkOrderId(order.getId());
		rescueOrder.setBelongedRegion("555"); //所属区域
		rescueOrder.setCarPosition("1"); // 车辆位置
		rescueOrder.setFkCarId(1L);// 车辆选择
		rescueOrder.setFkRescueMechanismId(1L);
		
		order.setRescueOrder(rescueOrder);
		MapRet ret = this.orderService.updateOrder(order);
		System.out.println(ret);
	}

	//@Test
	public void deleteOrder() throws Exception {
		Order order = orderService.queryById(3L);
		if(order != null){
			MapRet ret = this.orderService.deleteOrder(order);
			System.out.println(ret);
		}
		
	}

	@Test
	public void getOrderById() throws Exception {
		Order order = orderService.getOrderById(7L);
	}

}
