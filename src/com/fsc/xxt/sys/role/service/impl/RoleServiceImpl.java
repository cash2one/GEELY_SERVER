package com.fsc.xxt.sys.role.service.impl;

import com.fsc.xxt.sys.role.dao.RoleDao;
import com.fsc.xxt.sys.role.po.Role;
import com.fsc.xxt.sys.role.po.RolePriv;
import com.fsc.xxt.sys.role.service.RoleService;
import com.fsc.xxt.sys.role.vo.RoleVo;
import com.fsc.framework.base.service.impl.BaseServiceImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色管理服务接口实现</p>
 * <p>创建日期:2010-11-30</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role findRoleById(String id) throws DataAccessException {
        return roleDao.findRoleById(id);
    }

    public Role findRoleByName(String roleName) throws DataAccessException {
        String hql = "from Role where roleName=?";
        List list = roleDao.selectDataByHQL(hql, new String[] { roleName });

        if (list.size() > 0) {
            return (Role) list.get(0);
        } else {
            return null;
        }
    }

    public List selectRole() throws DataAccessException {
        return roleDao.selectRole();
    }

    public void selectPageRole(RoleVo roleVo) throws Exception {
        String hql = "from Role ";
        String countHql = "select count(roleId) from Role";
        hql += " order by orderNum";
        selectPageData(roleVo, hql, countHql);
    }

    public List selectRolePriv(String roleId) throws Exception {
        String hql = "from Privilege where privId in(select privId from RolePriv where roleId=?)";

        return roleDao.selectDataByHQL(hql, new String[] { roleId });
    }

    public void saveRolePriv(String roleId, String[] priv)
        throws Exception {
        String hql = "from RolePriv where roleId=?";
        List list = roleDao.selectDataByHQL(hql, new String[] { roleId });
        roleDao.deleteCollection(list);

        if (priv != null) {
            for (int i = 0; i < priv.length; i++) {
                RolePriv rolePriv = new RolePriv();
                rolePriv.setRolePrivId(getId());
                rolePriv.setRoleId(roleId);
                rolePriv.setPrivId(priv[i]);
                roleDao.insertObject(rolePriv);
            }
        }
    }

    public void deleteRole(String roleId) throws Exception {
        Role role = this.findRoleById(roleId);

        if (role != null) {
            String hql = "from RolePriv where roleId=?";
            List list = roleDao.selectDataByHQL(hql, new String[] { roleId });
            deleteCollection(list);
            deleteObject(role);
        }
    }

    public List selectUserRole(String roleId) throws Exception {
        String hql = "from UserRole where roleId=?";

        return roleDao.selectDataByHQL(hql, new String[] { roleId });
    }
}
