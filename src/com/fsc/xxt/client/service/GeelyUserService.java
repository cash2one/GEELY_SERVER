package com.fsc.xxt.client.service;

import java.util.List;
import java.util.Map;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.client.po.GeelyUser;

/**
 * 
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:吉利用户信息服务接口层</p>
 * <p>创建日期:2013-4-9</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface GeelyUserService extends BaseService {
	/**
	 * 根据用户ID查找用户信息
	 * @param id
	 * @return
	 */
	public GeelyUser findUserById(Integer id) throws Exception;
	
	/**
	 * 根据用户名查找用户信息
	 * @param userName
	 * @return
	 */
	public GeelyUser findUserByName(String userName) throws Exception;
	
	
	public List<Map> selectUserUnreadMetting(Integer userId)throws Exception;
	
	
	public List<Map> selectUserUnreadWarning(Integer userId)throws Exception;
	
	public List<Map> selectUsreUnsendAndUnreadMetting()throws Exception;
	
	public List<Map> selectUserUnsendAndUnreadWarning()throws Exception;
	
	public void updateWarningSendFlag(String ids)throws Exception;
	
	public void updateMettingSendFlag(String ids)throws Exception;
	
	public void updateWarningReadFlag(Integer userId,String ids)throws Exception;
	
	public void updateMettingReadFlag(Integer userId,String ids)throws Exception;
	
	public Map getLastVersionInfo()throws Exception;
	
}
