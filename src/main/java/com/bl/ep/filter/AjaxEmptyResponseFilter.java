package com.bl.ep.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName AjaxEmptyResponseFilter
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/23 11:10
 * @Version 1.0
 **/
public class AjaxEmptyResponseFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        chain.doFilter(request, response);
        if (!response.isCommitted()) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            Writer writer = response.getWriter();
            writer.write("null");
            writer.close();
            response.flushBuffer();
        }
    }

}
