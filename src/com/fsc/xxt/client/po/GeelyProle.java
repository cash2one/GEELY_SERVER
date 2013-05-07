package com.fsc.xxt.client.po;

import com.fsc.framework.base.po.Base;


/**
 *
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:权限表对应的POJO类</p>
 * <p>创建日期:2013-4-10</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class GeelyProle extends Base {
    private static final long serialVersionUID = -7071791801799604459L;

    //ID
    private Integer id;

    //用户ID
    private String userId;

    //角色权限
    private String userProle;

    //创建人
    private String createUser;

    //创建时间
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserProle() {
        return userProle;
    }

    public void setUserProle(String userProle) {
        this.userProle = userProle;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
