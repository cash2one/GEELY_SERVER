package com.fsc.xxt.client.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.client.dao.GeelyUserDao;
import com.fsc.xxt.client.po.GeelyUser;
import com.fsc.xxt.client.service.GeelyUserService;

import java.util.List;
import java.util.Map;


/**
 *
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:吉利用户信息表服务接口实现</p>
 * <p>创建日期:2013-4-9</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class GeelyUserServiceImpl extends BaseServiceImpl
    implements GeelyUserService {
    private GeelyUserDao geelyUserDao;

    @Override
    public GeelyUser findUserById(Integer id) throws Exception {
        return null;
    }

    @Override
    public GeelyUser findUserByName(String userName) throws Exception {
        return geelyUserDao.findUserByName(userName);
    }

    public GeelyUserDao getGeelyUserDao() {
        return geelyUserDao;
    }

    public void setGeelyUserDao(GeelyUserDao geelyUserDao) {
        this.geelyUserDao = geelyUserDao;
    }

    @Override
    public List<Map> selectUserUnreadMetting(Integer userId)
        throws Exception {
        String sql = "select m.* from meeting m where exists " +
            "(select 1 from user_meeting um where m.id=um.metting_id and um.receive_flag=0 and um.user_id=" +
            userId + ")";

        return selectDataBySQLMap(sql);
    }

    @Override
    public List<Map> selectUserUnreadWarning(Integer userId)
        throws Exception {
        String sql = "select w.* from warning_mng w where exists " +
            "(select 1 from user_warning uw where w.id=uw.warning_id and uw.receive_flag=0 and uw.user_id=" +
            userId + ")";

        return selectDataBySQLMap(sql);
    }

    @Override
    public List<Map> selectUsreUnsendAndUnreadMetting()
        throws Exception {
        String sql = "select u.id,u.utext from sy_user u where exists " +
            "(select 1 from user_meeting um where u.id=um.user_id and um.send_flag=0 and um.receive_flag=0)";

        return selectDataBySQLMap(sql);
    }

    @Override
    public List<Map> selectUserUnsendAndUnreadWarning()
        throws Exception {
        String sql = "select u.id,u.utext from sy_user u where exists " +
            "(select 1 from user_warning uw where u.id=uw.user_id and uw.send_flag=0 and uw.receive_flag=0)";

        return selectDataBySQLMap(sql);
    }

    @Override
    public void updateWarningSendFlag(String ids) throws Exception {
        String sql = "update user_warning set SEND_FLAG=1 where user_id in (" +
            ids + ")";
        executeSQL(sql);
    }

    @Override
    public void updateMettingSendFlag(String ids) throws Exception {
        String sql = "update user_meeting set SEND_FLAG=1 where user_id in (" +
            ids + ")";
        executeSQL(sql);
    }

    @Override
    public void updateWarningReadFlag(Integer userId, String ids)
        throws Exception {
        String sql = "update user_warning set RECEIVE_FLAG=1 where USER_ID=" +
            userId + " and WARNING_ID in (" + ids + ")";
        executeSQL(sql);
    }

    @Override
    public void updateMettingReadFlag(Integer userId, String ids)
        throws Exception {
        String sql = "update user_meeting set RECEIVE_FLAG=1 where USER_ID=" +
            userId + " and METTING_ID in (" + ids + ")";
        executeSQL(sql);
    }

    @Override
    public Map getLastVersionInfo() throws Exception {
        String sql = "select * from version where version_code=(select max(version_code) from version)";
        List<Map> list = selectDataBySQLMap(sql);

        return list.get(0);
    }
}
