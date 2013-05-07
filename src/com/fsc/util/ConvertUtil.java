package com.fsc.util;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:转化工具</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ConvertUtil {
    /**
     * 把字符串转化为GBK编码格式的字符串
     * @param String str
     * @return String
     */
    public static String getGBK(String str) {
        if (str == null) {
            return "";
        }

        String strReturn = "";

        try {
            byte[] buf = str.trim().getBytes("iso8859-1");
            strReturn = new String(buf, "GBK");
        } catch (Exception e) {
            strReturn = str;
        }

        return strReturn;
    }

    /**
     * 把字符串转化为iso8859-1编码格式的字符串
     * @param String str
     * @return String
     */
    public static String getISO(String str) {
        if (str == null) {
            return "";
        }

        String strReturn = "";

        try {
            byte[] buf = str.trim().getBytes("GBK");
            strReturn = new String(buf, "iso8859-1");
        } catch (Exception e) {
            strReturn = str;
        }

        return strReturn;
    }

    /**
     * 把字符串转为整型
     * @param String str
     * @return int
     */
    public static int strToInt(String str) {
        int i = 0;

        if ((str != null) && (str.length() != 0)) {
            try {
                i = Integer.parseInt(str.trim());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        return i;
    }

    /**
     * 把字符串转为Double
     * @param String str
     * @return double
     */
    public static double strToDouble(String str) {
        double d = 0;

        if ((str != null) && (str.length() != 0)) {
            try {
                d = Double.parseDouble(str.trim());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        return d;
    }

    /**
     * 把数组转化成以指定分隔符隔开的字符串
     * @param Object[] array
     * @param String separator
     * @return String
     */
    public static final String arrayToString(Object[] array, String separator) {
        String str = "";

        for (int i = 0; i < array.length; i++) {
            str += array[i];

            if (i != (array.length - 1)) {
                str += separator;
            }
        }

        return str;
    }
}
