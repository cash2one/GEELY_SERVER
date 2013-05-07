package com.fsc.xxt.sys.org.dao.impl;

import com.fsc.xxt.sys.org.dao.OrgDao;
import com.fsc.xxt.sys.org.po.Org;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:单位机构信息管理DAO实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class OrgDaoImpl extends BaseDaoImpl implements OrgDao {
    public Org findOrgById(String id) throws DataAccessException {
        return (Org) getObject(Org.class, id);
    }

    public Org findOrgByOrgNo(String orgNo) throws DataAccessException {
        String hql = "from Org where orgNo=?";
        List list = this.selectDataByHQL(hql, new String[] { orgNo });

        if (list.size() > 0) {
            return (Org) list.get(0);
        } else {
            return null;
        }
    }

    public List selectOrg() throws DataAccessException {
        String hql = "from Org order by orgNo,sortNo";

        return selectDataByHQL(hql);
    }
}
