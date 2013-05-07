package com.fsc.framework.base.action;

import com.fsc.xxt.sys.user.po.User;
import com.fsc.framework.base.action.BaseAction;
import com.fsc.framework.constant.CommonConstants;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:后台管理基础Action</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ManageAction extends BaseAction {
    private String rootUnitNo = com.fsc.framework.constant.CommonConstants.ROOTUNITNO;

    public String getRootUnitNo() {
        return rootUnitNo;
    }

    public void setRootUnitNo(String rootUnitNo) {
        this.rootUnitNo = rootUnitNo;
    }

    public boolean isLogin() throws Exception {
        Object u = session.get(CommonConstants.USER);

        return (u == null) ? false : true;
    }

    public User getLoginUser() throws Exception {
        return (User) session.get(CommonConstants.USER);
    }

    /** 主方法，用于测试 */
    public static void main(String[] args) {
    }
}
