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
import com.yile.micro.controller.system.service.MenuService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;

	/**菜单管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/menuPage")
	public String menuPage(Model model) {
		return "system/menu/menuPage";
	}
	/**添加菜单项的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addMenuPage")
	public String addMenuPage(Model model) {
		return "system/menu/addMenuPage/addMenuPage";
	}
	/**添加菜单分类的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addMenuFolderPage")
	public String addMenuFolderPage(Model model) {
		return "system/menu/addMenuFolderPage/addMenuFolderPage";
	}
	/**选择菜单分类的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/menuFolderPage")
	public String menuFolderPage(Model model) {
		return "system/menu/menuFolderPage/menuFolderPage";
	}
	/**选择权限
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectPermission")
	public String selectPermission(Model model) {
		return "system/menu/permission/selectPermission";
	}
	/**菜单树
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/getTreeData")
	public ResponseEntity getTreeData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONArray allData = menuService.getTreeData(map);
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	/**菜单分类树
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/getMenuFolderTreeData")
	public ResponseEntity getMenuFolderTreeData() {
		JSONArray allData = menuService.getMenuFolderTreeData();
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	/**根据账号读取菜单
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/getTreeDataByAccount")
	public ResponseEntity getTreeDataByAccount() {
		JSONArray allData = menuService.getTreeDataByAccount();
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = menuService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveMenuFolder")
	public ResponseEntity saveMenuFolder(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = menuService.saveMenuFolder(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	@RequestMapping(value = "/saveMenu")
	public ResponseEntity saveMenu(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = menuService.saveMenu(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = menuService.getById(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除菜单或分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delMenu")
	public ResponseEntity delMenu(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = menuService.delMenu(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	
}
