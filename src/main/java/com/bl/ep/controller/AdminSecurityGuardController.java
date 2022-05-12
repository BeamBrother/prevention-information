package com.bl.ep.controller;

import com.bl.ep.bean.Role;
import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.service.AdminSecurityGuardService;
import com.bl.ep.service.RoleService;
import com.bl.ep.service.SecurityGuardService;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.security.MD5Utils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName AdminSecurityGuardController
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/16 13:19
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/adminSecurity")
public class AdminSecurityGuardController {
    @Resource
    private AdminSecurityGuardService adminSecurityGuardService;
    @Resource
    private SecurityGuardService securityGuardService;
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/addGuard")
    public ModelAndView addSecurityGuard(@RequestParam(value = "gno")String gno,
                                         @RequestParam(value = "username")String username,
                                         @RequestParam(value = "password")String password,
                                         @RequestParam(value = "birth")Date birthday,
                                         @RequestParam(value = "email")String email,
                                         @RequestParam(value = "phone")String phone){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/addSec");

        SecurityGuard temp = securityGuardService.findByAccount(gno);
        if (temp != null){
            mv.addObject("error","工号已存在！");
            return mv;
        }

        SecurityGuard securityGuard = new SecurityGuard();
        securityGuard.setNo(gno);
        securityGuard.setUsername(username);
        securityGuard.setPassword(MD5Utils.md5(password));
        securityGuard.setEmail(email);
        securityGuard.setBirthday(birthday);
        securityGuard.setPhone(phone);
        securityGuard.setOnDay(false);
        Role ro = roleService.findByRoleStr(Constant.ROLE_GUARD);
        securityGuard.setRole(ro);

        int i = adminSecurityGuardService.addSecurityGuard(securityGuard);
        if (i == 0){
            mv.addObject("error","添加失败，请重试！");
        }else {
            mv.addObject("success","添加成功！");
        }

        return mv;
    }
}
