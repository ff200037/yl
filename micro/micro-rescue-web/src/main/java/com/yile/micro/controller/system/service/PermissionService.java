package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface PermissionService {

	JSONArray getTreeData(Map< String, String > map);
	JSONArray getRoleTreeData(Map< String, String > map);

	JSONObject savePermissionFolder(Map< String, String > map) ;

	JSONObject savePermission(Map< String, String > map);

	JSONObject getListData(Map< String, String > map);
	JSONArray getPermissionFolderTreeData();
	JSONObject getById(Map< String, String > map);
	JSONObject delPermission(Map< String, String > map);


}
