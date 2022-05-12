package com.bl.ep.interceptor;

import com.bl.ep.bean.SecurityGuard;
import com.bl.ep.service.SecurityGuardService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @ClassName UserValidationAspect
 * @Description 用户验证
 * @Author 陈宝梁
 * @Date 2021/12/19 9:36
 * @Version 1.0
 **/
@Component
public class GuardValidationInterceptor implements HandlerInterceptor {
    @Resource
    private SecurityGuardService securityGuardService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String gId = request.getParameter("gno");
        SecurityGuard guard = securityGuardService.findByPrimaryKey(Long.valueOf(gId));
        if (guard.getOnDay()) {
            return true;
        }
        String err = "非当班人员";
        String uri = request.getHeader("Referer");
        int i = uri.indexOf("?");
        if (i != -1) {
            uri = uri.substring(0, i);
        }
        response.sendRedirect(uri + "?error="+ URLEncoder.encode(err,"UTF-8"));
        return false;
    }
}
