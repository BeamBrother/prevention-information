package com.bl.ep.controller;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.service.HealthCodeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName HealthCodeController
 * @Description 健康码 信息
 * @Author 陈宝梁
 * @Date 2021/11/22 12:49
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/health")
public class HealthCodeController {
    @Resource
    private HealthCodeService healthCodeService;

    /**
     * 查找 所有 健康码信息
     */
    @PreAuthorize("hasAnyRole('ROLE_TESTER')")
    @RequestMapping(value = "/heathCode")
    public ModelAndView heathCode() {
        List<HealthCode> healthCodes = healthCodeService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tester/healthCodeInfo");
        mv.addObject("healthCode", healthCodes);
        return mv;
    }

    /**
     * 根据 id 查询健康码信息
     *
     * @param id 健康码信息表的唯一标识
     * @return
     */
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_TESTER')")
    @GetMapping(value = "/healthById")
    public HealthCode healthById(String id) {
        HealthCode healthCode = healthCodeService.findByPrimaryKey(id);
        return healthCode;
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_TESTER')")
    @PostMapping(value = "/editHealth")
    public String editHealth(String id, String hCode) {
        HealthCode healthCode = healthCodeService.findByPrimaryKey(id);
        healthCode.sethCode(Integer.valueOf(hCode));
        int i = healthCodeService.updateByHealthCode(healthCode);
        return String.valueOf(i);
    }

    @PreAuthorize("hasAnyRole('ROLE_TESTER')")
    @PostMapping(value = "/healthBySno")
    public ModelAndView healthBySno(@RequestParam(value = "sno") String sno,
                                    @RequestParam(value = "sname") String sname) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tester/healthCodeInfo");
        HealthCode healthCode = healthCodeService.findByStudentAndNo(sno);
        if (healthCode == null) {
            mv.addObject("error", "无学号为" + sno + " 姓名为" + sname + "学生的健康码信息");
            return mv;
        }
        mv.addObject("healthCode",healthCode);
        return mv;
    }
}
