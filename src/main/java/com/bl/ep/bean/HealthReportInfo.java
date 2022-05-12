package com.bl.ep.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName HealthReportInfo
 * @Description 签到
 * @Author 陈宝梁
 * @Date 2021/11/15 16:58
 * @Version 1.0
 **/
public class HealthReportInfo implements Serializable {
    private static final long serialVersionUID = 7226511771253874017L;
    private Boolean refer;  //是否签到
    private Student student;  // 签到学生
    private Date reportTime;  // 签到日期

    public HealthReportInfo() {
    }

    public HealthReportInfo(Boolean refer, Student student, Date reportTime) {
        this.refer = refer;
        this.student = student;
        this.reportTime = reportTime;
    }

    public Boolean getRefer() {
        return refer;
    }

    public void setRefer(Boolean refer) {
        this.refer = refer;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public String toString() {
        return "HealthReportInfo{" +
                "refer=" + refer +
                ", student=" + student +
                ", reportTime=" + reportTime +
                '}';
    }
}

