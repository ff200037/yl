package com.yile.micro.user.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.user.bean.RescueRescues;
import com.yile.micro.user.mapper.RescueRescuesMapper;

@Transactional
@Service("rescueRescuesService")
public class RescueRescuesServiceImpl extends BaseService<RescueRescues> implements RescueRescuesService {

	@Autowired
	private RescueRescuesMapper rescueRescuesMapper;

	@PostConstruct
	public void setMapper() {
		super.setMapper(rescueRescuesMapper);
	}

}
