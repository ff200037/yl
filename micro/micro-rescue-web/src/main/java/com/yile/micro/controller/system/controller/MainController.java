package com.yile.micro.controller.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.LoginConstant;
import com.yile.micro.controller.system.bean.Account;
import com.yile.micro.controller.system.service.MainService;
import com.yile.micro.util.RequestUtil;
@Controller
@RequestMapping("/system/main")
public class MainController {
	@Autowired
	private MainService mainService;
	
	//登录页面
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest request,Model model){
		return "main/login/loginPage";
	}
	@RequestMapping("/mainPage")
	public String mainPage(HttpServletRequest request,Model model){
		Account currentAccount = mainService.getCurrentAccount();
		model.addAttribute("accountName", currentAccount.getAccountName());
		return "main/MainPanel/MainPanel";
	}
	//登录接口
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/doLogin")
	public ResponseEntity doLogin(HttpServletRequest request,HttpServletResponse response)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = mainService.doLogin(map,request,response);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	//点击退出系统按钮的时候一定要记得清除cookie值（）
	//返回登录页面的中转方法,用于清除cookie中内容，不要在登录方法中清除,因为首次登录时候进入登录方法cookie是不需要清除的 
    @RequestMapping(value="/doExit")  
    public void doExit(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	
    	//使用AuthorizedInterceptor拦截器类才会用到下面的代码
//        CookieTool.addCookie(response, LoginConstant.ACCOUNT_NAME, null, 0); // 清除Cookie  
//        CookieTool.addCookie(response, LoginConstant.ACCOUNT_PASSWORD, null, 0); // 清除Cookie  
        
        mainService.setCurrentAccount(null);
        
        String path = request.getContextPath();  
        String basePath = request.getScheme() + "://"  + request.getServerName() + ":" + request.getServerPort()  + path ;  
        String finalPath = basePath+LoginConstant.LOGIN_PAGE;  
        response.sendRedirect(finalPath);  
          
    }  
}
