package com.fsc.xxt.sys.shortcut.dao.impl;

import com.fsc.xxt.sys.shortcut.dao.ShortCutDao;
import com.fsc.xxt.sys.shortcut.po.ShortCut;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:快捷菜单管理DAO接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ShortCutDaoImpl extends BaseDaoImpl implements ShortCutDao {
    public void saveShortCut(ShortCut shortCut) throws DataAccessException {
        saveOrUpdateObject(shortCut);
    }

    public void removeShortCut(String id) throws DataAccessException {
        ShortCut shortCut = (ShortCut) getHibernateTemplate()
                                           .load(ShortCut.class, id);
        deleteObject(shortCut);
    }

    public ShortCut findShortCutById(String id) throws DataAccessException {
        return (ShortCut) getHibernateTemplate().get(ShortCut.class, id);
    }

    /**
     * 根据菜单模块ID查询用户快捷菜单
     * @param userId
     * @param moduleId
     * @return
     * @throws DataAccessException
     */
    public ShortCut findUserShortCutByModuleId(String userId, Integer moduleId)
        throws DataAccessException {
        String hql = "from ShortCut where userId=? and moduleId=?";
        List list = this.selectDataByHQL(hql, new Object[] { userId, moduleId });

        if (list.size() > 0) {
            return (ShortCut) list.get(0);
        } else {
            return null;
        }
    }

    public List selectShortCutByUserId(String userId)
        throws DataAccessException {
        String hql = "from ShortCut where userId=?";

        return this.selectDataByHQL(hql, new String[] { userId });
    }
}
