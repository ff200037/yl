package com.yile.micro.user.mapper;

import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.user.bean.RescueCustomer;

/**
 * Mapper.xml 文件配置相应的方法
 * 
 * @author
 * 
 */
public interface RescueCustomerMapper extends BaseMapper<RescueCustomer> {
	void deleteByCustomerId(Long id);

	void updateByCustomerId(RescueCustomer rescueCustomer);
}
