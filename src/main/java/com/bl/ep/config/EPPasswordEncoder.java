package com.bl.ep.config;

import com.bl.ep.utils.security.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName EPPasswordEncoder
 * @Description 自定义加密规则  security
 * @Author 陈宝梁
 * @Date 2021/11/17 19:05
 * @Version 1.0
 **/
public class EPPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utils.md5((String) charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5Utils.md5((String) charSequence));
    }
}
