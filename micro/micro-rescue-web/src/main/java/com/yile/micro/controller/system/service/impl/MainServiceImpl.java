package com.yile.micro.controller.system.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.LoginConstant;
import com.yile.micro.controller.system.SysParamConstant;
import com.yile.micro.controller.system.bean.Account;
import com.yile.micro.controller.system.mapper.AccountDao;
import com.yile.micro.controller.system.service.MainService;
import com.yile.micro.controller.system.service.PramDataService;
import com.yile.micro.util.MD5Util;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private PramDataService pramDataService;
	
	@SuppressWarnings("unused")
	public JSONObject doLogin(Map< String, String > map, HttpServletRequest request,
			HttpServletResponse response) {
		String accountName = map.get("accountName");
		String accountPassword = map.get("accountPassword");
		accountPassword=MD5Util.encryptPassword(accountPassword);//对密码做两次加密
		Account account = accountDao.findByName(accountName);
		if (account == null) {
			return ResultUtil.failed("该账号不存在");
		}
		
		if (!accountPassword.equals(account.getAccountPassword())) {
			return ResultUtil.failed("密码不正确");
		}
		if (account.getAccountStatus()==2) {
			return ResultUtil.failed("您的账号已被禁用");
		}
		String reqUrl;
		if (request.getSession().getAttribute(LoginConstant.REQUEST_URL) !=null) {
			//跳到登录之前的页面
			reqUrl = (String) request.getSession().getAttribute(LoginConstant.REQUEST_URL);
			request.getSession().setAttribute(LoginConstant.REQUEST_URL, null);
		} else {
			//跳到主页面
			String basePath=RequestUtil.getBasePath();
			reqUrl=basePath+LoginConstant.MAIN_PAGE;
		}
		
		this.setCurrentAccount(account);
		//使用AuthorizedInterceptor拦截器类才会用到下面的代码
//		int loginMaxAge = 30 * 24 * 60 * 60; // 定义账户密码的生命周期，这里是一个月。单位为秒
//		CookieTool.addCookie(response, LoginConstant.ACCOUNT_NAME, accountName, loginMaxAge); // 将姓名加入到cookie中
//		CookieTool.addCookie(response, LoginConstant.ACCOUNT_PASSWORD, accountPassword, loginMaxAge); // 将密码加入到cookie中
		
		JSONObject  jsonObject=ResultUtil.success("登录成功");
		jsonObject.put("reqUrl", reqUrl);
		return jsonObject;
	}
	public Account getCurrentAccount() {
		HttpServletRequest request=RequestUtil.getRequest();
		Object object=request.getSession().getAttribute(LoginConstant.SESSION_ATTR);
		if (object==null) {
			return null;
		} else {
			Account currentAccount = (Account) object;
			return currentAccount;
		}
	}
	public void setCurrentAccount(Account account) {
		HttpServletRequest request=RequestUtil.getRequest();
		if (account==null) {
//	        request.getSession().invalidate();//我在这里使用这代码时会出错
	        request.getSession().setAttribute(LoginConstant.SESSION_ATTR, null);
		} else {
			String sessionInvalidTime=pramDataService.getSysParamData(SysParamConstant.SESSION_INVALID_TIME, "30");//session默认失效时间为30分钟
			int time=Integer.valueOf(sessionInvalidTime);
			if (time<=0) {
				time=30;
			}
			request.getSession().setAttribute(LoginConstant.SESSION_ATTR, account);
			request.getSession().setMaxInactiveInterval(60*time);
		}
		
	}
}
