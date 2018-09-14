package com.yile.micro.controller.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.service.ApplicationService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/application")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	/**获取下拉框用的数据
	 * @return
	 */
	@RequestMapping(value = "/getAppListData")
	public ResponseEntity getAppListData() {
		List<Map<String, String>> list = applicationService.getAppListData();
		return new ResponseEntity( list, HttpStatus.OK);
	}
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = applicationService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**应用管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/applicationPage")
	public String applicationPage(Model model) {
		return "system/application/applicationPage";
	}
	/**删除应用
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delApplication")
	public ResponseEntity delApplication(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = applicationService.delApplication(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**保存应用
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveApplication")
	public ResponseEntity saveApplication(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = applicationService.saveApplication(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
