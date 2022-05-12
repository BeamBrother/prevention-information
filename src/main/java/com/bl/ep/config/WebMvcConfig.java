package com.bl.ep.config;

import com.bl.ep.interceptor.AddAccessInfoInterceptor;
import com.bl.ep.interceptor.GuardValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Description mvc 控制器
 * @Author 陈宝梁
 * @Date 2021/12/19 10:12
 * @Version 1.0
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private GuardValidationInterceptor guardValidationInterceptor;
    @Autowired
    private AddAccessInfoInterceptor addAccessInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = {"/login/userLogin","/","/css/**","/js/**","/img/**","favicon.ico"};
        registry.addInterceptor(guardValidationInterceptor).addPathPatterns("/access/insert","/detected/insertHI")
                .excludePathPatterns(patterns);
        registry.addInterceptor(addAccessInfoInterceptor).addPathPatterns("/access/insert")
                .excludePathPatterns(patterns);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login_user/login");
    }

    /**
     * 注册一个字符编码过滤器
     * spring 提供的
     * 注：只有当spring.http.encoding.enabled=false配置成false后，过滤器才起作用
     * @return
     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        //创建一个spring提供的过滤器
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        //强制编码
//        characterEncodingFilter.setForceEncoding(true);
//        characterEncodingFilter.setEncoding("UTF-8");
//
//        //filter注册bean
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(characterEncodingFilter);
//        //要过滤的路径
//        registrationBean.addUrlPatterns("/*");
//        return  registrationBean;
//    }
}
