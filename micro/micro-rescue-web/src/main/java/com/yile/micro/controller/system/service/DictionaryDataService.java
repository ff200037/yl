package com.yile.micro.controller.system.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface DictionaryDataService {

	JSONObject getListData(Map< String, String > map);

	JSONObject saveDictionaryData(Map< String, String > map);

	JSONObject getById(Map< String, String > map);

	JSONObject delDictionaryData(Map< String, String > map);

	List<Map<String, String>> getListDataByCode(String dictionaryCode);

}
