package com.fsc.xxt.si.teacher.po;

import com.fsc.framework.base.po.Base;
import com.fsc.framework.base.po.MobileUser;

import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:教师PO</p>
 * <p>创建日期:Dec 27, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class Teacher extends Base implements MobileUser {
    private String id;
    private String role;
    private String spart;
    private String loginname;
    private String name;
    private String workno;
    private String nickname;
    private String schoolid;
    private School school;
    private String mobile;
    private String pass;
    private Integer score;
    private String faceimg;
    private String smscode;
    private String status;
    private Date regtime;
    private String classid;
    private Classes classes;
    private String tea_smscode;
    private String islogin;
    private Set teacherCourses = new HashSet(0);
    

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSpart() {
        return this.spart;
    }

    public void setSpart(String spart) {
        this.spart = spart;
    }

    public String getLoginname() {
        return this.loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkno() {
        return this.workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFaceimg() {
        return this.faceimg;
    }

    public void setFaceimg(String faceimg) {
        this.faceimg = faceimg;
    }

    public String getSmscode() {
        return this.smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegtime() {
        return this.regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getClassid() {
        return this.classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public Set getTeacherCourses() {
        return this.teacherCourses;
    }

    public void setTeacherCourses(Set teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
    
    
    public String getTea_smscode() {
		return tea_smscode;
	}

	public void setTea_smscode(String teaSmscode) {
		tea_smscode = teaSmscode;
	}

	@Override
	public String getUserType() {
		return DictionaryConstant.USER_TEACHER;
	}

	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	
	
}
