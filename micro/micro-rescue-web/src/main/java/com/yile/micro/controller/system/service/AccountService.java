package com.yile.micro.controller.system.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface AccountService {

	JSONObject getListData(Map< String, String > map);

	JSONObject saveAccount(Map< String, String > map);

	JSONObject getById(Map< String, String > map);

	JSONObject delAccount(Map< String, String > map);

	JSONObject modifyPassword(Map< String, String > map);

}
