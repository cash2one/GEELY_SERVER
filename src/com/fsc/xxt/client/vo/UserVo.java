package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:用户信息视图Vo</p>
 * <p>创建日期:Feb 3, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class UserVo extends ClientVo {
    //用户名称
    private String LOGIN_NAME;

    //用户类型
    private String USER_TYPE;

    //别名
    private String NICK_NAME;

    //多个用户名以","隔开
    private String USER_NAMES;
    private String FACE_IMG;

    //用户ID
    private String USER_ID;
    
    //短信码
    private String SMSCODE;
    
    //手机号码
    private String MOBILE;
    
    //联系人ID
    private String LINK_MAN_ID;
    
    //联系人类型
    private String LINK_MAN_TYPE;

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

    public String getNICK_NAME() {
        return NICK_NAME;
    }

    public void setNICK_NAME(String nick_name) {
        NICK_NAME = nick_name;
    }

    public String getUSER_NAMES() {
        return USER_NAMES;
    }

    public void setUSER_NAMES(String user_names) {
        USER_NAMES = user_names;
    }

    public String getFACE_IMG() {
        return FACE_IMG;
    }

    public void setFACE_IMG(String face_img) {
        FACE_IMG = face_img;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String user_id) {
        USER_ID = user_id;
    }

	public String getSMSCODE() {
		return SMSCODE;
	}

	public void setSMSCODE(String sMSCODE) {
		SMSCODE = sMSCODE;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getLINK_MAN_ID() {
		return LINK_MAN_ID;
	}

	public void setLINK_MAN_ID(String lINKMANID) {
		LINK_MAN_ID = lINKMANID;
	}

	public String getLINK_MAN_TYPE() {
		return LINK_MAN_TYPE;
	}

	public void setLINK_MAN_TYPE(String lINKMANTYPE) {
		LINK_MAN_TYPE = lINKMANTYPE;
	}
    
    
}
