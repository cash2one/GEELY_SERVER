package com.fsc.xxt.sys.module.dao.impl;

import com.fsc.xxt.sys.module.dao.ModuleDao;
import com.fsc.xxt.sys.module.po.Module;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:菜单模块Dao接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ModuleDaoImpl extends BaseDaoImpl implements ModuleDao {
    public void saveModule(Module b) throws DataAccessException {
        saveOrUpdateObject(b);
    }

    public void removeModule(Integer id) throws DataAccessException {
        Module module = (Module) getHibernateTemplate()
                                     .load(Module.class, id);
        deleteObject(module);
    }

    public Module findModuleById(Integer id) throws DataAccessException {
        return (Module) getHibernateTemplate().get(Module.class, id);
    }

    public Module findModuleByModuleCode(String moduleCode)
        throws DataAccessException {
        String hql = "from Module c where c.sign=1 and c.moduleCode=? order by c.moduleCode asc";
        List list = selectDataByHQL(hql, new String[] { moduleCode });

        if (list.size() > 0) {
            return (Module) list.get(0);
        } else {
            return null;
        }
    }

    public List findModuleByParentModuleCode(String parentModuleCode)
        throws DataAccessException {
        String hql = "from Module c where c.sign=1 and c.parentModuleCode = '" +
            parentModuleCode + "' order by c.moduleCode asc";

        return getHibernateTemplate().find(hql);
    }

    public List findModuleByParentModuleCode2(String parentModuleCode)
        throws DataAccessException {
        String hql = "from Module c where c.sign<=1 and c.parentModuleCode = '" +
            parentModuleCode + "' order by c.moduleCode asc";

        return getHibernateTemplate().find(hql);
    }

    public List findModuleByUserFirstModule(String userId)
        throws DataAccessException {
        String hql = "from Module c where c.sign=1 and c.parentModuleCode=0 " +
            "and  exists ( select d.parentModuleCode  from Module d where d.parentModuleCode = c.moduleCode and  d.sign=1 and  exists (select e.moduleId from Privilege e where e.moduleId = d.moduleId and  exists (" +
            "select f.privId from RolePriv f where f.privId = e.privId and  exists (" +
            "select g.roleId from UserRole g where g.roleId = f.roleId and  g.userId=?)))) " +
            "order by c.moduleCode asc";
        List list = getHibernateTemplate().find(hql, new Object[] { userId });

        return list;
    }

    public List findModuleByUserSecondModule(String userId,
        String parentModuleCode) throws DataAccessException {
        String hql = "from Module c where c.sign=1 and c.parentModuleCode=? " +
            "and  exists (select d.moduleId from Privilege d where  d.moduleId = c.moduleId and   exists (" +
            "select e.privId from RolePriv e where e.privId = d.privId and  exists (" +
            "select f.roleId from UserRole f where f.roleId = e.roleId and f.userId=?))) " +
            "order by c.moduleCode asc";
        List list = getHibernateTemplate()
                        .find(hql, new Object[] { parentModuleCode, userId });

        return list;
    }

    public List findAllModuleByParentModuleCode(String parentModuleCode)
        throws DataAccessException {
        String hql = "from Module m where m.parentModuleCode=? and m.sign<=1 order by m.moduleCode asc";
        List list = getHibernateTemplate()
                        .find(hql, new Object[] { parentModuleCode });

        if ((list != null) && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Module s = (Module) list.get(i);
                s.setModuleName(s.getModuleName());

                List list1 = findAllModuleByParentModuleCode(s.getModuleCode());

                if ((list1 != null) && !list1.isEmpty()) {
                    if (i < (list.size() - 1)) { //加在中间
                        list.addAll(i + 1, list1);
                    } else { //加在最后
                        list.addAll(list1);
                    }
                }
            }
        }

        return list;
    }
}
