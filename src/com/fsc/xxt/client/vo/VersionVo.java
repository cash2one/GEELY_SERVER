package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端apk管理控制类</p>
 * <p>创建日期:Feb 28, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class VersionVo extends ClientVo {
    //下载的文件名
    private String FILE_NAME;

    //设置下载时显示的文件名
    private String fileName;
    
    //项目名称
    private String projectName;

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String file_name) {
        FILE_NAME = file_name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
    
    
}
