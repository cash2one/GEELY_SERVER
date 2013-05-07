package com.fsc.xxt.sys.role.po;

import com.fsc.framework.base.po.Base;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色权限分配信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class RolePriv extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = -2383940578633375282L;

    /** 标识ID */
    private String rolePrivId;

    /** 角色ID */
    private String roleId;

    /** 功能权限ID */
    private String privId;

    // Constructors
    /** default constructor */
    public RolePriv() {
    }

    public String getRolePrivId() {
        return rolePrivId;
    }

    public void setRolePrivId(String rolePrivId) {
        this.rolePrivId = rolePrivId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }
}
