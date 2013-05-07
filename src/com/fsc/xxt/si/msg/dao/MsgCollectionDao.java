package com.fsc.xxt.si.msg.dao;

import java.util.List;

import com.fsc.framework.base.dao.BaseDao;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:消息收藏夹Dao接口</p>
 * <p>创建日期:Jan 9, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface MsgCollectionDao extends BaseDao {
	/**
	 * 删除信息收藏
	 * @param id 收藏ID
	 * @return
	 * @throws Exception
	 */
	public boolean delMsgCollect(String id)throws Exception;
	
	
	/**
	 * 查找用户收藏信息列表
	 * @param userType
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List selectMsgCollectList(String userType,String userId)throws Exception;
}
