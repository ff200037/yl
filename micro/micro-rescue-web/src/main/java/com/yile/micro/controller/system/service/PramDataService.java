package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface PramDataService {
	JSONObject getListData(Map< String, String > map);

	JSONObject saveParamData(Map< String, String > map);

	JSONObject getById(Map< String, String > map);

	JSONObject delParamData(Map< String, String > map);
	
	String getSysParamData(String paramDataCode, String defaultValue);

}
