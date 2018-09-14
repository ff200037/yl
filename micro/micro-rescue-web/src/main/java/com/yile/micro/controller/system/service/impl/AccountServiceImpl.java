package com.yile.micro.controller.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Account;
import com.yile.micro.controller.system.mapper.AccountDao;
import com.yile.micro.controller.system.service.AccountService;
import com.yile.micro.controller.system.service.MainService;
import com.yile.micro.util.MD5Util;
import com.yile.micro.util.ResultUtil;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private MainService mainService;
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=accountDao.getListData(map);
		Long count=accountDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONObject modifyPassword(Map< String, String > map) {
		String oldPassword= map.get("oldPassword");
		String newPassword=map.get("newPassword");
		if (StringUtils.isEmpty(oldPassword)) {
			return ResultUtil.failed("旧密码不能为空");
		}
		if (StringUtils.isEmpty(newPassword)) {
			return ResultUtil.failed("新密码不能为空");
		}
		oldPassword=MD5Util.encryptPassword(oldPassword);//对密码做两次加密
		newPassword=MD5Util.encryptPassword(newPassword);//对密码做两次加密
		Account sessionAccount = mainService.getCurrentAccount();
		Account account = accountDao.findByName(sessionAccount.getAccountName());
		if (!oldPassword.equals(account.getAccountPassword())) {
			return ResultUtil.failed("旧密码不正确");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", account.getId());
		params.put("newPassword",newPassword);
		accountDao.modifyPassword(params);
		return ResultUtil.success("修改成功!");
	}
	public JSONObject saveAccount(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			Account account = accountDao.findByName(map.get("accountName").toString());
			if (account != null) {
				return ResultUtil.failed("该账号已存在");
			}
			//新建账号的默认密码是123456
			String accountPassword=MD5Util.encryptPassword("123456");//对密码做两次加密
			map.put("accountPassword", accountPassword);
			map.put("accountType", "2");//普通账号
			accountDao.saveAccount(map);
			return ResultUtil.success("保存成功!");
		} else {
			Map<String, Object> resultMap=accountDao.getById(map.get("id").toString());
			//系统账号
			if (resultMap.get("accountType").toString().equals("1")) {
				if (map.get("accountStatus").toString().equals("2")) {
					return ResultUtil.failed("不能禁用系统账号");
				}
			}
			if (map.get("accountPassword")!=null) {
				String accountPassword=(String) map.get("accountPassword");
				if (accountPassword.trim().equals("")) {
					map.put("accountPassword", null);//在密码为空白的情况下不修改密码
				}else {
					accountPassword=MD5Util.encryptPassword(accountPassword);//对密码做两次加密
					map.put("accountPassword", accountPassword);
				}
			}
			accountDao.updateAccount(map);
			return ResultUtil.success("保存成功!");
		}
		
	}
	public JSONObject getById(Map< String, String > map) {
		Map<String, Object> resultMap=accountDao.getById(map.get("id").toString());
		return ResultUtil.formDataJson(resultMap);
	}
	public JSONObject delAccount(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			accountDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			return ResultUtil.failed("无法删除");
		}
	}
}
