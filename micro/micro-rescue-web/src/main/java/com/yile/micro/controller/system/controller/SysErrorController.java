package com.yile.micro.controller.system.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Permission;
import com.yile.micro.controller.system.service.ErrorService;
import com.yile.micro.util.ResultUtil;

@Controller
@RequestMapping("/system/error")
public class SysErrorController {
	@Autowired
	private ErrorService errorService;
	
//	@RequestMapping(value = "/showPermissionErrorForAjax")
	@RequestMapping(value="/showPermissionErrorForAjax", produces = "application/json; charset=utf-8")
	public ResponseEntity showPermissionErrorForAjax(HttpServletRequest request)  {
		String servletPath=(String) request.getAttribute("servletPath");
		Permission permission=errorService.findPermissionByPath(servletPath);
		JSONObject json = ResultUtil.failed("");
		json.put("errorType", "permissionError");
		if (permission==null) {
			json.put("errorMsg", "请求的地址没有配置权限："+servletPath);
			//返回的状态是"403 Forbidden"，这样firebug会显示一个错误。这个状态码，网上的解释是：表示服务器拒绝提供相关的资源，不管是否授权。  
			return new ResponseEntity(json, HttpStatus.FORBIDDEN);
		} else {
			json.put("errorMsg", "您没有\""+permission.getPermissionName()+"\"这个权限");
			//返回的状态是"403 Forbidden"，这样firebug会显示一个错误。这个状态码，网上的解释是：表示服务器拒绝提供相关的资源，不管是否授权。  
			return new ResponseEntity(json, HttpStatus.FORBIDDEN);
		}
//			return new ResponseEntity(json, HttpStatus.BAD_REQUEST);//返回的状态是"400 Bad Request"，这样firebug会显示一个错误
		
	}
	/**显示权限错误的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/showPermissionError")
	public String showPermissionError(Model model,HttpServletRequest request,HttpServletResponse response) {
		String servletPath=(String) request.getAttribute("servletPath");
		Permission permission=errorService.findPermissionByPath(servletPath);
//		model.addAttribute("servletPath", "xxxxxxxxxx");
		StringBuffer buffer = new StringBuffer();
		if (permission==null) {
			buffer.append("您访问的页面未配置权限："+servletPath);
		} else {
			buffer.append("您没有\""+permission.getPermissionName()+"\"这个权限");
		}
		model.addAttribute("msg", buffer.toString());
		return "system/error/showPermissionError";
		
	}
	/**显示权限错误的提示信息，针对文件上传
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/showPermissionErrorForFileUpload")
	public void showPermissionErrorForFileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String servletPath=(String) request.getAttribute("servletPath");
		Permission permission=errorService.findPermissionByPath(servletPath);
		if (permission==null) {
			out.println(ResultUtil.failed("求的地址没有配置权限："+servletPath));
		} else {
			out.println(ResultUtil.failed("您没有\""+permission.getPermissionName()+"\"这个权限"));
		}
		out.close();
	}
	@RequestMapping("/showError")
	public String showError(Model model,HttpServletRequest request,HttpServletResponse response) {
		return "system/error/showError";
		
	}
	@RequestMapping(value="/showLoginErrorForAjax")
	public ResponseEntity showLoginErrorForAjax(HttpServletRequest request)  {
		JSONObject json = ResultUtil.failed("");
		json.put("errorType", "permissionError");
		json.put("errorMsg", "请重新登录");
		return new ResponseEntity(json, HttpStatus.FORBIDDEN);
	}
	@RequestMapping("/showLoginErrorForFileUpload")
	public void showLoginErrorForFileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(ResultUtil.failed("请重新登录"));
		out.close();
	}
}
