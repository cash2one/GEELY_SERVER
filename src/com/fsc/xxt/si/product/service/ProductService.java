package com.fsc.xxt.si.product.service;


import java.math.BigDecimal;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.client.vo.MsgVo;
import com.fsc.xxt.client.vo.ProductVo;
import com.fsc.xxt.si.product.po.Product;

/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品管理服务层接口</p>
 * <p>创建日期:Feb 9, 2012</p>
 * @author ZhouChao
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface ProductService extends BaseService {
	/**
	 * 根据条件分页查找产品列表
	 * @param productVo
	 * @throws Exception
	 */
	public void selectProList(ProductVo productVo)throws Exception;
	
	/**
	 * 根据ID查找产品信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Product findProductById(String id)throws Exception;
	
	/**
	 * 保存产品收藏信息
	 * @param userId 用户ID
	 * @param userType 用户类型
	 * @param productId 产品ID
	 * @return
	 * @throws Exception
	 */
	public boolean saveProCollect(String userId,String userType,String productId)throws Exception;
	
	/**
	 * 查找用户产品收藏数量
	 * @param userType
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Long selectUserCollectCount(String userType,String userId)throws Exception;
	
	/**
	 * 分页查找产品收藏信息
	 * @param msgVo
	 * @throws Exception
	 */
	public void selectProCollectPageData(MsgVo msgVo,String userId)throws Exception;
	
	/**
	 * 删除产品收藏
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delProCoolect(String id)throws Exception;
	
	/**
	 * 分页查找产品信息(后台)
	 * @author: 刘源
	 * @date：2012-5-9 下午04:13:06   
	 * @return：void   
	 * @throws: Exception
	 */
	public void selectPageData(ProductVo productvo)throws Exception;
	
	/**
	 * 新产品发布保存(后台)
	 * @author: 刘源
	 * @date：2012-5-13 下午05:39:15   
	 * @return：void   
	 * @throws: Exception
	 */
	public void saveNewProduct(ProductVo productvo)throws Exception;
	
	/**
	 * 产品删除根据ID（后台）
	 * @author: 刘源
	 * @date：2012-5-14 下午02:01:35   
	 * @return：void   
	 * @throws: Exception
	 */
	public void delProductById(String ids[])throws Exception;
}
