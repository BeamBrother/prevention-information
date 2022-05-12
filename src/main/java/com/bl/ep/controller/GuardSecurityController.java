package com.bl.ep.controller;

import com.bl.ep.bean.Role;
import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.service.RoleService;
import com.bl.ep.service.SecurityGuardService;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.security.MD5Utils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GuardSecurityController
 * @Description 门岗检测员 handler
 * @Author 陈宝梁
 * @Date 2021/11/19 23:00
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/guard")
public class GuardSecurityController {
    @Resource
    private SecurityGuardService securityGuardService;
    @Resource
    private RoleService roleService;

    /**
     * 修改面膜
     * @param id 用户id
     * @param oldpassword 旧密码
     * @param password  新密码
     */
    @PreAuthorize("hasAnyRole('ROLE_GUARD','ROLE_MG','ROLE_TESTER')")
    @PostMapping(value = "/updatePassword")
    public ModelAndView updatePassword(Long id, String oldpassword, String password) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/login/main");

        SecurityGuard securityGuard = securityGuardService.findByPrimaryKey(id);
        String oldpwd = securityGuard.getPassword();
        oldpassword = MD5Utils.md5(oldpassword);
        if (!oldpwd.equals(oldpassword)) {
            mv.addObject("error", "初始密码错误！");
            return mv;
        }

        securityGuard.setPassword(MD5Utils.md5(password));
        int i = securityGuardService.modifyPassword(securityGuard);
        if (i > 0) {
            mv.addObject("success", "修改成功");
            return mv;
        }
        mv.addObject("error", "修改失败，请重试");
        return mv;

    }

    /**
     * 查询所有 员工
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @GetMapping(value = "/arrangeStaff")
    public ModelAndView arrangeStaff() {
        List<SecurityGuard> securityGuardServiceAll = securityGuardService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/arrangeStaff");
        mv.addObject("guards", securityGuardServiceAll);
        return mv;
    }

    /**
     * 查询当班状态员工
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @GetMapping(value = "/onDayStaff")
    public ModelAndView onDayStaff(){
        List<SecurityGuard> serviceByOnDay = securityGuardService.findByOnDay();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/onDayStaff");
        mv.addObject("onDayGuard",serviceByOnDay);
        return mv;
    }

    /**
     * 根据id清除该人员当班状态
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @PostMapping(value = "/clearOnDayById")
    public Map clearOnDayById(@RequestParam(value = "id") String id){
        //查询该人员
        SecurityGuard securityGuard = securityGuardService.findByPrimaryKey(Long.valueOf(id));
        //修改其当班状态
        securityGuard.setOnDay(false);
        List<SecurityGuard> securityGuards = new ArrayList<>();
        securityGuards.add(securityGuard);
        //调用业务逻辑进行修改
        securityGuardService.modifyOnDayById(securityGuards);
        Map<String,String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }

    /**
     * 设置核酸检测人员
     * @param checkStaff  指定人员的 id 数组
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MG')")
    @PostMapping(produces= "application/json;charset=utf-8",value = "/testerStaff")
    public Object testerStaff(@RequestParam(value = "checkStaff[]") String[] checkStaff) {
        List<SecurityGuard> securityGuards = new ArrayList<>();
        //将其权限设置 核酸检测员
        Role role_tester = roleService.findByRoleStr(Constant.ROLE_TESTER);

        for (int i = 0; i < checkStaff.length; i++) {//将数组中的每一个人都设置当班状态 和 核酸检测员权限
            SecurityGuard securityGuard = securityGuardService.findByPrimaryKey(Long.valueOf(checkStaff[i]));
            securityGuard.setRole(role_tester);
            securityGuard.setOnDay(true);
            securityGuards.add(securityGuard);
        }
        //批量修改
        securityGuardService.modifySelective(securityGuards);
        Map<String,String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }

    /**
     * 设置核酸检测人员
     * @param checkStaff 指定人员的 id集合
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MG')")
    @PostMapping(produces= "application/json;charset=utf-8",value = "/guardStaff")
    public Object guardStaff(@RequestParam(value = "checkStaff[]") List<String> checkStaff) {
        List<SecurityGuard> securityGuards = new ArrayList<>();
        //将其权限设置为 门岗检测员
        Role role_tester = roleService.findByRoleStr(Constant.ROLE_GUARD);

        for (String item : checkStaff) { //遍历所有人员
            SecurityGuard securityGuard = securityGuardService.findByPrimaryKey(Long.valueOf(item));
            securityGuard.setRole(role_tester);
            securityGuard.setOnDay(true);
            securityGuards.add(securityGuard);
        }
        //批量设置
        securityGuardService.modifySelective(securityGuards);
        Map<String,String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }

    /**
     * 清除 除防疫管理员 外人员的当班状态
     */
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MG')")
    @PostMapping(produces= "application/json;charset=utf-8",value = "/guardClear")
    public Object guardClear() {
        //清除 除防疫管理员 外人员的当班状态
        securityGuardService.modifyOnDay(false);
        Map<String,String> map = new HashMap<>();
        map.put("msg","success");
        return map;
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @PostMapping(produces= "application/json;charset=utf-8",value = "/securityGuardOnDay")
    public Map securityGuardOnDay(@RequestParam(value = "role") String rid){
        Map<String,Object> map = new HashMap<>();
        List<SecurityGuard> role_guard = null;
        if (rid.equals("1")){
            role_guard = securityGuardService.findByRole(Constant.ROLE_GUARD);
        }else if (rid.equals("2")){
            role_guard = securityGuardService.findByRole(Constant.ROLE_TESTER);
        }
        map.put("guard",role_guard);
        return map;
    }
}
