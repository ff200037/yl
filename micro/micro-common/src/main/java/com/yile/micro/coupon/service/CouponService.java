package com.yile.micro.coupon.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.base.service.IBaseService;
import com.yile.micro.coupon.bean.Coupon;
@Component
public interface CouponService extends IBaseService<Coupon> {
	
	JSONObject getListData(Map< String, Object > map);
	
	public void couponBlockUp(Long id);
	
	public void couponUse(Long id);
	
	//发放优惠券
	JSONObject grantCoupon(Map< String, String > map);
	//客户领取优惠券
	JSONObject receiveCoupon(Map< String, String > map);
}

