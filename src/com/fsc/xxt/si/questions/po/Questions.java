package com.fsc.xxt.si.questions.po;

import java.util.Date;

import com.fsc.framework.base.po.Base;

/**
*
* <p>Title:校讯通手机服务端</p>
* <p>Description:问题反馈表对应的POJO类</p>
* <p>创建日期:2012/03/05</p>
* @author ZhouChao
* @version 1.0
* <p>湖南家校圈科技有限公司</p>
* <p>http://www.139910.com</p>
* <p>http://wps.139910.com</p>
*/
public class Questions extends Base{
	private String id;
	private String content;
	private String submission;
	private Date submi_time;
	private String hanflag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubmission() {
		return submission;
	}
	public void setSubmission(String submission) {
		this.submission = submission;
	}
	public Date getSubmi_time() {
		return submi_time;
	}
	public void setSubmi_time(Date submiTime) {
		submi_time = submiTime;
	}
	public String getHanflag() {
		return hanflag;
	}
	public void setHanflag(String hanflag) {
		this.hanflag = hanflag;
	}
	
	

}
