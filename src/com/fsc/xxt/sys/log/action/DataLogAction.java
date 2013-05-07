package com.fsc.xxt.sys.log.action;


import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.xxt.sys.log.po.DataLog;
import com.fsc.xxt.sys.log.service.DataLogService;
import com.fsc.xxt.sys.log.vo.DataLogVo;
import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;
import com.opensymphony.xwork2.ActionContext;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据日志控制类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DataLogAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 7830737281033202077L;

    /**数据日志信息服务层*/
    private DataLogService datalogService;

    /**数据日子信息Vo层*/
    private DataLogVo dataLogVo = new DataLogVo();

    /**采用模型驱动必须实现的方法*/
    
    public BaseVo getModel() {
        return dataLogVo;
    }

    public void setDatalogService(DataLogService datalogService) {
        this.datalogService = datalogService;
    }

    /**
    * 系统数据日志管理列表
    * @return
    * @throws Exception
    */
    public String list() throws Exception {
        datalogService.selectPageDataLog(dataLogVo);
        return "list";
    }

    /**
     * 删除系统数据日志信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        DataLog dataLog = datalogService.findDataLogById(dataLogVo.getId());
        datalogService.deleteObject(dataLog);

        return SUCCESS;
    }

    /**
     * 清空系统数据日志
     * @return
     * @throws Exception
     */
    public String clear() throws Exception {
        datalogService.clearDataLog();

        return SUCCESS;
    }
    
    public String view() throws Exception{
    	DataLog datalog=datalogService.findDataLogById(dataLogVo.getId());
    	PropertyUtils.copyProperties(dataLogVo,datalog);
		return "view";
	}
}
