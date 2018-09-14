package com.yile.micro.controller.system.bean;

public class Application {
    private Integer id;

    private String appName;

    private String appCode;

    private String appWebpath;

    private String remark;

    private Integer useState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public String getAppWebpath() {
        return appWebpath;
    }

    public void setAppWebpath(String appWebpath) {
        this.appWebpath = appWebpath == null ? null : appWebpath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }
}