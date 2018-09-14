package com.yile.micro.controller.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.service.AccountService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/accountPage")
	public String accountPage(Model model) {
		return "system/account/accountPage";
	}
	
	/**添加和修改账号的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addAccountPage")
	public String addAccountPage(Model model) {
		return "system/account/addAccountPage/addAccount";
	}
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveAccount")
	public ResponseEntity saveAccount(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountService.saveAccount(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyPassword")
	public ResponseEntity modifyPassword(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountService.modifyPassword(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountService.getById(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除账号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delAccount")
	public ResponseEntity delAccount(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = accountService.delAccount(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
