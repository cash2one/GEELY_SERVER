package com.fsc.xxt.sys.log.po;

import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.po.Base;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志信息表相映象的POJO类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SysLog extends Base {
    /** 日志ID */
    private String logId;

    /** 人员编号 */
    private String userId;

    /** IP地址 */
    private String ipAddress;

    /** MAC地址 */
    private String macAddress;

    /** 日志内容 */
    private String logContent;

    /** 日志时间 */
    private String logTime;

    /** 系统用户 */
    private User user;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
