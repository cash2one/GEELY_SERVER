package com.fsc.xxt.si.teacher.vo;

import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.school.po.School;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class TeacherVo extends BaseVo {
    /**主键ID */
    private String id;

    /**教师角色分类 */
    private String role;

    /**所教学段 */
    private String spart;

    /**登录名 */
    private String loginname;

    /**教师姓名 */
    private String name;

    /**教师工号 */
    private String workno;

    /**学校ID */
    private String schoolid;

    /**所属学校 */
    private School school;

    /**手机号码 */
    private String mobile;

    /**登录密码 */
    private String pass;

    /**积分 */
    private Integer score;

    /**短信码 */
    private String smscode;

    /**用户状态 */
    private String status;

    /**注册时间 */
    private Date regtime;

    /**班级ID */
    private String classid;

    /**所教班级 */
    private Classes classes;

    /**教师短信码 */
    private String tea_smscode;

    /**是否登陆过客户端 */
    private String islogin;
    
    /**市 */
    private String city;
    
    /**镇 */
    private String town;
    
    /**学校名字 */
    private String schoolname;
    
    /**班级名字 */
    private String classname;
    
    /**班级名字 */
    private Set teacherCourses = new HashSet(0);
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSpart() {
        return spart;
    }

    public void setSpart(String spart) {
        this.spart = spart;
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

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
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

    public String getTea_smscode() {
        return tea_smscode;
    }

    public void setTea_smscode(String tea_smscode) {
        this.tea_smscode = tea_smscode;
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

	public Set getTeacherCourses() {
		return teacherCourses;
	}

	public void setTeacherCourses(Set teacherCourses) {
		this.teacherCourses = teacherCourses;
	}
    
	
    
}
