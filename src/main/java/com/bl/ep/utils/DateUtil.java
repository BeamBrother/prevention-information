package com.bl.ep.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @ClassName DateUtil
 * @Description 日期格式类
 * @Author 陈宝梁
 * @Date 2021/11/19 15:26
 * @Version 1.0
 **/
public class DateUtil {
    public static int getAgeByBirth(Date birthDay) throws ParseException {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 将CST时间转化成GMT
     * @param date
     */
    public static String dateToString(Date date,String format) {
        //将CST时间转化成GMT
        DateFormat gmtDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return  gmtDateFormat.format(date);
    }

    /**
     * 将字符串转为日期
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static Date dateFormat(Date date,String format) throws ParseException {
        return  stringToDate(dateToString(date,format),format);
    }

}
