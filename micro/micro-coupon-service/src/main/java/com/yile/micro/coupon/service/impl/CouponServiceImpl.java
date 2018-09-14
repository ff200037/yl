package com.yile.micro.coupon.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.base.service.BaseService;
import com.yile.micro.coupon.bean.Coupon;
import com.yile.micro.coupon.mapper.CouponMapper;
import com.yile.micro.coupon.service.CouponService;
import com.yile.micro.util.ResultUtil;

@Transactional
@Service("couponService")
public class CouponServiceImpl extends BaseService<Coupon> implements CouponService {
	
	@Autowired
	private CouponMapper couponMapper;
	
	@PostConstruct
	public void setMapper() {
		super.setMapper(couponMapper);
	}

	@Override
	public JSONObject getListData(Map<String, Object> map) {
		List<Coupon> list= queryList(map);
		Integer count=queryCount(map);
		Long countLong = count.longValue();
		return ResultUtil.getPageResult(countLong,list);
	}

	@Override
	public void couponBlockUp(Long id) {
		couponMapper.couponBlockUp(id);
		
	}

	@Override
	public void couponUse(Long id) {
		couponMapper.couponUse(id);
	}
	
}



