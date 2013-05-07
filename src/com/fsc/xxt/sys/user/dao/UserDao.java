package com.fsc.xxt.sys.user.dao;

import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.dao.BaseDao;


import org.springframework.dao.DataAccessException;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户信息管理DAO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface UserDao extends BaseDao {
    /**
     * 根据用户登录名查找用户信息
     * @param loginName
     * @return
     * @throws DataAccessException
     */
    public User findUserByLoginName(String loginName)
        throws DataAccessException;

    /**
     * 根据用户ID查找用户信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public User findUserById(String id) throws DataAccessException;
}
