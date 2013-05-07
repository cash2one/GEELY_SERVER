package com.fsc.xxt.si.msg.service;

import java.util.List;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.client.vo.MsgVo;
import com.fsc.xxt.si.msg.po.MsgCollection;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:消息收藏夹服务接口</p>
 * <p>创建日期:Jan 9, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface MsgCollectionService extends BaseService {
	/**
	 * 保存消息收藏
	 * @param msgCollection
	 * @throws Exception
	 */
	void saveMsgCollection(MsgCollection msgCollection) throws Exception;
	
	/**
	 * 查找用户收藏信息列表
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public List<MsgCollection> selectMsgCollectionByLoginName(String userId)throws Exception;
	
	/**
	 * 删除信息收藏
	 * @param msgCollectId
	 * @return
	 * @throws Exception
	 */
	public boolean delMsgCollect(String msgCollectId)throws Exception;
	
	/**
	 * 查找用户收藏信息列表
	 * @param userType
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List selectMsgCollectList(String userType,String userId)throws Exception;
	
	/**
	 * 分页查找用户收藏信息
	 * @param msgVo
	 * @throws Exception
	 */
	public void selectMsgCollectPageData(MsgVo msgVo,String userId)throws Exception;
}
