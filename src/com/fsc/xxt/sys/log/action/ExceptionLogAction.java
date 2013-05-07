package com.fsc.xxt.sys.log.action;


import com.fsc.xxt.sys.log.po.ExceptionLog;
import com.fsc.xxt.sys.log.service.ExceptionLogService;
import com.fsc.xxt.sys.log.vo.ExceptionLogVo;
import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:异常日志控制类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ExceptionLogAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 7830737281033202077L;

    /**异常日志信息服务层*/
    private ExceptionLogService exceptionlogService;

    /**异常日子信息Vo层*/
    private ExceptionLogVo exceptionLogVo = new ExceptionLogVo();

    /**采用模型驱动必须实现的方法*/
    
    public BaseVo getModel() {
        return exceptionLogVo;
    }

    public void setExceptionlogService(ExceptionLogService exceptionlogService) {
        this.exceptionlogService = exceptionlogService;
    }

    /**
    * 系统异常日志管理列表
    * @return
    * @throws Exception
    */
    public String list() throws Exception {
        exceptionlogService.selectPageExceptionLog(exceptionLogVo);

        return "list";
    }

    /**
     * 删除系统异常日志信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        ExceptionLog exceptionLog = exceptionlogService.findExceptionLogById(exceptionLogVo.getId());
        exceptionlogService.deleteObject(exceptionLog);

        return SUCCESS;
    }

    /**
     * 清空系统异常日志
     * @return
     * @throws Exception
     */
    public String clear() throws Exception {
        exceptionlogService.clearExceptionLog();

        return SUCCESS;
    }
}
