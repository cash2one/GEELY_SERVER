package com.fsc.xxt.version.po;

import com.fsc.framework.base.po.Base;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:apk版本信息表对应的pojo类</p>
 * <p>创建日期:Feb 27, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class Version extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = -9097930670033304244L;

    //ID
    private String id;

    //外部版本号，主要用于显示
    private String mobileVersionId;

    //内部版本号，主要用于升级时的判断
    private int versionInnerId;

    //升级的描述
    private String description;

    //发布时间
    private Date pubDate;

    //是否必须升级 01必须 02不必须
    private String versionRequest;

    //apk文件名
    public String apkFile;
    
    //项目名称
    private String projectname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileVersionId() {
        return mobileVersionId;
    }

    public void setMobileVersionId(String mobileVersionId) {
        this.mobileVersionId = mobileVersionId;
    }

    public int getVersionInnerId() {
        return versionInnerId;
    }

    public void setVersionInnerId(int versionInnerId) {
        this.versionInnerId = versionInnerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getVersionRequest() {
        return versionRequest;
    }

    public void setVersionRequest(String versionRequest) {
        this.versionRequest = versionRequest;
    }

    public String getApkFile() {
        return apkFile;
    }

    public void setApkFile(String apkFile) {
        this.apkFile = apkFile;
    }

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
    
    
    
}
