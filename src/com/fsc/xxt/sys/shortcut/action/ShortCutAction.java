package com.fsc.xxt.sys.shortcut.action;

import com.fsc.xxt.sys.shortcut.po.ShortCut;
import com.fsc.xxt.sys.shortcut.service.ShortCutService;
import com.fsc.xxt.sys.shortcut.vo.ShortCutVo;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.action.ManageAction;


import java.util.Set;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:快捷菜单管理控制</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ShortCutAction extends ManageAction {
    private ShortCutService shortCutService; //快捷菜单服务
    private ShortCutVo shortCutVo = new ShortCutVo();

    public ShortCutVo getModel() {
        return shortCutVo;
    }

    public void setShortCutService(ShortCutService shortCutService) {
        this.shortCutService = shortCutService;
    }

    /**
     * 添加快捷菜单
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        User user = getLoginUser();

        if (!shortCutService.ifExist("ShortCut",
                    new String[] { "moduleId", "userId" },
                    new Object[] { shortCutVo.getModuleId(), user.getId() },
                    new String[] { "=", "=" })) {
            ShortCut shortCut = new ShortCut();
            shortCut.setId(shortCutService.getId());
            shortCut.setModuleId(shortCutVo.getModuleId());
            shortCut.setUserId(user.getId());
            shortCutService.saveShortCut(shortCut);
            user.getShortCutSet().add(shortCut);
        }

        response.getWriter().print(1);

        return null;
    }

    /**
     * 删除快捷菜单
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        User user = getLoginUser();
        ShortCut shortCut = shortCutService.findUserShortCutByModuleId(user.getId(),
                shortCutVo.getModuleId());

        if (shortCut != null) {
            Set shortCutSet = user.getShortCutSet();
            shortCutSet.remove(shortCut);
            shortCutService.removeShortCut(shortCut.getId());
        }

        response.getWriter().print(1);

        return null;
    }
}
