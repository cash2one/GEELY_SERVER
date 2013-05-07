package com.fsc.xxt.sys.log.service;

import com.fsc.xxt.sys.log.po.SysLog;
import com.fsc.xxt.sys.log.vo.SysLogVo;
import com.fsc.framework.base.service.BaseService;


import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志信息管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface SysLogService extends BaseService {
    /**
     * 根据ID查找系统日志信息
     * @param id
     * @return
     * @throws DataAccessException
     */
    public SysLog findSysLogById(String id) throws DataAccessException;

    /**
     * 查找系统日志信息列表
     * @return
     * @throws DataAccessException
     */
    public List selectSysLog() throws DataAccessException;

    /**
     * 分页查找系统日志信息，查询条件参数通过Vo传递，将查询结果置于VO中，VO中传递分页参数值
     * @param roleVo
     * @throws Exception
     */
    public void selectPageSysLog(SysLogVo sysLogVo) throws Exception;

    /**
     * 清空系统日志
     * @throws Exception
     */
    public void clearSysLog() throws Exception;
}
