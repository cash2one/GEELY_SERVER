package com.fsc.xxt.sys.privilege.vo;

import com.fsc.xxt.sys.module.po.Module;
import com.fsc.framework.base.vo.BaseVo;


import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统功能权限信息VO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class PrivilegeVo extends BaseVo {
    /** 标识ID */
    private String privId;

    /** 类名称 */
    private String className;

    /** 类描述 */
    private String classDesc;

    /** 方法名称 */
    private String methodName;

    /** 方法描述 */
    private String methodDesc;

    /** 菜单ID */
    private Integer moduleId;

    /** 排序号 */
    private Integer orderNum;

    /** 标志(0-默认情况；1-保存日志；2-隐藏不显示) */
    private Integer sign;

    /** 系统菜单模块信息列表 */
    private List moduleList;

    /** 所属菜单模块信息 */
    private Module module;

    public PrivilegeVo() {
    }

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public List getModuleList() {
        return moduleList;
    }

    public void setModuleList(List moduleList) {
        this.moduleList = moduleList;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
