package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.Role;


public interface RoleDao {

	List<Map<String, Object>> getListData(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	void saveRole(Map< String, String > map);

	Role getRoleByName(String roleName);

	void updateRole(Map< String, String > map);

	Role getRoleBy(String id);

	void delById(String id);

	List<Map<String, Object>> getComboboxData(Map<String, String> map);
}