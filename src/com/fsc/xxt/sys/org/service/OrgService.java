package com.fsc.xxt.sys.org.service;

import com.fsc.xxt.sys.org.po.Org;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:单位机构信息管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface OrgService extends BaseService {
    /**
    * 根据ID查找单位机构信息
    * @param id
    * @return
    * @throws DataAccessException
    */
    public Org findOrgById(String id) throws DataAccessException;

    /**
     * 根据单位机构编号查找单位机构信息
     * @param unitNo
     * @return
     * @throws DataAccessException
     */
    public Org findOrgByOrgNo(String orgNo) throws DataAccessException;

    /**
     * 查找单位机构信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectOrg() throws DataAccessException;

    /**
     * 查询排序的单位机构信息列表
     * @param orgNo 顶层单位编号
     * @return
     * @throws DataAccessException
     */
    public List selectOrgSort(String orgNo) throws DataAccessException;

    /**
     * 根据单位编号，查询下级单位机构列表
     * @param orgNo
     * @return
     * @throws Exception
     */
    public List selectOrgByOrgNo(String orgNo) throws Exception;

    /**
     * 查找格式化单位机构信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectFormatOrg() throws DataAccessException;

    /**
     * 查找格式化单位机构信息列表(去除给定的单位及子单位)
     * @return
     * @throws DataAccessException
     */
    public List selectFormatOrg(String id) throws DataAccessException;

    /**
     * 查看在相同上级部门下是否存在相同的部门名称
     * @param porgNo
     * @param name
     * @return
     * @throws Exception
     */
    public boolean ifExistsSameNameInOneOrg(String porgNo, String name)
        throws Exception;
}
