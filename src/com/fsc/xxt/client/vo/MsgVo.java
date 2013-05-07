package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息VO</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgVo extends ClientVo {
	private String ID;
    private String LINK_MAN_ID;
    private String LINK_MAN_TYPE;
    private String LINK_MAN_NAME;
    private String USER_TYPE;
    private String MESSAGE_CONTENT;
    private String MO_TYPE;
    private String RUSER_NAME;
    private String RUSER_TYPE;
    private String MESSAGE_TYPE;
    private String MESSAGE_NO;
    private String CLASS_ID;
    private String MOBILE;
    private String RUSER_IDS;
    private String RUSER_TYPES;
    private String LOGIN_NAME;
    private String LOGIN_ID;
    private String RUSER_ID;
    private String TYPE;
    private String userId;

    //收藏信息ID
    private String MSG_COLLECT_ID;

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String mobile) {
        MOBILE = mobile;
    }

    public String getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(String class_id) {
        CLASS_ID = class_id;
    }

    public String getMESSAGE_TYPE() {
        return MESSAGE_TYPE;
    }

    public void setMESSAGE_TYPE(String message_type) {
        MESSAGE_TYPE = message_type;
    }

    public String getMESSAGE_CONTENT() {
        return MESSAGE_CONTENT;
    }

    public void setMESSAGE_CONTENT(String message_content) {
        MESSAGE_CONTENT = message_content;
    }

    public String getRUSER_NAME() {
        return RUSER_NAME;
    }

    public void setRUSER_NAME(String ruser_name) {
        RUSER_NAME = ruser_name;
    }

    public String getRUSER_TYPE() {
        return RUSER_TYPE;
    }

    public void setRUSER_TYPE(String ruser_type) {
        RUSER_TYPE = ruser_type;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String user_type) {
        USER_TYPE = user_type;
    }

    public String getLINK_MAN_ID() {
        return LINK_MAN_ID;
    }

    public void setLINK_MAN_ID(String link_man_id) {
        LINK_MAN_ID = link_man_id;
    }

    public String getLINK_MAN_TYPE() {
        return LINK_MAN_TYPE;
    }

    public void setLINK_MAN_TYPE(String link_man_type) {
        LINK_MAN_TYPE = link_man_type;
    }

    public String getMO_TYPE() {
        return MO_TYPE;
    }

    public void setMO_TYPE(String mo_type) {
        MO_TYPE = mo_type;
    }

    public String getMESSAGE_NO() {
        return MESSAGE_NO;
    }

    public void setMESSAGE_NO(String message_no) {
        MESSAGE_NO = message_no;
    }

    public String getRUSER_IDS() {
        return RUSER_IDS;
    }

    public void setRUSER_IDS(String ruser_ids) {
        RUSER_IDS = ruser_ids;
    }

    public String getRUSER_TYPES() {
        return RUSER_TYPES;
    }

    public void setRUSER_TYPES(String ruser_types) {
        RUSER_TYPES = ruser_types;
    }

    public String getMSG_COLLECT_ID() {
        return MSG_COLLECT_ID;
    }

    public void setMSG_COLLECT_ID(String msg_collect_id) {
        MSG_COLLECT_ID = msg_collect_id;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String login_name) {
        LOGIN_NAME = login_name;
    }

    public String getRUSER_ID() {
        return RUSER_ID;
    }

    public void setRUSER_ID(String ruser_id) {
        RUSER_ID = ruser_id;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String type) {
        TYPE = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLINK_MAN_NAME() {
		return LINK_MAN_NAME;
	}

	public void setLINK_MAN_NAME(String lINKMANNAME) {
		LINK_MAN_NAME = lINKMANNAME;
	}

	public String getLOGIN_ID() {
		return LOGIN_ID;
	}

	public void setLOGIN_ID(String lOGINID) {
		LOGIN_ID = lOGINID;
	}
	
	
}
