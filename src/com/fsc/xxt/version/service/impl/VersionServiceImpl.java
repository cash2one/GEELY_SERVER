package com.fsc.xxt.version.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.util.FileUtil;
import com.fsc.util.StringUtil;

import com.fsc.xxt.version.dao.VersionDao;
import com.fsc.xxt.version.po.Version;
import com.fsc.xxt.version.service.VersionService;
import com.fsc.xxt.version.vo.VersionVo;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts2.ServletActionContext;

import java.io.File;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端apk版本服务层接口实现</p>
 * <p>创建日期:Feb 27, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class VersionServiceImpl extends BaseServiceImpl
    implements VersionService {
    private VersionDao versionDao;

    public void setVersionDao(VersionDao versionDao) {
        this.versionDao = versionDao;
    }

    @Override
    public void saveVersion(VersionVo versionVo) throws Exception {
        Version version = new Version();

        if ((versionVo.getId() == null) || "".equals(versionVo.getId())) {
            PropertyUtils.copyProperties(version, versionVo);
            version.setId(getId());
            version.setApkFile(versionVo.getFileFileName());
            version.setPubDate(new Date());

            int innerId = versionDao.selectMaxVersionInnerId();
            version.setVersionInnerId(innerId + 1);
        } else {
            version = this.findVersionById(versionVo.getId());
            PropertyUtils.copyProperties(version, versionVo);

            if (!StringUtil.isEmpty(versionVo.getFileFileName())) {
                String path = ServletActionContext.getServletContext()
                                                  .getRealPath("apkupload");
                File file = new File(path + "\\" + version.getApkFile());
                file.delete();
                version.setApkFile(versionVo.getFileFileName());
                
            }
        }

        this.saveOrUpdateObject(version);
    }

    @Override
    public void selectPageData(VersionVo versionVo) throws Exception {
        String hql = "from Version ";
        String countHql = "select count(*) " + hql;
        hql += "order by versionInnerId desc";
        this.selectPageData(versionVo, hql, countHql);
    }

    @Override
    public Version findVersionById(String id) throws Exception {
        return versionDao.findVersionById(id);
    }

    @Override
    public boolean deleteVersionInfo(String id) throws Exception {
        return versionDao.deleteVersionInfo(id);
    }

    @Override
    public Integer selectMaxVersionInnerId() throws Exception {
        return null;
    }

    @Override
    public Version selectLatestVersion(String projectname) throws Exception {
        return versionDao.selectLatestVersion(projectname);
    }
}
