package com.fsc.mobile.dao;

import com.fsc.framework.base.dao.BaseDao;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信上行信息获取DAO接口</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface SmsDeliverDAO extends BaseDao {
    /**
     * 查询短信服务器上行短信信息列表
     * @return
     * @throws Exception
     */
    public List selectSmsDeliver() throws Exception;
}
