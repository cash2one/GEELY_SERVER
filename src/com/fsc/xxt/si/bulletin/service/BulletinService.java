package com.fsc.xxt.si.bulletin.service;

import java.util.List;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.client.vo.BulletinVo;
import com.fsc.xxt.si.bulletin.po.Bulletin;

/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:系统公告服务层接口</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface BulletinService extends BaseService {
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
	
	/**
	 * 分页产品信息（后台）
	 * @author: 刘源
	 * @date：2012-5-15 上午09:00:13   
	 * @return：void   
	 * @throws: Exception
	 */
	public void selectPageData(BulletinVo bullentivo)throws Exception;
	
	/**
	 * 保存新公告信息(后台)
	 * @author: 刘源
	 * @date：2012-5-15 下午02:42:57   
	 * @return：void   
	 * @throws: Exception
	 */
	public void saveBulletin(BulletinVo bulletinvo)throws Exception;
	
	/**
	 * 根据ID获取公告信息(后台)
	 * @author: 刘源
	 * @date：2012-5-15 下午03:43:28   
	 * @return：Bulletin   
	 * @throws: Exception
	 */
	public Bulletin getBulletinById(BulletinVo bulletinvo)throws Exception;
	
	/**
	 * 公告信息删除根据ID（后台）
	 * @author: 刘源
	 * @date：2012-5-15 下午03:59:09   
	 * @return：void   
	 * @throws: Exception
	 */
	public void delBulletinById(String ids[])throws Exception;
}
