package com.yile.micro.controller.system.mapper;

import java.util.List;
import java.util.Map;

import com.yile.micro.controller.system.bean.Account;


public interface AccountDao {

	List<Map<String, Object>> getListData(Map< String, String > map);

	Account findByNameAndPwd(Map< String, String > map);

	Map<String, Object> getById(String id);

	void saveAccount(Map< String, String > map);

	Account findByName(String name);

	void updateAccount(Map< String, String > map);

	void delById(String id);

	void modifyPassword(Map<String, Object> params);

	Long getListDataCount(Map<String, String> map);
    
}