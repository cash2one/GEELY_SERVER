package com.fsc.mobile.dao;

import java.util.Date;

import com.fsc.framework.base.dao.BaseDao;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信下行DAO接口</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface SmsMtDAO extends BaseDao {
    /**
     * 调用远程服务器的数据库接口发送短信
     * @param phoneNo
     * @param content
     * @throws Exception
     */
    void saveMobileSms(String phoneNo, String content)
        throws Exception;
    /**
     * 发送短信成功后批量修改数据库字段状态值(mttime,mtflag)
     * @param Object[] ob
     * @throws Exception
     */
    void updateSMSStetu(Object[] ob)throws Exception;
}
