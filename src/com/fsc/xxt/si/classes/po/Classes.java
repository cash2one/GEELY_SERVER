package com.fsc.xxt.si.classes.po;

import com.fsc.framework.base.po.Base;

import com.fsc.xxt.si.school.po.School;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:班级PO</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class Classes extends Base {
    private String id;
    private String name;
    private String schoolid;
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolid() {
        return this.schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }
}
