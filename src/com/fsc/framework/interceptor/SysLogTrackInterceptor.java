package com.fsc.framework.interceptor;


import com.fsc.xxt.sys.log.po.SysLog;
import com.fsc.xxt.sys.log.service.SysLogService;
import com.fsc.xxt.sys.privilege.po.Privilege;
import com.fsc.xxt.sys.privilege.service.PrivilegeService;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.constant.CommonConstants;

import com.fsc.util.CommonUtil;
import com.fsc.util.DateUtil;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;

import java.util.Map;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统日志跟踪拦截器</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SysLogTrackInterceptor extends AbstractInterceptor {
    /** 生成记录器Logger类的对象 */
    protected Logger log = Logger.getLogger(getClass());

    /** 日志管理业务逻辑组件 */
    private SysLogService sysLogService;

    /** 功能权限业务逻辑组件 */
    private PrivilegeService privilegeService;

    /** 设置日志管理业务逻辑组件 */
    public void setSysLogService(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    /**
    * 拦截器实现拦截的方法
    * @param ActionInvocation invocation
    * @return String
    * @throws Exception
    */
    public String intercept(ActionInvocation invocation)
        throws Exception {
        /* 获取下一处理的类名和方法名 */
        //String className = invocation.getAction().getClass().getName(); //含包路径的类名
        String classSimpleName = invocation.getAction().getClass()
                                           .getSimpleName();
        String methodName = invocation.getProxy().getMethod();

        Map session = invocation.getInvocationContext().getSession();
        User user = (User) session.get(CommonConstants.USER);
        Privilege privilege = privilegeService.findPrivilegeByClassMethod(classSimpleName,
                methodName);

        if ((user != null) && (privilege != null)) {
            //判断是否需要保存日志记录
            if ((privilege.getSign() != null) && (privilege.getSign() == 1)) {
                //记录日志信息
                StringBuffer sbfMessage = new StringBuffer("");
                String ipAddress = ServletActionContext.getRequest()
                                                       .getRemoteAddr();
                String macAddress = CommonUtil.getLocalMacAddress(sbfMessage);

                SysLog sysLog = new SysLog();
                sysLog.setLogId(sysLogService.getId());
                sysLog.setUserId(user.getId());
                sysLog.setIpAddress(ipAddress);
                sysLog.setMacAddress(macAddress);
                sysLog.setLogContent("访问了功能模块：" + privilege.getMethodDesc());
                sysLog.setLogTime(DateUtil.getDateTimeString());
                sysLogService.insertObject(sysLog);
            }
        }

        return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
    }

    /**
     * 在Action执行前调用
     * @param ActionInvocation invocation
     * @throws Exception
     */
    protected void before(ActionInvocation invocation)
        throws Exception {
    }

    /**
     * 在Action执行之后运行
     * @param ActionInvocation invocation
     * @param String result
     * @throws Exception
     */
    protected void after(ActionInvocation invocation, String result)
        throws Exception {
    }
}
