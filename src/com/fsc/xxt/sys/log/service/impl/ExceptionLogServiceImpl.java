package com.fsc.xxt.sys.log.service.impl;

import com.fsc.xxt.sys.log.po.ExceptionLog;
import com.fsc.xxt.sys.log.service.ExceptionLogService;
import com.fsc.xxt.sys.log.vo.ExceptionLogVo;
import com.fsc.framework.base.service.impl.BaseServiceImpl;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:异常日志服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ExceptionLogServiceImpl extends BaseServiceImpl
    implements ExceptionLogService {
    public void clearExceptionLog() throws Exception {
        String hql = "delete from ExceptionLog";
        this.executeHQL(hql);
    }

    public ExceptionLog findExceptionLogById(String id)
        throws Exception {
        String hql = "from ExceptionLog e where e.id='" + id + "'";

        return (ExceptionLog) this.getObject(hql);
    }

    public void selectPageExceptionLog(ExceptionLogVo exceptionLogVo)
        throws Exception {
        StringBuffer sb = new StringBuffer();

        if ((exceptionLogVo.getSTime() != null) &&
                !"".equals(exceptionLogVo.getSTime())) {
            sb.append(" and e.time>=");
            sb.append("to_date('" + exceptionLogVo.getSTime() +
                "','yyyy-MM-dd')");
        }

        if ((exceptionLogVo.getETime() != null) &&
                !"".equals(exceptionLogVo.getETime())) {
            sb.append(" and e.time<=");
            sb.append("to_date('" + exceptionLogVo.getSTime() +
                "','yyyy-MM-dd')");
        }

        String countHql = "select count(id) from ExceptionLog e where 1=1" +
            sb.toString();
        String hql = "from ExceptionLog e where 1=1" + sb.toString() +
            " order by e.time";
        this.selectPageData(exceptionLogVo, hql, countHql);
    }
}
