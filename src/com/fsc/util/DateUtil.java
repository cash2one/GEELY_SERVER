package com.fsc.util;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:时间处理工具</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DateUtil {
	
    /**oracle的日期格式**/
    public static final String oraDateFormat = "'yyyy-MM-dd hh24:mi:ss'";
    /**
     * 获得当前时间（日期时间型）
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp getDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String strDateTime = simpleDateFormat.format(calendar.getTime());

        return (java.sql.Timestamp.valueOf(strDateTime));
    }

    /**
     * 获得当前时间（日期时间型）
     * @return String
     */
    public static String getDateTimeString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String strDateTime = simpleDateFormat.format(calendar.getTime());

        return strDateTime;
    }

    /**
     * 获得当前日期（日期型）
     * @return java.sql.Date
     */
    public static java.sql.Date getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(cal.getTime());

        return (java.sql.Date.valueOf(strDate));
    }

    /**
     * 获得当前日期（日期型）(yyyy-MM-dd)
     * @return String
     */
    public static String getDateString() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(cal.getTime());

        return strDate;
    }

    /**
     * 获得当前日期（日期型）(yyyyMMdd)
     * @return String
     */
    public static String getDateString2() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(cal.getTime());

        return strDate;
    }

    /**
     * 获得当前日期前一天日期（日期型）
     * @return java.sql.Date
     */
    public static java.sql.Date getPreviousDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, -1);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mDateTime = formatter.format(cal.getTime());

        return (java.sql.Date.valueOf(mDateTime));
    }

    /**
     * 获得当前日期的后一天日期(日期型)
     * @return
     */
    public static java.sql.Timestamp getNextDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, 1);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mDateTime = formatter.format(cal.getTime());

        java.sql.Date date = (java.sql.Date.valueOf(mDateTime));

        return java.sql.Timestamp.valueOf(date.toString() + " 09:00:00");
    }

    //    /**
    //     * 获得当前日期的后几天日期（日期时间型）
    //     * 
    //     * @return
    //     */
    //    public static java.sql.Timestamp getNextDateByDateTime(java.sql.Timestamp dateTime,int i_day) {
    //    	
    //    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //    	
    //        Calendar cal = Calendar.getInstance();
    //        
    //        cal.setTime(dateTime);
    //        
    //        cal.add(java.util.Calendar.DATE, i_day);
    //        
    //        String strDateTime = formatter.format(cal.getTime());
    //
    //        return (java.sql.Timestamp.valueOf(strDateTime));
    //        
    //    }

    /**
     * 获得当前日期的后几天日期（日期时间型）
     * @return
     */
    public static java.sql.Timestamp getNextDateByDateTime(
        java.sql.Timestamp dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        cal.setTime(dateTime);

        cal.add(java.util.Calendar.DATE, 1);

        String strDateTime = formatter.format(cal.getTime());

        return (java.sql.Timestamp.valueOf(strDateTime));
    }

    public static void main(String[] args) {
        System.out.println(getNextDate());
    }

    /**
     * 得到当前年月
     * @return String
     */
    public static String getThisYearAndMonth() {
        String dateString = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date currentDate = new Date();

        dateString = formatter.format(currentDate);

        return dateString;
    }

    /**
     * 得到当前年
     * @return String
     */
    public static String getThisYear() {
        String dateString = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date currentDate = new Date();

        dateString = formatter.format(currentDate);

        return dateString;
    }

    /**
     * 得到当前月
     * @return String
     */
    public static String getThisMonth() {
        String dateString = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date currentDate = new Date();

        dateString = formatter.format(currentDate);

        return dateString;
    }

    /**
     * 得到当前日
     * @return String
     */
    public static String getThisDay() {
        String dateString = "";

        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date currentDate = new Date();

        dateString = formatter.format(currentDate);

        return dateString;
    }

    /**
     * 取得本月份第一天，格式“YYYY-MM-DD”
     * @return String
     */
    public static String getFirstDateOfThisMonth() {
        String firstDateOfMonth = "";
        firstDateOfMonth = getThisYearAndMonth() + "-01";

        return firstDateOfMonth;
    }

    /**
     * 根据当前间时生成不同的问候语（欢迎词）
     * @return String
     */
    public static String getWelcome() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        String strTmp = simpleDateFormat.format(calendar.getTime());

        int intHour = 0;

        try {
            intHour = Integer.parseInt(strTmp);
        } catch (Exception e) {
            intHour = -1;
        }

        strTmp = "您好！";

        if ((intHour >= 0) && (intHour < 7)) {
            strTmp = "夜猫子，要注意身体哦！ ";
        } else if ((intHour >= 7) && (intHour < 10)) {
            strTmp = "早上好！ ";
        } else if ((intHour >= 10) && (intHour < 12)) {
            strTmp = "上午好！ ";
        } else if ((intHour >= 12) && (intHour < 14)) {
            strTmp = "中午好，注意休息！";
        } else if ((intHour >= 14) && (intHour < 18)) {
            strTmp = "祝您下午工作愉快！";
        } else if ((intHour >= 18) && (intHour < 22)) {
            strTmp = "加班辛苦了！";
        } else if ((intHour >= 22) && (intHour < 24)) {
            strTmp = "您应该休息了！";
        }

        return strTmp;
    }

    /**
     * 判断是否为有效的日期
     * @param String strDate
     * @return boolean true:有效的 false:无效的
     */
    public static boolean isValidDate(String strDate) {
        try {
            java.sql.Date.valueOf(strDate);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 判断是否为有效的日期时间
     * @param String strDateTime
     * @return boolean true:有效的 false:无效的
     */
    public static boolean isValidDateTime(String strDateTime) {
        try {
            java.sql.Timestamp.valueOf(strDateTime);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 将java.util.Data转换为长整型
     * @param date
     * @return
     */
    public static Long dateToLong(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);

        return c.getTimeInMillis();
    }

    /**
     * 返回上一年的年
     * @return
     */
    public static String getLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        Date lastYear = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        return formatter.format(lastYear);
    }

    /**
     * 返回上一月的的年月
     * @return
     */
    public static String getLastYearAndMonthFormatyyyyMM() {
        String dateString = "";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        Date lastYear = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        dateString = formatter.format(lastYear);

        return dateString;
    }

    /**
     * 返回上一年的年月以yyyyMM格式化
     * @return
     */
    public static String getThisYearAndMonthFormatyyyyMM() {
        String dateString = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date currentDate = new Date();

        dateString = formatter.format(currentDate);

        return dateString;
    }

    /**
         * 功能描述：返回年份
         *
         * @param date
         *            Date 日期
         * @return 返回年份
         */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月份
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     * @param Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getTimeInMillis();
    }

    /**
     * 功能描述：返回字符型日期
     * @param date 日期
     * @return 返回字符型日期 yyyy/MM/dd 格式
     */
    public static String getDate(Date date) {
        return formatDateByFormat(date, "yyyy/MM/dd");
    }

    /**
     * 功能描述：返回字符型时间
     * @param date 日期
     * @return 返回字符型时间 HH:mm:ss 格式
     */
    public static String getTime(Date date) {
        return formatDateByFormat(date, "HH:mm:ss");
    }

    /**
     * 功能描述：返回字符型日期时间
     * @param date 日期
     * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
     */
    public static String getDateTime(Date date) {
        return formatDateByFormat(date, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 功能描述：日期相加
     * @param date 日期
     * @param day 天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        long millis = getMillis(date) + (((long) day) * 24 * 3600 * 1000);
        calendar.setTimeInMillis(millis);

        return calendar.getTime();
    }

    /**
     * 功能描述：日期相减
     * @param date 日期
     * @param date1 日期
     * @return 返回相减后的日期
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 功能描述：取得指定月份的第一天
     * @param strdate 字符型日期
     * @return String yyyy-MM-dd 格式
     */
    public static String getMonthBegin(String strdate) {
        Date date = parseDate(strdate);

        return formatDateByFormat(date, "yyyy-MM") + "-01";
    }

    /**
     * 功能描述：取得指定月份的最后一天
     * @param strdate 字符型日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String getMonthEnd(String strdate) {
        Date date = parseDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        return formatDate(calendar.getTime());
    }

    /**
     * 功能描述：格式化日期
     * @param dateStr 字符型日期
     * @param format 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        Date date = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String dt = dateStr.replaceAll("-", "/");

            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length())
                            .replaceAll("[YyMmDdHhSs]", "0");
            }

            date = (Date) dateFormat.parse(dt);
        } catch (Exception e) {
        }

        return date;
    }

    /**
     * 功能描述：格式化日期
     * @param dateStr 字符型日期：YYYY-MM-DD 格式
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy/MM/dd");
    }

    /**
     * 功能描述：常用的格式化日期
     * @param date 日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 功能描述：以指定的格式来格式化日期
     * @param date 日期
     * @param format 格式
     * @return String 日期字符串
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";

        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }
}
