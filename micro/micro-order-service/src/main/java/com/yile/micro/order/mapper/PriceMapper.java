package com.yile.micro.order.mapper;

import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.order.bean.Price;

import java.util.List;
import java.util.Map;

/**
 * Mapper.xml 文件配置相应的方法
 * @author 
 * 
 */
public interface PriceMapper extends BaseMapper<Price>{
    List<Price> getListData(Map<String, Object> map);
}
