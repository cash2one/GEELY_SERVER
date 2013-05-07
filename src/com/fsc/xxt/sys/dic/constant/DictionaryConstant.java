package com.fsc.xxt.sys.dic.constant;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:常量类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DictionaryConstant {
    /**01：学科*/
    public final static String COURSE = "01";

    /**02：学历*/
    public final static String DEGREE = "02";

    /**03：年级*/
    public final static String GRADE = "03";

    /**04：用户类型*/
    public final static String CUSTOMER_TYPE = "04";
    
    /** 用户类型：教师 */
    public final static String USER_TEACHER = "01";
    
    /** 用户类型：学生 */
    public final static String USER_STUDENT = "02";
    
    /** 用户类型：学校管理员 */
    public final static String USER_SCHADMIN = "03";
    
    /**05：学段*/
    public final static String SPART = "05";

    /**06：建议问题类别*/
    public final static String SUGGEST_TYPE = "06";

    /**08：单位类别*/
    public final static String UNIT_TYPE = "08";

    /**09：后台用户状态*/
    public final static String USER_STATUS = "09";

    /**10：用户性别*/
    public final static String USER_SEX = "10";

    /**11：教师角色*/
    public final static String TEA_ROLE = "11";

    /**12：消息类型*/
    public final static String MSG_TYPE = "12";

    /**13：下行方式*/
    public final static String MT_TYPE = "13";

    /**14：上行方式*/
    public final static String MO_TYPE = "14";

    /**15：考试类型*/
    public final static String EXAM_TYPE = "15";

    /**16：关键字过滤*/
    public final static String KEYWORD = "16";
    
    /**18:考试类型*/
    public final static String EXAMTYPE="18";
    
    /**数据类型接口**/
    public final static String DATATYPE="19";
    
    /**用户是否用客户端登录过**/
    public final static String LOGIN_ISFLAG="01";

    /** 私有化的构造方法，使之不可外部实例化 */
    private DictionaryConstant() {
    }
}
