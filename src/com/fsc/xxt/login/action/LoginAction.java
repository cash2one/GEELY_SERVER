package com.fsc.xxt.login.action;

import com.fsc.xxt.login.vo.LoginVo;
import com.fsc.xxt.sys.user.po.User;
import com.fsc.xxt.sys.user.service.UserService;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.constant.CommonConstants;

import com.fsc.util.CryptoUtil;

import com.opensymphony.xwork2.ActionContext;

import java.util.Map;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:管理用户登录</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class LoginAction extends ManageAction {
    private UserService userService; //管理用户服务组件
    private LoginVo loginVo = new LoginVo();

    public LoginVo getModel() {
        return loginVo;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 后台登录方法
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        String imageVerifyCode = (String) session.get("imageVerifyCode");

        if ((null == imageVerifyCode) ||
                !imageVerifyCode.equalsIgnoreCase(loginVo.getVerifyCode())) {
            loginVo.setMessage("验证码错误");

            return "login";
        }

        User u = userService.findUserByLoginName(loginVo.getLoginName().trim());
        String _pass = CryptoUtil.desEncrypt(loginVo.getPass());
        loginVo.setPass("");

        if (u != null) {
            if (u.getPwd().equals(_pass)) {
                if (!"01".equals(u.getStatus())) {
                    loginVo.setMessage("该用户帐号被注销、锁定，请与系统管理员联系进行解锁");

                    return "login";
                } else {
                    session.put(CommonConstants.USER, u);
                }
            } else {
                loginVo.setMessage("用户名或密码错误");

                return "login";
            }
        } else {
            loginVo.setMessage("用户名或密码错误");

            return "login";
        }

        String result = SUCCESS;

        return result;
    }

    /**
     * 登出
     * @return
     * @throws Exception
     */
    public String logout() throws Exception {
        session.put(CommonConstants.USER, null);

        return SUCCESS;
    }

    /**
     * 密码修改
     * @return
     * @throws Exception
     */
    public String changePassWord() throws Exception {
        User user = (User) session.get(CommonConstants.USER);
        loginVo.setLoginName(user.getLogin());

        return SUCCESS;
    }

    /**
     * 保存密码修改
     * @return
     * @throws Exception
     */
    public String passwordChange() throws Exception {
        try {
            User u = userService.findUserByLoginName(loginVo.getLoginName());
            String pass = CryptoUtil.desEncrypt(loginVo.getPass());

            if (pass.equals(u.getPwd())) {
                u.setPwd(CryptoUtil.desEncrypt(loginVo.getNewPass()));
                userService.updateObject(u);
            } else {
                //原始密码不正确
                loginVo.setMessage("2");

                return SUCCESS;
            }

            //修改成功
            loginVo.setMessage("1");
        } catch (Exception e) {
            //修改失败
            loginVo.setMessage("3");
            e.printStackTrace();
        }

        return SUCCESS;
    }
}
