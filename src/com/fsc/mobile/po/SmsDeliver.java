package com.fsc.mobile.po;

import java.util.Date;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信上行信息（来源211.142.226.167 SQL SERVER）</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SmsDeliver implements java.io.Serializable {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 4537927702331473163L;

    /** 上行手机号码 */
    private String sourceUser;

    /** 上行信息 */
    private String msg;

    /** 上行信息ID */
    private Integer mo_id;

    /**  */
    private String dest_id;

    /** 生成时间 */
    private Date genTime;

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getMo_id() {
        return mo_id;
    }

    public void setMo_id(Integer mo_id) {
        this.mo_id = mo_id;
    }

    public String getDest_id() {
        return dest_id;
    }

    public void setDest_id(String dest_id) {
        this.dest_id = dest_id;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }
}
