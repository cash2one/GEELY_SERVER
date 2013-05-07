package com.fsc.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:通用工具</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class CommonUtil {
    /**
     * 根据变量名字在Cookie中查找相应的变量值
     * @param Cookie[] cookies
     * @param String cookieName
     * @param String defaultValue
     * @return String
     */
    public static String getCookieValue(Cookie[] cookies, String cookieName,
        String defaultValue) {
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];

            if (cookieName.equals(cookie.getName())) {
                return (cookie.getValue());
            }
        }

        return (defaultValue);
    }

    /**
     * 取参值
     * @param HttpServletRequest httpServletRequest
     * @param String paraName
     * @return String
     */
    public static String getParameter(HttpServletRequest httpServletRequest,
        String paraName) {
        if (paraName == null) {
            return "";
        }

        String strReturn = httpServletRequest.getParameter(paraName);

        if (strReturn == null) {
            strReturn = "";
        } else {
            strReturn = strReturn.trim();
        }

        return strReturn;
    }

    /**
     * 获取本机的网卡MAC地址
     * @param StringBuffer message
     * @return String
     */
    public static String getLocalMacAddress(StringBuffer message) {
        String macAddress = "";

        try {
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            String line;

            while ((line = lineNumberReader.readLine()) != null) {
                if (line.indexOf("Physical Address") > 0) {
                    macAddress = line.substring(line.indexOf("-") - 2);
                }
            }
        } catch (java.io.IOException e) {
            message.append(e.getMessage());
            macAddress = "";
        }

        return macAddress;
    }

    /**
     * 根据IP地址获取相应机器的网卡MAC地址
     * @param String ipAddress
     * @param StringBuffer message
     * @return String
     */
    public static String getRemoteMacAddress(String ipAddress,
        StringBuffer message) {
        String macAddress = "";

        try {
            Process process = Runtime.getRuntime()
                                     .exec("nbtstat -a " + ipAddress);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            String line;

            while ((line = lineNumberReader.readLine()) != null) {
                if (line.indexOf("MAC Address") > 0) {
                    macAddress = line.substring(line.indexOf("-") - 2);
                }
            }
        } catch (java.io.IOException e) {
            message.append(e.getMessage());
            macAddress = "";
        }

        return macAddress;
    }

    /**
     * 判断某个IP地址是否在某IP地址段内
     * @param String strIpAddress
     * @param String strIpStart
     * @param String strIpEnd
     * @return boolean
     */
    public static boolean checkIpFw(String strIpAddress, String strIpStart,
        String strIpEnd) {
        long lngIpAddress = splitIpAddress(strIpAddress);
        long lngIpStart = splitIpAddress(strIpStart);
        long lngIpEnd = splitIpAddress(strIpEnd);

        if (lngIpEnd < lngIpStart) {
            return false; //IP范围无效
        }

        if ((lngIpAddress >= lngIpStart) && (lngIpAddress <= lngIpEnd)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 把IP地址转化成长整型数
     * @param String strIpEnd
     * @return long
     */
    public static long splitIpAddress(String strIpAddress) {
        int[] intReturn = { 0, 0, 0, 0 };
        int intCount = 0;
        long lngReturn = 0;

        if (strIpAddress == null) {
            return lngReturn;
        }

        for (int intFor = 0; intFor < strIpAddress.length(); intFor++) {
            if (strIpAddress.substring(intFor, intFor + 1).equals(".")) {
                intCount++;
            }
        }

        if (intCount != 3) {
            return lngReturn;
        }

        int intFirst = strIpAddress.indexOf(".");
        int intLast = strIpAddress.lastIndexOf(".");
        String str1 = strIpAddress.substring(0, intFirst);
        String strSubip = strIpAddress.substring(0, intLast);
        int intSubLength = strSubip.length();
        int intSecond = strSubip.lastIndexOf(".");
        String str2 = strSubip.substring(intFirst + 1, intSecond);
        String str3 = strSubip.substring(intSecond + 1, intSubLength);
        String str4 = strIpAddress.substring(intLast + 1, strIpAddress.length());

        try {
            intReturn[0] = Integer.parseInt(str1);
            intReturn[1] = Integer.parseInt(str2);
            intReturn[2] = Integer.parseInt(str3);
            intReturn[3] = Integer.parseInt(str4);
            lngReturn = (intReturn[0] * 1000000000) + (intReturn[1] * 1000000) +
                (intReturn[2] * 1000) + intReturn[3];
        } catch (Exception e) {
            return lngReturn;
        }

        return lngReturn;
    }
    /**
     * 把IP转换成LONG值，256进制
     * @param strIpAddress
     * @return
     */
    public static Long ipToLong(String strIpAddress) {
    	Long[] intReturn = { 0L, 0L, 0L, 0L };
        int intCount = 0;
        long lngReturn = 0;

        if (strIpAddress == null) {
            return lngReturn;
        }

        for (int intFor = 0; intFor < strIpAddress.length(); intFor++) {
            if (strIpAddress.substring(intFor, intFor + 1).equals(".")) {
                intCount++;
            }
        }

        if (intCount != 3) {
            return lngReturn;
        }

        int intFirst = strIpAddress.indexOf(".");
        int intLast = strIpAddress.lastIndexOf(".");
        String str1 = strIpAddress.substring(0, intFirst);
        String strSubip = strIpAddress.substring(0, intLast);
        int intSubLength = strSubip.length();
        int intSecond = strSubip.lastIndexOf(".");
        String str2 = strSubip.substring(intFirst + 1, intSecond);
        String str3 = strSubip.substring(intSecond + 1, intSubLength);
        String str4 = strIpAddress.substring(intLast + 1, strIpAddress.length());

        try {
            intReturn[0] = Long.parseLong(str1);
            intReturn[1] = Long.parseLong(str2);
            intReturn[2] = Long.parseLong(str3);
            intReturn[3] = Long.parseLong(str4);
            lngReturn = (intReturn[0] * 256*256*256) + (intReturn[1] * 256*256) +
                (intReturn[2] * 256) + intReturn[3];
        } catch (Exception e) {
            return lngReturn;
        }

        return lngReturn;
    	
	}
    public static void main(String[] args) {
		System.out.println(ipToLong("192.168.1.1"));
	}
}
