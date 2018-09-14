package com.yile.micro.user.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.user.bean.RescueCustomer;
import com.yile.micro.user.mapper.RescueCustomerMapper;

@Transactional
@Service("rescueCustomerService")
public class RescueCustomerServiceImpl extends BaseService<RescueCustomer> implements RescueCustomerService {

	@Autowired
	private RescueCustomerMapper rescueCustomerMapper;

	@PostConstruct
	public void setMapper() {
		super.setMapper(rescueCustomerMapper);
	}

}
