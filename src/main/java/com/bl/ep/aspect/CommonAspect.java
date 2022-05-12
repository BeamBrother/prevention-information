package com.bl.ep.aspect;

import com.bl.ep.service.StudentService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName CommonAspect
 * @Description 公共切面类
 * @Author 陈宝梁
 * @Date 2021/12/19 15:18
 * @Version 1.0
 **/
@Aspect
@Component
public class CommonAspect {
    @Resource
    private StudentService studentService;

    @Pointcut("execution(* com.bl.ep.controller.*.*.addAccessInfo(..))")
    public void addAccessInfoPointcut(){}

}
