package com.yile.micro.order.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.order.bean.MechanismPrice;

/**
 * Mapper.xml 文件配置相应的方法
 * @author 
 * 
 */
public interface MechanismPriceMapper extends BaseMapper<MechanismPrice>{
    int queryCountByRescueObject(Map<String, Object> map);
    
    List<Map<String, Object>> queryMechanismList(Map<String, Object> map);
    
    void deleteByPriceId(Long priceId);
}
