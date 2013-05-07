package com.fsc.xxt.si.teacher.po;

import com.fsc.framework.base.po.Base;
import com.fsc.framework.base.po.MobileUser;

public class TeacherCourse extends Base {
	/** 主键 */
	private  String id;
	
	/** 教师ID */
	private  String tid;
	
	/** 课程ID */
	private  String cid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
}
