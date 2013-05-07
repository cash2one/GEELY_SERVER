package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:手机登录VO</p>
 * <p>创建日期:Dec 26, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ClassesVo extends ClientVo {
    private String LOGIN_NAME;
    private String USER_TYPE;
    private String PASSWORD;

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String login_name) {
        LOGIN_NAME = login_name;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String user_type) {
        USER_TYPE = user_type;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String password) {
        PASSWORD = password;
    }
}
