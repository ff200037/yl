package com.yile.micro.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.base.service.IBaseService;
import com.yile.micro.coupon.bean.CouponHistory;

public interface CouponHistoryService extends IBaseService<CouponHistory>{
	
	public JSONObject findByCouponId(Long id) throws Exception;
	
}
