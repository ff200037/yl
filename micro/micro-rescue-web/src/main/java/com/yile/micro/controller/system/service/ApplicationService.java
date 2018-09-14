package com.yile.micro.controller.system.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ApplicationService {

	List<Map<String, String>> getAppListData();

	JSONObject delApplication(Map< String, String > map);

	JSONObject saveApplication(Map< String, String > map);

	JSONObject getListData(Map< String, String > map);

}
