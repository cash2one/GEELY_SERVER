package com.fsc.xxt.si.msg.service;

import com.fsc.framework.base.service.BaseService;

import com.fsc.xxt.client.vo.BulletinVo;
import com.fsc.xxt.si.msg.po.ClassMsg;
import com.fsc.xxt.si.msg.po.Msg;
import com.fsc.xxt.si.msg.vo.ClassMsgVo;
import com.fsc.xxt.si.msg.vo.MsgVo;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:交互信息Service接口</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface MsgService extends BaseService {
    /**
     * 保存并发送个人短消息
     * @param msg
     * @param mobile
     * @throws Exception
     */
    void saveMsg(Msg msg, String mobile) throws Exception;

    /**
     * 保存并发送批量短消息
     * @param msg
     * @param mobile
     * @throws Exception
     */
    void saveMsg(List msg, List mobileList) throws Exception;

    /**
     * 查询联系人
     * @param suser
     * @param susertype
     * @return
     * @throws Exception
     */
    List selectLinkMan(String suser, String susertype)
        throws Exception;


    List selectLinkMan(String suser, String susertype, Integer maxRowNum)
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
     * 保存消息
     * @param msg
     * @param mobile
     * @throws Exception
     */
    void saveMsg(Msg msg) throws Exception;
    
    /**
     * 删除消息
     * @param id
     * @throws Exception
     */
    void delMsg(String id) throws Exception;
    
    /**
     * 按ID查找消息
     * @param id
     * @return
     * @throws Exception
     */
    Msg selectMsgById(String id) throws Exception;
   
    /**
     * 更新阅读状态
     * @param ruserid
     * @param rusertype
     * @param suserid
     * @param susertype
     * @param readflag 搜索条件
     * @param readflagVal 更新后的值
     * @throws Exception
     */
    void updateReadFlag(String ruserid,String rusertype, String suserid,
            String susertype, String readflag,String readflagVal) throws Exception;
    
    // Carfield 2012-01-13 [Add] 增加班级发送信息历史记录  [Start]
    /**
     * 保存班级发送消息
     * @param msg
     * @throws Exception
     */
    void saveClassMsg(ClassMsg msg) throws Exception;
    
    /**
     * 获取班级发送信息历史记录
     * @param teacherId
     * @param classId
     * @throws Exception
     */
    List getClassMsgList(String teacherId, String classId) throws Exception;
    
    /**
     * 删除班级发送消息
     * @param msg
     * @throws Exception
     */
    void delClassMsg(String msgId, boolean logic) throws Exception;
    
    /**
     * 删除班级发送消息
     * @param msg
     * @throws Exception 
     * @throws Exception
     */
    ClassMsg getClassMsgById(String id) throws Exception;
    
    /**
     * 根据id,用户类型获得未删除未读信息
     * @param susername
     * @throws Exception 
     * @throws Exception
     */
    List getNotReadInfoByName(String id,String rusertype) throws Exception;
    
    /**
     * 根据登录名获得已删除未读信息
     * @param susername
     * @throws Exception 
     * @throws Exception
     */
    List getDelNotReadInfoByName(String susername) throws Exception;
    /**
     * 根据登录姓名与联系人姓名删除个人信息
     * @param susername
     * @throws Exception 
     * @throws Exception
     */
    void delOneInfo(String loginid,String logintype,String linkmanid,String linkmantype)throws Exception;
    
    /**
     * 查询所有未发送的信息
     * @return
     * @throws Exception
     */
    List<Msg> selectNotSendMsgList()throws Exception;
    
    
    void updateMsgPullFail(String msgId)throws Exception;
    
    /**
     * 分页查询(交互信息)
     * @author: 刘源
     * @date：2012-5-15 下午05:09:42   
     * @return：void   
     * @throws: Exception
     */
    public void selectPageData(MsgVo msgvo)throws Exception;
    
    /**
     * 分页查询(班级信息)
     * @author: 刘源
     * @date：2012-5-16 上午12:29:04   
     * @return：void   
     * @throws: Exception
     */
    public void selectPageData(ClassMsgVo classmsgvo)throws Exception;
}
