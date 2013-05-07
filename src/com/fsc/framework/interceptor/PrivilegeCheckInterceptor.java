package com.fsc.framework.interceptor;

import com.fsc.xxt.sys.privilege.service.PrivilegeService;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.constant.CommonConstants;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.apache.log4j.Logger;

import java.util.Map;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:功能权限检查拦截器（检查当前用户是否拥有当前操作的权限</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class PrivilegeCheckInterceptor extends AbstractInterceptor {
    /** 生成记录器Logger类的对象 */
    protected Logger log = Logger.getLogger(getClass());

    /** 功能权限检查业务逻辑组件 */
    private PrivilegeService privilegeService;

    /** 设置功能权限检查业务逻辑组件 */
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
        Object object = session.get(CommonConstants.USER);
        User user = null;

        if (object instanceof User) {
            user = (User) object;
        }

        /* 判断当前用户是否拥有下一处理的操作权限 */
        if (privilegeService.privilegeCheck(user, classSimpleName, methodName)) {
            return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
        } else {
            return "privilegeNoEnough"; //跳转至权限不够提示界面
        }
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
