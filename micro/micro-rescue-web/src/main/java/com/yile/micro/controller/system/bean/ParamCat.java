package com.yile.micro.controller.system.bean;

import java.util.List;

public class ParamCat {
    private String id;

    private String catName;

    private String fkPid;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public String getFkPid() {
        return fkPid;
    }

    public void setFkPid(String fkPid) {
        this.fkPid = fkPid == null ? null : fkPid.trim();
    }
    
    //——————————————
    private List<ParamCat> childList;
    public void setChildList(List<ParamCat> childList) {
		this.childList = childList;
	}
    public List<ParamCat> getChildList() {
		return childList;
	}
}