package com.fsc.xxt.sys.log.action;

import com.fsc.xxt.sys.log.po.SysLog;
import com.fsc.xxt.sys.log.service.SysLogService;
import com.fsc.xxt.sys.log.vo.SysLogVo;
import com.fsc.framework.base.action.ManageAction;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志管理控制类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SysLogAction extends ManageAction {
    private SysLogVo sysLogVo;
    private SysLogService sysLogService;

    public SysLogAction() {
        sysLogVo = new SysLogVo();
    }

    public void setSysLogService(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    public SysLogVo getModel() {
        return sysLogVo;
    }

    /**
     * 系统日志管理列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        sysLogService.selectPageSysLog(sysLogVo);

        return "list";
    }

    /**
     * 删除系统日志信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        SysLog sysLog = sysLogService.findSysLogById(sysLogVo.getLogId());
        sysLogService.deleteObject(sysLog);

        return SUCCESS;
    }

    /**
     * 清空系统日志
     * @return
     * @throws Exception
     */
    public String clear() throws Exception {
        sysLogService.clearSysLog();

        return SUCCESS;
    }
}
