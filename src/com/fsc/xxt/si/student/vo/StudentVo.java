package com.fsc.xxt.si.student.vo;

import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.school.po.School;

import java.util.Date;


public class StudentVo extends BaseVo {
    /**主键ID */
    private String id;

    /**登录名 */
    private String loginname;

    /**姓名 */
    private String name;

    /**学号 */
    private String stdno;

    /**家长名字 */
    private String parent;

    /**别名 */
    private String nickname;

    /**所在年纪 */
    private String grade;

    /**所在班级id */
    private String classid;

    /**班级信息 */
    private Classes classes;

    /**学校ID */
    private String schoolid;

    /**学校信息 */
    private School school;

    /**手机号码 */
    private String mobile;

    /**登录密码 */
    private String pass;

    /**订购服务 */
    private String service;

    /**注册时间 */
    private Date regtime;

    /**图片地址 */
    private String faceimg;

    /**帐号状态 */
    private String status;

    /**是否用客户端登陆过 */
    private String islogin;
    
    /**市 */
    private String city;
    
    /**镇 */
    private String town;
    
    /**学校名字 */
    private String schoolname;
    
    /**班级名字 */
    private String classname;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStdno() {
        return stdno;
    }

    public void setStdno(String stdno) {
        this.stdno = stdno;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	
    
    
}
