package com.yile.micro.controller.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.mapper.AccountRoleDao;
import com.yile.micro.controller.system.service.AccountRoleService;
import com.yile.micro.util.ResultUtil;

@Service
public class AccountRoleServiceImpl implements AccountRoleService{
	@Autowired
	private AccountRoleDao accountRoleDao;
	public JSONObject saveAccountRole(Map< String, String > map) {
		Long count=accountRoleDao.findCount(map);
		if (count>0) {
			return ResultUtil.failed("添加失败，不能重复添加角色");
		} else {
			accountRoleDao.saveAccountRole(map);
			return ResultUtil.success("保存成功!");
		}
		
	}
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=accountRoleDao.getListData(map);
		Long count=accountRoleDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONObject delAccountRole(Map< String, String > map) {
		String id=map.get("id").toString();
		accountRoleDao.delById(id);
		return ResultUtil.success("删除成功!");
	}
}
