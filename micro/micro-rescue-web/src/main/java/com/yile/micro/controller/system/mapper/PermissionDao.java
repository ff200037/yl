package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.Permission;


public interface PermissionDao {


	void savePermission(Map< String, String > map);

	List<Permission> getAllPermission();

	List<Map<String, Object>> getListData(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	Map<String, Object> getById(String id);

	void updatePermission(Map< String, String > map);

	void updatePermissionFolder(Map< String, String > map);

	Permission findPermissionByPath(String servletPath);

	void delById(String id);
}