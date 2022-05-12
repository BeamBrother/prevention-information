package com.bl.ep.controller;

import com.bl.ep.bean.*;
import com.bl.ep.service.*;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName NucleicAcidDetectController
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/22 9:40
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/detected")
public class NucleicAcidDetectController {
    @Resource
    private NucleicAcidDetectService nucleicAcidDetectService;
    @Resource
    private StudentService studentService;
    @Resource
    private SecurityGuardService securityGuardService;
    @Resource
    private AccessInformationService accessInformationService;
    @Resource
    private HealthCodeService healthCodeService;
    @Autowired
    private  AccessInformation accessInformation;

    /**
     * @Description 插入 检测信息
     * @param sno  学号
     * @param sname   姓名
     * @param address  地址
     * @param tresult  检测结果
     * @param guarId   检测员
     */
    @PreAuthorize("hasRole('ROLE_TESTER')")
    @PostMapping(value = "/insertHI")
    public ModelAndView insertInfo(@RequestParam(value = "sno") String sno,
                                   @RequestParam(value = "sname") String sname,
                                   @RequestParam(value = "address") String address,
                                   @RequestParam(value = "tresult") Boolean tresult,
                                   @RequestParam(value = "gno") Long guarId) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/studentHealth");
        //查询学生
        Student stu = studentService.findByAccount(sno);
        if (!stu.getUsername().equals(sname)) {
            mv.addObject("error", "学号" + sno + "与姓名" + sname + "不匹配！");
            return mv;
        }
        //查询 防疫管理员
        SecurityGuard securityGuard = securityGuardService.findByPrimaryKey(guarId);
        if (!securityGuard.getOnDay()) { //判断检测员是否是当班人员
            mv.addObject("error", "不是当班人员！");
            return mv;
        }
        // 根据学生id查找该学生的健康码
        HealthCode healthCode = healthCodeService.findByStudent(stu.getId());
        if (healthCode == null){
            mv.addObject("error", "添加失败,无此学生健康码信息！");
            return mv;
        }
        // 核酸检测结果为阴性
        if (tresult) {
            //转换日期格式
            Date time = DateUtil.dateFormat(new Date(), Constant.YYYY_DD_MM_HH_MM_SS);
            //结果为阴性 可进入校园 添加进入校园数据
//            accessInformation.setId(IdUtils.getIncreaseIdByCurrentTimeMillis());
//            accessInformation.setTime(time);
//            accessInformation.setTurnover(true);
//            accessInformation.setStudent(stu);
//            accessInformation.setYn(Integer.valueOf("1"));
//            accessInformation.setSecurityGuard(securityGuard);

            healthCode.sethCode(Integer.valueOf("1"));
            int b = healthCodeService.updateByHealthCode(healthCode);
            NucleicAcidDetect nucleicAcidDetect = new NucleicAcidDetect(IdUtils.getIncreaseIdByCurrentTimeMillis(), time, tresult, address, healthCode,securityGuard);
            //添加 核酸检测信息
            int c = nucleicAcidDetectService.addSelective(nucleicAcidDetect);
//            int a = accessInformationService.addSelective(accessInformation);
//            if (a == 0 || b == 0 || c == 0) {
//                mv.addObject("error", "添加失败,请重试！");
//            }
            mv.addObject("success", "添加成功！");
            return mv;
        }
        healthCode.sethCode(Integer.valueOf("3"));
        //转换日期格式
        Date time = DateUtil.dateFormat(new Date(), Constant.YYYY_DD_MM_HH_MM_SS);
        //添加检测结果信息
        NucleicAcidDetect nucleicAcidDetect = new NucleicAcidDetect(IdUtils.getIncreaseIdByCurrentTimeMillis(), time, tresult, address, healthCode,securityGuard);
        //添加 核酸检测信息
        int i = healthCodeService.updateByHealthCode(healthCode);
        int j = nucleicAcidDetectService.addSelective(nucleicAcidDetect);
        if (i == 0 || j == 0) {
            mv.addObject("error", "添加失败,请重试！");
            return mv;
        }
        mv.addObject("success", "添加成功！");
        return mv;
    }

    /**
     * 查看所有核酸检测信息
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @GetMapping(value = "/findAll")
    public ModelAndView findAll(){
        List<NucleicAcidDetect> nucleicAcidDetects = nucleicAcidDetectService.findAll();
        ModelAndView mv= new ModelAndView();
        if (nucleicAcidDetects.isEmpty()){//如果为空则提示
            mv.addObject("waring","暂无任何核酸检测信息！");
        }
        mv.addObject("nucleicAcidDetects" ,nucleicAcidDetects);
        mv.setViewName("tester/healthResult");
        return mv;
    }

    /**
     * 根据学号和姓名查询该生的核酸检测信息
     * @param sno    学号
     * @param sname  姓名
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @PostMapping(value = "/findByStuId")
    public ModelAndView findByStuNoAndName(@RequestParam(value = "sno") String sno,
                                    @RequestParam(value = "sname")String sname){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tester/healthResult");
        List<NucleicAcidDetect> nucleicAcidDetectServiceByUserId = nucleicAcidDetectService.findByUserNo(sno);
        if (nucleicAcidDetectServiceByUserId.isEmpty()){ //如果为空则提示
            mv.addObject("waring","无此学生的核酸检测信息！");
        }
        mv.addObject("nucleicAcidDetects" ,nucleicAcidDetectServiceByUserId);
        return mv;
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = "/findResultByNo")
    public ModelAndView findByStuId(@RequestParam(value = "id") String id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/healthDetectAboutMe");
        Student student = studentService.findByPrimaryKey(Long.valueOf(id));
        List<NucleicAcidDetect> nucleicAcidDetectServiceByUserId = nucleicAcidDetectService.findByUserNo(student.getNo());
        if (nucleicAcidDetectServiceByUserId.isEmpty()){ //如果为空则提示
            mv.addObject("waring","无核酸检测信息！");
        }
        mv.addObject("nucleicAcidDetects" ,nucleicAcidDetectServiceByUserId);
        return mv;
    }

}
