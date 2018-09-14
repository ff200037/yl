package com.yile.micro.controller.system.bean;

public class AccountRole {
    private String id;

    private String fkAccount;

    private Integer fkRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFkAccount() {
        return fkAccount;
    }

    public void setFkAccount(String fkAccount) {
        this.fkAccount = fkAccount == null ? null : fkAccount.trim();
    }

    public Integer getFkRole() {
        return fkRole;
    }

    public void setFkRole(Integer fkRole) {
        this.fkRole = fkRole;
    }
}