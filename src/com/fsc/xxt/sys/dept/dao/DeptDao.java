package com.fsc.xxt.sys.dept.dao;

import com.fsc.xxt.sys.dept.po.Dept;
import com.fsc.framework.base.dao.BaseDao;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:部门信息管理DAO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface DeptDao extends BaseDao {
    /**
    * 根据部门ID查找部门信息
    * @param id
    * @return
    * @throws DataAccessException
    */
    public Dept findDeptById(String id) throws DataAccessException;

    /**
     * 根据部门编号查找部门信息
     * @param deptNo
     * @return
     * @throws DataAccessException
     */
    public Dept findDeptByDeptNo(String deptNo) throws DataAccessException;

    /**
     * 查找部门信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectDeptList() throws DataAccessException;
}
