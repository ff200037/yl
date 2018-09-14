package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.ParamData;


public interface ParamDataDao {
	
	List<Map<String, Object>> getListData(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	void saveParamData(Map< String, String > map);

	Map<String, Object> getById(Integer id);

	void updateParamData(Map< String, String > map);

	void delById(Integer id);

	Long getCountByCode(String paramDataCode);

	ParamData getByCode(String paramDataCode);

}