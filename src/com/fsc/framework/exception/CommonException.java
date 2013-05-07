package com.fsc.framework.exception;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:通用异常处理类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class CommonException extends RuntimeException {
    /** serialVersionUID */
    private static final long serialVersionUID = -6454694475156989765L;

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(createOutputMessage(message));
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause) {
        super(createOutputMessage(message), cause);
    }

    /**
     * 生成友好的异常输出消息
     *
     * @param String msgBody 消息主体
     * @return String
     */
    private static String createOutputMessage(String msgBody) {
        String prefixStr = "抱歉，";

        String suffixStr = " 请稍后再试或与管理员联系！";

        StringBuffer outputMessage = new StringBuffer("");

        outputMessage.append(prefixStr);

        outputMessage.append(msgBody);

        outputMessage.append(suffixStr);

        return outputMessage.toString();
    }
}
