package com.fsc.xxt.si.product.dao;

import java.math.BigDecimal;

import com.fsc.framework.base.dao.BaseDao;

/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品管理Dao层接口</p>
 * <p>创建日期:Feb 9, 2012</p>
 * @author ZhouChao
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface ProductDao extends BaseDao {
	/**
	 * 查找用户产品收藏数量
	 * @param userType
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Long selectUserCollectCount(String userType,String userId)throws Exception;
	
	
	/**
	 * 删除产品收藏
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delProCoolect(String id)throws Exception;
	
}
