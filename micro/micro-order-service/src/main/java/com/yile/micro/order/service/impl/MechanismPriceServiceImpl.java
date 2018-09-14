package com.yile.micro.order.service.impl;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.MechanismPrice;
import com.yile.micro.order.mapper.MechanismPriceMapper;
import com.yile.micro.order.service.MechanismPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("mechanismPriceService")
public class MechanismPriceServiceImpl extends BaseService<MechanismPrice> implements MechanismPriceService {

	@Autowired
	private MechanismPriceMapper mechanismPriceMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(mechanismPriceMapper);
	}
	
}
