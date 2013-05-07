package com.fsc.xxt.si.student.po;

import java.util.Date;

import com.fsc.framework.base.po.Base;
import com.fsc.framework.base.po.MobileUser;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.msg.po.NickName;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学生PO</p>
 * <p>创建日期:Dec 27, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class Student extends Base implements MobileUser{

	private String id;
	private String loginname;
	private String name;
	private String stdno;
	private String parent;
	private String nickname;
	private String grade;
	private String classid;
	private Classes classes;
	private String schoolid;
	private School school;
	private String mobile;
	private String pass;
	private String service;
	private Date regtime;
	private String faceimg;
	private String status;
	private String islogin;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String stdname) {
		this.name = stdname;
	}

	public String getStdno() {
		return this.stdno;
	}

	public void setStdno(String stdno) {
		this.stdno = stdno;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassid() {
		return this.classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getSchoolid() {
		return this.schoolid;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Date getRegtime() {
		return this.regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Override
	public String getUserType() {
		return DictionaryConstant.USER_STUDENT;
	}

	public String getFaceimg() {
		return faceimg;
	}

	public void setFaceimg(String faceimg) {
		this.faceimg = faceimg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	
	
}