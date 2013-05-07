package com.fsc.xxt.version.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.io.File;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端版本视图层</p>
 * <p>创建日期:Feb 27, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class VersionVo extends BaseVo {
    private String id;

    //外部版本号，主要用于显示
    private String mobileVersionId;

    //内部版本号，主要用于升级时的判断
    private int versionInnerId;

    //升级的描述
    private String description;

    //发布时间
    private Date pubDate;

    //是否必须升级
    private String versionRequest;

    //apk文件
    private File file;

    //apk文件名
    private String fileFileName;

    //apk文件类型
    private String fileContentType;
    
    //项目名称
    private String projectName;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
    
}
