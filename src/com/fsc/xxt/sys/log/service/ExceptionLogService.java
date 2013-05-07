package com.fsc.xxt.sys.log.service;

import com.fsc.xxt.sys.log.po.ExceptionLog;
import com.fsc.xxt.sys.log.vo.ExceptionLogVo;
import com.fsc.framework.base.service.BaseService;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:异常日志服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface ExceptionLogService extends BaseService {
    /**
     * 根据条件分页查找页面数据
     * @param exceptionLogVo
     * @throws Exception
     */
    public void selectPageExceptionLog(ExceptionLogVo exceptionLogVo)
        throws Exception;

    /***
     * 更加异常日子信息Id查找异常日志信息实体
     * @param id
     * @return
     * @throws Exception
     */
    public ExceptionLog findExceptionLogById(String id)
        throws Exception;

    /**
     * 清空异常日子信息
     * @throws Exception
     */
    public void clearExceptionLog() throws Exception;
}
