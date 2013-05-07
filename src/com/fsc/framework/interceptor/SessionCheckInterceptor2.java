package com.fsc.framework.interceptor;

import com.fsc.framework.constant.CommonConstants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.apache.struts2.ServletActionContext;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:(前台站点)会话状态检查拦截器（检查用户是否已登录且会话尚未超时）</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SessionCheckInterceptor2 extends AbstractInterceptor {
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
            Object webUser = session.get(CommonConstants.WEBUSER);

            if (webUser != null) {
                return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法 
            } else {
                //保存用户请求的URL
                HttpServletRequest request = ServletActionContext.getRequest();
                StringBuffer sb = request.getRequestURL();
                sb.append("?");

                Enumeration paremerters = request.getParameterNames();

                if (paremerters.hasMoreElements()) {
                    String name = (String) paremerters.nextElement();
                    sb.append(name + "=");
                    sb.append(request.getParameter(name));
                }

                session.put(CommonConstants.URL, sb.toString());

                return CommonConstants.NOLOGIN; //会话超时跳转至重新登录页面
            }
        } else {
            //保存用户请求的URL
            HttpServletRequest request = ServletActionContext.getRequest();
            StringBuffer sb = request.getRequestURL();
            sb.append("?");

            Enumeration paremerters = request.getParameterNames();

            if (paremerters.hasMoreElements()) {
                String name = (String) paremerters.nextElement();
                sb.append(name + "=");
                sb.append(request.getParameter(name));
            }

            session.put(CommonConstants.URL, sb.toString());

            return CommonConstants.NOLOGIN;
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
