package com.bl.ep.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @ClassName Role
 * @Description 权限对应表ep_role
 * @Author 陈宝梁
 * @Date 2021/11/15 23:18
 * @Version 1.0
 **/
public class Role implements Serializable {
    private static final long serialVersionUID = 5346129180591786794L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String role;
    private String describe;

    public Role() {
    }

    public Role(String role, String describe) {
        this.role = role;
        this.describe = describe;
    }

    public Role(Integer id, String role, String describe) {
        this.id = id;
        this.role = role;
        this.describe = describe;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
