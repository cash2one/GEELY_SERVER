package com.fsc.xxt.sys.module.service;

import com.fsc.xxt.sys.module.po.Module;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:菜单模块信息管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface ModuleService extends BaseService {
    /**
     * 根据菜单模块编号查找菜单模块
     * @param moduleCode
     * @return
     * @throws DataAccessException
     */
    Module findModuleByModuleCode(String moduleCode) throws DataAccessException;

    /**
     * 根据父节点菜单编号查找子菜单模块列表
     * @param parentModuleCode
     * @return
     * @throws DataAccessException
     */
    List findModuleByParentModuleCode(String parentModuleCode)
        throws DataAccessException;

    /**
     * 根据父节点菜单编号查找子菜单模块列表（包含隐藏菜单模块）
     * @param parentModuleCode
     * @return
     * @throws DataAccessException
     */
    List findModuleByParentModuleCode2(String parentModuleCode)
        throws DataAccessException;

    /**
     * 查询登录用户一级模块菜单列表
     * @param userId
     * @param parentModuleCode
     * @return
     * @throws DataAccessException
     */
    List findModuleByUserFirstModule(String userId) throws DataAccessException;

    /**
     * 查询登录用户二级模块菜单列表
     * @param userId
     * @param parentModuleCode
     * @return
     * @throws DataAccessException
     */
    List findModuleByUserSecondModule(String userId, String parentModuleCode)
        throws DataAccessException;

    /**
     * 根据父节点菜单编号查找子菜单模块列表(递归查询所有子级模块菜单)
     * @param parentModuleCode
     * @return
     * @throws DataAccessException
     */
    List findAllModuleByParentModuleCode(String parentModuleCode)
        throws DataAccessException;
}
