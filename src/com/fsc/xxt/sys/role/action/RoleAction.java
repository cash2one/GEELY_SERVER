package com.fsc.xxt.sys.role.action;

import com.fsc.xxt.sys.module.po.Module;
import com.fsc.xxt.sys.module.service.ModuleService;
import com.fsc.xxt.sys.privilege.service.PrivilegeService;
import com.fsc.xxt.sys.role.po.Role;
import com.fsc.xxt.sys.role.service.RoleService;
import com.fsc.xxt.sys.role.vo.RoleVo;
import com.fsc.framework.base.action.ManageAction;

import com.fsc.util.StringUtil;


import org.apache.commons.beanutils.PropertyUtils;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统角色管理控制类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class RoleAction extends ManageAction {
    private RoleVo roleVo;
    private RoleService roleService;
    private PrivilegeService privilegeService; //系统权限管理服务组件
    private ModuleService moduleService; //系统菜单模块管理服务组件

    public RoleAction() {
        roleVo = new RoleVo();
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    public RoleVo getModel() {
        return roleVo;
    }

    /**
     * 系统角色管理列表
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        roleService.selectPageRole(roleVo);

        return "list";
    }

    /**
     * 新增系统角色信息
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        return "input";
    }

    /**
     * 系统角色信息编辑
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        Role role = roleService.findRoleById(roleVo.getRoleId());
        PropertyUtils.copyProperties(roleVo, role);


        return "input";
    }

    /**
     * 保存系统角色信息服务器验证
     */
    public void validateSave() {
        if ((roleVo.getRoleName() == null) || "".equals(roleVo.getRoleName())) {
            addFieldError("roleName", "请输入系统角色名称");
        }

        if (roleVo.getOrderNum() == null) {
            addFieldError("orderNum", "请输入排序号");
        }
    }

    /**
     * 保存系统角色信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Role role = new Role();

        if (roleVo.getRoleId() == null) {
            Role r = roleService.findRoleByName(roleVo.getRoleName());

            if (r == null) {
                roleVo.setRoleId(roleService.getId());
                PropertyUtils.copyProperties(role, roleVo);
                roleService.insertObject(role);
            } else {
                roleVo.setMessage("角色名称不能重复");

                return "input";
            }
        } else {
            Role r = roleService.findRoleByName(roleVo.getRoleName());

            if (r != null) {
                if (!r.getRoleId().equals(roleVo.getRoleId())) {
                    if (r.getRoleName().equals(roleVo.getRoleName())) {
                        roleVo.setMessage("角色名称不能重复");

                        return "input";
                    }
                }
            }

            PropertyUtils.copyProperties(role, roleVo);
            roleService.updateObject(role);
        }

        return SUCCESS;
    }

    /**
     * 删除系统角色信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        if (roleService.selectUserRole(roleVo.getRoleId()).size() > 0) {
            roleVo.setMessage("该系统角色已经分配给相关系统用户，不能删除！");
        } else {
            roleService.deleteRole(roleVo.getRoleId());
        }

        return SUCCESS;
    }

    /**
     * 系统角色权限分配
     * @return
     * @throws Exception
     */
    public String privSet() throws Exception {
        Role role = roleService.findRoleById(roleVo.getRoleId());
        PropertyUtils.copyProperties(roleVo, role);
        roleVo.setRolePrivList(roleService.selectRolePriv(roleVo.getRoleId())); //系统角色已分配权限列表
        roleVo.setModuleList(moduleService.findModuleByParentModuleCode2("0")); //一级菜单模块列表

        for (int i = 0; i < roleVo.getModuleList().size(); i++) {
            Module module = (Module) roleVo.getModuleList().get(i);
            module.setChildList(moduleService.findModuleByParentModuleCode2(
                    module.getModuleCode())); //二级菜单模块列表

            for (int j = 0; j < module.getChildList().size(); j++) {
                Module module2 = (Module) module.getChildList().get(j);
                module2.setPrivList(privilegeService.selectPrivilegeByModuleId(
                        module2.getModuleId())); //菜单下功能权限列表
            }
        }

        return "privSet";
    }

    /**
     * 保存系统角色权限分配信息
     * @return
     * @throws Exception
     */
    public String saveRolePriv() throws Exception {
        roleService.saveRolePriv(roleVo.getRoleId(), roleVo.getIds());

        return SUCCESS;
    }
}
