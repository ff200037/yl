package com.yile.micro.controller.system.bean;

import java.util.List;

public class MenuVo {
    private String id;
    private String fkPid;
    private String menuName;
    private String isFolder;
    private String permissionName;
    private String permissionType;
    private String permissionPath;
    private String appName;
    private String appWebpath;
    private String appCode;

    private List<MenuVo> childList;
    public void setChildList(List<MenuVo> childList) {
		this.childList = childList;
	}
    public List<MenuVo> getChildList() {
		return childList;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }



    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder == null ? null : isFolder.trim();
    }
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}
	public String getPermissionPath() {
		return permissionPath;
	}
	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppWebpath() {
		return appWebpath;
	}
	public void setAppWebpath(String appWebpath) {
		this.appWebpath = appWebpath;
	}
	public String getFkPid() {
		return fkPid;
	}
	public void setFkPid(String fkPid) {
		this.fkPid = fkPid;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppCode() {
		return appCode;
	}
}
