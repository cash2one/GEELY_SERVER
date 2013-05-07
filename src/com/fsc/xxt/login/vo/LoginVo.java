package com.fsc.xxt.login.vo;

import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.vo.BaseVo;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:管理用户登录信息表单</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class LoginVo extends BaseVo {
    /** 登录名称 */
    private String loginName;

    /** 登录密码 */
    private String pass;

    /** 登录状态（true成功,false失败） */
    private boolean status;

    /** 登录消息 */
    private String message;

    /**用户信息*/
    private User user;

    /**新密码*/
    private String newPass;

    /**旧密码*/
    private String rePass;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRePass() {
        return rePass;
    }

    public void setRePass(String rePass) {
        this.rePass = rePass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
