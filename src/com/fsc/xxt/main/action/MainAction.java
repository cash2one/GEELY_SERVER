package com.fsc.xxt.main.action;

import com.fsc.xxt.main.vo.MainVo;
import com.fsc.xxt.sys.dic.service.DictionaryService;
import com.fsc.xxt.sys.module.service.ModuleService;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.action.ManageAction;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:后台管理主界面</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class MainAction extends ManageAction {
    private MainVo mainVo = new MainVo();

    /**  数据字典业务逻辑层  */
    private DictionaryService dictionaryService;

    /** 模块菜单服务组件 */
    private ModuleService moduleService;

    public MainVo getModel() {
        return mainVo;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    /**
    * 进入首页桌面
    * @return
    * @throws Exception
    */
    public String desktop() throws Exception {
        User user = getLoginUser();

        if (user != null) {
            if (user.getLogin().equals("admin")) {
                mainVo.setModuleList(moduleService.findModuleByParentModuleCode(
                        "0"));
            } else {
                mainVo.setModuleList(moduleService.findModuleByUserFirstModule(
                        getLoginUser().getId()));
            }
        }

        return "desktop";
    }

    /**
     * 菜单
     * @return
     * @throws Exception
     */
    public String menu() throws Exception {
        return "menu";
    }

    /**
     * 操作内容区
     * @return
     * @throws Exception
     */
    public String info() throws Exception {
        return "info";
    }

    /**
     * 进入没有实现的
     * @return
     * @throws Exception
     */
    public String notbuild() throws Exception {
        return "notbuild";
    }
}
