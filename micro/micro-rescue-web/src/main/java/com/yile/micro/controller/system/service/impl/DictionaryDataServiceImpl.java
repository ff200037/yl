package com.yile.micro.controller.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.mapper.DictionaryDataDao;
import com.yile.micro.controller.system.service.DictionaryDataService;
import com.yile.micro.util.ResultUtil;

@Service
public class DictionaryDataServiceImpl implements DictionaryDataService{
	@Autowired
	private DictionaryDataDao dictionaryDataDao;
	
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=dictionaryDataDao.getListData(map);
		Long count=dictionaryDataDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONObject saveDictionaryData(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			dictionaryDataDao.saveDictionaryData(map);
		} else {
			dictionaryDataDao.updateDictionaryData(map);
		}
		return ResultUtil.success("保存成功!");
	}
	public JSONObject getById(Map< String, String > map) {
		Integer id=Integer.parseInt(map.get("id").toString());
		Map<String, Object> resultMap=dictionaryDataDao.getById(id);
		return ResultUtil.formDataJson(resultMap);
	}
	public JSONObject delDictionaryData(Map< String, String > map) {
		Integer id=Integer.parseInt(map.get("id").toString()) ;
		dictionaryDataDao.delById(id);
		return ResultUtil.success("删除成功!");
	}
	public List<Map<String, String>> getListDataByCode(String dictionaryCode) {
		List<Map<String, String>> list=dictionaryDataDao.getListDataByCode(dictionaryCode);
		return list;
	}
}
