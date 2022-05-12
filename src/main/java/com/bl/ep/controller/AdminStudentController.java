package com.bl.ep.controller;

import com.bl.ep.anno.InsertManagerPower;
import com.bl.ep.bean.AccessInformation;
import com.bl.ep.bean.Role;
import com.bl.ep.bean.Student;
import com.bl.ep.service.AccessInformationService;
import com.bl.ep.service.AdminStudentService;
import com.bl.ep.service.RoleService;
import com.bl.ep.service.StudentService;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.ExcelUtil;
import com.bl.ep.utils.security.MD5Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * @ClassName AdminStudentController
 * @Description 学生管理
 * @Author 陈宝梁
 * @Date 2021/11/26 22:32
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/admin")
public class AdminStudentController {
    @Resource
    private AccessInformationService accessInformationService;
    @Resource
    private AdminStudentService adminStudentService;
    @Resource
    private StudentService studentService;
    @Resource
    private RoleService roleService;

    /**
     * 导出数据为 excel
     * @param response  输出流
     * @param startTime  开始时间  非必要
     * @param endTime    结束时间  非必要
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_MG','ROLE_ADMIN')")
    @PostMapping(value = "/excelByAccess")
    public void excelByAccessInformation(HttpServletResponse response,
                                         @RequestParam(value = "startTime", defaultValue = "") String startTime,
                                         @RequestParam(value = "endTime", defaultValue = "") String endTime) throws Exception {
        List<AccessInformation> accessInformationList = null;
        //判断是否为默认值 ”“
        if (startTime.equals("") || endTime.equals("")) {
            accessInformationList = accessInformationService.findAll();
        }else {
            accessInformationList = accessInformationService.findByTimeAndId(DateUtil.stringToDate(startTime, Constant.YYYY_DD_MM_HH_MM_SS),DateUtil.stringToDate(endTime,Constant.YYYY_DD_MM_HH_MM_SS));
        }
        List collect = new ArrayList();
        for (AccessInformation information : accessInformationList) {
            Map<String, Object> map = new HashMap<>();
            map.put("stNo", information.getStudent().getNo());
            map.put("stName", information.getStudent().getUsername());
            if (information.getTurnover()) {
                map.put("turnover", "进入");
            } else {
                map.put("turnover", "离开");
            }
            map.put("time", DateUtil.dateToString(information.getTime(), Constant.YYYY_DD_MM_HH_MM_SS));
            if (information.getYn() == 1) {
                map.put("yn", "绿码");
            } else {
                map.put("yn", "非绿码");
            }
            map.put("gno", information.getSecurityGuard().getNo());
            map.put("gname", information.getSecurityGuard().getUsername());
            collect.add(map);
        }

        String[] title = {"学号", "姓名", "出入", "出入时间", "健康码状态", "检测人员工号", "检测人员姓名"};
        String[] dataArray = {"stNo", "stName", "turnover", "yn", "time", "gno", "gname"};
        Workbook wb = ExcelUtil.exportExcel(collect, title, dataArray);
        String fileName = "学生出入信息表" + DateUtil.dateToString(new Date(), "yyyy-MM-dd-HH-mm-ss") + ".xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");

        ServletOutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/addStu")
    public ModelAndView addStudent(@RequestParam(value = "sno")String sno,
                                   @RequestParam(value = "username")String username,
                                   @RequestParam(value = "birth")Date birthday,
                                   @RequestParam(value = "email")String email,
                                   @RequestParam(value = "password",defaultValue = "123456")String password,
                                   @RequestParam(value = "depart")String department,
                                   @RequestParam(value = "major")String profession){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/addStu");
        Student temp = studentService.findByAccount(sno);
        if (temp != null){
            mv.addObject("error","学号一存在！");
            return mv;
        }

        Role role = roleService.findByRoleStr(Constant.ROLE_STUDENT);
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(MD5Utils.md5(password));
        student.setBirthday(birthday);
        student.setDepartment(department);
        student.setMajor(profession);
        student.setEmail(email);
        student.setNo(sno);
        student.setRole(role);
        int i = adminStudentService.addStudent(student);
        if (i == 0){
            mv.addObject("error","添加失败！");
        }else {
            mv.addObject("success","添加成功！");
        }
        return mv;
    }
}
