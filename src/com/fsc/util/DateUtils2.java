package com.fsc.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:时间日期处理工具类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DateUtils2 {
    public final static String FM_PATTERN_CN_MD_HM = "MM月dd日 HH:mm";
    public final static String FM_PATTERN_CN_MD_NO = "MM月dd日";
    public final static String FM_PATTERN_CN_YMD_HM = "yyyy年MM月dd日 HH:mm";
    public final static String FM_PATTERN_CN_YMD_NO = "yyyy年MM月dd日";
    public final static String FM_PATTERN_CN_YM_NO = "yyyy年MM月";
    public final static String FM_PATTERN_EN_MD_HM = "MM-dd HH:mm";
    public final static String FM_PATTERN_EN_MD_NO = "MM-dd";
    public final static String FM_PATTERN_EN_YMD_HM = "yyyy/MM/dd HH:mm";
    public final static String FM_PATTERN_EN_YMD_NO = "yyyy/MM/dd";
    public final static String FM_PATTERN_EN_YM_NO = "yyyy/MM";

    // 不能外部实例化
    private DateUtils2() {
    }

    /**
     * 取得当前日期
     * @return 当前日期
     */
    public static java.sql.Date getNowDate() {
        Calendar cal = Calendar.getInstance();

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * 取得当前日期，时间
     * @return 当前日期 时间
     */
    public static java.sql.Timestamp getNowTimestamp() {
        Calendar cal = Calendar.getInstance();

        return new java.sql.Timestamp(cal.getTimeInMillis());
    }

    /**
     * 当前日期，时间转换为长整型
     * @return 长整型数字
     */
    public static long getNowDateTimeLong() {
        Calendar cal = Calendar.getInstance();

        return cal.getTimeInMillis();
    }

    /**
     * 取得当前日期 时间 字符串格式
     * @return 格式化到分钟的日期 时间
     */
    public static String getNowDateTimeString() {
        Calendar cal = Calendar.getInstance();
        long millis = cal.getTimeInMillis();

        return formatDateLong(millis);
    }

    /**
     * 根据长整形日期时间数字 取得它的日期时间类型
     * @param millis 长整形日期 时间数字
     * @return 日期时间类型
     */
    public static Date getDate(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);

        return cal.getTime();
    }

    /**
     * 格式化输入的日期 时间
     * @param date 日期 时间
     * @return 日期
     */
    public static java.sql.Date getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * 把字符串形式的日期 转换为 date类型
     * @param dateString
     * @return 日期
     */
    public static java.sql.Date getDate(String dateString) {
        return java.sql.Date.valueOf(dateString);
    }

    /**
     * 根据长整型数字获取date
     * @param millis 长整型
     * @return date MM-dd HH:mm
     */
    public static String formatDateShort(long millis) {
        String pattern = "MM-dd HH:mm";
        String date = DateFormatUtils.format(millis, pattern);

        return date;
    }

    /**
     * 根据长整型数字获取date
     * @param millis 长整型
     * @return date yyyy-MM-dd HH:mm
     */
    public static String formatDateLong(long millis) {
        String pattern = "yyyy-MM-dd HH:mm";
        String date = DateFormatUtils.format(millis, pattern, Locale.CHINA);

        return date;
    }

    // 格式化日期时间 yyyy年MM月dd日 HH时mm分ss秒
    public static String formatDate1(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy年MM月dd日 HH时mm分ss秒");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    // 格式化日期时间 yyyy年MM月dd日
    public static String formatDate2(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    //    /**
    //     * 根据开始时间和结束时间返回相应的时间段字符串，如果开始时间和结束时间在同一天，
    //     * 则返回的格式为“X点X分-X点X分”，如果不在同一天，返回的格式为“X月X日-X月X日”
    //     *
    //     * @param startTime
    //     *            开始时间
    //     *
    //     * @param endTime
    //     *            结束时间
    //     *
    //     * @return 返回的时间段字符串
    //     */
    //    public static String formatDate2(Timestamp timestamp) {
    //    	
    //    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
    //        String strDate = formatter.format(timestamp.getDate());
    //        return strDate;    	
    //    }

    // 格式化日期时间 yyyy-MM-dd HH:mm:ss
    public static String formatDate3(Date myDate) {
        String strDate = null;

        if (myDate == null) {
            strDate = null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = formatter.format(myDate);

        return strDate;
    }

    // 格式化日期时间 yyyy-MM-dd
    public static String formatDate4(Date myDate) {
        String strDate = null;

        if (myDate == null) {
            strDate = null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        strDate = formatter.format(myDate);

        return strDate;
    }

    // 格式化日期时间 yyyy/MM/dd
    public static String formatDate5(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    // 格式化日期时间 MM-dd HH:mm
    public static String formatDate6(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    // 格式化日期时间 yyyyMMdd HH:mm:ss
    public static String formatDate7(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    // 格式化日期时间 yyyyMMddHHmmss
    public static String formatDate8(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    //格式化日期时间 yyyyMMddHHmmss
    public static String formatDate9(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(myDate);

        return strDate;
    }

    /**
     * 将java.sql.Date类对象转换为java.sql.Timestamp类对象， 时间部分为"00:00:00.000"
     * @param date java.sql.Date 要转换的Date型对象
     * @return java.sql.Timestamp 转换后的Timestamp型对象
     */
    public static Timestamp convertDateToTimestampMin(java.sql.Date date) {
        return Timestamp.valueOf(date.toString() + " 00:00:00.000");
    }

    /**
     * 将java.sql.Date类对象转换为java.sql.Timestamp类对象， 时间部分为"23:59:59.999"
     * @param date java.sql.Date 要转换的Date型对象
     * @return java.sql.Timestamp 转换后的Timestamp型对象
     */
    public static Timestamp convertDateToTimestampMax(java.sql.Date date) {
        return Timestamp.valueOf(date.toString() + " 23:59:59.999");
    }

    /**
     * 用于获取指定日期该月的所有日期
     * @param date java.sql.Date 要获取的月日期列表的指定日期
     * @return Date[] java.sql.Date 返回的日期列表
     */
    public static java.sql.Date[] getMonthDays(java.sql.Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);

        int today = cale.get(Calendar.DAY_OF_MONTH);
        int days = cale.getActualMaximum(Calendar.DAY_OF_MONTH);
        long millis = cale.getTimeInMillis();

        java.sql.Date[] dates = new java.sql.Date[days];

        for (int i = 1; i <= days; i++) {
            long sub = (today - i) * 24 * 60 * 60 * 1000L;
            dates[i - 1] = new java.sql.Date(millis - sub);
        }

        cale = null;

        return dates;
    }

    /**
     * 用于获取指定日期该周的所有日期
     * @param date java.sql.Date 要获取的周日期列表的指定日期
     * @return Date[] java.sql.Date 返回的日期列表
     */
    public static java.sql.Date[] getWeekDays(java.sql.Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.setFirstDayOfWeek(Calendar.SUNDAY);

        int days = 7;
        int today = cale.get(Calendar.DAY_OF_WEEK);
        long millis = cale.getTimeInMillis();

        java.sql.Date[] dates = new java.sql.Date[days];

        for (int i = 1; i <= days; i++) {
            long sub = (today - i) * 24 * 60 * 60 * 1000L;
            dates[i - 1] = new java.sql.Date(millis - sub);
        }

        cale = null;

        return dates;
    }

    /**
     * 根据开始时间和结束时间返回相应的时间段字符串，如果开始时间和结束时间在同一天，
     * 则返回的格式为“X点X分-X点X分”，如果不在同一天，返回的格式为“X月X日-X月X日”
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回的时间段字符串
     */
    public static String getTimeSlice(Timestamp startTime, Timestamp endTime) {
        String rtn = "";

        Calendar caleStart = Calendar.getInstance();
        Calendar caleEnd = Calendar.getInstance();
        caleStart.setTimeInMillis(startTime.getTime());
        caleEnd.setTimeInMillis(endTime.getTime());

        String dayStart = caleStart.get(Calendar.YEAR) + "年" +
            (caleStart.get(Calendar.MONTH) + 1) + "月" +
            caleStart.get(Calendar.DAY_OF_MONTH) + "日";
        String dayEnd = caleEnd.get(Calendar.YEAR) + "年" +
            (caleEnd.get(Calendar.MONTH) + 1) + "月" +
            caleEnd.get(Calendar.DAY_OF_MONTH) + "日";

        if (dayStart.equals(dayEnd)) {
            //同一天
            rtn = caleStart.get(Calendar.HOUR_OF_DAY) + "点" +
                caleStart.get(Calendar.MINUTE) + "分-" +
                caleEnd.get(Calendar.HOUR_OF_DAY) + "点" +
                caleEnd.get(Calendar.MINUTE) + "分";
        } else {
            //不在同一天
            rtn = (caleStart.get(Calendar.MONTH) + 1) + "月" +
                caleStart.get(Calendar.DAY_OF_MONTH) + "日" + "-" +
                (caleEnd.get(Calendar.MONTH) + 1) + "月" +
                caleEnd.get(Calendar.DAY_OF_MONTH) + "日";
        }

        caleStart = null;
        caleEnd = null;

        return rtn;
    }

    /**
     * 根据日期获得日期星期几格式的字符串，如“2004-07-28 星期三”
     * @param date 指定的日期
     * @return 返回的日期星期几格式的字符串
     */
    public static String getDayWeek(java.sql.Date date) {
        String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.setFirstDayOfWeek(Calendar.SUNDAY);

        return date.toString() + " " +
        days[cale.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 根据日期获得日期星期几格式的字符串，如“星期三”
     * @param strDate   指定的日期()
     * @return 返回的日期星期几格式的字符串
     */
    public static String getDayWeek(String strDate) {
        SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = formatYMD.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.setFirstDayOfWeek(Calendar.SUNDAY);

        return days[cale.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 根据日期获得日期星期数,礼拜天为0，礼拜一为1
     * @param date 指定的日期
     * @return 返回的日期的星期数
     */
    public static int getDayOfWeek(java.util.Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.setFirstDayOfWeek(Calendar.SUNDAY);

        return cale.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 闰年判断算法
     * @param year
     * @return
     */
    private static boolean isLeapyear(int year) {
        boolean isLeapyear = false;

        //闰年算法
        if ((((year % 4) == 0) && ((year % 100) != 0)) || ((year % 400) == 0)) {
            isLeapyear = true;
        }

        return isLeapyear;
    }

    /**
     * 根据年和月返回 该月的天数
     * @param y
     * @param m
     * @return
     */
    public static int getDayOfMonth(Date date) {
        String strDate = formatDate4(date);
        int y;
        int m;

        try {
            y = Integer.parseInt(strDate.substring(0, 4));
            m = Integer.parseInt(strDate.substring(5, 7));
        } catch (Exception e) {
            return 0;
        }

        int day_no = 30;

        switch (m) {
        case 1:
            day_no = 31;

            break;

        case 3:
            day_no = 31;

            break;

        case 5:
            day_no = 31;

            break;

        case 7:
            day_no = 31;

            break;

        case 8:
            day_no = 31;

            break;

        case 10:
            day_no = 31;

            break;

        case 12:
            day_no = 31;

            break;

        case 4:
            day_no = 30;

            break;

        case 6:
            day_no = 30;

            break;

        case 9:
            day_no = 30;

            break;

        case 11:
            day_no = 30;

            break;

        case 2:

            if (isLeapyear(y)) {
                day_no = 29;

                break;
            } else {
                day_no = 28;

                break;
            }
        }

        return day_no;
    }

    /**
     * 获得指定日期所在月最小时间，时间部分为“00:00:00.000” 例如：param:2004-08-20 return:2004-08-01
     * 00.00.00.000
     * @param date 指定的日期
     * @return 指定日期所在月的最小时间
     */
    public static Timestamp getMinDayInMonth(java.sql.Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH,
            cale.getActualMinimum(Calendar.DAY_OF_MONTH));

        java.sql.Date newDate = new java.sql.Date(cale.getTimeInMillis());

        cale = null;

        return Timestamp.valueOf(newDate.toString() + " 00:00:00.000");
    }

    /**
     * 获得指定日期所在月的最大时间，时间部分为“23.59.59.999” 例如：param:2004-08-20,return:2004-08-31
     * 23.59.59.999
     * @param date
     * @return
     */
    public static Timestamp getMaxDayInMonth(java.sql.Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH,
            cale.getActualMaximum(Calendar.DAY_OF_MONTH));

        java.sql.Date newDate = new java.sql.Date(cale.getTimeInMillis());

        cale = null;

        return Timestamp.valueOf(newDate.toString() + " 23:59:59.999");
    }

    //得到当前年-月
    public static String getThisYearAndMonth() {
        String dateString = "";

        try {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                    "yyyy-MM");
            java.util.Date currentTime_1 = new java.util.Date();

            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {
        }

        return dateString;
    }

    //  得到当前年
    public static String getThisYear() {
        String dateString = "";

        try {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                    "yyyy");
            java.util.Date currentTime_1 = new java.util.Date();
            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {
        }

        return dateString;
    }

    //  得到当前月
    public static String getThisMonth() {
        String dateString = "";

        try {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                    "MM");
            java.util.Date currentTime_1 = new java.util.Date();
            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {
        }

        return dateString;
    }

    //  得到当前日
    public static String getThisDay() {
        String dateString = "";

        try {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                    "dd");
            java.util.Date currentTime_1 = new java.util.Date();
            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {
        }

        return dateString;
    }

    //  得到当前日
    public static String getThisDate() {
        String dateString = "";

        try {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            java.util.Date currentTime_1 = new java.util.Date();
            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {
        }

        return dateString;
    }

    // 得到当前季度
    public static String getThisQuarter() {
        String quarter = "";
        String month = getThisMonth();

        if (month.equals("01") || month.equals("02") || month.equals("03")) {
            quarter = "一季度";
        } else if (month.equals("04") || month.equals("05") ||
                month.equals("06")) {
            quarter = "二季度";
        } else if (month.equals("07") || month.equals("08") ||
                month.equals("09")) {
            quarter = "三季度";
        } else {
            quarter = "四季度";
        }

        return quarter;
    }

    /**
     * 通用日期格式转换函数，将日期转换为想要的格式
     * 转换错误则返回空字符串
     * @param aDate 要转换的日期
     * @param format 日期字符串
     * @return
     */
    public static String formatUtilDate(java.util.Date aDate, String format) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat(format);

            return myFmt.format(aDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期格式化,将日期装换成yyyy.MM.dd的格式
     * 转换错误则返回空字符串
     * @param adate 要转换的日期
     * @return String 日期字符串
     */
    public static String formatUtilDateUsingDot(java.util.Date adate) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy.MM.dd");

            return myFmt.format(adate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期格式化,将日期装换成yyyy-MM-dd的格式
     * 转换错误则返回空字符串
     * @param adate 要转换的日期
     * @return String 日期字符串
     */
    public static String formatUtilDateUsingLine(java.util.Date adate) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");

            return myFmt.format(adate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期格式化,将日期装换成yyyy年MM月dd日的格式
     * 转换错误则返回空字符串
     * @param adate 要转换的日期
     * @return String 日期字符串
     */
    public static String formatUtilDateUsingChinese(java.util.Date adate) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日");

            return myFmt.format(adate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * 日期格式化,将日期装换成yyyyMMdd的格式
    * 转换错误则返回空字符串
    * @param adate 要转换的日期
    * @return String 日期字符串
    */
    public static String formatUtilDatetimeUsingBlank(java.util.Date adate) {
        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");

            return myFmt.format(adate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串转化为可以存到数据库的时间类型，适用于页面上日期的输入
     * 几种样式(yyyy-MM-dd,yyyy年MM月dd日,yyyy.MM.dd)可以成功转换
     * @param strDate 要转换的字符串
     * @return java.sql.Date 数据库时间
     */
    public static java.sql.Date parseStringToSqlDate(String strDate) {
        boolean hasGetDate = false;

        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date temp1 = myFmt.parse(strDate);
            java.sql.Date result1 = new java.sql.Date(temp1.getTime());
            hasGetDate = true;

            return result1;
        } catch (Exception e) {
        }

        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日");
            java.util.Date temp2 = myFmt.parse(strDate);
            java.sql.Date result2 = new java.sql.Date(temp2.getTime());
            hasGetDate = true;

            return result2;
        } catch (Exception e) {
        }

        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy.MM.dd");
            java.util.Date temp3 = myFmt.parse(strDate);
            java.sql.Date result3 = new java.sql.Date(temp3.getTime());
            hasGetDate = true;

            return result3;
        } catch (Exception e) {
        }

        return null;
    }

    /**
     * 获取格式为yyyy-MM-dd的日期与当前日期之间的天数
     * @param strDate
     * @return
     */
    public static long getDateNumToNow(String strDate) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        long day = 0;

        if ((strDate != null) && !strDate.equals("")) {
            try {
                date = myFormatter.parse(formatDate4(new Date()));
                mydate = myFormatter.parse(strDate);
            } catch (ParseException pe) {
            }

            day = (mydate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
        }

        return day;
    }

    /**
     * 取两日期相隔天数
     * @param dateBegin
     * @param dateEnd
     * @return
     */
    public static Integer getDateNumFromDateBeginTODateEnd(Date dateBegin,
        Date dateEnd) {
        Integer day = 0;
        String date1 = DateUtils2.formatDate4(dateBegin);
        String date2 = DateUtils2.formatDate4(dateEnd);
        Date date = null;
        Date myDate = null;

        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");

        if (((date1 != null) && !"".equals(date1)) &&
                ((date2 != null) && !"".equals(date2))) {
            try {
                date = myFormatter.parse(date1);
                myDate = myFormatter.parse(date2);
            } catch (ParseException pe) {
            }

            long dayNums = (myDate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
            day = Integer.valueOf(String.valueOf(dayNums));
        }

        return day;
    }

    public static String modifyDate(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);

        Date newdate = cal.getTime();
        String nowdate = formatDate4(newdate);

        return nowdate;
    }

    public static Date parseStringToUtilDate(String strDate) {
        Date result = null;

        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
            result = myFmt.parse(strDate);
        } catch (Exception e) {
        }

        return result;
    }

    public static Date parseStringToUtilDate1(String strDate) {
        Date result = null;

        try {
            SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = myFmt.parse(strDate);
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * 根据月分取得季度
     * @param month
     * @return
     */
    public static int getQuanter(int month) {
        int ret = 1;

        switch (month) {
        case 1:
            ret = 1;

            break;

        case 2:
            ret = 1;

            break;

        case 3:
            ret = 1;

            break;

        case 4:
            ret = 2;

            break;

        case 5:
            ret = 2;

            break;

        case 6:
            ret = 2;

            break;

        case 7:
            ret = 3;

            break;

        case 8:
            ret = 3;

            break;

        case 9:
            ret = 3;

            break;

        case 10:
            ret = 4;

            break;

        case 11:
            ret = 4;

            break;

        case 12:
            ret = 4;

            break;
        }

        return ret;
    }

    public static String getFirstMonth(int quarter) {
        String ret = "01";

        switch (quarter) {
        case 1:
            ret = "01";

            break;

        case 2:
            ret = "04";

            break;

        case 3:
            ret = "07";

            break;

        case 4:
            ret = "10";

            break;
        }

        return ret;
    }

    //  取得本月份第一天，格式“YYYY-MM-DD”
    public static String getFirstDayThisMonth() {
        String monthbegin = "";
        monthbegin = DateUtils2.getThisYearAndMonth() + "-01";

        return monthbegin;
    }

    //  取得本季度第一天，格式“YYYY-MM-DD”
    public static String getFirstDayThisQuarter() {
        String quarbegin = "";
        int month = Integer.parseInt(DateUtils2.getThisMonth());
        System.out.println("****************" +
            Integer.parseInt(DateUtils2.getThisMonth()));
        quarbegin = DateUtils2.getThisYear() + "-" +
            DateUtils2.getFirstMonth(DateUtils2.getQuanter(month)) + "-01";

        return quarbegin;
    }

    //取得本年度第一天，格式“YYYY-MM-DD”
    public static String getFirstDayThisYear() {
        String yearbegin = "";
        yearbegin = getThisYear() + "-01-01";

        return yearbegin;
    }

    public static Timestamp getTimestamp(String year, String month, String day) {
        return getTimestamp(year, month, day, "00", "00", "00");
    }

    public static Timestamp getTimestamp(String year, String month, String day,
        String hour, String i, String s) {
        return setTimestamp(year + "-" + month + "-" + day + " " + hour + ":" +
            i + ":" + s);
    }

    public static Timestamp setTimestamp(String Str) {
        Timestamp time = null;

        try {
            time = java.sql.Timestamp.valueOf(Str);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

        return time;
    }

    //两个日期字符串相加
    //dateTime = 当前时间 yyyy年MM月dd日 HH时mm分ss秒 ,attemperTime = 间隔时间 (秒)
    public static String addToDateTime(java.sql.Timestamp dateTime,
        int attemperTime) {
        int second = 0;

        if ((dateTime != null) && !dateTime.equals("")) {
            second = dateTime.getSeconds() + attemperTime;

            dateTime.setSeconds(second);
        }

        return dateTime.toString();
    }

    /**
     * 在当前日期时间上增加秒
     * @param second
     * @return 增加秒后的日期时间
     */
    public static String addSecond(int second) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        calendar.add(Calendar.SECOND, second);

        String strDateTime = simpleDateFormat.format(calendar.getTime());

        return strDateTime;
    }

    /**
     * 在当前日期时间上增加分钟
     * @param second
     * @return 增加秒后的日期时间
     */
    public static String addMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        calendar.add(Calendar.MINUTE, minute);

        String strDateTime = simpleDateFormat.format(calendar.getTime());

        return strDateTime;
    }

    /**
     * 在当前日期时间上增加分钟
     * @param minute
     * @return 增加分钟后的日期时间
     */
    public static String addMinuteByDateTime(Date dateTime, int minute) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        cal.setTime(dateTime);

        cal.add(Calendar.MINUTE, minute);

        String strDateTime = simpleDateFormat.format(new java.sql.Date(
                    cal.getTimeInMillis()));

        return strDateTime;
    }

    /**
     * 在当前日期时间上增加月份
     * @param amount 增加数 (如果是减月份, 则amount 为负数)
     * @return 月份
     */
    public static String addMonth(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, amount);

        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        String strMonth = formatMonth.format(cal.getTime());

        return strMonth;
    }

    //在日期上增加分钟
    //dateTime = yyyy-MM-dd HH:mm:ss ,attemperTime = 间隔时间 (分)
    public static String addMinuteToDateTime(Date dateTime, int attemperTime) {
        System.out.println("时间" + dateTime);
        System.out.println("分钟" + attemperTime);

        int minute = 0;

        if ((dateTime != null) && !dateTime.equals("")) {
            minute = dateTime.getMinutes() + attemperTime;

            dateTime.setMinutes(minute);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String strDateTime = simpleDateFormat.format(dateTime);

        return strDateTime;
    }

    /**
     * 根据指定的年月计算该月的工作天数（星期一到星期五，不包括设置的非工作日）
     * @param year
     * @param month
     * @return
     */
    public static int getWorkdayNum(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, 1);

        int maxDays = cal.getActualMaximum(Calendar.DATE);
        int workDayNum = 0; //该月工作日天数
        int dayOfWeek = 0; //星期几（星期天为0，星期一为1）

        //判断该月第一天是否为工作日
        dayOfWeek = getDayOfWeek(new java.sql.Date(cal.getTimeInMillis()));

        if ((dayOfWeek != 6) && (dayOfWeek != 0)) //不为星期六和星期天
         {
            workDayNum = 1;
        }

        for (int i = 1; i < maxDays; i++) {
            cal.add(Calendar.DATE, 1);

            dayOfWeek = getDayOfWeek(new java.sql.Date(cal.getTimeInMillis())); //星期几

            if ((dayOfWeek != 6) && (dayOfWeek != 0)) //不为星期六和星期天
             {
                workDayNum++;
            }
        }

        return workDayNum;
    }

    /**
     * 用于获取指定日期前一周的所有日期
     * @param date java.sql.Date 要获取的周日期列表的指定日期
     * @return Date[] java.sql.Date 返回的日期列表
     */
    public static java.sql.Date[] getLastWeekDays(java.sql.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int dayWeek = Calendar.DAY_OF_WEEK;
        cal.add(Calendar.DATE, -dayWeek);

        return getWeekDays(new java.sql.Date(cal.getTimeInMillis()));
    }

    /**
    * 取得指定日期是多少周
    * @param date 指定日期
    * @return
    */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
    * 得到某一年周的总数
    * @param year 指定年度
    * @return
    */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    /**取得指定周的开始日期(以星期一为每周的开始)
     * @param year 指定年份
     * @param weekNo 指定周次(该年的第几周)
     * @return java.sql.Date型日期
     */
    public static java.sql.Date getStartDate(int year, int weekNo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**取得指定周的结束日期(以星期日为每周的结束)
     * @param year 指定年份
     * @param weekNo 指定周次(该年的第几周)
     * @return java.sql.Date型日期
     */
    public static java.sql.Date getEndDate(int year, int weekNo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**取得指定年份的所有周的开始日期和结束日期(以周日为每周的开始,周六为每周的结束,并且对跨年和跨月的周进行分隔)
     * @param year 指定年份
     * @return Object[][3] 包括周次,周起始时间,周结束时间
     */
    public static Object[][] getStartAndEndWeekDate(int year) {
        int count = getMaxWeekNumOfYear(year); //得到总周数

        List list1 = new ArrayList(); //保存每周开始日期
        List list2 = new ArrayList(); //保存每周结束日期
        List list3 = new ArrayList(); //保存周开始时间,当周跨月或跨年时,分上半周和下半周,保存上半周开始时间和下半周开始时间
        List list4 = new ArrayList(); //保存周结束日期,当周跨月或跨年时,分上半周和下半周,保存上半周结束时间和下半周结束时间
        List list5 = new ArrayList(); //保存周次(一年内第几周)

        //得到一年内每周的开始时间和结束时间
        for (int i = 1; i <= (count + 1); i++) {
            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = Calendar.getInstance();
            calEnd.set(Calendar.YEAR, year);
            calStart.set(Calendar.YEAR, year);
            calStart.set(Calendar.WEEK_OF_YEAR, i);
            calStart.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calEnd.set(Calendar.WEEK_OF_YEAR, i);
            calEnd.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            list1.add(calStart);
            list2.add(calEnd);
        }

        //对每周的开始时间和结束时间做处理,把跨年和跨月的周分为上半周和下半周
        int weekNo = 1;

        for (int i = 0; i < (count + 1); i++) {
            Calendar cal1 = (Calendar) list1.get(i);
            Calendar cal2 = (Calendar) list2.get(i);
            Calendar cal3 = Calendar.getInstance();

            if (cal1.get(Calendar.YEAR) < year) //对每年的第一周进行处理
             {
                cal3 = cal1;
                cal3.set(year, Calendar.JANUARY, 1);
                list3.add(new java.sql.Date(cal3.getTimeInMillis()));
                list4.add(new java.sql.Date(cal2.getTimeInMillis()));

                list5.add(new Integer(weekNo));
                weekNo++;
            } else if (cal1.get(Calendar.YEAR) > year) //对每年最后一周进行处理
             {
                break;
            } else if ((cal2.get(Calendar.MONTH) == cal1.get(Calendar.MONTH))) //保存不跨月的周
             {
                list3.add(new java.sql.Date(cal1.getTimeInMillis()));
                list4.add(new java.sql.Date(cal2.getTimeInMillis()));
                list5.add(new Integer(weekNo));
                weekNo++;
            }

            if (cal2.get(Calendar.YEAR) > year) //当最后一周跨年时,周结束时间为当年最后一天
             {
                list3.add(new java.sql.Date(cal1.getTimeInMillis()));
                cal3.set(year, Calendar.DECEMBER, 31);
                list4.add(new java.sql.Date(cal3.getTimeInMillis()));

                list5.add(new Integer(weekNo));
                weekNo++;
            }

            if (cal2.get(Calendar.MONTH) > cal1.get(Calendar.MONTH)) //跨月的周分为上下两个半周
             {
                //上半周的起止时间
                list3.add(new java.sql.Date(cal1.getTimeInMillis()));
                cal3.set(year, cal1.get(Calendar.MONTH),
                    cal1.getActualMaximum(Calendar.DATE));
                list4.add(new java.sql.Date(cal3.getTimeInMillis()));

                list5.add(new Integer(weekNo));

                //下半周的起止时间
                cal3.set(year, cal2.get(Calendar.MONTH), 1);
                list3.add(new java.sql.Date(cal3.getTimeInMillis()));
                list4.add(new java.sql.Date(cal2.getTimeInMillis()));

                list5.add(new Integer(weekNo));
                weekNo++;
            }
        }

        Object[][] weekDayDate = new Object[list3.size()][3];

        for (int i = 0; i < list3.size(); i++) {
            if (list3.size() == list4.size()) {
                weekDayDate[i][0] = list5.get(i);
                weekDayDate[i][1] = list3.get(i);
                weekDayDate[i][2] = list4.get(i);
            }
        }

        return weekDayDate;
    }

    /**取得指定日期过NUM天后的日期
     * @param date 指定日期
     * @param num  天数(为正数时表明为指定日期之后多少天,为负数则表示为指定日期之前多少天)
     * @return 日期
     */
    public static java.sql.Date getDayDate(java.sql.Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**取得指定的日期所在月的最后一天的日期
     * @param date 指定日期
     * @return 指定日期所在月的最后一天日期
     */
    public static java.sql.Date getLastDayMonth(java.sql.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.getActualMaximum(Calendar.DATE));

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * 取得指定年月所在月的第一天的日期
     * @param int year
     * @param int month
     * @return java.sql.Date
     */
    public static java.sql.Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * 取得指定年月所在月的最后一天的日期
     * @param int year
     * @param int month
     * @return java.sql.Date
     */
    public static java.sql.Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.getActualMaximum(Calendar.DATE));

        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**取得指定年月所在月的第一天和最后一天的日期
     * @param year
     * @param month
     * @return
     */
    public static java.sql.Date[] getFirstAndLastDayMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        java.sql.Date[] date = new java.sql.Date[2];
        cal.set(year, month - 1, 1);
        date[0] = new java.sql.Date(cal.getTimeInMillis());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.getActualMaximum(Calendar.DATE));
        date[1] = new java.sql.Date(cal.getTimeInMillis());

        return date;
    }

    /**取得指定年所在月的第一天和最后一天的日期
     * @param year
     * @return
     */
    public static java.sql.Date[] getFirstAndLastDayYear(int year) {
        Calendar cal = Calendar.getInstance();
        java.sql.Date[] date = new java.sql.Date[2];
        cal.set(year, 0, 1);
        date[0] = new java.sql.Date(cal.getTimeInMillis());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.getActualMaximum(Calendar.DATE));
        date[1] = new java.sql.Date(cal.getTimeInMillis());

        return date;
    }

    /**
     * 取得指定年份的所有周的开始日期和结束日期(以周日为每周的开始,周六为每周的结束,并且对跨年和跨月的周进行分隔,月开始时间和结束时间由用户自定义)
     * @param year 指定年份
     * @param monthStart 指定月开始日期
     * @param monthEnd 指定月结束日期
     * @return Object[][3] 包括周次,周起始时间,周结束时间
     */
    public static Object[][] getStartAndEndWeekDate(int year, int monthStart,
        int monthEnd) {
        java.sql.Date[] date1 = new java.sql.Date[2];
        java.sql.Date[] date2 = new java.sql.Date[2];

        date1 = getStartAndEndDateOfLogicMonth(year, 1, monthStart, monthEnd);
        date2 = getStartAndEndDateOfLogicMonth(year, 12, monthStart, monthEnd);

        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();

        calStart.setTime(date1[0]);
        calEnd.setTime(date2[1]);

        List list1 = new ArrayList(); //保存每周开始日期
        List list2 = new ArrayList(); //保存每周结束日期
        List list3 = new ArrayList(); //保存周开始时间,当周跨月或跨年时,分上半周和下半周,保存上半周开始时间和下半周开始时间
        List list4 = new ArrayList(); //保存周结束日期,当周跨月或跨年时,分上半周和下半周,保存上半周结束时间和下半周结束时间
        List list5 = new ArrayList(); //保存周次(一年内第几周)

        //得到一年内每周的开始时间和结束时间
        for (int i = 0; calEnd.after(calStart) || calStart.equals(calEnd);
                calStart.add(Calendar.DATE, 1), i++) {
            int weekNo = calStart.get(Calendar.DAY_OF_WEEK);

            if ((weekNo != 1) && (i == 0)) {
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.setTimeInMillis(calStart.getTimeInMillis());
                list1.add(cal1);
                calStart.add(Calendar.DATE, 7 - weekNo);
                cal2.setTimeInMillis(calStart.getTimeInMillis());
                list2.add(cal2);
            }

            if (weekNo == 1) {
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.setTimeInMillis(calStart.getTimeInMillis());
                list1.add(cal1);
                calStart.add(Calendar.DATE, 6);

                if (calStart.after(calEnd)) {
                    cal2.setTimeInMillis(calEnd.getTimeInMillis());
                } else {
                    cal2.setTimeInMillis(calStart.getTimeInMillis());
                }

                list2.add(cal2);
            }
        }

        //对每周的开始时间和结束时间做处理,把跨年和跨月的周分为上半周和下半周
        int weekNo = 1; //周次

        for (int i = 0; i < list1.size(); i++) {
            Calendar cal1 = (Calendar) list1.get(i);
            Calendar cal2 = (Calendar) list2.get(i);
            Calendar cal3 = Calendar.getInstance();

            if (((cal1.get(Calendar.DATE) < monthStart) &&
                    (cal2.get(Calendar.DATE) >= monthStart)) ||
                    (((cal1.get(Calendar.DATE) < monthStart) ||
                    (cal2.get(Calendar.DATE) >= monthStart)) &&
                    (cal2.get(Calendar.MONTH) > cal1.get(Calendar.MONTH)))) //跨月的周分为上下两个半周
             {
                //上半周的起止时间
                list3.add(new java.sql.Date(cal1.getTimeInMillis()));

                if ((cal1.get(Calendar.MONTH) == 1) &&
                        (monthStart > cal1.getActualMaximum(Calendar.DATE))) //处理跨2月的周
                 {
                    cal3.set(year, cal1.get(Calendar.MONTH),
                        cal1.getActualMaximum(Calendar.DATE));
                } else {
                    if (cal2.get(Calendar.DATE) >= monthStart) {
                        cal3.set(year, cal2.get(Calendar.MONTH), monthEnd);
                    } else {
                        cal3.set(year, cal1.get(Calendar.MONTH), monthEnd);
                    }
                }

                list4.add(new java.sql.Date(cal3.getTimeInMillis()));

                list5.add(new Integer(weekNo));

                //下半周的起止时间
                if ((cal2.get(Calendar.MONTH) == 2) &&
                        (monthStart > cal1.getActualMaximum(Calendar.DATE))) //处理跨2月的周
                 {
                    cal3.set(year, cal2.get(Calendar.MONTH), 1);
                } else {
                    cal3.set(year, cal1.get(Calendar.MONTH), monthStart);
                }

                list3.add(new java.sql.Date(cal3.getTimeInMillis()));
                list4.add(new java.sql.Date(cal2.getTimeInMillis()));

                list5.add(new Integer(weekNo));
                weekNo++;
            } else {
                //保存不跨月的周
                list3.add(new java.sql.Date(cal1.getTimeInMillis()));
                list4.add(new java.sql.Date(cal2.getTimeInMillis()));
                list5.add(new Integer(weekNo));
                weekNo++;
            }
        }

        Object[][] weekDayDate = new Object[list3.size()][3];

        for (int i = 0; i < list3.size(); i++) {
            if (list3.size() == list4.size()) {
                weekDayDate[i][0] = list5.get(i);
                weekDayDate[i][1] = list3.get(i);
                weekDayDate[i][2] = list4.get(i);
            }
        }

        return weekDayDate;
    }

    /**
     * 取得指定年月的逻辑开始日期和逻辑结束日期
     * @param year 年份
     * @param month 月份
     * @param startDay 逻辑开始日期
     * @param endDay 逻辑结束日期
     * @return java.sql.Date[] date 起止日期
     */
    public static java.sql.Date[] getStartAndEndDateOfLogicMonth(int year,
        int month, int startDay, int endDay) {
        java.sql.Date[] date = new java.sql.Date[2];

        int m = month - 1;

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(year, m - 1, 1);
        cal2.set(year, m, 1);

        if (cal1.getActualMaximum(Calendar.DATE) < startDay) {
            cal1.set(year, m, 1);
        } else {
            cal1.set(year, m - 1, startDay);
        }

        if (cal2.getActualMaximum(Calendar.DATE) < endDay) {
            cal2.set(year, m, cal2.getActualMaximum(Calendar.DATE));
        } else {
            cal2.set(year, m, endDay);
        }

        date[0] = new java.sql.Date(cal1.getTimeInMillis());
        date[1] = new java.sql.Date(cal2.getTimeInMillis());

        return date;
    }

    /**
     * 调试方法
     * @param args
     */

    /**
     * @param args
     */

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*Object obj[][]=getStartAndEndWeekDate(2009,27,26);
        for(int i=0;i<obj.length;i++)
        {
                System.out.println("当前周次"+obj[i][0]+" 周结开始:"+obj[i][1]+"  结束日期:"+obj[i][2]);
        }
            int year = 2009;
            int monthStart = 27;
            int monthEnd = 26;
        
        java.sql.Date[] date1= new java.sql.Date[2];
        java.sql.Date[] date2= new java.sql.Date[2];
        List list1 = new ArrayList();//保存每周开始日期
            List list2 = new ArrayList();//保存每周结束日期
            date1 = getStartAndEndDateOfLogicMonth(year,1,monthStart,monthEnd);
            date2 = getStartAndEndDateOfLogicMonth(year,12,monthStart,monthEnd);
        
            Calendar   calStart   =   Calendar.getInstance();
               Calendar   calEnd   =   Calendar.getInstance();
               Calendar   calTemp   =   Calendar.getInstance();
               calStart.setTime(date1[0]);
               calEnd.setTime(date2[1]);
               calTemp.setTime(date1[0]);
        
               System.out.println("初值1为"+new java.sql.Date(calStart.getTimeInMillis()));
               System.out.println("初值2为"+new java.sql.Date(calEnd.getTimeInMillis()));
        
               for(int i=0;calEnd.after(calStart)||calStart.equals(calEnd);calStart.add(Calendar.DATE,1),i++)
               {
                       int weekNo = calStart.get(Calendar.DAY_OF_WEEK);
                       if(weekNo!=1&&i==0)
                       {
                               Calendar   cal1 =  Calendar.getInstance();
                               Calendar   cal2 =  Calendar.getInstance();
                           cal1.setTimeInMillis(calStart.getTimeInMillis());
                               list1.add(cal1);
                               calStart.add(Calendar.DATE,7-weekNo);
                               cal2.setTimeInMillis(calStart.getTimeInMillis());
                               list2.add(cal2);
                       }
                   if(weekNo==1)
                       {
                               Calendar   cal1 =  Calendar.getInstance();
                               Calendar   cal2 =  Calendar.getInstance();
                           cal1.setTimeInMillis(calStart.getTimeInMillis());
                               list1.add(cal1);
                               calStart.add(Calendar.DATE,6);
                               if(calStart.after(calEnd))
                               {
                                       cal2.setTimeInMillis(calEnd.getTimeInMillis());
                               }
                               else
                               {
                                       cal2.setTimeInMillis(calStart.getTimeInMillis());
                               }
                               list2.add(cal2);
                       }
               }
        
               System.out.println("list1值:"+list1.size()+" list2值:"+list2.size());
        
                   for(int j=0;j<list1.size();j++)
                 {
                           Calendar   cal1 =  (Calendar)list1.get(j);
                     Calendar   cal2 =  (Calendar)list2.get(j);
                     System.out.println("测试"+(j+1)+" 开始日期为"+new java.sql.Date(cal1.getTimeInMillis())+" 结束日期为"+new java.sql.Date(cal2.getTimeInMillis()));
                 }*/

        /*//验证逻辑月函数
            java.sql.Date[] date1= new java.sql.Date[2];
            date1 = getStartAndEndDateOfLogicMonth(2007,5,31,30);
            System.out.println("逻辑月起止时间:"+date1[0]+"--"+date1[1]);*/

        //java.sql.Date[] date1= new java.sql.Date[2];
        //date1 = getFirstAndLastDayMonth(2007,2);
        System.out.println("格式化时间:" +
            DateUtils2.formatDate8(new java.util.Date()));
    }
}
