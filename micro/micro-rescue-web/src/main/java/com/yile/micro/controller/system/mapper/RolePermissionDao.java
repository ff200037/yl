package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;


public interface RolePermissionDao {

	List<String> getPermissionIds(String roleId);

	void deleteByRoleId(int roleId);

	void saveRolePermission(List<Map<String, Object>> list);

	void deleteByPermissionIds(Map<String, Object> paramsMap);

	List<String> findAccountPermissionPaths(String accountId);

	void deleteByPermissionId(String permissionId);
   
}