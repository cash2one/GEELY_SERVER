package com.fsc.framework.constant;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:常量类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class CommonConstants {
	/** 数据库字符编码*/
	public final static String DATABASE_ENCODING="gbk";
    /** 应用程序名称 */
    public final static String APPNAME = "AQN";

    /** 前台站点登录客户用户 */
    public final static String WEBUSER = "webuser";

    /** 未登录 */
    public final static String NOLOGIN = "Nologin";

    /** 系统操作用户 */
    public final static String USER = "user";

    /** 移动手机登陆用户 */
    public final static String MOBILEUSER = "mobileuser";

    /** 用户初始密码 */
    public final static String USERINITPWD = "000000";

    /** 用户ID */
    public final static String USERID = "userId";

    /** 用户名 */
    public final static String USERNAME = "userName";

    /** 用户真实姓名 */
    public final static String REALNAME = "realName";

    /** 顶层单位编号 */
    public final static String ROOTUNITNO = "43101";

    /**用户请求的页面*/
    public final static String URL = "url";

    /**上传文件存放目录*/
    public final static String UPLOAD_FILE_PATH="uploadfiles";

    /**逻辑删除标志-已删除*/
    public final static String DELFLAG_DEL="02";
    
    /**逻辑删除标志-正常*/
    public final static String DELFLAG_UNDEL="01";
    
    /** 私有化的构造方法，使之不可外部实例化 */
    private CommonConstants() {
    }
}
