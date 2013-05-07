package com.fsc.xxt.client.dao;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.xxt.client.po.GeelyUser;

public interface GeelyUserDao extends BaseDao {
	
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

}
