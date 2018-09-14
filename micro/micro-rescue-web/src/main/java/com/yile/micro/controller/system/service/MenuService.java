package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface MenuService {

	JSONArray getTreeData();

	JSONObject saveMenuFolder(Map< String, String > map);

	JSONObject saveMenu(Map< String, String > map);

	JSONObject getListData(Map< String, String > map);

	JSONArray getTreeDataByAccount();

	JSONArray getMenuFolderTreeData();

	JSONObject getById(Map< String, String > map);

	JSONObject delMenu(Map< String, String > map);


}
