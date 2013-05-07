package com.fsc.xxt.si.msg.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.msg.dao.MsgCollectionDao;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:消息收藏夹Dao接口实现类</p>
 * <p>创建日期:Jan 9, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgCollectionDaoImpl extends BaseDaoImpl
    implements MsgCollectionDao {
    @Override
    public boolean delMsgCollect(String id) throws Exception {
        try {
            String hql = "delete MsgCollection where id='" + id + "'";
            this.executeHQL(hql);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List selectMsgCollectList(String userType, String userId)
        throws Exception {
        String sql = "select count(c.id),c.type,(select d.dic_name from t_sys_dictionary d where d.dic_type='02' and d.dic_code=c.type) typeName from t_msg_collection c where c.USERID='" +
            userId + "' and c.USERTYPE='" + userType +
            "' group by c.type order by c.type";

        return this.selectDataBySQL(sql);
    }
}
