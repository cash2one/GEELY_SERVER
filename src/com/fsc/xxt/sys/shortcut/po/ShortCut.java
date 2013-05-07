package com.fsc.xxt.sys.shortcut.po;

import com.fsc.xxt.sys.module.po.Module;
import com.fsc.framework.base.po.Base;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统快捷菜单信息POJO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ShortCut extends Base {
    /** ID */
    private String id;

    /** 模块ID*/
    private Integer moduleId;

    /** 系统用户ID */
    private String userId;

    /** 菜单模块信息 */
    private Module module;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
