package com.bl.ep.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName NucleicAcidDetect
 * @Description 核酸检测结果
 * @Author 陈宝梁
 * @Date 2021/11/15 17:00
 * @Version 1.0
 **/
public class NucleicAcidDetect implements Serializable {
    private static final long serialVersionUID = 7518963711964439105L;
    private String id;
    private Date time;       //提交时间
    private Boolean result;  //检测结果 false（0）表示阳性 true（1）表示阴性
    private String address;  //检测地址
    private HealthCode healthCode;  //健康码
    private SecurityGuard securityGuard; //检测人员

    public NucleicAcidDetect() {
    }

    public NucleicAcidDetect(String id, Date time, Boolean result, String address, HealthCode healthCode, SecurityGuard securityGuard) {
        this.id = id;
        this.time = time;
        this.result = result;
        this.address = address;
        this.healthCode = healthCode;
        this.securityGuard = securityGuard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HealthCode getHealthCode() {
        return healthCode;
    }

    public void setHealthCode(HealthCode healthCode) {
        this.healthCode = healthCode;
    }

    public SecurityGuard getSecurityGuard() {
        return securityGuard;
    }

    public void setSecurityGuard(SecurityGuard securityGuard) {
        this.securityGuard = securityGuard;
    }

    @Override
    public String toString() {
        return "NucleicAcidDetect{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", result=" + result +
                ", address='" + address + '\'' +
                ", healthCode=" + healthCode +
                ", securityGuard=" + securityGuard +
                '}';
    }
}
