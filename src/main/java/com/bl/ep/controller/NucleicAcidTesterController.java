package com.bl.ep.controller;

import com.bl.ep.service.SecurityGuardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName NucleicAcidTesterController
 * @Description 核酸检测员 handler
 * @Author 陈宝梁
 * @Date 2021/11/21 22:34
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/nctester")
public class NucleicAcidTesterController {
    /**
     * 健康码信息
     */
    @RequestMapping(value = "/heathCode")
    public String heathCode(){
        return "tester/healthCodeInfo";
    }

}
