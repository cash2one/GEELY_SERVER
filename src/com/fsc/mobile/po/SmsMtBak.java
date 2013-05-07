package com.fsc.mobile.po;

import java.util.Date;

import com.fsc.framework.base.po.Base;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:下行短信PO</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SmsMtBak extends Base {
    private static final long serialVersionUID = 1968068159747068448L;
    private String id;
    private String fromMobile;
    private String toMobile;
    private String message;
    private Date mttime = new Date();
    private String traceid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromMobile() {
		return fromMobile;
	}
	public void setFromMobile(String fromMobile) {
		this.fromMobile = fromMobile;
	}
	public String getToMobile() {
		return toMobile;
	}
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMttime() {
		return mttime;
	}
	public void setMttime(Date mttime) {
		this.mttime = mttime;
	}
	public String getTraceid() {
		return traceid;
	}
	public void setTraceid(String traceid) {
		this.traceid = traceid;
	}
}
