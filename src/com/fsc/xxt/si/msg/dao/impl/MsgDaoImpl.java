package com.fsc.xxt.si.msg.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;
import com.fsc.framework.constant.CommonConstants;

import com.fsc.xxt.si.constant.MsgConstant;
import com.fsc.xxt.si.msg.dao.MsgDao;
import com.fsc.xxt.si.msg.po.Msg;

import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息Dao接口实现类</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MsgDaoImpl extends BaseDaoImpl implements MsgDao {
    @Override
    public List selectLinkMan(String userId, String usertype)
        throws Exception {
        String sql = "select t.LINK_MAN_ID,t.LINK_MAN_TYPE,t.LINK_MAN_NAME,max(motime) RELACTION_TIME," +
            "sum(case when t.flag = 'r' and t.readflag='" +
            MsgConstant.READFLAG_NOREAD +
            "' then 1 else 0 end) MESSAGE_NUM from (" +
            "select suser LINK_MAN_ID,susertype LINK_MAN_TYPE,susername LINK_MAN_NAME,motime,readflag,'r' flag from t_msg t" +
            " where (t.ruser = :userId and t.rusertype = :usertype and t.delflag='" +
            CommonConstants.DELFLAG_UNDEL + "')" + " union " +
            " select ruser LINK_MAN_ID,rusertype LINK_MAN_TYPE,rusername LINK_MAN_NAME,motime,readflag,'s' flag from t_msg t" +
            " where (t.suser = :userId and t.susertype = :usertype and t.delflag='" +
            CommonConstants.DELFLAG_UNDEL + "')" + ") t " +
            " group by t.LINK_MAN_ID,t.LINK_MAN_TYPE,t.LINK_MAN_NAME" +
            " order by RELACTION_TIME desc";
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("usertype", usertype);

        List list = selectDataBySQLMap(sql, params);

        return list;
    }

    @Override
    public List selectLinkMan(String userId, String usertype, Integer maxRowNum)
        throws Exception {
        StringBuffer sql = new StringBuffer();

        sql.append(
            "select r.LINK_MAN_ID, r.LINK_MAN_TYPE, r.LINK_MAN_NAME, r.RELACTION_TIME from (");
        sql.append(
            "select t.LINK_MAN_ID,t.LINK_MAN_TYPE,t.LINK_MAN_NAME,max(motime) RELACTION_TIME,");
        sql.append("sum(case when t.flag = 'r' and t.readflag='")
           .append(MsgConstant.READFLAG_NOREAD);
        sql.append("' then 1 else 0 end) MESSAGE_NUM from (");
        sql.append(
            "select suser LINK_MAN_ID,susertype LINK_MAN_TYPE,susername LINK_MAN_NAME,motime,readflag,'r' flag from t_msg t");
        sql.append(
            " where (t.ruser = :userId and t.rusertype = :usertype and t.delflag='");
        sql.append(CommonConstants.DELFLAG_UNDEL).append("' )");
        sql.append(" union ");
        sql.append(
            " select ruser LINK_MAN_ID,rusertype LINK_MAN_TYPE,rusername LINK_MAN_NAME,motime,readflag,'s' flag from t_msg t");
        sql.append(
            " where (t.suser = :userId and t.susertype = :usertype and t.delflag='");
        sql.append(CommonConstants.DELFLAG_UNDEL + "')" + ") t ");
        sql.append(" group by t.LINK_MAN_ID,t.LINK_MAN_TYPE,t.LINK_MAN_NAME");
        sql.append(" order by RELACTION_TIME ) r where rownum <= ")
           .append(maxRowNum);

        Map params = new HashMap();
        params.put("userId", userId);
        params.put("usertype", usertype);

        List list = selectDataBySQLMap(sql.toString(), params);

        return list;
    }

    @Override
    public List selectLinkManMsg(String ruserid, String rusertype,
        String suserid, String susertype, Integer rownum)
        throws Exception {
        String hql = "from Msg t where ((t.ruser = :ruserid and t.rusertype = :rusertype and t.suser = :suserid and t.susertype = :susertype)" +
            " or (t.suser = :ruserid and t.susertype = :rusertype and t.ruser = :suserid and t.rusertype = :susertype)) and t.delflag='" +
            CommonConstants.DELFLAG_UNDEL + "'" + " order by motime asc";
        Map params = new HashMap();
        params.put("ruserid", ruserid);
        params.put("rusertype", rusertype);
        params.put("suserid", suserid);
        params.put("susertype", susertype);

        List list = selectDataByHQL(hql, params);

        return list;
    }

    public Integer selectMsgCount(String ruserid, String rusertype,
        String suserid, String susertype, String readflag)
        throws Exception {
        String hql = "select count(id) from Msg where ruser=? and rusertype=? and suser=? and susertype=? and readflag=? and delflag='" +
            CommonConstants.DELFLAG_UNDEL + "'";
        Integer count = Integer.parseInt(selectDataByHQL(hql,
                    new Object[] {
                        ruserid, rusertype, suserid, susertype, readflag
                    }).get(0).toString());

        return count;
    }

    @Override
    public List selectMsg(String ruserid, String rusertype, String suserid,
        String susertype, String readflag) throws Exception {
        String hql = "from Msg where ruser=? and rusertype=? and suser=? and susertype=? and readflag=? and delflag='" +
            CommonConstants.DELFLAG_UNDEL + "'";
        List list = selectDataByHQL(hql,
                new Object[] { ruserid, rusertype, suserid, susertype, readflag });

        return list;
    }

    @Override
    public List selectNotReadMsg(String id, String rusertype)
        throws Exception {
        String hql = "from Msg where readflag ='01' and delflag='01' and ruser=" +
            "'" + id + "'" + " and rusertype=" + "'" + rusertype + "'";

        return selectDataByHQL(hql);
    }

    @Override
    public List selectDelNotReadInfoByName(String susername)
        throws Exception {
        String hql = "select content from Msg where readflag='01' and susername=" +
            "'" + susername + "'" + "and delflag='02'";
        List list = selectDataByHQL(hql);

        return list;
    }

    @Override
    public void delOneInFo(String loginid, String logintype, String linkmanid,
        String linkmantype) throws Exception {
        try {
            String hql = "delete from Msg where suser=" + "'" + loginid + "'" +
                " and ruser=" + "'" + linkmanid + "'" + " and susertype=" +
                "'" + logintype + "'" + " and rusertype=" + "'" + linkmantype +
                "'";
            executeHQL(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Msg> selectNotSendMsgList() throws Exception {
        String hql = "from Msg where readflag='01' and delflag='01' and pullflag='01'";
        List list = selectBeginNumToEndNumByHQL(hql, 0, 100);

        return list;
    }

	@Override
	public void updateMsgPullFail(String msgId) throws Exception {
		String hql="update Msg set pullflag='01' where id='"+msgId+"'";
		executeHQL(hql);
	}
}
