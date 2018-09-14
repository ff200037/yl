package com.yile.micro.coupon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.coupon.bean.CouponHistory;
import com.yile.micro.coupon.service.CouponHistoryService;

@Transactional
@Service("couponHistoryService")
public class CouponHistoryServiceImpl extends BaseService<CouponHistory> implements CouponHistoryService {

	
	
}
