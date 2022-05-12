package com.bl.ep.controller;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.bean.User;
import com.bl.ep.service.HealthCodeService;
import com.bl.ep.service.SecurityGuardService;
import com.bl.ep.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description 登录处理 handler
 * @Author 陈宝梁
 * @Date 2021/11/17 19:23
 * @Version 1.0
 **/
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private StudentService studentService;
    @Resource
    private SecurityGuardService securityGuardService;
    @Resource
    private HealthCodeService healthCodeService;

    /**
     * 进行页面重定向
     */
    @RequestMapping("/index")
    public String index() {
        return "redirect:/login/main";
    }

    /**
     * 主界面
     */
    @RequestMapping(value = "/main")
    public String main(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String no = authentication.getName();
        User user = studentService.findByAccount(no);
        if (user != null) {
            HealthCode healthCode = healthCodeService.findByStudent(user.getId());
            session.setAttribute("user", user);
            session.setAttribute("healthCode",healthCode);
        }

        SecurityGuard securityGuard = securityGuardService.findByAccount(no);
        if (securityGuard != null) {
            session.setAttribute("user", securityGuard);
        }

        return "main";
    }

    /**
     * 登录页面
     */
    @GetMapping(path = "/userLogin")
    public String userLogin() {
        return "login_user/login";
    }
}
