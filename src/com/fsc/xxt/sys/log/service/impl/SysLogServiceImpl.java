package com.fsc.xxt.sys.log.service.impl;

import com.fsc.xxt.sys.log.dao.SysLogDao;
import com.fsc.xxt.sys.log.po.SysLog;
import com.fsc.xxt.sys.log.service.SysLogService;
import com.fsc.xxt.sys.log.vo.SysLogVo;
import com.fsc.framework.base.service.impl.BaseServiceImpl;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志管理服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SysLogServiceImpl extends BaseServiceImpl implements SysLogService {
    private SysLogDao sysLogDao;

    public void setSysLogDao(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    public SysLog findSysLogById(String id) throws DataAccessException {
        return sysLogDao.findSysLogById(id);
    }

    public List selectSysLog() throws DataAccessException {
        return sysLogDao.selectSysLog();
    }

    public void selectPageSysLog(SysLogVo sysLogVo) throws Exception {
        String hql = "from SysLog where 1=1";
        String stime = sysLogVo.getStime();
        String etime = sysLogVo.getEtime();

        if ((stime != null) && !"".equals(stime) && (etime != null) &&
                !"".equals(etime)) {
            stime += " 00:00:00";
            etime += " 23:59:59";
            hql += (" and to_date(logTime,'yyyy-mm-dd hh24:mi:ss') between to_date('" +
            stime + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + etime +
            "','yyyy-mm-dd hh24:mi:ss')");
        }

        String countHql = "select count(logId) " + hql;
        hql += " order by logTime";
        selectPageData(sysLogVo, hql, countHql);
    }

    public void clearSysLog() throws Exception {
        String hql = "delete from SysLog";
        sysLogDao.executeHQL(hql);
    }
}
