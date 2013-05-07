package com.fsc.xxt.si.bulletin.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.bulletin.dao.BulletinDao;
import com.fsc.xxt.si.bulletin.po.Bulletin;

import java.util.List;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:系统公告Dao层接口实现</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class BulletinDaoImpl extends BaseDaoImpl implements BulletinDao {
    @Override
    public List<Bulletin> selectBulletinListByUserType(String userType)
        throws Exception {
        String hql = "from Bulletin where userType='" + userType +
            "' and flag='02' order by putTime desc";

        return this.selectDataByHQL(hql);
    }

    @Override
    public Bulletin selectLatestBulletin(String userType)
        throws Exception {
        String hql = "from Bulletin where userType='" + userType +
            "' and flag='02' order by putTime desc";
        List list = selectPageDataByHQL(hql, 1, 1);

        if ((list != null) && (list.size() > 0)) {
            return (Bulletin) list.get(0);
        }

        return null;
    }
}
