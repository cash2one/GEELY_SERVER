package com.fsc.xxt.si.teacher.po;

import com.fsc.framework.base.po.Base;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:添加类描述信息</p>
 * <p>创建日期:Jan 12, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class TeacherClass extends Base {
	//-- Field Region Begin -- 
	
	/** 主键 */
	private  String id;
	
	/** 教师ID */
	private String teacherId;
	
	/** 班级ID */
	private String classId;

	//-- Field Region End -- 
	
	
	//-- Method Region Begin -- 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	//-- Method Region End -- 
}
