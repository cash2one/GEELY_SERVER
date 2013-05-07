package com.fsc.xxt.sys.privilege.service.impl;

import com.fsc.xxt.sys.privilege.dao.PrivilegeDao;
import com.fsc.xxt.sys.privilege.po.Privilege;
import com.fsc.xxt.sys.privilege.service.PrivilegeService;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.service.impl.BaseServiceImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统权限信息管理服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class PrivilegeServiceImpl extends BaseServiceImpl
    implements PrivilegeService {
    private PrivilegeDao privilegeDao;

    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    /**
     * 根据ID查找功能权限信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Privilege findPrivilegeById(String id) throws DataAccessException {
        return privilegeDao.findPrivilegeById(id);
    }

    /**
     * 根据功能模块类名、方法名查询功能权限信息
     * @param className
     * @param methodName
     * @return
     */
    public Privilege findPrivilegeByClassMethod(String className,
        String methodName) {
        String hql = "from Privilege where upper(className)=? and upper(methodName)=?";
        List list = privilegeDao.selectDataByHQL(hql,
                new String[] { className.toUpperCase(), methodName.toUpperCase() });

        if (list.size() > 0) {
            return (Privilege) list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据模块ID查找该模块下的系统功能权限信息列表
     * @param moduleId
     * @return
     * @throws Exception
     */
    public List selectPrivilegeByModuleId(Integer moduleId)
        throws Exception {
        return privilegeDao.selectPrivilegeByModuleId(moduleId);
    }

    /**
     * 系统功能权限检查
     * @param user
     * @param className
     * @param methodName
     * @return
     */
    public boolean privilegeCheck(User user, String className, String methodName) {
        boolean flag = true;
        Privilege privilege = findPrivilegeByClassMethod(className, methodName);

        if ((privilege != null) && (user != null) &&
                !"admin".equalsIgnoreCase(user.getLogin())) {
            String hql = "from Privilege where privId=? and privId " +
                "in(select privId from RolePriv where roleId in(select roleId from UserRole where userId " +
                "in(select id from User where id=? and status='01')))";

            List list2 = privilegeDao.selectDataByHQL(hql,
                    new String[] { privilege.getPrivId(), user.getId() });

            if ((list2 == null) || (list2.size() <= 0)) {
                flag = false;
            }
        }

        return flag;
    }
}
