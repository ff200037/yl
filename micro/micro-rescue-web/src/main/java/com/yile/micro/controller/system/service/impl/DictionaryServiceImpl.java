package com.yile.micro.controller.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Dictionary;
import com.yile.micro.controller.system.mapper.DictionaryDao;
import com.yile.micro.controller.system.service.DictionaryService;
import com.yile.micro.util.ResultUtil;

@Service
public class DictionaryServiceImpl implements DictionaryService{
	@Autowired
	private DictionaryDao dictionaryDao;
	

	public JSONArray getTreeData() {
		List<Dictionary> allDictionary = getAllDictionary();
		return getChildrenData(allDictionary);
	}
	private List<Dictionary> getAllDictionary() {
		List<Dictionary> allDictionary = dictionaryDao.getAllDictionary();
		List<Dictionary> rootList = new ArrayList<Dictionary>();
		for (Dictionary item : allDictionary) {
			//根节点
			if (item.getFkPid()==null) {
				item.setChildList(getChild(item.getId(), allDictionary));//添加所有的子列表
				rootList.add(item);
			}
		}
		return rootList;
	}
	private JSONArray getChildrenData(List<Dictionary> childList) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		
		for (Dictionary item : childList) {
			json = new JSONObject();
			json.put("id", item.getId());
			json.put("text", item.getDictionaryName());
			json.put("dictionaryCode", item.getDictionaryCode());
			if (item.getId().equals("root")) {
				json.put("expanded", true);
			}
			if (item.getChildList()!=null && item.getChildList().size()>0 ) {
				json.put("children", getChildrenData(item.getChildList()));
				json.put("leaf", false);
			}else {
				json.put("leaf", true);
			}
			allData.add(json);
		}
		return allData;
	}

	private List<Dictionary> getChild(String id, List<Dictionary> allDictionary) {
		List<Dictionary> childList = new ArrayList<Dictionary>();
		for (Dictionary item : allDictionary) {
			//为null的是根节点
			if (item.getFkPid()!=null) {
				if (item.getFkPid().equals(id)) {
					item.setChildList(getChild(item.getId(), allDictionary));//添加所有的子列表
					childList.add(item);
				}
			}
		}
		return childList;
	}
	
	public JSONObject saveDictionary(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			Long count=dictionaryDao.getCountByCode(map.get("dictionaryCode").toString());
			if (count>0) {
				return ResultUtil.failed("保存失败，编码已存在");
			} else {
				dictionaryDao.saveDictionary(map);
				return ResultUtil.success("保存成功!");
			}
			
		} else {
			List<Dictionary> allDictionary = getAllDictionary();
			List<String> myChildList=new ArrayList<String>();
			findDictionary(allDictionary,map.get("id").toString(),myChildList);
			if (myChildList.contains(map.get("fkPid").toString())) {
				return ResultUtil.failed("不能移到当前分类或子分类下");
			} else {
				dictionaryDao.updateDictionary(map);
				return ResultUtil.success("保存成功!");
			}
		}
		
	}
	private void findDictionary(List<Dictionary> allDictionary, String id,List<String> myChildList) {
		for (Dictionary item : allDictionary) {
			if (item.getId().equals(id)) {
				//找到了
				myChildList.add(item.getId());
				addChildIds(myChildList,item.getChildList());
			}else {
				//没找到就继续找子数据
				findDictionary(item.getChildList(), id, myChildList);
			}
		}
		
	}
	private void addChildIds(List<String> myChildList,
			List<Dictionary> childList) {
		for (Dictionary item : childList) {
			myChildList.add(item.getId());
			addChildIds(myChildList,item.getChildList());
		}
		
	}
	public JSONObject delDictionary(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			dictionaryDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			return ResultUtil.failed("无法删除");
		}
	}
	
}
