package com.fsc.xxt.si.msg.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.client.vo.MsgVo;
import com.fsc.xxt.si.msg.dao.MsgCollectionDao;
import com.fsc.xxt.si.msg.po.MsgCollection;
import com.fsc.xxt.si.msg.service.MsgCollectionService;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:消息收藏夹服务接口实现类</p>
 * <p>创建日期:Jan 9, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgCollectionServiceImpl extends BaseServiceImpl
    implements MsgCollectionService {
    private MsgCollectionDao msgCollectionDao;

    public void setMsgCollectionDao(MsgCollectionDao msgCollectionDao) {
        this.msgCollectionDao = msgCollectionDao;
    }

    @Override
    public void saveMsgCollection(MsgCollection msgCollection)
        throws Exception {
        msgCollectionDao.saveOrUpdateObject(msgCollection);
    }

    @Override
    public List<MsgCollection> selectMsgCollectionByLoginName(String userId)
        throws Exception {
        String hql = "from MsgCollection where userid='" + userId + "'";

        return selectDataByHQL(hql);
    }

    @Override
    public boolean delMsgCollect(String msgCollectId) throws Exception {
        return msgCollectionDao.delMsgCollect(msgCollectId);
    }

    @Override
    public List selectMsgCollectList(String userType, String userId)
        throws Exception {
        return msgCollectionDao.selectMsgCollectList(userType, userId);
    }

    @Override
    public void selectMsgCollectPageData(MsgVo msgVo, String userId)
        throws Exception {
        String hql = "from MsgCollection where userid='" + userId +
            "' and usertype='" + msgVo.getUSER_TYPE() + "' and type='" +
            msgVo.getTYPE() + "'";
        String countHql = "select count(*) " + hql;
        this.selectPageData(msgVo, hql, countHql);
    }
}
