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
import com.yile.micro.controller.system.service.DictionaryDataService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/dictionaryData")
public class DictionaryDataController {
	@Autowired
	private DictionaryDataService dictionaryDataService;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = dictionaryDataService.getById(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**添加字典数据的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addDictionaryDataPage")
	public String addDictionaryDataPage(Model model) {
		return "system/dictionary/addDictionaryData/addDictionaryDataPage";
	}
	/**读取列表数据
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = dictionaryDataService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**通过编码获取字典数据
	 * @return
	 */
	@RequestMapping(value = "/getListDataByCode")
	public ResponseEntity getListDataByCode(String dictionaryCode) {
		List<Map<String, String>> list=dictionaryDataService.getListDataByCode(dictionaryCode);
		return new ResponseEntity( list, HttpStatus.OK);
	}
	@RequestMapping(value = "/saveDictionaryData")
	public ResponseEntity saveDictionaryData(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = dictionaryDataService.saveDictionaryData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除字典数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delDictionaryData")
	public ResponseEntity delDictionaryData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = dictionaryDataService.delDictionaryData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
