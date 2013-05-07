package com.fsc.xxt.sys.log.dao.impl;

import com.fsc.xxt.sys.log.dao.SysLogDao;
import com.fsc.xxt.sys.log.po.SysLog;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志信息DAO实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SysLogDaoImpl extends BaseDaoImpl implements SysLogDao {
    public SysLog findSysLogById(String id) throws DataAccessException {
        return (SysLog) getObject(SysLog.class, id);
    }

    public List selectSysLog() throws DataAccessException {
        String hql = "from SysLog order by logTime";

        return selectDataByHQL(hql);
    }
}
