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
import com.yile.micro.controller.system.service.PramDataService;
import com.yile.micro.util.RequestUtil;
@Controller
@RequestMapping("/system/paramData")
public class PramDataController {
	@Autowired
	private PramDataService pramDataService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = pramDataService.getById(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	@RequestMapping("/addParamDataPage")
	public String addParamDataPage(Model model) {
		return "system/param/addParamData/addParamDataPage";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = pramDataService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	@RequestMapping(value = "/saveParamData")
	public ResponseEntity saveParamData(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = pramDataService.saveParamData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除字典数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delParamData")
	public ResponseEntity delParamData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = pramDataService.delParamData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
