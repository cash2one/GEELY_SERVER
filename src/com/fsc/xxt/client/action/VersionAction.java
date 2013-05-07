package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.DateUtils2;
import com.fsc.util.StringUtil;

import com.fsc.xxt.client.vo.VersionVo;
import com.fsc.xxt.version.po.Version;
import com.fsc.xxt.version.service.VersionService;

import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端apk更新管理类</p>
 * <p>创建日期:Feb 28, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class VersionAction extends ClientAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = -3067181756896131256L;
    private VersionVo versionVo = new VersionVo();

    /**指定要被下载的文件路径  */
    private String inputPath = "/apkupload/";
    private VersionService versionService;

    /**
     * 输出的文件流
     * @return
     * @throws Exception 
    */
    public InputStream getInputStream() throws Exception {
        return ServletActionContext.getServletContext()
                                   .getResourceAsStream(inputPath);
    }

    @Override
    public BaseVo getModel() {
        return versionVo;
    }

    /**
     * 获得最新apk信息
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String getLatestApk() throws Exception {
    	if (StringUtil.isEmpty(versionVo.getProjectName())) {
    		versionVo.setResultCode("-1");
    		versionVo.setResultDesc("缺少参数：项目名称");

			return SUCCESS;
		}
        try {
            Version version = versionService.selectLatestVersion(versionVo.getProjectName());
            Map map = new HashMap();
            map.put("mobileVersionId", version.getMobileVersionId());
            map.put("versionInnerId", version.getVersionInnerId() + "");
            map.put("description", version.getDescription());
            map.put("pubDate", DateUtils2.formatDate3(version.getPubDate()));
            map.put("versionRequest", version.getVersionRequest());
            map.put("projectname", version.getProjectname());
            String url = "clientVersion_downLoad.do?FILE_NAME=" +
                version.getApkFile();
            map.put("downLoadUrl", url);
            versionVo.setReturnMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            versionVo.setResultCode("-1");
            versionVo.setResultDesc("程序出错" + e.getMessage());
        }

        return SUCCESS;
    }

    /**
     * 文件下载
     * @return
     * @throws Exception
     */
    public String downLoad() throws Exception {
        String downloadDir = ServletActionContext.getServletContext()
                                                 .getRealPath("/apkupload");
        File rootDir = new File(downloadDir + "/" + versionVo.getFILE_NAME());

        if (!rootDir.exists()) {
            versionVo.setMessage("文件不存在，请联系管理员");

            return ERROR;
        } else {
            inputPath += versionVo.getFILE_NAME();
            versionVo.setFileName(new String("校讯通.apk".getBytes("utf-8"),
                    "iso-8859-1"));
        }

        return "download";
    }

    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }
}
