package com.bl.ep.controller;

import com.bl.ep.bean.Student;
import com.bl.ep.service.StudentService;
import com.bl.ep.utils.security.MD5Utils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StudentController
 * @Description 学生信息 handler
 * @Author 陈宝梁
 * @Date 2021/11/17 15:47
 * @Version 1.0
 **/
@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/info")
    public String studentInfo(Principal principal) {
        return principal.getName();
    }

    /**
     * 修改密码
     *
     * @param id          学生 id
     * @param oldpassword 旧密码
     * @param password    新密码
     */
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping(value = "/updatePassword")
    public ModelAndView updatePassword(Long id, String oldpassword, String password) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/login/main");
        //获取该学生
        Student student = studentService.findByPrimaryKey(id);
        // 获取旧密码
        String studentPassword = student.getPassword();
        //将密码 MD5 加密
        oldpassword = MD5Utils.md5(oldpassword);
        // 进行旧密码比对
        if (!studentPassword.equals(oldpassword)) {//比对失败
            mv.addObject("error", "初始密码错误！");
            return mv;
        }
        //比对 内容 相等，将新密码加密并设置其中
        student.setPassword(MD5Utils.md5(password));
        //修改密码
        int i = studentService.modifyPassword(student);
        if (i > 0) {
            mv.addObject("success", "修改成功");
            return mv;
        }
        mv.addObject("error", "修改失败，请重试");
        return mv;
    }

    @ResponseBody
    @PostMapping(produces = "application/json;charset=utf-8", value = "/findSByN")
    public Map findStudentByNo(@RequestParam(value = "sno") String sno) {
        Map<String, Object> map = new HashMap<>();
        Student student = studentService.findByAccount(sno);
        if (student == null) {
            map.put("status", 0);
            map.put("error", "无此学号的学生！！！");
        } else {
            student.setPassword("");
            map.put("status", 1);
            map.put("stu", student);
        }
        return map;
    }
}
