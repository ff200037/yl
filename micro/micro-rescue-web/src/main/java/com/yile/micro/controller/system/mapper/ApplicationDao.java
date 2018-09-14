package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.Application;


public interface ApplicationDao {

	List<Map<String, String>> getListData(Map<String, String> map);

	void delById(String id);

	void saveApplication(Map< String, String > map);

	Long getListDataCount(Map< String, String > map);

	void updateApplication(Map< String, String > map);

	List<Map<String, String>> getAppListData();

	Long getCountByCode(String appCode);

	Application getById(int id);
}