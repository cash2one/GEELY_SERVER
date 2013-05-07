package com.fsc.xxt.sys.module.vo;

import com.fsc.framework.base.vo.BaseVo;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:菜单模块VO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ModuleVo extends BaseVo {
    /**<code>id</code> 模块ID*/
    private Integer moduleId;

    /**<code>parentModuleId</code> 父模块编号*/
    private String parentModuleCode;

    /**<code>moduleCode</code> 模块编号*/
    private String moduleCode;

    /**<code>moduleName</code> 模块名称*/
    private String moduleName;

    /**<code>moduleDesc</code> 模块描述*/
    private String moduleDesc;

    /**<code>moduleLink</code> 模块链接*/
    private String moduleLink;

    /**<code>pictureLink</code> 图片链接*/
    private String pictureLink;

    /**<code>target</code> 目标窗口*/
    private String target;

    /**<code>sign</code> 标志,0为叶子接点，1为树结点，9为隐藏结点*/
    private Integer sign;

    public ModuleVo() {
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleLink() {
        return moduleLink;
    }

    public void setModuleLink(String moduleLink) {
        this.moduleLink = moduleLink;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getParentModuleCode() {
        return parentModuleCode;
    }

    public void setParentModuleCode(String parentModuleCode) {
        this.parentModuleCode = parentModuleCode;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
