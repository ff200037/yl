package com.yile.micro.util;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {

	public static Map< String, String > paramsToMap(HttpServletRequest request) {
		Map< String, String > map = new HashMap< String, String >();
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				map.put(key, values[0].trim());
//				map.put(key, values.length == 1 ? values[0].trim() : values);同参数名的情况是很少的
			}
		}
		return map;
	}
	
	public static Map< String, Object > paramsToMap2(HttpServletRequest request) {
		Map< String, Object > map = new HashMap< String, Object >();
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				map.put(key, values[0].trim());
//				map.put(key, values.length == 1 ? values[0].trim() : values);同参数名的情况是很少的
			}
		}
		return map;
	}
	
	public static void showRequestInfo(HttpServletRequest request) {
		System.out.println("rotocol: " + request.getProtocol());   
		  
		System.out.println("Scheme: " + request.getScheme());   
		  
		System.out.println("Server Name: " + request.getServerName() );   
		  
		System.out.println("Server Port: " + request.getServerPort());   
		  
		System.out.println("rotocol: " + request.getProtocol());   
		  
		System.out.println("Remote Addr: " + request.getRemoteAddr());   
		  
		System.out.println("Remote Host: " + request.getRemoteHost());   
		  
		System.out.println("Character Encoding: " + request.getCharacterEncoding());   
		  
		System.out.println("Content Length: " + request.getContentLength());   
		  
		System.out.println("Content Type: "+ request.getContentType());   
		  
		System.out.println("Auth Type: " + request.getAuthType());   
		  
		System.out.println("HTTP Method: " + request.getMethod());   
		  
		System.out.println("ath Info: " + request.getPathInfo());   
		  
		System.out.println("ath Trans: " + request.getPathTranslated());   
		  
		System.out.println("Query String: " + request.getQueryString());   
		  
		System.out.println("Remote User: " + request.getRemoteUser());   
		  
		System.out.println("Session Id: " + request.getRequestedSessionId());   
		  
		System.out.println("Request URI: " + request.getRequestURI());   
		  
		System.out.println("Servlet Path: " + request.getServletPath());   
		  
		System.out.println("Accept: " + request.getHeader("Accept"));   
		  
		System.out.println("Host: " + request.getHeader("Host"));   
		  
		System.out.println("Referer : " + request.getHeader("Referer"));   
		  
		System.out.println("Accept-Language : " + request.getHeader("Accept-Language"));   
		  
		System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));   
		  
		System.out.println("User-Agent : " + request.getHeader("User-Agent"));   
		  
		System.out.println("Connection : " + request.getHeader("Connection"));   
		  
		System.out.println("Cookie : " + request.getHeader("Cookie")); 
		
	}
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		if (accept != null&& !(accept.indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request
				.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))){
			return false;
		}
		return true;
		
		
	}
	public static String getBasePath() {
		HttpServletRequest request=getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
		return basePath;
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
//		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	public static ServletContext getServletContext() {
		ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		return context;
	}
	public static WebApplicationContext getWebApplicationContext() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
//		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//		AccountService accountService=applicationContext.getBean("accountService", AccountService.class);
		return applicationContext;
	}
	
	

	

}
