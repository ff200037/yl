package com.yile.micro.controller.system.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Account;

public interface MainService {

	JSONObject doLogin(Map< String, String > map, HttpServletRequest request, HttpServletResponse response);

	/**从session或redis获取当前登录账号信息
	 * @return
	 */
	Account getCurrentAccount();

	/**将账号信息保存到session或redis
	 * @param account
	 */
	void setCurrentAccount(Account account);

}
