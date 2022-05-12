package com.bl.ep.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AccessInformation
 * @Description 人员出入信息
 * @Author 陈宝梁
 * @Date 2021/11/15 17:12
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "stu.access-info")
@Scope("prototype")
public class AccessInformation implements Serializable {
    private static final long serialVersionUID = 7595548732912094495L;
    private String id;  // id 唯一标识
    private Date time;  //进出时间
    private Boolean turnover;  // ture 1表示进入， false O表示出去
    private Student student;    //进出人员
    private Integer yn;     // 1 表示绿码   2 表示黄码   3 表示红码
    private String DoorPost;  // 门岗
    private SecurityGuard securityGuard; //门岗检测员

    public AccessInformation() {
    }

    public AccessInformation(String id, Date time, Boolean turnover, Student student, Integer yn, SecurityGuard securityGuard) {
        this.id = id;
        this.time = time;
        this.turnover = turnover;
        this.student = student;
        this.yn = yn;
        this.securityGuard = securityGuard;
    }

    public AccessInformation(String id, Date time, Boolean turnover, Student student, Integer yn, String doorPost, SecurityGuard securityGuard) {
        this.id = id;
        this.time = time;
        this.turnover = turnover;
        this.student = student;
        this.yn = yn;
        DoorPost = doorPost;
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

    public Boolean getTurnover() {
        return turnover;
    }

    public void setTurnover(Boolean turnover) {
        this.turnover = turnover;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

//    @ConfigurationProperties(prefix = "stu.access-info.door-post")
    public String getDoorPost() {
        return DoorPost;
    }

    public void setDoorPost(String doorPost) {
        DoorPost = doorPost;
    }

    public SecurityGuard getSecurityGuard() {
        return securityGuard;
    }

    public void setSecurityGuard(SecurityGuard securityGuard) {
        this.securityGuard = securityGuard;
    }

    @Override
    public String toString() {
        return "AccessInformation{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", turnover=" + turnover +
                ", student=" + student +
                ", yn=" + yn +
                ", DoorPost='" + DoorPost + '\'' +
                ", securityGuard=" + securityGuard +
                '}';
    }
}
