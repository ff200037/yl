package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;


public interface DictionaryDataDao {

	List<Map<String, Object>> getListData(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	void saveDictionaryData(Map< String, String > map);

	Map<String, Object> getById(Integer id);

	void updateDictionaryData(Map< String, String > map);

	void delById(Integer id);

	List<Map<String, String>> getListDataByCode(String dictionaryCode);

	List<Map<String, String>> getListDataByName(Map<String, String> paramsMap);
}