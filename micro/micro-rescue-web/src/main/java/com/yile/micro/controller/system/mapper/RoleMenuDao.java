package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;


public interface RoleMenuDao {

	List<String> getMenuIds(String string);

	void deleteByRoleId(Integer roleId);

	void saveRoleMenu(List<Map<String, Object>> list);

	List<String> getBeforePermiissionIds(Integer roleId);

	void deleteByMenuId(String menuId);
}