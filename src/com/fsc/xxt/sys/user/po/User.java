package com.fsc.xxt.sys.user.po;

import com.fsc.framework.base.po.Base;

import java.util.Set;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统管理用户基本信息表相映象的POJO类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class User extends Base {
    /** 人员ID */
    private String id;

    /** 部门ID */
    private String deptId;

    /** 工号 */
    private String staffNo;

    /** 简码 */
    private String abc;

    /** 姓名 */
    private String name;

    /** 性别 */
    private String genDer;

    /** 手机号码 */
    private String mobile;

    /** 电话 */
    private String tel;

    /** 备注 */
    private String remark;

    /** 邮箱 */
    private String email;

    /** 登录用户名 */
    private String login;

    /** 登录密码 */
    private String pwd;

    /** 当前状态 */
    private String status;

    /** 是否在线 */
    private String onlines;

    /** 部门名称 */
    private String deptName;

    /** 单位名称 */
    private String orgName;

    /** 用户快捷菜单集合 */
    private Set shortCutSet;
    

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenDer() {
        return genDer;
    }

    public void setGenDer(String genDer) {
        this.genDer = genDer;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnlines() {
        return onlines;
    }

    public void setOnlines(String onlines) {
        this.onlines = onlines;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Set getShortCutSet() {
        return shortCutSet;
    }

    public void setShortCutSet(Set shortCutSet) {
        this.shortCutSet = shortCutSet;
    }
}
