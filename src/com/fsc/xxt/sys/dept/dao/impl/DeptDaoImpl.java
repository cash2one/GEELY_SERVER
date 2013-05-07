package com.fsc.xxt.sys.dept.dao.impl;

import com.fsc.xxt.sys.dept.dao.DeptDao;
import com.fsc.xxt.sys.dept.po.Dept;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:部门信息管理DAO实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DeptDaoImpl extends BaseDaoImpl implements DeptDao {
    public Dept findDeptById(String id) throws DataAccessException {
        return (Dept) getObject(Dept.class, id);
    }

    public Dept findDeptByDeptNo(String deptNo) throws DataAccessException {
        List<Dept> list = selectDept("deptNo", deptNo);

        if ((list != null) && (list.size() > 0)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List selectDeptList() throws DataAccessException {
        String hql = "from Dept order by deptNo";

        return selectDataByHQL(hql);
    }

    /**
     * 根据字段名条件查找对象
     * @param field
     * @param value
     * @return
     */
    public List<Dept> selectDept(String field, Object value) {
        String hql = "from Dept c where c." + field + "=?";

        return selectDataByHQL(hql, new Object[] { value });
    }
}
