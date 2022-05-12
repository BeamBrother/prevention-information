package com.bl.ep.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName EPAccessDeniedHandler
 * @Description 自定义403处理
 * @Author 陈宝梁
 * @Date 2021/11/18 15:14
 * @Version 1.0
 **/
public class EPAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //状态
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);;



    }
}
