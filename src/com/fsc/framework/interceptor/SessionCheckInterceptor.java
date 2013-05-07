package com.fsc.framework.interceptor;

import com.fsc.framework.constant.CommonConstants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:(系统后台管理)会话状态检查拦截器（检查用户是否已登录且会话尚未超时）</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SessionCheckInterceptor extends AbstractInterceptor {
    /**
    * 拦截器实现拦截的方法
    * @param ActionInvocation invocation
    * @return String
    * @throws Exception
    */
    public String intercept(ActionInvocation invocation)
        throws Exception {
        Map session = invocation.getInvocationContext().getSession();

        if (session != null) {
            Object user = session.get(CommonConstants.USER);

            if (user != null) {
                return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法 
            } else {
                return "sessionTimeOut"; //跳至会话超时提示界面
            }
        } else {
            return "sessionTimeOut";
        }

        /*//查找是否是需要拦截的模块
        Object value = module.get(invocation.getAction().getClass().getName());
        
        if (value != null) {
            Map session = invocation.getInvocationContext().getSession();
            Object obj = null;
        
            if (value.equals(CommonConstants.USER)) {
                //后台系统用户
                obj = session.get(CommonConstants.USER);
        
                if (obj == null) {
                    return "sessionTimeOut"; //跳至会话超时提示界面
                }
            }
        
            if (value.equals(CommonConstants.WEBUSER)) {
                //前台客户
                obj = session.get(CommonConstants.WEBUSER);
        
                if (obj == null) {
                    return CommonConstants.NOLOGIN;
                }
            }
        
            return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
        } else {
            return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
        }*/
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
