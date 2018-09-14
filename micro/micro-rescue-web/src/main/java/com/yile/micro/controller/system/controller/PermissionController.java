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
import com.yile.micro.controller.system.service.PermissionService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/permission")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;

	/**权限管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/permissionPage")
	public String permissionPage(Model model) {
		return "system/permission/permissionPage";
	}
	/**添加权限的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPermissionPage")
	public String addPermissionPage(Model model) {
		return "system/permission/addPermissionPage/addPermissionPage";
	}
	/**添加权限分类的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPermissionFolderPage")
	public String addPermissionFolderPage(Model model) {
		return "system/permission/addPermissionFolderPage/addPermissionFolderPage";
	}
	/**选择权限分类的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/permissionFolderPage")
	public String permissionFolderPage(Model model) {
		return "system/permission/permissionFolderPage/permissionFolderPage";
	}
	/**权限分类树
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/getPermissionFolderTreeData")
	public ResponseEntity getPermissionFolderTreeData() {
		JSONArray allData = permissionService.getPermissionFolderTreeData();
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	/**权限树
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/getTreeData")
	public ResponseEntity getTreeData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONArray allData = permissionService.getTreeData(map);
		return new ResponseEntity(allData, HttpStatus.OK);
	}
	/**角色管理页面里读取权限树
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/getRoleTreeData")
	public ResponseEntity getRoleTreeData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONArray allData = permissionService.getRoleTreeData(map);
		return new ResponseEntity(allData, HttpStatus.OK);
	}

	/**保存权限夹
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/savePermissionFolder")
	public ResponseEntity savePermissionFolder(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = permissionService.savePermissionFolder(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**保存权限
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/savePermission")
	public ResponseEntity savePermission(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = permissionService.savePermission(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**读取列表数据
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = permissionService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getById")
	public ResponseEntity getById(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = permissionService.getById(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除权限或分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delPermission")
	public ResponseEntity delPermission(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = permissionService.delPermission(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	      
	
	
	
	
	
}
