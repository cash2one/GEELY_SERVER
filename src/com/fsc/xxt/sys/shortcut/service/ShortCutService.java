package com.fsc.xxt.sys.shortcut.service;

import com.fsc.xxt.sys.shortcut.po.ShortCut;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:快捷菜单管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface ShortCutService extends BaseService {
    /**
    * 保存快捷方式
    * @param shortCut
    * @throws DataAccessException
    */
    void saveShortCut(ShortCut shortCut) throws DataAccessException;

    /**
     * 删除快捷方式信息
     * @param id
     * @throws DataAccessException
     */
    void removeShortCut(String id) throws DataAccessException;

    /**
     * 根据ID查找快捷菜单信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    ShortCut findShortCutById(String id) throws DataAccessException;

    /**
     * 根据菜单模块ID查询用户快捷菜单
     * @param userId
     * @param moduleId
     * @return
     * @throws DataAccessException
     */
    ShortCut findUserShortCutByModuleId(String userId, Integer moduleId)
        throws DataAccessException;

    /**
     * 根据用户ID查询用户快捷菜单列表
     * @param userId
     * @return
     * @throws DataAccessException
     */
    List selectShortCutByUserId(String userId) throws DataAccessException;
}
