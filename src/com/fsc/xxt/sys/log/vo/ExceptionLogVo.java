package com.fsc.xxt.sys.log.vo;

import com.fsc.framework.base.vo.BaseVo;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:异常日志视图Vo</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ExceptionLogVo extends BaseVo {
    /**系统异常日志Id*/
    private String id;

    /**异常最早发生时间*/
    private String sTime;

    /**异常最晚发生时间*/
    private String eTime;

    public String getSTime() {
        return sTime;
    }

    public void setSTime(String time) {
        sTime = time;
    }

    public String getETime() {
        return eTime;
    }

    public void setETime(String time) {
        eTime = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
