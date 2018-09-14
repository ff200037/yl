package com.yile.micro.order.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yile.micro.OrderApplication;
import com.yile.micro.common.utils.MapRet;
import com.yile.micro.order.bean.Order;
import com.yile.micro.order.bean.RescueOrder;
import com.yile.micro.order.service.RescueOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class RescueOrderServiceImplTest {

	@Autowired
	RescueOrderService rescueOrderService;

	@Test
	public void saveRescueOrder() throws Exception {
		RescueOrder rescueOrder = new RescueOrder();
		rescueOrder.setCarLon("12");
		rescueOrder.setCarLat("11");
		Order order = new Order();
		order.setCreateOrderTime(new Date());
		order.setOrderNo("1234");
		order.setOrderStatus("1");
		order.setFkCustomerId(1L);
		order.setOrderType(1);
		order.setCreateTime(new Date());
		order.setServiceType("1");
		rescueOrder.setOrder(order);
		rescueOrder.setBelongedRegion("123");
		rescueOrder.setCarPosition("1");
		rescueOrder.setFkCarId(1L);
		rescueOrder.setFkRescueMechanismId(1L);
		MapRet ret = this.rescueOrderService.saveRescueOrder(rescueOrder);
		System.out.println(ret);
	}

	@Test
	public void updateRescueOrder() throws Exception {
		RescueOrder rescueOrder = new RescueOrder();
		rescueOrder.setId(2L);
		rescueOrder.setCarLon("12");
		rescueOrder.setCarLat("11");
		Order order = new Order();
		order.setId(2L);
		order.setCreateOrderTime(new Date());
		order.setOrderNo("5555");
		order.setOrderStatus("1");
		order.setFkCustomerId(1L);
		order.setOrderType(1);
		order.setCreateTime(new Date());
		order.setServiceType("1");
		rescueOrder.setFkOrderId(order.getId());
		rescueOrder.setOrder(order);
		rescueOrder.setBelongedRegion("555"); //所属区域
		rescueOrder.setCarPosition("1"); // 车辆位置
		rescueOrder.setFkCarId(1L);// 车辆选择
		rescueOrder.setFkRescueMechanismId(1L);
		MapRet ret = this.rescueOrderService.updateRescueOrder(rescueOrder);
		System.out.println(ret);
	}

	@Test
	public void deleteRescueOrder() throws Exception {
		RescueOrder rescueOrder = rescueOrderService.queryById(1L);
		if(rescueOrder != null){
			MapRet ret = this.rescueOrderService.deleteRescueOrder(rescueOrder);
			System.out.println(ret);
		}
		
	}

	@Test
	public void getRescueOrderById() throws Exception {
		RescueOrder rescueOrder = rescueOrderService.getRescueOrderById(2L);
	}

}
