package com.yile.micro.order.service.impl;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.OrderRelRescues;
import com.yile.micro.order.mapper.OrderRelRescuesMapper;
import com.yile.micro.order.service.OrderRelRescuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("orderRelRescuesService")
public class OrderRelRescuesServiceImpl extends BaseService<OrderRelRescues> implements OrderRelRescuesService {

	@Autowired
	private OrderRelRescuesMapper orderRelRescuesMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(orderRelRescuesMapper);
	}
	
}
