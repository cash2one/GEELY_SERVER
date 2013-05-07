package com.fsc.framework.base.action;

import com.fsc.framework.base.vo.BaseVo;
import com.fsc.framework.base.vo.ClientVo;
import com.fsc.framework.constant.CommonConstants;
import com.fsc.framework.exception.CommonException;

import com.fsc.xxt.client.service.UserService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:手机交互接口公共Action</p>
 * <p>创建日期:Dec 29, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ClientAction extends BaseAction {
    protected UserService userService;
    private ClientVo clientVo = new ClientVo();
    private CommonException exception;

    @Override
    public BaseVo getModel() {
        return clientVo;
    }

    public void setException(CommonException exception) {
        this.exception = exception;
    }

    public void setClientUserService(UserService userService) {
        this.userService = userService;
    }

    public Object getUserByLogin(String userType, String loginname)
        throws Exception {
        return userService.getUserByLogin(userType, loginname);
    }

    public String getUserIdByLogin(String userType, String loginname)
        throws Exception {
        return userService.getUserIdByLogin(userType, loginname);
    }

    public Object getLoginUser() {
        return session.get(CommonConstants.MOBILEUSER);
    }

    public boolean valiUserType(String userType) {
        return DictionaryConstant.USER_TEACHER.equals(userType) ||
        DictionaryConstant.USER_STUDENT.equals(userType) ||
        DictionaryConstant.USER_SCHADMIN.equals(userType);
    }

    /**
     * 异常处理
     * @return
     */
    public String handleException() {
        clientVo.setResultCode("-1");

        if (null == exception) {
            clientVo.setResultDesc("异常为空");
        } else {
            exception.printStackTrace();
            clientVo.setResultDesc(exception.getMessage());
        }

        return SUCCESS;
    }
}
