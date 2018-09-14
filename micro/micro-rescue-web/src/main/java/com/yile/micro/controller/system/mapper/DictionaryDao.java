package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.Dictionary;


public interface DictionaryDao {

	List<Dictionary> getAllDictionary();

	void saveDictionary(Map< String, String > map);

	Long getCountByCode(String code);

	void updateDictionary(Map< String, String > map);

	void delById(String id);
}