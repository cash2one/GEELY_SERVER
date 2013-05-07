package com.fsc.mobile.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.mobile.dao.SmsDeliverDAO;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信上行信息获取DAO接口实现类</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SmsDeliverDAOImpl extends BaseDaoImpl implements SmsDeliverDAO {
    public List selectSmsDeliver() throws Exception {
        String hql = "from SmsDeliver";

        return selectDataByHQL(hql);
    }
}
