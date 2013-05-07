package com.fsc.xxt.sys.user.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.xxt.sys.dept.dao.DeptDao;
import com.fsc.xxt.sys.org.dao.OrgDao;
import com.fsc.xxt.sys.user.dao.UserDao;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.xxt.sys.user.po.UserRole;
import com.fsc.xxt.sys.user.service.UserService;
import com.fsc.xxt.sys.user.vo.UserVo;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:用户信息管理服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByLoginName(String loginName)
        throws DataAccessException {
        User user = userDao.findUserByLoginName(loginName);

        return user;
    }

    public User findUserById(String id) throws DataAccessException {
        User user = userDao.findUserById(id);

        return user;
    }

    public List selectUser(UserVo userVo) throws DataAccessException {
        String hql = "from User where login<>'admin' ";
        String deptId = userVo.getDeptId();

        if ((deptId != null) && !"".equals(deptId)) {
            hql += (" and deptId='" + deptId + "'");
        }

        return userDao.selectDataByHQL(hql);
    }

    public void selectPageUser(UserVo userVo) throws Exception {
        String hql = "from User where login<>'admin' ";
        String deptId = userVo.getDeptId();
        String status = userVo.getStatus();
        String login=userVo.getLogin();
        if ((deptId != null) && !"".equals(deptId)) {
            hql += (" and deptId='" + deptId + "'");
        }

        if ((status != null) && !"".equals(status)) {
            hql += (" and status='" + status + "'");
        }
        
        if ((login != null) && !"".equals(login)) {
            hql += (" and login like '%" + login + "%'");
        }

        String countHql = "select count(name) " + hql;
        this.selectPageData(userVo, hql, countHql);
    }

    public List selectUserRole(String userId) throws Exception {
        String hql = "from Role where roleId in(select roleId from UserRole where userId=?)";

        return userDao.selectDataByHQL(hql, new String[] { userId });
    }

    public void saveUserRole(String userId, String[] roleId)
        throws Exception {
        String hql = "from UserRole where userId=?";
        List list = userDao.selectDataByHQL(hql, new String[] { userId });
        userDao.deleteCollection(list);

        if (roleId != null) {
            for (int i = 0; i < roleId.length; i++) {
                UserRole userRole = new UserRole();
                userRole.setId(getId());
                userRole.setUserId(userId);
                userRole.setRoleId(roleId[i]);
                insertObject(userRole);
            }
        }
    }

    public List selectUserByDeptId(String deptId) throws Exception {
        String hql = "from User where login<>'admin' and deptId=?";

        return userDao.selectDataByHQL(hql, new String[] { deptId });
    }
    
}