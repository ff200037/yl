package com.yile.micro.controller.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.service.ParamCatService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/paramCat")
public class ParamCatController {
	@Autowired
	private ParamCatService paramCatService;
	/**参数配置主页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/paramInitPage")
	public String paramInitPage(Model model) {
		return "system/param/paramInitPage";
	}
	@RequestMapping("/paramCatTreePage")
	public String paramCatTreePage(Model model) {
		return "system/param/paramCatTree/paramCatTreePage";
	}

	@RequestMapping("/couponInitPage")
	public String couponInitPage(Model model) {
		return "system/coupon/couponInitPage";
	}
	
	@RequestMapping(value = "/getTreeData")
	public ResponseEntity getTreeData() {
		JSONArray allData = paramCatService.getTreeData();
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveParamCat")
	public ResponseEntity saveParamCat(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = paramCatService.saveParamCat(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	@RequestMapping(value = "/delParamCat")
	public ResponseEntity delParamCat(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = paramCatService.delParamCat(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}


}
