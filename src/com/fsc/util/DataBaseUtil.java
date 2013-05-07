package com.fsc.util;

import java.io.File;
import java.io.IOException;


/**
 *
 * <p>Title: 办公设备及用品信息管理系统</p>
 * <p>Description:数据库备份还原工具类</p>
 * <p>创建日期:Nov 19, 2010</p>
 * @author chao
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class DataBaseUtil {
    /**
     * 备份数据库
     * @param databasename  数据库用户名
     * @param databasepw 数据库密码
     * @param netname 实例名
     * @param filepath 文件路径
     * @param filename 文件名
     * @throws IOException
     */
    public static void dataBackup(String databasename, String databasepw,
        String netname, String filepath, String filename)
        throws Exception {
        Runtime rt = Runtime.getRuntime();
        @SuppressWarnings("unused")
		Process processexp = null;
        checkCreatDir(filepath);

        String exp = "exp " + databasename + "/" + databasepw + "@" + netname +
            " file='" + filepath + "/" + filename + ".dmp'";
        
        System.out.println(exp);

        processexp = rt.exec("cmd /k start "+exp);
    } 

    /**
     * 恢复数据库
     * @param databasename  数据库用户名
     * @param databasepw 数据库密码
     * @param netname 实例名
     * @param fromuser 数据库用户名
     * @param filename 文件名
     * @throws IOException
     */
    public static void dataResume(String databasename, String databasepw,
        String netname, String fromuser, String filename)
        throws Exception {
        Runtime rt = Runtime.getRuntime();
        @SuppressWarnings("unused")
		Process processimp = null; // 
        System.out.println(filename);
        if (checkDir(filename)) {
            String imp = "imp " + databasename + "/" + databasepw + "@" +
                netname + " fromuser=" + fromuser + " touser=" + fromuser +
                " file='" + filename + "'  ignore=y";
            processimp = rt.exec("cmd /c start "+imp);
        }
    }

    public static void checkCreatDir(String dirPath) {
        File file = new File(dirPath);

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static boolean checkDir(String filename) {
        File file = new File(filename);

        return file.exists();
    }

    public static void main(String[] args) {
        try {
            /* DataBaseUtil.dataResume("oemis", "oemis", "192.168.3.4", "oemis",
                 "d:/backup");*/
            DataBaseUtil.dataBackup("oemis", "oemis", "192.168.3.4", "C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/oemis/databackup",
                "backup");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
