package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;


public interface AccountRoleDao {

	void saveAccountRole(Map< String, String > map);

	List<Map<String, Object>> getListData(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	Long findCount(Map< String, String > map);

	void delById(String id);
    
}