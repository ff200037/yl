package com.yile.micro.controller.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Permission;
import com.yile.micro.controller.system.mapper.PermissionDao;
import com.yile.micro.controller.system.mapper.RolePermissionDao;
import com.yile.micro.controller.system.service.PermissionService;
import com.yile.micro.util.ResultUtil;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	public JSONArray getPermissionFolderTreeData() {
		List<Permission> rootList = getAllPermission();
	    return processePermissionFolderTreeData(rootList);
	}
	private JSONArray processePermissionFolderTreeData(List<Permission> childList) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		
		for (Permission item : childList) {
			json = new JSONObject();
			String isFolder=item.getIsFolder();
			if ("T".equals(isFolder)) {
				json.put("leaf", false);
				json.put("expanded", false);
				json.put("iconCls", "folder");
			}
			if ("F".equals(isFolder)) {
				continue;
			}
			json.put("id", item.getId());
			json.put("text", item.getPermissionName());
			if (item.getId().equals("root")) {
				json.put("expanded", true);
			}
			json.put("is_folder", isFolder);
			json.put("children", processePermissionFolderTreeData(item.getChildList()));
			allData.add(json);
		}
		return allData;
	}
	public JSONArray getTreeData(Map< String, String > map) {
		String isOnlyShowPagePermission=map.get("isOnlyShowPagePermission");
		List<Permission> allPermission = getAllPermission();
		if (isOnlyShowPagePermission==null) {
			isOnlyShowPagePermission="0";//1只显示权限类型为页面的权限，0显示所有权限类型的权限
		}
		Boolean expanded=true;
		String expandedStr=map.get("expanded");
		if (expandedStr !=null && "0".equals(expandedStr)) {
			expanded=false;
		}
	    return processTreeData(allPermission,isOnlyShowPagePermission,expanded);
	}
	private List<Permission> getAllPermission() {
		List<Permission> db_allData = permissionDao.getAllPermission();
	    List<Permission> allPermission = new ArrayList<Permission>();
	    for (Permission item : db_allData) {
	    	//根节点
	    	if (item.getFkPid()==null) {
				item.setChildList(getChild(item.getId(), db_allData));//添加所有的子列表
				allPermission.add(item);
			}
		}
		return allPermission;
	}
	
	
	private JSONArray processTreeData(List<Permission> childList, String isOnlyShowPagePermission, Boolean expanded) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;

		for (Permission item : childList) {
			json = new JSONObject();
			json.put("id", item.getId());
			json.put("text", item.getPermissionName());
			json.put("permissionType", item.getPermissionType());//添加菜单那里选择权限的时候要用到
			
			json.put("permission_path", item.getPermissionPath());
			json.put("is_folder", item.getIsFolder());
			if ("T".equals(item.getIsFolder())) {
				json.put("leaf", false);
				if (item.getId().equals("root")) {
					json.put("expanded", true);//只展开根节点
				}else {
					//在角色管理那里，根节点下的所有节点都必须展开，如果不展开的话，当点击一个角色名称的时候就会报错
					json.put("expanded", expanded);
				}
				json.put("iconCls", "folder");
				if (item.getChildList()!=null ) {
					json.put("children", processTreeData(item.getChildList(),isOnlyShowPagePermission,expanded));
				}
				allData.add(json);
			}
			else if ("F".equals(item.getIsFolder())) {
				json.put("leaf", true);
				if (item.getPermissionType().equals("page")) {
					json.put("iconCls", "application_form");
					allData.add(json);
				} 
				if (item.getPermissionType().equals("notPage")) {
					json.put("iconCls", "application_form_edit");
					if ("0".equals(isOnlyShowPagePermission)) {
						allData.add(json);
					}
				} 
				
			}
			
			
		}
		return allData;
	}
	public JSONArray getRoleTreeData(Map< String, String > map) {
		List<Permission> rootList = getAllPermission();
		return processRoleTreeData(rootList);
	}
	private JSONArray processRoleTreeData(List<Permission> childList) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		
		for (Permission item : childList) {
			json = new JSONObject();
			json.put("id", item.getId());
			json.put("text", item.getPermissionName());
			if (item.getId().equals("root")) {
				json.put("expanded", true);
			}
			json.put("permission_path", item.getPermissionPath());
			json.put("is_folder", item.getIsFolder());
			if ("T".equals(item.getIsFolder())) {
				json.put("leaf", false);
				json.put("expanded", true);//根节点下的所有节点都展开
			}
			if ("F".equals(item.getIsFolder())) {
				json.put("leaf", true);
			}
			if (item.getChildList()!=null ) {
				json.put("children", processRoleTreeData(item.getChildList()));
			}
			allData.add(json);
		}
		return allData;
	}

	private List<Permission> getChild(String id, List<Permission> db_allData) {
	    List<Permission> childList = new ArrayList<Permission>();
	    for (Permission item : db_allData) {
	    	//为null的是根节点
	    	if (item.getFkPid()!=null) {
	    		if (item.getFkPid().equals(id)) {
					item.setChildList(getChild(item.getId(), db_allData));//添加所有的子列表
					childList.add(item);
				}
			}
	    }
	    return childList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject savePermissionFolder(Map< String, String > map)  {
		if (StringUtils.isEmpty(map.get("id"))) {
			map.put("isFolder", "T");
			map.put("fkApplication", null);
			map.put("permissionType", null);
			map.put("permissionPath", null);
			map.put("remark", null);
			permissionDao.savePermission(map);
			return ResultUtil.success("保存成功!");
		} else {
			Map<String, Object> dataMap=permissionDao.getById(map.get("id").toString());
			String isBuiltIn=(String) dataMap.get("isBuiltIn");
			if ("T".equals(isBuiltIn)) {
				return ResultUtil.failed("不能修改系统权限");
			} else {
				List<Permission> rootList = getAllPermission();
				List<String> myChildList=new ArrayList<String>();
				findPermissionFolder(rootList,map.get("id").toString(),myChildList);
				if (myChildList.contains(map.get("fkPid").toString())) {
					return ResultUtil.failed("不能移到当前分类或子分类下");
				} else {
					permissionDao.updatePermissionFolder(map);
					return ResultUtil.success("保存成功!");
				}
			}
		}
		
	}
	private void findPermissionFolder(List<Permission> allPermission, String id,List<String> myChildList) {
		for (Permission item : allPermission) {
			if (item.getId().equals(id)) {
				//找到了
				myChildList.add(item.getId());
				addChildIds(myChildList,item.getChildList());
			}else {
				//没找到就继续找子数据
				findPermissionFolder(item.getChildList(), id, myChildList);
			}
		}
		
	}
	private void addChildIds(List<String> myChildList,
			List<Permission> childList) {
		for (Permission item : childList) {
			myChildList.add(item.getId());
			addChildIds(myChildList,item.getChildList());
		}
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject savePermission(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			map.put("isFolder", "F");
			permissionDao.savePermission(map);
		} else {
			Map<String, Object> dataMap=permissionDao.getById(map.get("id").toString());
			String isBuiltIn=(String) dataMap.get("isBuiltIn");
			if ("T".equals(isBuiltIn)) {
				return ResultUtil.failed("不能修改系统权限");
			} else {
				permissionDao.updatePermission(map);
			}
			
		}
		return ResultUtil.success("保存成功!");
	}

	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=permissionDao.getListData(map);
		Long count=permissionDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONObject getById(Map< String, String > map) {
		String id=map.get("id").toString();
		Map<String, Object> resultMap=permissionDao.getById(id);
		return ResultUtil.formDataJson(resultMap);
	}
	public JSONObject delPermission(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			Map<String, Object> bean=permissionDao.getById(id);
			String isBuiltIn=(String) bean.get("isBuiltIn");
			if (isBuiltIn.equals("T")) {
				return ResultUtil.failed("不能删除系统权限");
			}
			rolePermissionDao.deleteByPermissionId(id);
			permissionDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed("无法删除");
		}
	}
}
