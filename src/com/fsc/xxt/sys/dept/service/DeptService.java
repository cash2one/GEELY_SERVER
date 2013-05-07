package com.fsc.xxt.sys.dept.service;

import com.fsc.xxt.sys.dept.po.Dept;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:部门信息管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface DeptService extends BaseService {
    /**
    * 根据部门ID查找部门信息
    * @param id
    * @return
    * @throws DataAccessException
    */
    public Dept findDeptById(String id) throws DataAccessException;

    /**
     * 根据所属单位编号、上级部门编号查询下级部门列表
     * @param orgNo
     * @param deptId
     * @return
     * @throws DataAccessException
     */
    public List selectDeptByPdeptNo(String orgNo, String deptId)
        throws DataAccessException;

    /**
     * 查询排序的部门信息列表
     * @param orgNo 单位编号
     * @param deptId 顶层部门编号
     * @return
     * @throws DataAccessException
     */
    public List selectDeptSort(String orgNo, String deptId)
        throws DataAccessException;

    /**
     * 查询排序的部门信息列表，并排除掉ID的部门及下级部门
     * @param id
     * @param orgNo
     * @param deptId
     * @return
     * @throws DataAccessException
     */
    public List selectDeptSort(String id, String orgNo, String deptId)
        throws DataAccessException;

    /**
     * 查找部门信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectDeptList() throws DataAccessException;

    /**
     * 把具有上下级关系的实体集通特殊处理转化为新的实体集
     * @param list
     * @return
     * @throws SQLException
     */
    List transformList(List list) throws SQLException;

    /**
     * 把具有上下级关系的实体集通特殊处理转化为新的实体集
     * @param list
     * @param prefix
     * @return
     * @throws SQLException
     */
    List transformList(List list, String prefix) throws SQLException;

    /**
     * 总长度15位，前5位为所属单位编号的前5位，后面10位为顺序号
     * @param orgNo
     * @return
     * @throws Exception
     */
    public String genDeptNo(String orgNo) throws Exception;

    /**
     * 根据供电单位获取部门列表
     * @param orgNo
     * @return
     * @throws Exception
     */
    public List<Dept> selectDeptByPreviousOrgNo(String orgNo)
        throws Exception;

    /**
     * 是否存在相同的名字在同一部门下
     * @param pdeptNo
     * @param name
     * @return
     * @throws Exception
     */
    public boolean ifExistsSameNameInOneDept(String pdeptNo, String name)
        throws Exception;
}
