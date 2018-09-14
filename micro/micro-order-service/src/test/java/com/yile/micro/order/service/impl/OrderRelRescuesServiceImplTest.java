package com.yile.micro.order.service.impl;

import com.yile.micro.OrderApplication;
import com.yile.micro.order.bean.OrderRelRescues;
import com.yile.micro.order.service.OrderRelRescuesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderRelRescuesServiceImplTest {	
	@Autowired
	OrderRelRescuesService orderRelRescuesService;
	
    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
    	OrderRelRescues orderRelRescues = orderRelRescuesService.queryById(null);
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
    	OrderRelRescues orderRelRescues = new OrderRelRescues();    	
    	orderRelRescues.setId(null);
    	orderRelRescues.setOrderId(null);
    	orderRelRescues.setRescuesId(null);
    	orderRelRescues.setRescuesStatus(null);
    	orderRelRescues.setCreateTime(null);
    	orderRelRescuesService.insert(orderRelRescues);
    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
    	orderRelRescuesService.deleteById(null);
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
    	OrderRelRescues orderRelRescues = orderRelRescuesService.queryById(null);
    	orderRelRescues.setId(null);
    	orderRelRescues.setOrderId(null);
    	orderRelRescues.setRescuesId(null);
    	orderRelRescues.setRescuesStatus(null);
    	orderRelRescues.setCreateTime(null);
    	orderRelRescuesService.update(orderRelRescues);
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<OrderRelRescues> listOrderRelRescues = orderRelRescuesService.queryList(map);
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        Integer count = orderRelRescuesService.queryCount(map);
    }

    /**
     * 分页查询
     *
     * @throws Exception
     */
    @Test
    public void queryPage() throws Exception {

    }
}