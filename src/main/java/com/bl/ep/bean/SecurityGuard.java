package com.bl.ep.bean;

import java.util.Date;

/**
 * @ClassName SecurityGuard
 * @Description 门岗检测员
 * @Author 陈宝梁
 * @Date 2021年11月15日 16:48
 * @Version 1.0
 **/
public class SecurityGuard extends User {

    private String phone;
    private Boolean onDay; //当班 true(1)当班 false(0)非当班

    public SecurityGuard() {
    }

    public SecurityGuard(Long id, String no, String username, String email, String password, Date birthday, Role role, String phone, Boolean onDay) {
        super(id, no, username, email, password, birthday, role);
        this.phone = phone;
        this.onDay = onDay;
    }

    public SecurityGuard(String no, String username, String email, String password, Date birthday, Role role, String phone, Boolean onDay) {
        super(no, username, email, password, birthday, role);
        this.phone = phone;
        this.onDay = onDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getOnDay() {
        return onDay;
    }

    public void setOnDay(Boolean onDay) {
        this.onDay = onDay;
    }

    @Override
    public String toString() {
        return "SecurityGuard{" +
                "phone='" + phone + '\'' +
                ", onDay='" + onDay + '\'' +
                '}';
    }
}
