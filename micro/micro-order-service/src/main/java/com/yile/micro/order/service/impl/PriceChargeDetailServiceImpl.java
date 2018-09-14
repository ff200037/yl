package com.yile.micro.order.service.impl;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.order.bean.PriceChargeDetail;
import com.yile.micro.order.mapper.PriceChargeDetailMapper;
import com.yile.micro.order.service.PriceChargeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@Service("priceChargeDetailService")
public class PriceChargeDetailServiceImpl extends BaseService<PriceChargeDetail> implements PriceChargeDetailService {

	@Autowired
	private PriceChargeDetailMapper priceChargeDetailMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(priceChargeDetailMapper);
	}
	
}
