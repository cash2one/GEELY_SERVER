package com.fsc.xxt.si.bulletin.dao;

import java.util.List;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.xxt.si.bulletin.po.Bulletin;
/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:系统公告Dao层接口</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface BulletinDao extends BaseDao {
	/**
	 * 获取最新公告
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public Bulletin selectLatestBulletin(String userType)throws Exception;
	
	/**
	 * 根据用户类型获取公告列表
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<Bulletin> selectBulletinListByUserType(String userType)throws Exception;
}
