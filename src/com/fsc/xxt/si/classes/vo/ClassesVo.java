package com.fsc.xxt.si.classes.vo;

import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.school.po.School;


public class ClassesVo extends BaseVo {
    private String id;
    private String name;
    private String schoolid;
    private School school;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
    
    
}
