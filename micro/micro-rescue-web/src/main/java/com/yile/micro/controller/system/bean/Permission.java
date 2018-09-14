package com.yile.micro.controller.system.bean;

import java.util.List;

public class Permission {
    private String id;

    private Integer fkApplication;

    private String permissionName;

    private String permissionType;

    private String permissionPath;

    private String remark;

    private String isFolder;
    private String isBuiltIn;
    
    private String fkPid;

    private List<Permission> childList;
    public void setChildList(List<Permission> childList) {
		this.childList = childList;
	}
    public List<Permission> getChildList() {
		return childList;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFkApplication() {
        return fkApplication;
    }

    public void setFkApplication(Integer fkApplication) {
        this.fkApplication = fkApplication;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }


    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType == null ? null : permissionType.trim();
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath == null ? null : permissionPath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder == null ? null : isFolder.trim();
    }

	public String getFkPid() {
		return fkPid;
	}

	public void setFkPid(String fkPid) {
		this.fkPid = fkPid;
	}
	public void setIsBuiltIn(String isBuiltIn) {
		this.isBuiltIn = isBuiltIn;
	}
	public String getIsBuiltIn() {
		return isBuiltIn;
	}
    
}