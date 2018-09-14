package com.yile.micro.order.service.impl;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.PriceCharge;
import com.yile.micro.order.mapper.PriceChargeMapper;
import com.yile.micro.order.service.PriceChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("priceChargeService")
public class PriceChargeServiceImpl extends BaseService<PriceCharge> implements PriceChargeService {

	@Autowired
	private PriceChargeMapper priceChargeMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(priceChargeMapper);
	}
	
}
