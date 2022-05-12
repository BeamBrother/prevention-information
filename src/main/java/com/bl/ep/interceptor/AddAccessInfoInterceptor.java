package com.bl.ep.interceptor;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.service.HealthCodeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @ClassName AddAccessInfoInterceptor
 * @Description 登记出入信息 拦截器
 * @Author 陈宝梁
 * @Date 2021/12/19 15:39
 * @Version 1.0
 **/
@Component
public class AddAccessInfoInterceptor implements HandlerInterceptor {
    @Resource
    private HealthCodeService healthCodeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sno = request.getParameter("sno");
        //查询该学生的健康码
        HealthCode healthCode = healthCodeService.findByStudentAndNo(sno);
        if (healthCode.gethCode() != 1) {//健康码为非绿码
            //获取上一个页面 链接
            String uri = request.getHeader("Referer");
            int i = uri.indexOf("?");
            if (i != -1) {
                uri = uri.substring(0, i);
            }
            String err = "此学生的健康码为非绿码，无法登记！";
            //重定向
            response.sendRedirect(uri + "?error=" + URLEncoder.encode(err, "UTF-8"));
            return false;
        }
        return true;
    }
}
