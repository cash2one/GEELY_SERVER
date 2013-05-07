package com.fsc.xxt.si.msg.po;

import com.fsc.framework.base.po.Base;

public class NickName extends Base{
	//主键
	private String id;
	//登录ID
	private String loginid;
	//登录类型
	private String logintype;
	//联系人ID
	private String linkmanid;
	//联系人类型
	private String linkmantype;
	//昵称
	private String nickname;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public String getLinkmanid() {
		return linkmanid;
	}
	public void setLinkmanid(String linkmanid) {
		this.linkmanid = linkmanid;
	}
	public String getLinkmantype() {
		return linkmantype;
	}
	public void setLinkmantype(String linkmantype) {
		this.linkmantype = linkmantype;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
