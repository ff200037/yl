package com.yile.micro.user.mapper;

import com.yile.micro.base.mapper.BaseMapper;
import com.yile.micro.user.bean.RescueRescues;

/**
 * Mapper.xml 文件配置相应的方法
 * 
 * @author
 * 
 */
public interface RescueRescuesMapper extends BaseMapper<RescueRescues> {
	void deleteByIdByFkTcustomerId(Long id);

	void UpdateByFkTcustomerId(RescueRescues rescueRescues);
}
