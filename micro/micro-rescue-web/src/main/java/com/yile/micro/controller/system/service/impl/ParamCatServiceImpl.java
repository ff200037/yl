package com.yile.micro.controller.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.ParamCat;
import com.yile.micro.controller.system.mapper.ParamCatDao;
import com.yile.micro.controller.system.service.ParamCatService;
import com.yile.micro.util.ResultUtil;

@Service
public class ParamCatServiceImpl implements ParamCatService{

	@Autowired
	private ParamCatDao paramCatDao;
	

	public JSONArray getTreeData() {
		List<ParamCat> allParamCat = getAllParamCat();
		return getChildrenData(allParamCat);
	}
	private List<ParamCat> getAllParamCat() {
		List<ParamCat> allParamCat = paramCatDao.getAllParamCat();
		List<ParamCat> rootList = new ArrayList<ParamCat>();
		for (ParamCat item : allParamCat) {
			//根节点
			if (item.getFkPid()==null) {
				item.setChildList(getChild(item.getId(), allParamCat));//添加所有的子列表
				rootList.add(item);
			}
		}
		return rootList;
	}
	private JSONArray getChildrenData(List<ParamCat> childList) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		
		for (ParamCat item : childList) {
			json = new JSONObject();
			json.put("id", item.getId());
			json.put("text", item.getCatName());
//			json.put("paramCatCode", item.getParamCatCode());
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

	private List<ParamCat> getChild(String id, List<ParamCat> allParamCat) {
		List<ParamCat> childList = new ArrayList<ParamCat>();
		for (ParamCat item : allParamCat) {
			//为null的是根节点
			if (item.getFkPid()!=null) {
				if (item.getFkPid().equals(id)) {
					item.setChildList(getChild(item.getId(), allParamCat));//添加所有的子列表
					childList.add(item);
				}
			}
		}
		return childList;
	}
	
	public JSONObject saveParamCat(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			paramCatDao.saveParamCat(map);
			return ResultUtil.success("保存成功!");
			
		} else {
			List<ParamCat> allParamCat = getAllParamCat();
			List<String> myChildList=new ArrayList<String>();
			findParamCat(allParamCat,map.get("id").toString(),myChildList);
			if (myChildList.contains(map.get("fkPid").toString())) {
				return ResultUtil.failed("不能移到当前分类或子分类下");
			} else {
				paramCatDao.updateParamCat(map);
				return ResultUtil.success("保存成功!");
			}
		}
		
	}
	private void findParamCat(List<ParamCat> allParamCat, String id,List<String> myChildList) {
		for (ParamCat item : allParamCat) {
			if (item.getId().equals(id)) {
				//找到了
				myChildList.add(item.getId());
				addChildIds(myChildList,item.getChildList());
			}else {
				//没找到就继续找子数据
				findParamCat(item.getChildList(), id, myChildList);
			}
		}
		
	}
	private void addChildIds(List<String> myChildList,
			List<ParamCat> childList) {
		for (ParamCat item : childList) {
			myChildList.add(item.getId());
			addChildIds(myChildList,item.getChildList());
		}
		
	}
	public JSONObject delParamCat(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			paramCatDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			return ResultUtil.failed("无法删除");
		}
	}
	

}
