package com.fsc.xxt.sys.log.po;

import com.fsc.framework.base.po.Base;

import java.util.Date;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据日志实体信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DataLog extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 4378782299589910421L;

    /**Id标识*/
    private String id;

    /**类名*/
    private String className;

    /**方法名*/
    private String methodName;

    /**发生行号*/
    private Integer lineNumber;

    /**异常信息*/
    private String message;

    /**发生时间*/
    private Date time;
    
    private Integer type;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public DataLog(String id, String className, String methodName,
        Integer lineNumber, String message, Date time) {
        super();
        this.id = id;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.message = message;
        this.time = time;
    }

    public DataLog() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
