package com.fsc.xxt.sys.user.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户信息VO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class UserVo extends BaseVo {
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

    /** 旧密码 */
    private String oldPwd;

    /** 初始密码 */
    private String initPass;

    /** 用户所在单位编号 */
    private String orgNo;

    /** 单位信息列表 */
    private List orgList;

    /** 部门信息列表 */
    private List deptList;

    /** 用户状态字典列表 */
    private List statusList;

    /** 用户已分配角色信息列表 */
    private List userRoleList;

    /** 系统角色信息列表 */
    private List roleList;

    /** 用户列表 */
    private List userList;
    
    /** 用户能查询的外呼员列表 */ 
    private List selUserList;

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

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public List getOrgList() {
        return orgList;
    }

    public void setOrgList(List orgList) {
        this.orgList = orgList;
    }

    public List getDeptList() {
        return deptList;
    }

    public void setDeptList(List deptList) {
        this.deptList = deptList;
    }

    public List getStatusList() {
        return statusList;
    }

    public void setStatusList(List statusList) {
        this.statusList = statusList;
    }

    public List getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List getRoleList() {
        return roleList;
    }

    public void setRoleList(List roleList) {
        this.roleList = roleList;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

    public String getInitPass() {
        return initPass;
    }

    public void setInitPass(String initPass) {
        this.initPass = initPass;
    }

	public List getSelUserList() {
		return selUserList;
	}

	public void setSelUserList(List selUserList) {
		this.selUserList = selUserList;
	}
}
