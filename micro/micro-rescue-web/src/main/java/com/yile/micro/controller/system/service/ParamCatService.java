package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ParamCatService {
	JSONArray getTreeData();

	JSONObject saveParamCat(Map< String, String > map);


	JSONObject delParamCat(Map< String, String > map);
}
