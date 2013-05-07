package com.fsc.xxt.sys.user.dao.impl;

import com.fsc.xxt.sys.user.dao.UserDao;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户信息管理DAO实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    public User findUserByLoginName(String loginName)
        throws DataAccessException {
        List<User> list = selectUser("login", loginName);

        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public User findUserById(String id) throws DataAccessException {
        List<User> list = selectUser("id", id);

        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据字段名条件查找对象
     * @param field
     * @param value
     * @return
     */
    public List<User> selectUser(String field, Object value) {
        String hql = "from User c where c." + field + "=?";

        return selectDataByHQL(hql, new Object[] { value });
    }
}
