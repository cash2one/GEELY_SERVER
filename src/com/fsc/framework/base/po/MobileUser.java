package com.fsc.framework.base.po;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端用户共用属性</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface MobileUser {
    /**获得主键*/
    String getId();

    /**获得手机号码*/
    String getMobile();

    /**获得真实姓名*/
    String getName();

    /**获得登录名*/
    String getLoginname();

    /**获得用户类型*/
    String getUserType();
    
}
