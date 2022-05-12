package com.bl.ep.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description 首页
 * @Author 陈宝梁
 * @Date 2021/11/19 10:21
 * @Version 1.0
 **/
@Controller
public class IndexController {
//    @RequestMapping(value = "/")
//    public String index(){
//        return "login_user/login";
//    }

    /**
     * 核酸检测信息 登记页
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_TESTER','ROLE_MG')")
    @GetMapping(value = "/studentHealth")
    public String studentHealth() {
        return "tester/studentHealthInfo";
    }

    /**
     * 值班人员安排页
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_MG')")
    @GetMapping(value = "/arrangeStaff")
    public String arrangeStaff() {
        return "admin/arrangeStaff";
    }

    /**
     * 添加学生
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addStu")
    public String addStudent() {
        return "admin/addStudent";
    }

    /**
     * 添加 工作人员
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addSec")
    public String addSecurityGuard() {
        return "admin/addGuard";
    }

    /**
     * 签到 打卡
     * @return
     */
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping(value = "/stu/sign-in")
    public String studentSignIn() {
        return "user/signIn";
    }

    /**
     * 查看已签到信息
     * @return
     */
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping(value = "/stu/sign-ed")
    public String studentSignED() {
        return "user/signED";
    }
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping(value = "/stu/sign-formation")
    public String studentSignInfo(){
        return "user/signInfo";
    }
}
