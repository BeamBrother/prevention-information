package com.bl.ep.bean;

import java.util.Date;

/**
 * @ClassName Student
 * @Description 学生
 * @Author 陈宝梁
 * @Date 2021年11月15日 16:47
 * @Version 1.0
 **/
public class Student extends User {

    private String department;  //系别
    private String major;  // 专业

    public Student() {
    }

    public Student(Long id, String no, String username, String email, String password, Date birthday, Role role, String department, String major) {
        super(id, no, username, email, password, birthday, role);
        this.department = department;
        this.major = major;
    }

    public Student(String no, String username, String email, String password, Date birthday, Role role, String department, String major) {
        super(no, username, email, password, birthday, role);
        this.department = department;
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "department='" + department + '\'' +
                ", major='" + major + '\'' +
                '}';
    }


}
