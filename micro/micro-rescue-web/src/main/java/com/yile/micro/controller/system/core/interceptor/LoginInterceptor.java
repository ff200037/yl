package com.yile.micro.controller.system.core.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yile.micro.controller.system.LoginConstant;
import com.yile.micro.controller.system.bean.Account;
import com.yile.micro.controller.system.mapper.AccountDao;
import com.yile.micro.controller.system.mapper.RolePermissionDao;
import com.yile.micro.controller.system.service.MainService;
import com.yile.micro.util.RequestUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private MainService mainService;
	@Autowired
    private UnprotectedPath paths;
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		int sessionInvalidTime=request.getSession().getMaxInactiveInterval();
//		System.out.println("session时间+"+sessionInvalidTime);
		String requestPath = request.getServletPath();
		List<String> unprotectedReg = toRegex(paths.getUnprotected());
		for (String sid : unprotectedReg) {
			if (requestPath.matches(sid)) {
                 return true;
			}
		}
		if (mainService.getCurrentAccount()==null) {
			String contentType=request.getHeader("Content-Type");
			if (contentType!=null && contentType.indexOf("multipart/form-data")!=-1) {
				request.getRequestDispatcher("/system/error/showLoginErrorForFileUpload").forward(request, response);
			} else {
				if (RequestUtil.isAjaxRequest(request)) {
					request.getRequestDispatcher("/system/error/showLoginErrorForAjax").forward(request, response);
				}else {
					doLogin(request, response);
				}
			}
			return false;
		} else {
			Account sessionAccount = mainService.getCurrentAccount();
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("accountName", sessionAccount.getAccountName());
			map.put("accountPassword", sessionAccount.getAccountPassword());//这是加密后的密码
			Account databaseAccount = accountDao.findByNameAndPwd(map);
			
			// 如果此人已经被管理员删除(databaseAccount == null)，或者账号的密码被修改了
			//又或者账号已被禁用(databaseAccount.getAccountStatus()==2)
			//则立即跳到登录页面重新登录
			if (databaseAccount == null || databaseAccount.getAccountStatus()==2) {
				doLogin(request, response);
				return false;
			}
			else {
				return validatePermission(request,response,sessionAccount);
//				return true;//不需要验证权限
			}
			
		}
	}

	private boolean validatePermission(HttpServletRequest request, HttpServletResponse response, Account sessionAccount) throws ServletException, IOException {
		String requestPath = request.getServletPath();
		if (sessionAccount.getAccountType()==1) {
			//如果是系统账号，则跳过权限验证
			return true;
		} else {
			//用户登录后不对这几个url做权限验证
			if (paths.getPaths().contains(requestPath)) {
				return true;
			} 

			//查找该用户的所有权限路径，如果用户的权限非常多，那就不建议用这种处理方式了
			List<String> list=rolePermissionDao.findAccountPermissionPaths(sessionAccount.getId());
			if (list.contains(requestPath)) {
				return true;
			} else {
				//如果用户没有该权限
				request.setAttribute("servletPath", requestPath);
				String contentType=request.getHeader("Content-Type");
				if (contentType!=null && contentType.indexOf("multipart/form-data")!=-1) {
					request.getRequestDispatcher("/system/error/showPermissionErrorForFileUpload").forward(request, response);
				} else {
					if (RequestUtil.isAjaxRequest(request)) {
						request.getRequestDispatcher("/system/error/showPermissionErrorForAjax").forward(request, response);
					}else {
						request.getRequestDispatcher("/system/error/showPermissionError").forward(request, response);
					}
				}
				return false;
			}
		}
	
	}


	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String queryString = request.getQueryString();
		String url;
		if (queryString==null) {
			url=request.getRequestURL().toString();
		} else {
			url=request.getRequestURL().toString()+"?"+queryString;
		}
		
		String basePath=RequestUtil.getBasePath();
		request.getSession().setAttribute(LoginConstant.REQUEST_URL, url); //登录之前记录请求的地址
		response.sendRedirect(basePath + LoginConstant.LOGIN_PAGE);
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	public static List<String> toRegex(List<String> list) {
		ArrayList regList = null;
		if (list != null) {
			regList = new ArrayList();
			Iterator i$ = list.iterator();
			for (String str : list) {
				if (str.contains("*.")) {
					regList.add("\\S" + str);
				} else if (str.contains("**")) {
					regList.add(str.replace("**", "\\S*"));
				} else if (str.contains("*")) {
					regList.add(str.replace("*", "[^/]*"));
				} else {
					regList.add(str);
				}
			}
		}

		return regList;
	}
}
