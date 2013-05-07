package com.fsc.xxt.sys.log.dao;

import com.fsc.xxt.sys.log.po.SysLog;
import com.fsc.framework.base.dao.BaseDao;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志信息管理DAO接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface SysLogDao extends BaseDao {
    /**
     * 根据ID查找系统日志信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public SysLog findSysLogById(String id) throws DataAccessException;

    /**
     * 查询系统日志信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectSysLog() throws DataAccessException;
}
