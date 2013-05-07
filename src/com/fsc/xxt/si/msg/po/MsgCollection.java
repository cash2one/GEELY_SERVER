package com.fsc.xxt.si.msg.po;

import com.fsc.framework.base.po.Base;

import java.util.Date;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:消息收藏夹PO</p>
 * <p>创建日期:Jan 9, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgCollection extends Base {
    private String id;
    private String userid;
    private String usertype;
    private String title;
    private String type;
    private String content;
    private Date puttime = new Date();
    private String ptime;
    private String suser;
    private String susertype;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPuttime() {
        return this.puttime;
    }

    public void setPuttime(Date puttime) {
        this.puttime = puttime;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

	public String getSuser() {
		return suser;
	}

	public void setSuser(String suser) {
		this.suser = suser;
	}

	public String getSusertype() {
		return susertype;
	}

	public void setSusertype(String susertype) {
		this.susertype = susertype;
	}
    
    
}
