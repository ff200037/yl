package com.yile.micro.controller.system.bean;

import java.util.List;

public class Menu {
    private String id;

    private String fkPid;

    private String menuName;

    private Integer menuIndex;

    private String isFolder;

    private String fkPermission;
    private List<Menu> childList;
    public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}
    public List<Menu> getChildList() {
		return childList;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFkPid() {
        return fkPid;
    }

    public void setFkPid(String fkPid) {
        this.fkPid = fkPid == null ? null : fkPid.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }


    public Integer getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Integer menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder == null ? null : isFolder.trim();
    }

    public String getFkPermission() {
        return fkPermission;
    }

    public void setFkPermission(String fkPermission) {
        this.fkPermission = fkPermission == null ? null : fkPermission.trim();
    }
}