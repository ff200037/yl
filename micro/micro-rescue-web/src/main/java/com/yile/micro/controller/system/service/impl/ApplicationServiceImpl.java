package com.yile.micro.controller.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Application;
import com.yile.micro.controller.system.mapper.ApplicationDao;
import com.yile.micro.controller.system.service.ApplicationService;
import com.yile.micro.util.ResultUtil;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	@Autowired
	private ApplicationDao applicationDao;
	
	public List<Map<String, String>> getAppListData() {
		List<Map<String, String>> list=applicationDao.getAppListData();
		return list;
	}
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, String >> list=applicationDao.getListData(map);
		Long count=applicationDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONObject delApplication(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			applicationDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			return ResultUtil.failed("无法删除");
		}
	}
	public JSONObject saveApplication(Map< String, String > map) {
		String appCode=map.get("appCode");
		if (StringUtils.isEmpty(map.get("id"))) {
			Long count=applicationDao.getCountByCode(appCode);
			if (count>0) {
				return ResultUtil.failed("保存失败，编码已存在");
			} else {
				map.put("useState", "2");//写死
				applicationDao.saveApplication(map);
				return ResultUtil.success("保存成功!");
			}
			
		} else {
			Application application=applicationDao.getById(Integer.valueOf(map.get("id").toString()));
			if (!appCode.equals(application.getAppCode())) {
				Long count=applicationDao.getCountByCode(appCode);
				if (count>0) {
					return ResultUtil.failed("保存失败，编码已存在");
				}
			}
			
			map.put("useState", "2");//写死
			applicationDao.updateApplication(map);
			return ResultUtil.success("保存成功!");
		}
	}
}
