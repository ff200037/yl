package com.yile.micro.controller.system.bean;

public class RolePermission {
    private String id;

    private Integer fkRole;

    private Integer fkPermission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getFkRole() {
        return fkRole;
    }

    public void setFkRole(Integer fkRole) {
        this.fkRole = fkRole;
    }

    public Integer getFkPermission() {
        return fkPermission;
    }

    public void setFkPermission(Integer fkPermission) {
        this.fkPermission = fkPermission;
    }
}