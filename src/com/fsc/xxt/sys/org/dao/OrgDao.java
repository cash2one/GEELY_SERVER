package com.fsc.xxt.sys.org.dao;

import com.fsc.xxt.sys.org.po.Org;
import com.fsc.framework.base.dao.BaseDao;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:单位机构信息管理DAO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface OrgDao extends BaseDao {
    /**
     * 根据ID查找单位机构信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public Org findOrgById(String id) throws DataAccessException;

    /**
     * 根据单位编号查找单位机构信息
     * @param orgNo
     * @return
     * @throws DataAccessException
     */
    public Org findOrgByOrgNo(String orgNo) throws DataAccessException;

    /**
     * 查询单位机构信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectOrg() throws DataAccessException;
}
