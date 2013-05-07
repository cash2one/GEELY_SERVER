package com.fsc.email.po;

import java.util.Date;

import com.fsc.framework.base.po.Base;
/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:Email下行备份po</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class EmailMtBak extends Base {

	private String id;
	private String fromadd;
	private String toadd;
	private String subject;
	private String content;
	private Date mttime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromadd() {
		return fromadd;
	}
	public void setFromadd(String fromadd) {
		this.fromadd = fromadd;
	}
	public String getToadd() {
		return toadd;
	}
	public void setToadd(String toadd) {
		this.toadd = toadd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getMttime() {
		return mttime;
	}
	public void setMttime(Date mttime) {
		this.mttime = mttime;
	}


}