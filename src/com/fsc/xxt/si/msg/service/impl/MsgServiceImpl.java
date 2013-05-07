package com.fsc.xxt.si.msg.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.constant.CommonConstants;

import com.fsc.mobile.dao.SmsMtDAO;
import com.fsc.mobile.po.SmsMt;

import com.fsc.xxt.si.constant.MsgConstant;
import com.fsc.xxt.si.msg.dao.ClassMsgDao;
import com.fsc.xxt.si.msg.dao.MsgDao;
import com.fsc.xxt.si.msg.po.ClassMsg;
import com.fsc.xxt.si.msg.po.Msg;
import com.fsc.xxt.si.msg.service.MsgService;
import com.fsc.xxt.si.msg.vo.ClassMsgVo;
import com.fsc.xxt.si.msg.vo.MsgVo;

import java.util.Date;
import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息Service接口实现类</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgServiceImpl extends BaseServiceImpl implements MsgService {
    private MsgDao msgDao;
    private SmsMtDAO smsMtDAO;
    private ClassMsgDao classMsgDao;

    public void setSmsMtDAO(SmsMtDAO smsMtDAO) {
        this.smsMtDAO = smsMtDAO;
    }

    public void setMsgDao(MsgDao msgDao) {
        this.msgDao = msgDao;
    }

    public void setClassMsgDao(ClassMsgDao classMsgDao) {
        this.classMsgDao = classMsgDao;
    }

    @Override
    public void saveMsg(Msg msg, String mobile) throws Exception {
        if (MsgConstant.MTFLAG_UNRECEIVED.equals(msg.getMtflag())) {
            if (MsgConstant.MTTYPE_SMS.equals(msg.getMttype())) {
                SmsMt smsMt = new SmsMt();
                smsMt.setId(getId());
                smsMt.setMessage(msg.getContent());
                smsMt.setToMobile(mobile);
                smsMt.setTraceid(msg.getId());
                smsMtDAO.saveOrUpdateObject(smsMt);
            } else if (MsgConstant.MTTYPE_CLIENT.equals(msg.getMttype())) {
            } else {
                throw new Exception("未知的下行类型");
            }
        }

        //        msg.setMttime(new Date());
        //        msg.setMtflag(MsgConstant.MTFLAG_RECEIVED);
        msgDao.saveOrUpdateObject(msg);
    }

    @Override
    public List selectLinkMan(String userId, String usertype)
        throws Exception {
        List list = msgDao.selectLinkMan(userId, usertype);

        return list;
    }

    @Override
    public List selectLinkMan(String userId, String usertype, Integer maxRowNum)
        throws Exception {
        List list = msgDao.selectLinkMan(userId, usertype, maxRowNum);

        return list;
    }

    @Override
    public List selectLinkManMsg(String ruserid, String rusertype,
        String suserid, String susertype, Integer rownum)
        throws Exception {
        List list = msgDao.selectLinkManMsg(ruserid, rusertype, suserid,
                susertype, rownum);

        return list;
    }

    @Override
    public Integer selectMsgCount(String ruserid, String rusertype,
        String suserid, String susertype, String readflag)
        throws Exception {
        return msgDao.selectMsgCount(ruserid, rusertype, suserid, susertype,
            readflag);
    }

    @Override
    public void saveMsg(List msgList, List mobileList)
        throws Exception {
        for (int i = 0; i < msgList.size(); i++) {
            Msg msg = (Msg) msgList.get(i);
            String mobile = (String) mobileList.get(i);
            saveMsg(msg, mobile);
        }
    }

    @Override
    public void saveMsg(Msg msg) throws Exception {
        msgDao.saveOrUpdateObject(msg);
    }

    public Msg selectMsgById(String id) throws Exception {
        return (Msg) msgDao.getObject(Msg.class, id);
    }

    @Override
    public void delMsg(String id) throws Exception {
        Msg msg = selectMsgById(id);
        msg.setDelflag(CommonConstants.DELFLAG_DEL);
        msgDao.saveOrUpdateObject(msg);
    }

    @Override
    public void updateReadFlag(String ruserid, String rusertype,
        String suserid, String susertype, String readflag, String readflagVal)
        throws Exception {
        List list = msgDao.selectMsg(ruserid, rusertype, suserid, susertype,
                readflag);

        for (int i = 0; i < list.size(); i++) {
            Msg msg = (Msg) list.get(i);
            msg.setReadflag(readflagVal);
            msgDao.saveOrUpdateObject(msg);
        }
    }

    // Carfield 2012-01-13 [Add] 增加班级发送信息历史记录  [Start]

    /**
     * 保存班级发送消息
     * @param msg
     * @throws Exception
     */
    @Override
    public void saveClassMsg(ClassMsg msg) throws Exception {
        classMsgDao.saveOrUpdateObject(msg);
    }

    /**
     * 获取班级发送信息历史记录
     * @param teacherId
     * @param classId
     * @throws Exception
     */
    @Override
    public List getClassMsgList(String teacherId, String classId)
        throws Exception {
        return classMsgDao.getClassMsgList(teacherId, classId);
    }

    /**
     * 逻辑删除某条班级信息
     * @param teacherId
     * @param classId
     * @throws Exception
     */
    @Override
    public void delClassMsg(String msgId, boolean logic)
        throws Exception {
        ClassMsg msg = (ClassMsg) getObject(ClassMsg.class, msgId);

        if (logic) {
            // 逻辑删除，做更新操作
            msg.setDelFlag(CommonConstants.DELFLAG_DEL);
            classMsgDao.updateObject(msg);
        } else {
            // 物理删除
            classMsgDao.deleteObject(msg);
        }
    }

    /**
     * 获取某条班级信息
     * @param teacherId
     * @param classId
     * @throws Exception
     */
    @Override
    public ClassMsg getClassMsgById(String msgId) throws Exception {
        return (ClassMsg) getObject(ClassMsg.class, msgId);
    }

    /**
    * 根据loginname获得未删除未读信息
    * @param susername
    * @throws Exception
    */
    @Override
    public List getNotReadInfoByName(String id, String rusertype)
        throws Exception {
        return msgDao.selectNotReadMsg(id, rusertype);
    }

    /**
    * 根据loginname获得已删除未读信息
    * @param susername
    * @throws Exception
    */
    @Override
    public List getDelNotReadInfoByName(String susername)
        throws Exception {
        return msgDao.selectDelNotReadInfoByName(susername);
    }

    @Override
    public void delOneInfo(String loginid, String logintype, String linkmanid,
        String linkmantype) throws Exception {
        this.msgDao.delOneInFo(loginid, logintype, linkmanid, linkmantype);
    }

    @Override
    public List<Msg> selectNotSendMsgList() throws Exception {
        return msgDao.selectNotSendMsgList();
    }

    @Override
    public void updateMsgPullFail(String msgId) throws Exception {
        msgDao.updateMsgPullFail(msgId);
    }

    @Override
    public void selectPageData(MsgVo msgvo) throws Exception {
        String hql = "from Msg where 1=1";

        if ((msgvo.getType() != null) && !"".equals(msgvo.getType())) {
            hql += (" and type='" + msgvo.getType() + "'");
        }

        if ((msgvo.getSusername() != null) && !"".equals(msgvo.getSusername())) {
            hql += (" and susername like'%" + msgvo.getSusername() + "%'");
        }

        if ((msgvo.getRusername() != null) && !"".equals(msgvo.getRusername())) {
            hql += (" and rusername like'%" + msgvo.getRusername() + "%'");
        }

        hql += " order by motime desc";

        String hqlcount = "select count(*) " + hql;
        this.selectPageData(msgvo, hql, hqlcount);
    }

    @Override
    public void selectPageData(ClassMsgVo classmsgvo) throws Exception {
        String hql = "from ClassMsg where 1=1";
        String hqlcount = "select count(*) " + hql;
        hql += " order by sendTime desc";
        this.selectPageData(classmsgvo, hql, hqlcount);
    }
}
