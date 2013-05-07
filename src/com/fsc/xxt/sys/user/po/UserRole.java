package com.fsc.xxt.sys.user.po;

import com.fsc.framework.base.po.Base;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户角色分配信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class UserRole extends Base {
    /** 标识ID */
    private String id;

    /** 角色ID */
    private String roleId;

    /** 人员编号 */
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
