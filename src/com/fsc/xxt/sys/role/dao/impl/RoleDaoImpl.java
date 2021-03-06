package com.fsc.xxt.sys.role.dao.impl;

import com.fsc.xxt.sys.role.dao.RoleDao;
import com.fsc.xxt.sys.role.po.Role;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色信息管理DAO实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {
    public Role findRoleById(String id) throws DataAccessException {
        return (Role) getObject(Role.class, id);
    }

    public List selectRole() throws DataAccessException {
        String hql = "from Role order by orderNum";

        return selectDataByHQL(hql);
    }
}
