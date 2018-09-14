package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface DictionaryService {

	JSONArray getTreeData();

	public JSONObject saveDictionary(Map< String, String > map);
	

	JSONObject delDictionary(Map< String, String > map);

}
