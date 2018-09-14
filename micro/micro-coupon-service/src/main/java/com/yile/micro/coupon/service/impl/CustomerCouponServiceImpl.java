package com.yile.micro.coupon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yile.micro.base.service.BaseService;
import com.yile.micro.coupon.bean.CustomerCoupon;
import com.yile.micro.coupon.service.CustomerCouponService;

@Transactional
@Service("customerCouponService")
public class CustomerCouponServiceImpl extends BaseService<CustomerCoupon> implements CustomerCouponService {

	
}
