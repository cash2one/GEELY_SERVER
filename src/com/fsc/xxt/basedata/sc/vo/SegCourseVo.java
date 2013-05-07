package com.fsc.xxt.basedata.sc.vo;

import java.util.List;

import com.fsc.framework.base.vo.BaseVo;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:学科学段关联vo</p>
 * <p>创建日期:2010-12-23</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SegCourseVo extends BaseVo{
	//学段列表
	private List spartList;
	//学科列表
	private List courseList;
	//学科学段列表
	private List scList;
	//学段
	private String spart;
	//学科
	private String course;
	public List getSpartList() {
		return spartList;
	}
	public void setSpartList(List spartList) {
		this.spartList = spartList;
	}
	public List getCourseList() {
		return courseList;
	}
	public void setCourseList(List courseList) {
		this.courseList = courseList;
	}
	public List getScList() {
		return scList;
	}
	public void setScList(List scList) {
		this.scList = scList;
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
	
}
