package com.bl.ep.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description 用户类
 * @Author 陈宝梁
 * @Date 2021年11月15日 16:47
 * @Version 1.0
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 4457290507070314669L;
    @TableId(type = IdType.AUTO)
    private Long id;  // id 唯一标识
    private String no;  // 学号/工号等
    private String username;  //姓名
    private String email;   //电子邮箱
    private String password; //密码
    private Date birthday;  //出生日期
    private Role role;   //角色权限，学生-ROLE_STUDENT 老师-ROLE_TEACHER 门岗检测员-ROLE_GUARD 核酸检测员-ROLE_TESTER 防疫管理员-ROLE_MG 超级管理员-ROLE_ADMIN

    public User() {
    }

    public User(Long id, String no, String username, String email, String password, Date birthday, Role role) {
        this.id = id;
        this.no = no;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public User(String no, String username, String email, String password, Date birthday, Role role) {
        this.no = no;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                '}';
    }
}
