package com.fsc.xxt.sys.role.dao;

import com.fsc.xxt.sys.role.po.Role;
import com.fsc.framework.base.dao.BaseDao;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色信息管理DAO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface RoleDao extends BaseDao {
    /**
     * 根据ID查找系统角色信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Role findRoleById(String id) throws DataAccessException;

    /**
     * 查询系统角色信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectRole() throws DataAccessException;
}
