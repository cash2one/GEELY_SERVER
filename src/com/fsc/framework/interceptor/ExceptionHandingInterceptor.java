package com.fsc.framework.interceptor;

import com.fsc.xxt.sys.log.po.ExceptionLog;
import com.fsc.xxt.sys.log.service.ExceptionLogService;
import com.fsc.framework.exception.CommonException;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import org.apache.log4j.Logger;

import org.hibernate.PropertyValueException;

import org.hibernate.hql.ast.QuerySyntaxException;

import org.springframework.dao.DataAccessException;

import org.springframework.orm.hibernate3.HibernateSystemException;

import java.io.IOException;

import java.sql.BatchUpdateException;
import java.sql.SQLException;

import java.util.Date;

import javax.crypto.IllegalBlockSizeException;

import javax.servlet.ServletException;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:异常处理拦截器</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ExceptionHandingInterceptor extends AbstractInterceptor {
    /** serialVersionUID */
    private static final long serialVersionUID = 4280670439606993583L;

    /**异常日志服务层*/
    private ExceptionLogService exceptionlogService;

    /** 生成记录器Logger类的对象 */
    protected Logger log = Logger.getLogger(this.getClass());

    public void setExceptionlogService(ExceptionLogService exceptionlogService) {
        this.exceptionlogService = exceptionlogService;
    }

    /**
    *  拦截器实现拦截的方法
    *
    * @param ActionInvocation invocation
    * @return String
    * @throws Exception
    */
    public String intercept(ActionInvocation invocation)
        throws Exception {
        //before(invocation);
        String result = "";
        String className = invocation.getAction().getClass().getName();

        try {
            result = invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
        } catch (NumberFormatException e) {
            saveExceptionLog(e, "类型转化异常！", className);
            throw new CommonException("类型转化异常！", new Throwable(e.getCause()));
        } catch (PropertyValueException e) {
            saveExceptionLog(e, "实体中非空属性的值不能为空！", className);
            throw new CommonException("实体中非空属性的值不能为空！",
                new Throwable(e.getCause()));
        } catch (HibernateSystemException e) {
            saveExceptionLog(e, "实体不存在或对象获取失败！", className);
            throw new CommonException("实体不存在或对象获取失败！",
                new Throwable(e.getCause()));
        } catch (BatchUpdateException e) {
            saveExceptionLog(e, "违反唯一约束条件或非空约束条件！", className);
            throw new CommonException("违反唯一约束条件或非空约束条件！",
                new Throwable(e.getCause()));
        } catch (QuerySyntaxException e) {
            saveExceptionLog(e, "查询语句语法错误！", className);
            throw new CommonException("查询语句语法错误！", new Throwable(e.getCause()));
        } catch (DataAccessException e) {
            saveExceptionLog(e, "数据库操作失败！", className);
            throw new CommonException("数据库操作失败！", new Throwable(e.getCause()));
        } catch (NullPointerException e) {
            saveExceptionLog(e, "引用了空对象！", className);
            throw new CommonException("引用了空对象！", new Throwable(e.getCause()));
        } catch (IOException e) {
            saveExceptionLog(e, "IO异常！", className);
            throw new CommonException("IO异常！", new Throwable(e.getCause()));
        } catch (ClassNotFoundException e) {
            saveExceptionLog(e, "指定的类不存在！", className);
            throw new CommonException("指定的类不存在！", new Throwable(e.getCause()));
        } catch (ArithmeticException e) {
            saveExceptionLog(e, "数学运算异常！", className);
            throw new CommonException("数学运算异常！", new Throwable(e.getCause()));
        } catch (ArrayIndexOutOfBoundsException e) {
            saveExceptionLog(e, "数组下标越界!", className);
            throw new CommonException("数组下标越界!", new Throwable(e.getCause()));
        } catch (StringIndexOutOfBoundsException e) {
            saveExceptionLog(e, "字符串索引越界!", className);
            e.printStackTrace();
            throw new CommonException("字符串索引越界!", new Throwable(e.getCause()));
        } catch (IllegalArgumentException e) {
            saveExceptionLog(e, "方法的参数错误！", className);
            throw new CommonException("方法的参数错误！", new Throwable(e.getCause()));
        } catch (ClassCastException e) {
            saveExceptionLog(e, "类型强制转换错误！", className);
            throw new CommonException("类型强制转换错误！", new Throwable(e.getCause()));
        } catch (SecurityException e) {
            saveExceptionLog(e, "违背安全原则异常！", className);
            throw new CommonException("违背安全原则异常！", new Throwable(e.getCause()));
        } catch (SQLException e) {
            saveExceptionLog(e, "数据库操作异常！", className);
            throw new CommonException("数据库操作异常！", new Throwable(e.getCause()));
        } catch (NoSuchMethodException e) {
            saveExceptionLog(e, "执行的方法不存在！", className);
            throw new CommonException("执行的方法不存在！", new Throwable(e.getCause()));
        } catch (NoSuchMethodError e) {
            saveExceptionLog(e, "执行的方法不存在！", className);
            throw new CommonException("执行的方法不存在！", new Throwable(e.getCause()));
        } catch (IllegalBlockSizeException e) {
            saveExceptionLog(e, "解密的数据位数不对！", className);
            throw new CommonException("解密的数据位数不对！", new Throwable(e.getCause()));
        } catch (ServletException e) {
            saveExceptionLog(e, "Servlet异常！", className);
            throw new CommonException("Servlet异常！", new Throwable(e.getCause()));
        } catch (InternalError e) {
            saveExceptionLog(e, "Java虚拟机发生了内部错误！", className);
            throw new CommonException("Java虚拟机发生了内部错误！",
                new Throwable(e.getCause()));
        } catch (Exception e) {
            saveExceptionLog(e, "程序内部错误，操作失败！", className);
            throw new CommonException("程序内部错误，操作失败！",
                new Throwable(e.getCause()));
        }

        //after(invocation, result);
        return result;
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

    /**
     * 保存异常日志信息
     */
    public void saveExceptionLog(Throwable e, String message, String className)
        throws Exception {
        StackTraceElement[] stacks = e.getStackTrace();
        StackTraceElement stack = null;

        for (StackTraceElement s : stacks) {
            if (className.equals(s.getClassName())) {
                stack = s;
            }
        }

        if ((e.getMessage() != null) && !"".equals(e.getCause())) {
            message += e.getMessage();
        }

        ExceptionLog el = new ExceptionLog(exceptionlogService.getId(),
                stack.getClassName(), stack.getMethodName(),
                stack.getLineNumber(), message, new Date());
        exceptionlogService.insertObject(el);
    }
}
