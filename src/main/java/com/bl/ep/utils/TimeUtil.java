package com.bl.ep.utils;

import java.util.Calendar;

/**
 * @ClassName TimeUtil
 * @Description redis 时间工具类
 * @Author 陈宝梁
 * @Date 2021/11/23 16:28
 * @Version 1.0
 **/
public class TimeUtil {
    /**
     * 计算第二天凌晨与当前时间的时间差秒数
     */
    public static Long getNowToNextDaySeconds() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

}
