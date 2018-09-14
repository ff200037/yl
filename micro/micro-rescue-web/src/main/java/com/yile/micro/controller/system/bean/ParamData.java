package com.yile.micro.controller.system.bean;

public class ParamData {
    private Integer id;

    private String fkParamCat;

    private String paramDataName;

    private String paramDataCode;

    private String paramDataValue;

    private String paramDataRemark;
    
    private String isEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFkParamCat() {
        return fkParamCat;
    }

    public void setFkParamCat(String fkParamCat) {
        this.fkParamCat = fkParamCat == null ? null : fkParamCat.trim();
    }

    public String getParamDataName() {
        return paramDataName;
    }

    public void setParamDataName(String paramDataName) {
        this.paramDataName = paramDataName == null ? null : paramDataName.trim();
    }

    public String getParamDataCode() {
        return paramDataCode;
    }

    public void setParamDataCode(String paramDataCode) {
        this.paramDataCode = paramDataCode == null ? null : paramDataCode.trim();
    }

    public String getParamDataValue() {
        return paramDataValue;
    }

    public void setParamDataValue(String paramDataValue) {
        this.paramDataValue = paramDataValue == null ? null : paramDataValue.trim();
    }

    public String getParamDataRemark() {
        return paramDataRemark;
    }

    public void setParamDataRemark(String paramDataRemark) {
        this.paramDataRemark = paramDataRemark == null ? null : paramDataRemark.trim();
    }
    public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
    public String getIsEnable() {
		return isEnable;
	}
}