package com.fsc.xxt.si.constant;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息常量类</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgConstant {
    /**下行状态：未接收*/
    public static final String MTFLAG_UNRECEIVED = "01";

    /**下行状态：已接收*/
    public static final String MTFLAG_RECEIVED = "02";

    /**阅读状态：未读*/
    public static final String READFLAG_NOREAD = "01";

    /**阅读状态：已读*/
    public static final String READFLAG_READ = "02";

    /**上行类型：个人发送*/
    public static final String MOTYPE_ONE = "01";

    /**上行类型：多人发送*/
    public static final String MOTYPE_MANY = "02";

    /**上行类型：班级发送*/
    public static final String MOTYPE_CLASS = "03";

    /**下行类型：平台短信*/
    public static final String MTTYPE_SMS = "01";

    /**下行类型：客户端消息*/
    public static final String MTTYPE_CLIENT = "02";
}
