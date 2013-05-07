package com.fsc.xxt.sys.privilege.dao;

import com.fsc.xxt.sys.privilege.po.Privilege;
import com.fsc.framework.base.dao.BaseDao;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统功能权限信息Dao接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface PrivilegeDao extends BaseDao {
    /**
     * 根据ID查找功能权限信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Privilege findPrivilegeById(String id) throws DataAccessException;

    /**
     * 根据模块ID查找该模块下的系统功能权限信息列表
     * @param moduleId
     * @return
     * @throws Exception
     */
    public List selectPrivilegeByModuleId(Integer moduleId)
        throws Exception;
}
