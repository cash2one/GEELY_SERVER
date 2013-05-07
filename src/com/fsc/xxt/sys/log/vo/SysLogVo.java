package com.fsc.xxt.sys.log.vo;

import com.fsc.framework.base.vo.BaseVo;


/**
 * <p>Title: 办公设备及用品信息管理系统</p>
 * <p>Description:系统日志信息Vo类</p>
 * <p>创建日期:2010-10-09</p>
 * @author thh
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class SysLogVo extends BaseVo {
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

    /** 日志开始时间 */
    private String stime;

    /** 日志结束时间 */
    private String etime;

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

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
