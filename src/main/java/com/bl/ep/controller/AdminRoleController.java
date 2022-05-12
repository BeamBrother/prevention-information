package com.bl.ep.controller;

import com.bl.ep.bean.Role;
import com.bl.ep.service.AdminRoleService;
import com.bl.ep.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminRoleController
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/12/1 14:07
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/adminPart")
public class AdminRoleController {
    @Resource
    private AdminRoleService adminRoleService;
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/viewAll")
    public ModelAndView findAllRole(){
        List<Role> roles = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roles",roles);
        mv.setViewName("admin/partManagement");
        if (roles == null){
            mv.addObject("waring","无任何角色数据！");
        }
        return mv;
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(produces= "application/json;charset=utf-8",value = "/addRo")
    public Map addRole(@RequestParam(value = "part")String part,
                          @RequestParam(value = "describe")String describe){
        Map<String,Object> map = new HashMap<>();
        Role role = new Role();
        role.setRole(part);
        role.setDescribe(describe);
        int i = adminRoleService.addRole(role);
        if (i == 0){
            map.put("error","添加失败，请重试！");
        }else {
            map.put("success","添加成功");
        }
        return map;
    }
}
