package com.yile.micro.order.mapper;

import java.util.List;
import java.util.Map;
import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.order.bean.Order;

/**
 * Mapper.xml 文件配置相应的方法
 * @author 
 * 
 */
public interface OrderMapper extends BaseMapper<Order>{

	List<Order> findList(Map<String, Object> map);
	
}
