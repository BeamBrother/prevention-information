package com.bl.ep.utils;

/**
 * @ClassName StrUtils
 * @Description 字符串工具类 判定字符串是否为空
 * @Author 陈宝梁
 * @Date 2021/11/15 17:29
 * @Version 1.0
 **/
public class StrUtils {
    public static boolean empty(String msg) {
        return msg != null && msg.length() > 0;
    }

    public static boolean empty(String... msg) {
        boolean res = true;
        for (String s : msg) {
            res = (s != null && s.length() > 0);
            if (!res) {
                break;
            }
        }
        return res;
    }
}
