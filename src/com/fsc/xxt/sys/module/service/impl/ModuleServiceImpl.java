package com.fsc.xxt.sys.module.service.impl;

import com.fsc.xxt.sys.module.dao.ModuleDao;
import com.fsc.xxt.sys.module.po.Module;
import com.fsc.xxt.sys.module.service.ModuleService;
import com.fsc.framework.base.service.impl.BaseServiceImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统模块菜单信息管理服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ModuleServiceImpl extends BaseServiceImpl implements ModuleService {
    private ModuleDao moduleDao;

    public void setModuleDao(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    public Module findModuleByModuleCode(String moduleCode)
        throws DataAccessException {
        return moduleDao.findModuleByModuleCode(moduleCode);
    }

    public List findModuleByParentModuleCode(String parentModuleCode)
        throws DataAccessException {
        return moduleDao.findModuleByParentModuleCode(parentModuleCode);
    }

    public List findModuleByParentModuleCode2(String parentModuleCode)
        throws DataAccessException {
        return moduleDao.findModuleByParentModuleCode2(parentModuleCode);
    }

    public List findModuleByUserFirstModule(String userId)
        throws DataAccessException {
        return moduleDao.findModuleByUserFirstModule(userId);
    }

    public List findModuleByUserSecondModule(String userId,
        String parentModuleCode) throws DataAccessException {
        return moduleDao.findModuleByUserSecondModule(userId, parentModuleCode);
    }

    public List findAllModuleByParentModuleCode(String parentModuleCode)
        throws DataAccessException {
        return moduleDao.findAllModuleByParentModuleCode(parentModuleCode);
    }
}
