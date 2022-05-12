package com.bl.ep.bean;

import java.io.Serializable;

/**
 * @ClassName HealthCode
 * @Description 健康码
 * @Author 陈宝梁
 * @Date 2021/11/15 16:55
 * @Version 1.0
 **/
public class HealthCode implements Serializable {
    private static final long serialVersionUID = -8265682039513122334L;
    private String id;
    private Integer hCode; // 健康码状态 1 表示绿码  2 表示黄码   3 表示红码
    private Student student;

    public HealthCode() {
    }

    public HealthCode(String id, Integer hCode, Student student) {
        this.id = id;
        this.hCode = hCode;
        this.student = student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer gethCode() {
        return hCode;
    }

    public void sethCode(Integer hCode) {
        this.hCode = hCode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "HealthCode{" +
                "id='" + id + '\'' +
                ", hCode='" + hCode + '\'' +
                ", student=" + student +
                '}';
    }
}
