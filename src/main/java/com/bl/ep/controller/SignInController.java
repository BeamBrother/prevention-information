package com.bl.ep.controller;

import com.alibaba.fastjson.JSON;
import com.bl.ep.bean.HealthReportInfo;
import com.bl.ep.bean.SignIn;
import com.bl.ep.bean.Student;
import com.bl.ep.service.HealthReportInfoService;
import com.bl.ep.service.SignInService;
import com.bl.ep.service.StudentService;
import com.bl.ep.utils.Constant;
import com.bl.ep.utils.DateUtil;
import com.bl.ep.utils.IdUtils;
import com.bl.ep.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SignInController
 * @Description 签到功能
 * @Author 陈宝梁
 * @Date 2021/11/23 13:42
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/security")
public class SignInController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private StudentService studentService;
    @Resource
    private SignInService signInService;

    private static final String prefix = "sign_";

    /*
       用户签到
    */
    @PostMapping(produces = "application/json;charset=utf-8", value = "/sign")
    public ModelAndView userSign(@RequestParam("sno") String sno,
                        @RequestParam(value = "tw") String tw,
                        @RequestParam(value = "bd") String bd,
                        @RequestParam(value = "jc") Boolean jcs,
                        @RequestParam(value = "jc2") Boolean jc,
                        @RequestParam(value = "ss") Boolean cn,
                        @RequestParam(value = "add") String address) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/signED");
        //获取当前时间 设置日期格式
        String date = DateUtil.dateToString(new Date(), Constant.YYYY_DD_MM);
        // 设置key值
        String redisKey = prefix + sno + "_" + date;
        // key:value  过期时间
        Student student = studentService.findByAccount(sno);
        SignIn signIn = new SignIn(IdUtils.getIncreaseIdByCurrentTimeMillis(), tw, bd, jcs, jc, cn, address, DateUtil.dateFormat(new Date(), Constant.YYYY_DD_MM), sno, student.getUsername());
        //添加到数据库
        int i = signInService.addSignIn(signIn);
        if (i == 0) {
            mv.addObject("status", 1);
            mv.addObject("msg", "签到失败，请重试！");
        }else {
            mv.addObject("status", 2);
            mv.addObject("msg", "签到成功");
        }
        redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(signIn), TimeUtil.getNowToNextDaySeconds(), TimeUnit.SECONDS);

        return mv;
    }

    /*
    查看签到的人
     */
    @ResponseBody
    @GetMapping(produces = "application/json;charset=utf-8", value = "/find")
    public Map findSign(String sno) {
        Map result = new HashMap();
        Set keys = redisTemplate.keys(prefix + sno + "_*");
        if (keys.size() != 0){
            for (Object key : keys) {
                String value = redisTemplate.opsForValue().get(key.toString());
                result.put("value", value);
            }
            result.put("status",true);
        }else {
            result.put("status", false);
        }
        return result;
    }

}
