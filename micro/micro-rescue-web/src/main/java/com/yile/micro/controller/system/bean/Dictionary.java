package com.yile.micro.controller.system.bean;

import java.util.List;

public class Dictionary {
    private String id;

    private String dictionaryName;

    private String dictionaryCode;

    private String fkPid;
    private List<Dictionary> childList;
    public void setChildList(List<Dictionary> childList) {
		this.childList = childList;
	}
    public List<Dictionary> getChildList() {
		return childList;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName == null ? null : dictionaryName.trim();
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode == null ? null : dictionaryCode.trim();
    }

    public String getFkPid() {
        return fkPid;
    }

    public void setFkPid(String fkPid) {
        this.fkPid = fkPid == null ? null : fkPid.trim();
    }
}