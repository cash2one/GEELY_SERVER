package com.fsc.xxt.sys.privilege.action;

import com.fsc.xxt.sys.module.service.ModuleService;
import com.fsc.xxt.sys.privilege.po.Privilege;
import com.fsc.xxt.sys.privilege.service.PrivilegeService;
import com.fsc.xxt.sys.privilege.vo.PrivilegeVo;
import com.fsc.framework.base.action.ManageAction;


import org.apache.commons.beanutils.PropertyUtils;

import java.util.regex.Pattern;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统功能权限信息管理控制</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class PrivilegeAction extends ManageAction {
    private PrivilegeVo privilegeVo;
    private ModuleService moduleService; //菜单模块服务组件
    private PrivilegeService privilegeService; //功能权限服务组件

    public PrivilegeAction() {
        privilegeVo = new PrivilegeVo();
    }

    public void setPrivilegeVo(PrivilegeVo privilegeVo) {
        this.privilegeVo = privilegeVo;
    }

    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    public PrivilegeVo getModel() {
        return privilegeVo;
    }

    /**
    * 进入系统功能权限信息管理框架页面
    * @return
    * @throws Exception
    */
    public String frame() throws Exception {
        return "frame";
    }

    /**
     * 查询系统菜单模块信息树形列表
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
        privilegeVo.setModuleList(moduleService.findAllModuleByParentModuleCode(
                "0"));

        return "tree";
    }

    /**
     * 模块下功能权限列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        privilegeVo.setList(privilegeService.selectPrivilegeByModuleId(
                privilegeVo.getModuleId()));

        return "list";
    }

    /**
     * 新增系统功能权限信息
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        return "input";
    }

    /**
     * 系统功能权限信息编辑
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        Privilege privilege = privilegeService.findPrivilegeById(privilegeVo.getPrivId());
        PropertyUtils.copyProperties(privilegeVo, privilege);

        return "input";
    }

    /**
     * 保存系统功能权限信息服务器验证
     */
    public void validateSave() {
        Pattern p = Pattern.compile("[0-9]+");

        if ((privilegeVo.getClassName() == null) ||
                "".equals(privilegeVo.getClassName())) {
            addFieldError("className", "请输入所属模块类名");
        }

        if ((privilegeVo.getClassDesc() == null) ||
                "".equals(privilegeVo.getClassDesc())) {
            addFieldError("classDesc", "请输入所属模块描述");

            return;
        }

        if ((privilegeVo.getMethodName() == null) ||
                "".equals(privilegeVo.getMethodName())) {
            addFieldError("methodName", "请输入功能权限方法名称");

            return;
        }

        if ((privilegeVo.getMethodDesc() == null) ||
                "".equals(privilegeVo.getMethodDesc())) {
            addFieldError("methodDesc", "请输入功能权限方法描述");

            return;
        }

        if (privilegeVo.getOrderNum() == null) {
            addFieldError("orderNum", "请输入排序号");

            return;
        }

        if ((privilegeVo.getSign() == null) ||
                "".equals(privilegeVo.getSign())) {
            addFieldError("sign", "请选择功能标志");

            return;
        }
    }

    /**
     * 保存系统功能权限信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Privilege privilege = new Privilege();

        if (privilegeVo.getPrivId() == null) {
            privilegeVo.setPrivId(privilegeService.getId());
            PropertyUtils.copyProperties(privilege, privilegeVo);
            privilegeService.insertObject(privilege);
        } else {
            PropertyUtils.copyProperties(privilege, privilegeVo);
            privilegeService.updateObject(privilege);
        }

        return SUCCESS;
    }

    /**
     * 删除系统功能权限信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        Privilege privilege = privilegeService.findPrivilegeById(privilegeVo.getPrivId());
        PropertyUtils.copyProperties(privilegeVo, privilege);
        privilegeService.deleteObject(privilege);

        return SUCCESS;
    }
}
