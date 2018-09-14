package com.yile.micro.order.service.impl;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.OrderProject;
import com.yile.micro.order.mapper.OrderProjectMapper;
import com.yile.micro.order.service.OrderProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("orderProjectService")
public class OrderProjectServiceImpl extends BaseService<OrderProject> implements OrderProjectService {

	@Autowired
	private OrderProjectMapper orderProjectMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(orderProjectMapper);
	}
	
}
