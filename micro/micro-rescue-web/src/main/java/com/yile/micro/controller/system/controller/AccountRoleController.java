package com.yile.micro.controller.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.service.AccountRoleService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/accountRole")
public class AccountRoleController {
	@Autowired
	private AccountRoleService accountRoleService;
	
	@RequestMapping(value = "/saveAccountRole")
	public ResponseEntity saveAccountRole(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountRoleService.saveAccountRole(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	@RequestMapping(value = "/delAccountRole")
	public ResponseEntity delAccountRole(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountRoleService.delAccountRole(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountRoleService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
