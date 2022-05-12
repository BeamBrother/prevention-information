package com.bl.ep.bean;

/**
  *@ClassName EPManager
  *@Description 管理员
  *@Author 陈宝梁
  *@Date 2021年11月15日 16:47
  *@Version 1.0
  **/
public class EPManager {
    private Integer id;
    private String account;
    private String password;
    private Role role;

    public EPManager() {
    }

    public EPManager(Integer id, String account, String password, Role role) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
