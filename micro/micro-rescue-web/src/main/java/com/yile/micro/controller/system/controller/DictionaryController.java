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
import com.yile.micro.controller.system.service.DictionaryService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;
	/**字典管理主页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/dictionaryPage")
	public String dictionaryPage(Model model) {
		return "system/dictionary/dictionaryPage";
	}
	@RequestMapping("/dictionaryTreePage")
	public String dictionaryTreePage(Model model) {
		return "system/dictionary/dictionaryTree/dictionaryTreePage";
	}

	
	
	@RequestMapping(value = "/getTreeData")
	public ResponseEntity getTreeData() {
		JSONArray allData = dictionaryService.getTreeData();
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveDictionary")
	public ResponseEntity saveDictionary(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = dictionaryService.saveDictionary(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除字典分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delDictionary")
	public ResponseEntity delDictionary(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = dictionaryService.delDictionary(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
