package com.yile.micro.order.service.impl;

import com.yile.micro.OrderApplication;
import com.yile.micro.order.bean.OrderProject;
import com.yile.micro.order.service.OrderProjectService;
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
public class OrderProjectServiceImplTest {	
	@Autowired
	OrderProjectService orderProjectService;
	
    /**
     * 查询单个对象
     *
     * @throws Exception
     */
    @Test
    public void queryById() throws Exception {
    	OrderProject orderProject = orderProjectService.queryById(null);
    }

    /**
     * 增加对象
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
    	OrderProject orderProject = new OrderProject();    	
    	orderProject.setId(null);
    	orderProject.setFkOrderRescueId(null);
    	orderProject.setRescueProject(null);
    	orderProject.setCreateTime(null);
    	orderProjectService.insert(orderProject);
    }

    /**
     * 删除某个对象
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
    	orderProjectService.deleteById(null);
    }

    /**
     * 更新某个对象
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
    	OrderProject orderProject = orderProjectService.queryById(null);
    	orderProject.setId(null);
    	orderProject.setFkOrderRescueId(null);
    	orderProject.setRescueProject(null);
    	orderProject.setCreateTime(null);
    	orderProjectService.update(orderProject);
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void queryList() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<OrderProject> listOrderProject = orderProjectService.queryList(map);
    }

    /**
     * 查询总数
     *
     * @throws Exception
     */
    @Test
    public void queryCount() throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        Integer count = orderProjectService.queryCount(map);
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