package com.fsc.framework.base.action;

import com.fsc.framework.base.service.BaseService;
import com.fsc.framework.base.vo.BaseVo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import java.io.PrintWriter;

import java.net.URLEncoder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:业务控制器</p>
 * <p>创建日期:2010-05-25  15:50:49</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 */
public class BaseAction extends ActionSupport implements ModelDriven<Object>,
    Preparable, SessionAware, ServletRequestAware, ServletResponseAware {
    /** serialVersionUID 用来表明类的不同版本间的兼容性 */
    private static final long serialVersionUID = 42466100065710854L;
    
    /** 生成记录器Logger类的对象 */
    protected Logger log = Logger.getLogger(this.getClass());

    /** 定义用于封装请求参数和处理结果的模型 */
    private BaseVo baseVo = new BaseVo();

    /** 业务逻辑组件 */
    private BaseService baseService;
    private String msg;
    protected Map session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /** 实现ModelDriven接口必须实现的方法 */
    public BaseVo getModel() {
        return baseVo;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    /** 设置业务逻辑组件 */
    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    /** 预处理方法 */
    public void prepare() throws Exception {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 验证码效验
     * @return
     * @throws Exception
     */
    public String validateCode() throws Exception {
        String imageVerifyCode = (String) session.get("imageVerifyCode"); //获取session中验证码信息

        if (imageVerifyCode.equalsIgnoreCase(baseVo.getVerifyCode())) {
            msg = "1";
        } else {
            msg = "0";
        }

        return SUCCESS;
    }
}
