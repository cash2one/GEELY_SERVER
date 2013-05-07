package com.fsc.xxt.sys.shortcut.service.impl;

import com.fsc.xxt.sys.shortcut.dao.ShortCutDao;
import com.fsc.xxt.sys.shortcut.po.ShortCut;
import com.fsc.xxt.sys.shortcut.service.ShortCutService;
import com.fsc.framework.base.service.impl.BaseServiceImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:快捷菜单信息管理服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ShortCutServiceImpl extends BaseServiceImpl
    implements ShortCutService {
    private ShortCutDao shortCutDao;

    public void setShortCutDao(ShortCutDao shortCutDao) {
        this.shortCutDao = shortCutDao;
    }

    public void saveShortCut(ShortCut shortCut) throws DataAccessException {
        shortCutDao.saveShortCut(shortCut);
    }

    public void removeShortCut(String id) throws DataAccessException {
        shortCutDao.removeShortCut(id);
    }

    public ShortCut findShortCutById(String id) throws DataAccessException {
        return shortCutDao.findShortCutById(id);
    }

    public ShortCut findUserShortCutByModuleId(String userId, Integer moduleId)
        throws DataAccessException {
        return shortCutDao.findUserShortCutByModuleId(userId, moduleId);
    }

    public List selectShortCutByUserId(String userId)
        throws DataAccessException {
        return shortCutDao.selectShortCutByUserId(userId);
    }
}
