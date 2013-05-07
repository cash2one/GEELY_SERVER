package com.fsc.xxt.si.msg.vo;

import com.fsc.framework.base.vo.BaseVo;
import com.fsc.framework.constant.CommonConstants;
import com.fsc.xxt.si.constant.MsgConstant;

import java.util.Date;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息VO</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgVo extends BaseVo {
//    private String id;
//    private String suser;
//    private String susertype;
//    private String ruser;
//    private String rusertype;
//    private String content;
//    private String type;
//    private String motype;
//    private String mttype;
//    private Date motime;
//    private Date mttime;
//    private String mtflag;
//    private String readflag;
//    private String rusers;
//    private String rusertypes;
    
    private String id;
    private String suser;
    private String susername;
    private String susertype;
    private String ruser;
    private String rusername;
    private String rusertype;
    private String content;
    private String type;
    private String motype;
    private String mttype;
    private Date motime;
    private Date mttime;
    private String mtflag; 
    private String readflag; 
    private String delflag; 
    private String pullflag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuser() {
		return suser;
	}
	public void setSuser(String suser) {
		this.suser = suser;
	}
	public String getSusername() {
		return susername;
	}
	public void setSusername(String susername) {
		this.susername = susername;
	}
	public String getSusertype() {
		return susertype;
	}
	public void setSusertype(String susertype) {
		this.susertype = susertype;
	}
	public String getRuser() {
		return ruser;
	}
	public void setRuser(String ruser) {
		this.ruser = ruser;
	}
	public String getRusername() {
		return rusername;
	}
	public void setRusername(String rusername) {
		this.rusername = rusername;
	}
	public String getRusertype() {
		return rusertype;
	}
	public void setRusertype(String rusertype) {
		this.rusertype = rusertype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMotype() {
		return motype;
	}
	public void setMotype(String motype) {
		this.motype = motype;
	}
	public String getMttype() {
		return mttype;
	}
	public void setMttype(String mttype) {
		this.mttype = mttype;
	}
	public Date getMotime() {
		return motime;
	}
	public void setMotime(Date motime) {
		this.motime = motime;
	}
	public Date getMttime() {
		return mttime;
	}
	public void setMttime(Date mttime) {
		this.mttime = mttime;
	}
	public String getMtflag() {
		return mtflag;
	}
	public void setMtflag(String mtflag) {
		this.mtflag = mtflag;
	}
	public String getReadflag() {
		return readflag;
	}
	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getPullflag() {
		return pullflag;
	}
	public void setPullflag(String pullflag) {
		this.pullflag = pullflag;
	}

    
}
