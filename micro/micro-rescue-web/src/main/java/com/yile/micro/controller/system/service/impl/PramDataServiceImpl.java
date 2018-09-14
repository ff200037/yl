package com.yile.micro.controller.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.ParamData;
import com.yile.micro.controller.system.mapper.ParamDataDao;
import com.yile.micro.controller.system.service.PramDataService;
import com.yile.micro.util.ResultUtil;

@Service
public class PramDataServiceImpl implements PramDataService{
	@Autowired
	private ParamDataDao paramDataDao; 
	
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=paramDataDao.getListData(map);
		Long count=paramDataDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONObject saveParamData(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			String paramDataCode=(String) map.get("paramDataCode");
			Long count=paramDataDao.getCountByCode(paramDataCode);
			if (count>0) {
				return ResultUtil.failed("保存失败，编码已存在");
			} else {
				paramDataDao.saveParamData(map);
			}
			
		} else {
			paramDataDao.updateParamData(map);
		}
		return ResultUtil.success("保存成功!");
	}
	public JSONObject getById(Map< String, String > map) {
		Integer id=Integer.parseInt(map.get("id").toString());
		Map<String, Object> resultMap=paramDataDao.getById(id);
		return ResultUtil.formDataJson(resultMap);
	}
	public JSONObject delParamData(Map< String, String > map) {
		Integer id=Integer.parseInt(map.get("id").toString()) ;
		paramDataDao.delById(id);
		return ResultUtil.success("删除成功!");
	}
	public String getSysParamData(String paramDataCode, String defaultValue) {
		ParamData paramData=paramDataDao.getByCode(paramDataCode);
		if (paramData==null) {
			return defaultValue;
		} else {
			return paramData.getParamDataValue();
		}
	}
}
