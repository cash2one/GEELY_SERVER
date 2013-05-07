package com.fsc.xxt.si.bulletin.po;

import com.fsc.framework.base.po.Base;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:公告信息表对应的POJO类</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class Bulletin extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 7703904354193203864L;
    private String id;

    //标题
    private String title;

    //内容
    private String content;

    //用户类型
    private String userType;

    //发布用户ID
    private String userId;

    //发布状态 01 未发布 ，02 已发布
    private String flag;

    //发布时间
    private Date putTime;

    //发布用户姓名
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
