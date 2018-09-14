package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface AccountRoleService {

	JSONObject saveAccountRole(Map< String, String > map);

	JSONObject getListData(Map< String, String > map);

	JSONObject delAccountRole(Map< String, String > map);

}
