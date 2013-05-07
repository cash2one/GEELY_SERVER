package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.CryptoUtil;
import com.fsc.util.StringUtil;

import com.fsc.xxt.client.po.GeelyUser;
import com.fsc.xxt.client.service.GeelyUserService;
import com.fsc.xxt.client.vo.LoginVo;

import java.util.List;
import java.util.Map;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:登录action</p>
 * <p>创建日期:Dec 22, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class LoginAction extends ClientAction {
    private static final long serialVersionUID = -1340056116717870825L;
    private LoginVo loginVo = new LoginVo();
    private GeelyUserService geelyUserService;

    @Override
    public BaseVo getModel() {
        return loginVo;
    }

    /**
     * 登录接口
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        if (StringUtil.isEmpty(loginVo.getUserName())) {
            loginVo.setResultCode("404");
            loginVo.setResultDesc("缺少参数：用户名");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(loginVo.getPassWord())) {
            loginVo.setResultCode("404");
            loginVo.setResultDesc("缺少参数：密码");

            return SUCCESS;
        }

        GeelyUser geelyUser = geelyUserService.findUserByName(loginVo.getUserName());
        String _pass = CryptoUtil.MD5(loginVo.getPassWord()).substring(8, 24);
        String jsessionid = request.getSession().getId();

        if (geelyUser != null) {
            if (_pass.equalsIgnoreCase(geelyUser.getPwd())) {
                if (!"0".equals(geelyUser.getFlag())) {
                    loginVo.setResultDesc("该用户已被停用");
                    loginVo.setResultCode("400");

                    return SUCCESS;
                }
            } else {
                loginVo.setResultDesc("密码错误");
                loginVo.setResultCode("400");

                return SUCCESS;
            }
        } else {
            loginVo.setResultDesc("用户名不存在");
            loginVo.setResultCode("400");

            return SUCCESS;
        }

        loginVo.setResultCode("200");
        loginVo.setResultDesc("登录成功");
        loginVo.setResultData("userId", geelyUser.getId());
        loginVo.setResultData("userName", geelyUser.getText());
        loginVo.setResultData("dept", geelyUser.getDept());
        loginVo.setResultData("tel", geelyUser.getTel());
        loginVo.setResultData("JSSEIONID", jsessionid);
        loginVo.setResultData("proSet", geelyUser.getProleSet());

        return SUCCESS;
    }

    /**
     * 获取用户未读的会议列表
     * @return
     * @throws Exception
     */
    public String getUnreadMetting() throws Exception {
        try {
            List<Map> list = geelyUserService.selectUserUnreadMetting(loginVo.getUserId());
            String ids = "";
            int length = list.size();

            for (int i = 0; i < length; i++) {
                ids += list.get(i).get("ID");

                if (i != (length - 1)) {
                    ids += ",";
                }
            }

            if (!StringUtil.isEmpty(ids)) {
                geelyUserService.updateMettingReadFlag(loginVo.getUserId(), ids);
            }

            loginVo.setResultCode("200");
            loginVo.setResultDesc("获取数据成功");
            loginVo.setResultData("dataList", list);
        } catch (Exception e) {
            loginVo.setResultCode("400");
            loginVo.setResultDesc("获取数据失败");
        }

        return SUCCESS;
    }

    /**
     * 获取用户未读的
     * @param geelyUserService
     */
    public String getUnreadWarning() throws Exception {
        try {
            List<Map> list = geelyUserService.selectUserUnreadWarning(loginVo.getUserId());
            String ids = "";
            int length = list.size();

            for (int i = 0; i < length; i++) {
                ids += list.get(i).get("ID");

                if (i != (length - 1)) {
                    ids += ",";
                }
            }

            if (!StringUtil.isEmpty(ids)) {
                geelyUserService.updateWarningReadFlag(loginVo.getUserId(), ids);
            }

            loginVo.setResultCode("200");
            loginVo.setResultDesc("获取数据成功");
            loginVo.setResultData("dataList", list);
        } catch (Exception e) {
            loginVo.setResultCode("400");
            loginVo.setResultDesc("获取数据失败");
        }

        return SUCCESS;
    }

    /**
     * 获取服务端最新版本信息
     * @param geelyUserService
     */
    public String getLastVersion() throws Exception {
        try {
            Map map = geelyUserService.getLastVersionInfo();
            loginVo.setResultCode("200");
            loginVo.setResultDesc("获取数据成功");
            loginVo.setReturnMap(map);
        } catch (Exception e) {
            loginVo.setResultCode("400");
            loginVo.setResultDesc("获取数据失败");
        }

        return SUCCESS;
    }

    public void setGeelyUserService(GeelyUserService geelyUserService) {
        this.geelyUserService = geelyUserService;
    }
}
