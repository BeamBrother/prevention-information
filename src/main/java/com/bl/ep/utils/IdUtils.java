package com.bl.ep.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @ClassName RandomUtils
 * @Description 生成 随机id号
 * @Author 陈宝梁
 * @Date 2021/11/15 22:42
 * @Version 1.0
 **/
public class IdUtils {
    private static String middle = "";

    static {
        middle = MathUtils.makeUpNewData(Math.abs(NetworkUtils.getHostIP().hashCode()) + "", 4) +   //4位IP地址hash
                MathUtils.makeUpNewData(NetworkUtils.getPid(), 4);                                 //4位PID进程hash
    }

    public static String getIncreaseIdByCurrentTimeMillis() {
        return System.currentTimeMillis() +                                             //时间戳-14位
                middle +                                                                 //标志-8位
                MathUtils.makeUpNewData(Thread.currentThread().hashCode() + "", 3) +       //3位线程标志
                MathUtils.randomDigitNumber(8);                                         //随机8位数
    }

    /**
     * 网络相关的处理类
     */
    private static class NetworkUtils {

        private static final String DEFAULT_HOST_IP = "10.10.10.10";

        /**
         * 获取当前进程的PID
         */
        public static String getPid() {
            return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        }

        /**
         * 获取当前进程的主机IP地址
         */
        public static String getHostIP() {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return DEFAULT_HOST_IP;
            }
        }

    }

    /**
     * 数据处理的相关类
     */
    private static class MathUtils {

        private static final String DEFAULT_DIGITS = "0";
        private static final String FIRST_DEFAULT_DIGITS = "1";

        /**
         * @param target 目标数字
         * @param length 需要补充到的位数, 补充默认数字[0], 第一位默认补充[1]
         * @return 补充后的结果
         */
        public static String makeUpNewData(String target, int length) {
            return makeUpNewData(target, length, DEFAULT_DIGITS);
        }

        /**
         * @param target 目标数字
         * @param length 需要补充到的位数
         * @param add    需要补充的数字, 补充默认数字[0], 第一位默认补充[1]
         * @return 补充后的结果
         */
        public static String makeUpNewData(String target, int length, String add) {
            if (target.startsWith("-")) target.replace("-", "");
            if (target.length() >= length) return target.substring(0, length);
            StringBuffer sb = new StringBuffer(FIRST_DEFAULT_DIGITS);
            for (int i = 0; i < length - (1 + target.length()); i++) {
                sb.append(add);
            }
            return sb.append(target).toString();
        }

        /**
         * 生产一个随机的指定位数的字符串数字
         *
         * @param length
         * @return
         */
        public static String randomDigitNumber(int length) {
            int start = Integer.parseInt(makeUpNewData("", length));//1000+8999=9999
            int end = Integer.parseInt(makeUpNewData("", length + 1)) - start;//9000
            return (int) (Math.random() * end) + start + "";
        }

    }

}
