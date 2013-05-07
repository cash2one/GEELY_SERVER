package com.fsc.xxt.si.msg.dao;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.xxt.si.msg.po.Msg;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息Dao接口</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface MsgDao extends BaseDao {
    /**
     * 查询联系技
     * @param suser
     * @param susertype
     * @return
     * @throws Exception
     */
    List selectLinkMan(String userId, String usertype)
    throws Exception;

    List selectLinkMan(String userId, String usertype, Integer maxRowNum)
    throws Exception;

    /**
     * 查询联系内容
     * @param suser
     * @param susertype
     * @return
     * @throws Exception
     */
    List selectLinkManMsg(String ruserid, String rusertype, String suserid,
        String susertype, Integer rownum) throws Exception;

    /**
     * 查询联系内容条数
     * @param suser
     * @param susertype
     * @return
     * @throws Exception
     */
    Integer selectMsgCount(String ruserid, String rusertype, String suserid,
        String susertype, String readflag) throws Exception;
    /**
     * 查找消息
     * @param ruserid
     * @param rusertype
     * @param suserid
     * @param susertype
     * @param readflag
     * @return
     * @throws Exception
     */
    List selectMsg(String ruserid, String rusertype, String suserid,
            String susertype, String readflag) throws Exception;
    
    /**
     * 查询未读信息
     * @param susername
     * @return
     * @throws Exception
     */
    List selectNotReadMsg(String id,String rusertype)throws Exception;
    
    /**
     * 查询已删除未读信息
     * @param susername
     * @return
     * @throws Exception
     */
    List selectDelNotReadInfoByName(String susername)throws Exception;
    /**
     * 删除与某个人的交互信息
     * @param susername
     * @return
     * @throws Exception
     */
    void delOneInFo(String loginid,String logintype,String linkmanid,String linkmantype)throws Exception;
    
    
    /**
     * 查询所有未发送的信息
     * @return
     * @throws Exception
     */
    List<Msg> selectNotSendMsgList()throws Exception;
    
    /**
     * 设置消息推送失败
     * @param msgId
     * @throws Exception
     */
    public void updateMsgPullFail(String msgId) throws Exception;
}
