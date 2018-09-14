package com.yile.micro.coupon.mapper;

import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.coupon.bean.Coupon;

/**
 * Mapper.xml 文件配置相应的方法
 * @author 
 */
public interface CouponMapper extends BaseMapper<Coupon>{
	
	//优惠券启用接口
	public void couponUse(Long id);
	//优惠券停用接口
	public void couponBlockUp(Long id);
	
}




