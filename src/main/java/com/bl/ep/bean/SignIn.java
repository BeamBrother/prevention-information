package com.bl.ep.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SignIn
 * @Description 签到
 * @Author 陈宝梁
 * @Date 2021/12/20 23:07
 * @Version 1.0
 **/
public class SignIn implements Serializable {
    private static final long serialVersionUID = 2894404178273236678L;
    private String id;
    private String tw;  //体温
    private String bd;  //身体健康状况
    private Boolean jcs; //同居人地域接触史
    private Boolean jc;  //同居人接触
    private Boolean cn;  //情况是否属实
    private String address;  //所在位置
    private Date reportTime;  //签到时间
    private String sno; //学生学号
    private String sname;  //学生姓名

    public SignIn() {
    }

    public SignIn(String id, String tw, String bd, Boolean jcs, Boolean jc, Boolean cn, String address, Date reportTime, String sno, String sname) {
        this.id = id;
        this.tw = tw;
        this.bd = bd;
        this.jcs = jcs;
        this.jc = jc;
        this.cn = cn;
        this.address = address;
        this.reportTime = reportTime;
        this.sno = sno;
        this.sname = sname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTw() {
        return tw;
    }

    public void setTw(String tw) {
        this.tw = tw;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public Boolean getJcs() {
        return jcs;
    }

    public void setJcs(Boolean jcs) {
        this.jcs = jcs;
    }

    public Boolean getJc() {
        return jc;
    }

    public void setJc(Boolean jc) {
        this.jc = jc;
    }

    public Boolean getCn() {
        return cn;
    }

    public void setCn(Boolean cn) {
        this.cn = cn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "SignIn{" +
                "id='" + id + '\'' +
                ", tw='" + tw + '\'' +
                ", bd='" + bd + '\'' +
                ", jcs=" + jcs +
                ", jc=" + jc +
                ", cn=" + cn +
                ", address='" + address + '\'' +
                ", reportTime=" + reportTime +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }
}
