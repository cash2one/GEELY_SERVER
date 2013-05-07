package com.fsc.xxt.si.msg.po;

import com.fsc.framework.base.po.Base;

public class MsgInfo extends Base {
	// 信息内容
	private String content;
	// 教师ID
	private String teacherid;
	// 学生ID
	private String studentid;
	//t_msg ID
	private String id;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
