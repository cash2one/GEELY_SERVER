package com.fsc.framework.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统合法性/有效性检查拦截器（检查系统是否已初始化和注册）</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SystemValidityCheckInterceptor extends AbstractInterceptor {
    /** 系统基础信息管理业务逻辑组件 */
    //private SystemService systemService;

    /** 设置系统基础信息管理业务逻辑组件 */
    /*public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }*/

    /**
     * 拦截器实现拦截的方法
     *
     * @param ActionInvocation invocation
     * @return String
     * @throws Exception
     */
    public String intercept(ActionInvocation invocation)
        throws Exception {
        StringBuffer sbfMessage = new StringBuffer("");
        Integer sign = 1; //systemService.checkSystemValidity(sbfMessage);

        /* 0:注册版,1:正式版,2:未注册或期限已到,3:未初始化设置 */
        if (sign == 3) {
            return "setup";
        } else if (sign == 2) {
            return "register";
        } else {
            return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
        }
    }

    /**
     * 在Action执行前调用
     *
     * @param ActionInvocation invocation
     * @throws Exception
     */
    protected void before(ActionInvocation invocation)
        throws Exception {
    }

    /**
     * 在Action执行之后运行
     *
     * @param ActionInvocation invocation
     * @param String result
     * @throws Exception
     */
    protected void after(ActionInvocation invocation, String result)
        throws Exception {
    }
}
