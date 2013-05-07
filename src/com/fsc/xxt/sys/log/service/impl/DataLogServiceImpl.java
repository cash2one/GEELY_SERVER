package com.fsc.xxt.sys.log.service.impl;

import com.fsc.xxt.sys.log.po.DataLog;
import com.fsc.xxt.sys.log.service.DataLogService;
import com.fsc.xxt.sys.log.vo.DataLogVo;
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
public class DataLogServiceImpl extends BaseServiceImpl
    implements DataLogService {
    public void clearDataLog() throws Exception {
        String hql = "delete from DataLog";
        this.executeHQL(hql);
    }

    public DataLog findDataLogById(String id)
        throws Exception {
        String hql = "from DataLog e where e.id='" + id + "'";

        return (DataLog) this.getObject(hql);
    }

    public void selectPageDataLog(DataLogVo dataLogVo)
        throws Exception {
        StringBuffer sb = new StringBuffer();

        if ((dataLogVo.getSTime() != null) &&
                !"".equals(dataLogVo.getSTime())) {
            sb.append(" and e.time>=");
            sb.append("to_date('" + dataLogVo.getSTime() +
                "','yyyy-MM-dd')");
        }

        if ((dataLogVo.getETime() != null) &&
                !"".equals(dataLogVo.getETime())) {
            sb.append(" and e.time<=");
            sb.append("to_date('" + dataLogVo.getSTime() +
                "','yyyy-MM-dd')");
        }
        
        if ((dataLogVo.getType() != null) &&
                !"".equals(dataLogVo.getType())) {
            sb.append(" and e.type="+dataLogVo.getType());
        }

        String countHql = "select count(id) from DataLog e where 1=1" +
            sb.toString();
        String hql = "from DataLog e where 1=1" + sb.toString() +
            " order by e.time";
        this.selectPageData(dataLogVo, hql, countHql);
    }
}
