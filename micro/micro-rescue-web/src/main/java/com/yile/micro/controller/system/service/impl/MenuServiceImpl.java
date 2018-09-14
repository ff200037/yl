package com.yile.micro.controller.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.bean.Account;
import com.yile.micro.controller.system.bean.MenuVo;
import com.yile.micro.controller.system.mapper.MenuDao;
import com.yile.micro.controller.system.mapper.RoleMenuDao;
import com.yile.micro.controller.system.service.MainService;
import com.yile.micro.controller.system.service.MenuService;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;

@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Autowired
	private MainService mainService;
	public JSONObject getListData(Map< String, String > map) {
		List<Map < String, Object >> list=menuDao.getListData(map);
		Long count=menuDao.getListDataCount(map);
		return ResultUtil.getPageResult(count,list);
	}
	public JSONArray getTreeDataByAccount() {
		Account currentAccount = mainService.getCurrentAccount();
		List<String> accountMenuIdList=null;
		//系统账号
		if (currentAccount.getAccountType()==1) {
			accountMenuIdList=menuDao.findAllMenuIdList();
		} else {
			accountMenuIdList=menuDao.findAccountMenuIdList(currentAccount.getId());
		}
		String basePath=RequestUtil.getBasePath();
		List<MenuVo> rootList = allMenus();
//		List<String> list=new ArrayList<String>();
//		list.add("/system/permission/permissionPage");
//		list.add("/system/role/rolePage");
//		list.add("/system/account/accountPage");
//		list.add("/system/application/applicationPage");
//		list.add("/system/dictionary/dictionaryPage");
//		list.add("/system/menu/menuPage");
	    return processAccountTreeData(rootList,accountMenuIdList,basePath);
	}
	public JSONArray getTreeData() {
		List<MenuVo> rootList = allMenus();
	    return processTreeData(rootList);
	}
	public JSONArray getMenuFolderTreeData() {
		List<MenuVo> rootList = allMenus();
	    return processMenuFolderTreeData(rootList);
	}
	private List<MenuVo> allMenus() {
		List<MenuVo> allMenu=menuDao.getDataForTree();
	    List<MenuVo> rootList = new ArrayList<MenuVo>();
	    for (MenuVo item : allMenu) {
	    	//根节点
	    	if (item.getFkPid()==null) {
				item.setChildList(getChild(item.getId(), allMenu));//添加所有的子列表
				rootList.add(item);
			}
		}
		return rootList;
	}
	private JSONArray processAccountTreeData(List<MenuVo> childList, List<String> accountMenuIdList, String basePath) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		for (MenuVo item : childList) {
			if (accountMenuIdList.contains(item.getId())) {
				json = new JSONObject();
				json.put("id", item.getId());
				json.put("text", item.getMenuName());
				if (item.getId().equals("root")) {
					json.put("expanded", true);
				}
				String isFolder=item.getIsFolder();
				json.put("is_folder", isFolder);
				if ("T".equals(isFolder)) {
					json.put("leaf", false);
					json.put("expanded", true);//根节点下的所有节点都展开
					json.put("iconCls", "folder");
				}
				if ("F".equals(isFolder)) {
					json.put("leaf", true);
					if ("systemApplication".equals(item.getAppCode())) {
						json.put("url",basePath+item.getPermissionPath());
					} else {
						json.put("url", item.getAppWebpath()+item.getPermissionPath());
					}
				}
				json.put("children", processAccountTreeData(item.getChildList(),accountMenuIdList,basePath));
				allData.add(json);
			}
		}
		return allData;
	}
	private JSONArray processTreeData(List<MenuVo> childList) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		
		for (MenuVo item : childList) {
			json = new JSONObject();
			json.put("id", item.getId());
			json.put("text", item.getMenuName());
			if (item.getId().equals("root")) {
				json.put("expanded", true);
			}
			String isFolder=item.getIsFolder();
			json.put("is_folder", isFolder);
			if ("T".equals(isFolder)) {
				json.put("leaf", false);
				json.put("expanded", true);//根节点下的所有节点都展开
				json.put("iconCls", "folder");
			}
			if ("F".equals(isFolder)) {
				json.put("leaf", true);
				json.put("url", item.getAppWebpath()+item.getPermissionPath());
			}
			json.put("children", processTreeData(item.getChildList()));
			allData.add(json);
		}
		return allData;
	}
	private JSONArray processMenuFolderTreeData(List<MenuVo> childList) {
		JSONArray allData = new JSONArray();
		JSONObject json = null;
		
		for (MenuVo item : childList) {
			json = new JSONObject();
			String isFolder=item.getIsFolder();
			if ("T".equals(isFolder)) {
				json.put("leaf", false);
				json.put("expanded", true);//根节点下的所有节点都展开
				json.put("iconCls", "folder");
			}
			if ("F".equals(isFolder)) {
				continue;
			}
			json.put("id", item.getId());
			json.put("text", item.getMenuName());
			if (item.getId().equals("root")) {
				json.put("expanded", true);
			}
			json.put("is_folder", isFolder);
			json.put("children", processMenuFolderTreeData(item.getChildList()));
			allData.add(json);
		}
		return allData;
	}

	private List<MenuVo> getChild(String id, List<MenuVo> allMenu) {
	    List<MenuVo> childList = new ArrayList<MenuVo>();
	    for (MenuVo item : allMenu) {
	    	//为null的是根节点
	    	if (item.getFkPid()!=null) {
	    		if (item.getFkPid().equals(id)) {
	    			item.setChildList(getChild(item.getId(), allMenu));//添加所有的子列表
					childList.add(item);
				}
			}
	    }
	    return childList;
	}
	
	public JSONObject saveMenuFolder(Map< String, String > map)  {
		if (StringUtils.isEmpty(map.get("id"))) {
			map.put("menuIndex", "1");
			map.put("isFolder", "T");
			map.put("fkPermission", null);
			map.put("remark", null);
			menuDao.saveMenu(map);
			return ResultUtil.success("保存成功!");
		} else {
			Map<String, Object> dataMap=menuDao.getById(map.get("id").toString());
			String isBuiltIn=(String) dataMap.get("isBuiltIn");
			if ("T".equals(isBuiltIn)) {
				return ResultUtil.failed("不能修改系统菜单");
			} else {
				List<MenuVo> allMenus = allMenus();
				List<String> myChildList=new ArrayList<String>();
				findMenuFolder(allMenus,map.get("id").toString(),myChildList);
				if (myChildList.contains(map.get("fkPid").toString())) {
					return ResultUtil.failed("不能移到当前分类或子分类下");
				} else {
					menuDao.updateMenuFolder(map);
					return ResultUtil.success("保存成功!");
				}
			}
			
			
		}
		
	}
	private void findMenuFolder(List<MenuVo> allMenuFolders, String id,List<String> myChildList) {
		for (MenuVo item : allMenuFolders) {
			if (item.getId().equals(id)) {
				//找到了
				myChildList.add(item.getId());
				addChildIds(myChildList,item.getChildList());
			}else {
				//没找到就继续找子数据
				findMenuFolder(item.getChildList(), id, myChildList);
			}
		}
		
	}
	private void addChildIds(List<String> myChildList,
			List<MenuVo> childList) {
		for (MenuVo item : childList) {
			myChildList.add(item.getId());
			addChildIds(myChildList,item.getChildList());
		}
		
	}
	public JSONObject saveMenu(Map< String, String > map) {
		if (StringUtils.isEmpty(map.get("id"))) {
			map.put("isFolder", "F");
			menuDao.saveMenu(map);
		} else {
			Map<String, Object> dataMap=menuDao.getById(map.get("id").toString());
			String isBuiltIn=(String) dataMap.get("isBuiltIn");
			if ("T".equals(isBuiltIn)) {
				return ResultUtil.failed("不能修改系统菜单");
			} else {
				menuDao.updateMenu(map);
			}
			
		}
		
		return ResultUtil.success("保存成功!");
	}
	public JSONObject getById(Map< String, String > map) {
		String id=map.get("id").toString();
		Map<String, Object> resultMap=menuDao.getById(id);
		return ResultUtil.formDataJson(resultMap);
	}
	public JSONObject delMenu(Map< String, String > map) {
		try {
			String id=map.get("id").toString();
			Map<String, Object> menuMap=menuDao.getById(id);
			if ("T".equals(menuMap.get("isBuiltIn").toString())) {
				return ResultUtil.failed("不能删除系统菜单");
			}
			roleMenuDao.deleteByMenuId(id);
			menuDao.delById(id);
			return ResultUtil.success("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed("无法删除");
		}
	}
	
	
}
