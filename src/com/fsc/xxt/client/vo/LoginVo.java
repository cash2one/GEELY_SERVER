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
public class LoginVo extends ClientVo {
	//用户ID
    private Integer userId;

    //用户名
    private String userName;

    //用户密码
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
