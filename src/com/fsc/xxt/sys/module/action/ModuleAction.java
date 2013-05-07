package com.fsc.xxt.sys.module.action;

import com.fsc.xxt.sys.module.po.Module;
import com.fsc.xxt.sys.module.service.ModuleService;
import com.fsc.xxt.sys.module.vo.ModuleVo;
import com.fsc.xxt.sys.shortcut.po.ShortCut;
import com.fsc.xxt.sys.shortcut.service.ShortCutService;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.action.ManageAction;


import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:后台管理(菜单管理)</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ModuleAction extends ManageAction {
    private ModuleService moduleService;
    private ShortCutService shortCutService;
    private ModuleVo moduleVo = new ModuleVo();

    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    public void setShortCutService(ShortCutService shortCutService) {
        this.shortCutService = shortCutService;
    }

    public ModuleVo getModel() {
        return moduleVo;
    }

    /**
     * 菜单
     * @return
     * @throws Exception
     */
    public String module() throws Exception {
        Module m = moduleService.findModuleByModuleCode(moduleVo.getModuleCode());

        if (m != null) {
            moduleVo.setModuleName(m.getModuleName());
            moduleVo.setList(moduleService.findModuleByParentModuleCode(
                    m.getModuleCode()));
        }

        return SUCCESS;
    }

    /**
     * 查询用户二级模块菜单
     * @return
     * @throws Exception
     */
    public String userSmodule() throws Exception {
        User user = getLoginUser();

        if (user != null) {
            if (user.getLogin().equalsIgnoreCase("admin")) {
                return module();
            }

            Module m = moduleService.findModuleByModuleCode(moduleVo.getModuleCode());

            if (m != null) {
                moduleVo.setModuleName(m.getModuleName());
                moduleVo.setList(moduleService.findModuleByUserSecondModule(
                        user.getId(), m.getModuleCode()));
            }
        }

        return SUCCESS;
    }

    /**
     * 查询用户快捷菜单
     * @return
     * @throws Exception
     */
    public String shortCutModule() throws Exception {
        User user = getLoginUser();
        List shortCutList = shortCutService.selectShortCutByUserId(user.getId());
        List moduleList = new ArrayList();

        for (int i = 0; i < shortCutList.size(); i++) {
            ShortCut shortCut = (ShortCut) shortCutList.get(i);
            moduleList.add(shortCut.getModule());
        }

        moduleVo.setList(moduleList);

        return SUCCESS;
    }
}
