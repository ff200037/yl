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
import com.yile.micro.controller.system.service.RoleService;
import com.yile.micro.util.RequestUtil;

@Controller
@RequestMapping("/system/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	/**角色管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/rolePage")
	public String rolePage(Model model) {
		return "system/role/rolePage";
	}
	@RequestMapping(value = "/getListData")
	public ResponseEntity getListData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = roleService.getListData(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	@RequestMapping(value = "/getComboboxData")
	public ResponseEntity getComboboxData(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		List<Map < String, Object >> list=roleService.getComboboxData(map);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveRolePermission")
	public ResponseEntity saveRolePermission(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = roleService.saveRolePermission(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	
	/**读取角色下的权限ids
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getRolePermissionIds")
	public ResponseEntity getRolePermissionIds(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		List<String> list=roleService.getRolePermissionIds(map);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	/**读取角色下的菜单ids
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getRoleMenuIds")
	public ResponseEntity getRoleMenuIds(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		List<String> list=roleService.getRoleMenuIds(map);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveRoleMenu")
	public ResponseEntity saveRoleMenu(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = roleService.saveRoleMenu(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**保存角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveRole")
	public ResponseEntity saveRole(HttpServletRequest request)  {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = roleService.saveRole(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
	/**删除角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delRole")
	public ResponseEntity delRole(HttpServletRequest request) {
		Map< String, String > map=RequestUtil.paramsToMap(request);
		JSONObject json = roleService.delRole(map);
		return new ResponseEntity(json, HttpStatus.OK);
	}
}
