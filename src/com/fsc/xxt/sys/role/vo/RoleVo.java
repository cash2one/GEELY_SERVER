package com.fsc.xxt.sys.role.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色信息VO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class RoleVo extends BaseVo {
    /** ID */
    private String roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 排序号 */
    private Integer orderNum;

    /** 角色已分配权限信息列表 */
    private List rolePrivList;

    /** 系统菜单模块信息列表 */
    private List moduleList;

    /** 系统权限信息列表 */
    private List privList;

    /** 菜单模块编号 */
    private String moduleCode;
    
    /** 角色类型 */
    private String[] roleTypes;
    // Constructors
    /** default constructor */
    public RoleVo() {
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List getRolePrivList() {
        return rolePrivList;
    }

    public void setRolePrivList(List rolePrivList) {
        this.rolePrivList = rolePrivList;
    }

    public List getModuleList() {
        return moduleList;
    }

    public void setModuleList(List moduleList) {
        this.moduleList = moduleList;
    }

    public List getPrivList() {
        return privList;
    }

    public void setPrivList(List privList) {
        this.privList = privList;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

	public String[] getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(String[] roleTypes) {
		this.roleTypes = roleTypes;
	}
}
