package com.yile.micro.controller.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Role;
import com.yile.micro.controller.system.mapper.RoleDao;
import com.yile.micro.controller.system.mapper.RoleMenuDao;
import com.yile.micro.controller.system.mapper.RolePermissionDao;
import com.yile.micro.controller.system.service.RoleService;
import com.yile.micro.util.ResultUtil;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private RoleMenuDao roleMenuDao;
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=roleDao.getListData(map);
		Long count=roleDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public List<Map<String, Object>> getComboboxData(Map< String, String > map) {
		List<Map < String, Object >> list=roleDao.getComboboxData(map);
		return list;
	}
	
	public JSONObject saveRolePermission(Map< String, String > map) {
		Integer roleId=Integer.parseInt(map.get("roleId").toString());
		//删除
		rolePermissionDao.deleteByRoleId(roleId);
		String[] ids=map.get("permissionIds").toString().split(",");
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> dataMap=null;
		for (String perId : ids) {
			if (!"".equals(perId)) {
				
				dataMap = new HashMap<String, Object>();
				dataMap.put("roleId", roleId);
				dataMap.put("perId", perId);
				list.add(dataMap);
			}
		}
		//批量保存
		if (list.size()>0) {
			
			rolePermissionDao.saveRolePermission(list);
		}
		return ResultUtil.success("保存成功!");
	}
	public List<String> getRolePermissionIds(Map< String, String > map) {
		List<String> rolePermissionIds=rolePermissionDao.getPermissionIds(map.get("roleId").toString());
		return rolePermissionIds;
	}
	public List<String> getRoleMenuIds(Map< String, String > map) {
		List<String> list=roleMenuDao.getMenuIds(map.get("roleId").toString());
		return list;
	}
	@SuppressWarnings("unused")
	public JSONObject saveRoleMenu(Map< String, String > map) {
		Integer roleId=Integer.parseInt(map.get("roleId").toString());
		//修改角色下的菜单之前先读取出之前保存的具有权限的菜单的权限ids
//		List<String> beforePermiissionIds=roleMenuDao.getBeforePermiissionIds(roleId);
//		if (beforePermiissionIds.size()>0) {
//			Map<String, Object> paramsMap=new HashMap<String, Object>();
//			paramsMap.put("roleId", roleId);
//			paramsMap.put("list", beforePermiissionIds);
//			rolePermissionDao.deleteByPermissionIds(paramsMap);
//		}
		
		//删除
		roleMenuDao.deleteByRoleId(roleId);
		String[] new_menuIds=map.get("menuIds").toString().split(",");
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> dataMap=null;
		List<String> menuIdList=new ArrayList<String>();
		for (String menuId : new_menuIds) {
			if (!"".equals(menuId)) {
				dataMap = new HashMap<String, Object>();
				dataMap.put("roleId", roleId);
				dataMap.put("menuId", menuId);
				list.add(dataMap);
				
				menuIdList.add(menuId);
				
			}
		}
		//批量保存
		if (menuIdList.size()>0) {
			roleMenuDao.saveRoleMenu(list);
		}
		return ResultUtil.success("保存成功!");
	}
	public JSONObject saveRole(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			Role role=roleDao.getRoleByName(map.get("roleName").toString());
			if (role!=null) {
				return ResultUtil.failed("该角色已存在");
			}
			roleDao.saveRole(map);
			return ResultUtil.success("保存成功!");
		} else {
			Role role=roleDao.getRoleBy(map.get("id").toString());
			String roleName=map.get("roleName").toString();//新的角色名称
			if (!roleName.equals(role.getRoleName())) {
				role=roleDao.getRoleByName(roleName);
				if (role!=null) {
					return ResultUtil.failed("该角色已存在");
				}
			}
			roleDao.updateRole(map);
			return ResultUtil.success("保存成功!");
		}
		
	}
	public JSONObject delRole(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			roleDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			return ResultUtil.failed("无法删除");
		}
	}
}
