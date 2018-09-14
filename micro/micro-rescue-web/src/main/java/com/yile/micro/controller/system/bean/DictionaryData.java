package com.yile.micro.controller.system.bean;

import java.util.Date;

public class DictionaryData {
	
    private Integer id;

    private String fkDictionary;

    private String dictdataName;

    private String dictdataValue;
    
    private String isEnable;

    private String remark;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFkDictionary() {
        return fkDictionary;
    }

    public void setFkDictionary(String fkDictionary) {
        this.fkDictionary = fkDictionary == null ? null : fkDictionary.trim();
    }

    public String getDictdataName() {
        return dictdataName;
    }

    public void setDictdataName(String dictdataName) {
        this.dictdataName = dictdataName == null ? null : dictdataName.trim();
    }

    public String getDictdataValue() {
        return dictdataValue;
    }

    public void setDictdataValue(String dictdataValue) {
        this.dictdataValue = dictdataValue == null ? null : dictdataValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
    public String getIsEnable() {
		return isEnable;
	}
}