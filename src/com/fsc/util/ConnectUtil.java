package com.fsc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:连接数据库工具类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ConnectUtil {
    //MySql数据库
    private static final String MYSQL = "jdbc:mysql://";

    //Oracle数据库
    private static final String ORACLE = "jdbc:oracle:thin:@";

    //SqlServer数据库
    private static final String SQLSERVER = "jdbc:microsoft:sqlserver://";

    private ConnectUtil() {
    }

    /**
     * 获得数据库连接
     * @param DBType 数据库类型
     * @param url  连接URL
     * @param user 用户名
     * @param password 密码
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String DBType, String url,
        String user, String password) throws SQLException {
        if ("mysql".equalsIgnoreCase(DBType)) {
            return getMySqlConn(url, user, password);
        }

        if ("oracle".equalsIgnoreCase(DBType)) {
            return getOracleConn(url, user, password);
        }

        if ("sqlserver".equals(DBType)) {
            return getSqlServerConn(url, user, password);
        }

        return null;
    }

    /**
     * 关闭当前连接
     * @param conn
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得MySql连接
     * @param url 连接URL
     * @param user 用户名
     * @param password 密码
     * @return
     * @throws SQLException
     */
    private static Connection getMySqlConn(String url, String user,
        String password) throws SQLException {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        conn = DriverManager.getConnection(MYSQL + url, "root", "root");

        return conn;
    }

    /**
     * 获得Oracle连接
     * @param url 连接URL
     * @param user 用户名
     * @param password 密码
     * @return
     * @throws SQLException
     */
    private static Connection getOracleConn(String url, String user,
        String password) throws SQLException {
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        conn = DriverManager.getConnection(ORACLE + url, "scott", "tiger");

        return conn;
    }

    /**
     * 获得SqlServer连接
     * @param url 连接URL
     * @param user 用户名
     * @param password 密码
     * @return
     * @throws SQLException
     */
    private static Connection getSqlServerConn(String url, String user,
        String password) throws SQLException {
        Connection conn = null;

        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); // 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        conn = DriverManager.getConnection(SQLSERVER + url, "root", "root");

        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection("MySQL", "127.0.0.1", "root", "root");

            if (conn == null) {
                System.out.println("Connection the database is failled !");
            } else {
                System.out.println(conn.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
