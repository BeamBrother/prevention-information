package com.bl.ep.controller;

import com.bl.ep.bean.*;
import com.bl.ep.service.AccessInformationService;
import com.bl.ep.service.HealthCodeService;
import com.bl.ep.service.SecurityGuardService;
import com.bl.ep.service.StudentService;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AccessInformationController
 * @Description 出入信息 handler
 * @Author 陈宝梁
 * @Date 2021/11/19 19:49
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/access")
public class AccessInformationController {
    @Resource
    private AccessInformationService accessInformationService;
    @Resource
    private StudentService studentService;
    @Resource
    private SecurityGuardService securityGuardService;
    @Resource
    private HealthCodeService healthCodeService;
    @Autowired
    AccessInformation accessInformation;

    /**
     * 给一个重定向的地址
     *
     * @return
     */
    @RequestMapping(value = "/accessInfo")
    public String accessInfo() {
        return "guard/guardAccessInfo";
    }

    /**
     * 根据id查询出入信息
     * 只有 学生 自己可见
     *
     * @param id
     */
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @RequestMapping(value = "/info")
    public ModelAndView info(@RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/userAccess");
        if (id == 0) { //查看是否为默认值，是默认值则直接返回
            return mv;
        }
        //根据学生id获取该学生的出入信息
        List<AccessInformation> accessInformations = accessInformationService.findAllByStudent(id);
        mv.addObject("accessInfo", accessInformations);
        if (accessInformations.isEmpty()) {
            mv.addObject("waring", "无任何出入信息！");
            return mv;
        }
        return mv;
    }

    /**
     * 出入信息登记
     * 只有核酸检测管理员 和 门岗检测员才可访问
     *
     * @param sno        学号
     * @param healthCode 健康码状态
     * @param turnover   进/出
     * @param gno        登记员
     */
    @PreAuthorize("hasAnyRole('ROLE_GUARD','ROLE_MG')")
    @RequestMapping(value = "/insert")
    public ModelAndView addAccessInfo(@RequestParam(value = "sno") String sno,
                                      @RequestParam(value = "healthCode") Integer healthCode,
                                      @RequestParam(value = "turnover") Boolean turnover,
                                      @RequestParam(value = "gno") Long gno) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/access/accessInfo");
        //根据 id 查询登记人员
        SecurityGuard securityGuard = securityGuardService.findByPrimaryKey(gno);
        //根据 学号 查询学生
        Student student = studentService.findByAccount(sno);

        if (healthCode != 1) {
            HealthCode healthCode1 = healthCodeService.findByStudent(student.getId());
            int i = 0;

            healthCode1.sethCode(healthCode);
            i = healthCodeService.updateByHealthCode(healthCode1);

            if (i == 0) {
                mv.addObject("error", "插入失败，请重试！");
            } else {
                mv.addObject("error", "请该学生移位至核酸检测地点进行核酸检测！");
            }
            return mv;
        }
        //转换日期格式
        Date time = DateUtil.dateFormat(new Date(), Constant.YYYY_DD_MM_HH_MM_SS);
        //插入数据
        accessInformation.setId(IdUtils.getIncreaseIdByCurrentTimeMillis());
        accessInformation.setTime(time);
        accessInformation.setTurnover(turnover);
        accessInformation.setStudent(student);
        accessInformation.setYn(healthCode);
        accessInformation.setSecurityGuard(securityGuard);

        int i = accessInformationService.addSelective(accessInformation);
        if (i > 0) {
            mv.addObject("success", "添加成功！");
            return mv;
        } else {
            mv.addObject("error", "插入失败，请重试！");
            return mv;
        }
    }


    /**
     * 根据学号和姓名查询学生的出入信息
     *
     * @param sno   学号
     * @param sname 姓名
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @PostMapping(value = "/accessByStudent")
    @ResponseBody
    public ModelAndView findAccessByStudent(@RequestParam(value = "sno", required = false) String sno,
                                            @RequestParam(value = "sname", required = false) String sname) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("guard/accessInfo");
        // 查询该生的所有出入信息
        List<AccessInformation> informations = accessInformationService.findAccessInfoByStudent(sno, sname);
        mv.addObject("information", informations);
        if (informations.isEmpty()) {
            mv.addObject("waring", "无" + sname + "学生的出入信息！");
        }
        return mv;

    }

    /**
     * 查询所有出入信息
     * 只有管理员 和 防疫管理员 才可访问
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @GetMapping(value = "/allInformation")
    public ModelAndView findALl() {
        List<AccessInformation> accessInformations = accessInformationService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("guard/accessInfo");
        mv.addObject("information", accessInformations);
        if (accessInformations.isEmpty()) {
            mv.addObject("waring", "无任何学生的出入信息！");
            return mv;
        }
        return mv;
    }
}
