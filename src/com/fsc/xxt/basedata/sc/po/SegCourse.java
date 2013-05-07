package com.fsc.xxt.basedata.sc.po;

import com.fsc.framework.base.po.Base;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:学段学科关联表</p>
 * <p>创建日期:2010-12-22</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SegCourse extends Base {
	//主键id
    private String id;
    //学段信息
    private String spart;
    //学科信息
    private String course;
    //学科名
    private String courseName;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
