package com.fsc.util;

import java.math.BigDecimal;

import java.text.NumberFormat;

import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:数学运算工具</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class MathUtil {
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    //这个类不能外部实例化
    private MathUtil() {
    }

    /**
     * 提供精确的加法运算
     *
     * @param double d1 被加数
     * @param double d2 加数
     * @return double 两个参数的和
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param double d1 被减数
     * @param double d2 减数
     * @return double 两个参数的差
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param double d1 被乘数
     * @param double d2 乘数
     * @return double 两个参数的积
     */
    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param double d1 被除数
     * @param double v2 除数
     * @return double 两个参数的商
     */
    public static double div(double d1, double d2) {
        return div(d1, d2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入
     *
     * @param double d1 被除数
     * @param double d2 除数
     * @param int scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double d1, double d2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param double d 需要四舍五入的数字
     * @param int scale 小数点后保留几位
     * @return double 四舍五入后的结果
     */
    public static double round(double d, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(Double.toString(d));
        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param Object number 需要四舍五入的数字
     * @param int scale 小数点后保留几位
     * @return Object 四舍五入后的结果
     */
    public static Object round(Object number, int scale) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(scale); //设置最大小数位   

        try {
            return (Object) numberFormat.format(number);
        } catch (IllegalArgumentException e) {
            return new Integer(0);
        }
    }

    /**
     * 根据掺入的参数，产生0-9之间的i位随机数
     *
     * @param int i
     * @return String
     */
    public static String getRandomNumber(int i) {
        if (i < 1) {
            i = 1;
        }

        String retStr = "";
        Date d = new Date();
        int value = 0;
        Random r = new Random(d.getTime());

        for (int j = 1; j <= i; j++) {
            value = Math.abs(r.nextInt());
            value = value % 9;
            retStr = retStr + value;
        }

        return retStr;
    }

    /**
     * 获取百分比
     *
     * @param double d1
     * @param double d2
     * @return  String
     */
    public static String percent(double d1, double d2) {
        String str;
        double d3 = d1 / d2;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        str = nf.format(d3);

        if (d2 == 0.0) {
            str = "0%";
        }

        return str;
    }

    /**
     * 判断是否数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();
    }
}
