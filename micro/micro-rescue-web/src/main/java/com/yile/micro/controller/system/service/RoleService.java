package com.yile.micro.controller.system.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface RoleService {

	JSONObject getListData(Map< String, String > map);

	List<Map < String, Object >> getComboboxData(Map< String, String > map);

	JSONObject saveRolePermission(Map< String, String > map);

	List<String> getRolePermissionIds(Map< String, String > map);

	List<String> getRoleMenuIds(Map< String, String > map);

	JSONObject saveRoleMenu(Map< String, String > map);

	JSONObject saveRole(Map< String, String > map);

	JSONObject delRole(Map< String, String > map);

}
