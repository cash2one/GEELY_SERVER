package com.fsc.xxt.sys.role.po;

import com.fsc.framework.base.po.Base;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class Role extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = -6022958164871980741L;

    /** ID */
    private String roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 排序号 */
    private Integer orderNum;

    // Constructors
    /** default constructor */
    public Role() {
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

}
