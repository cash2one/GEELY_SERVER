package com.fsc.xxt.sys.log.service;

import com.fsc.xxt.sys.log.po.DataLog;
import com.fsc.xxt.sys.log.vo.DataLogVo;
import com.fsc.framework.base.service.BaseService;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据日志服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface DataLogService extends BaseService {
    /**
     * 根据条件分页查找页面
     * @param dataLogVo
     * @throws Exception
     */
    public void selectPageDataLog(DataLogVo dataLogVo)
        throws Exception;

    /***
     * 更加数据日子信息Id查找数据日志信息实体
     * @param id
     * @return
     * @throws Exception
     */
    public DataLog findDataLogById(String id)
        throws Exception;

    /**
     * 清空数据日子信息
     * @throws Exception
     */
    public void clearDataLog() throws Exception;
}
