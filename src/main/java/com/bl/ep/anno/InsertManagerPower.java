package com.bl.ep.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName InsertManagerPower
 * @Description 自定义注解
 * @Author 陈宝梁
 * @Date 2021/11/20 14:53
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InsertManagerPower {
}
