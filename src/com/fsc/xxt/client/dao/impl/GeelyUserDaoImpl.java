package com.fsc.xxt.client.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;
import com.fsc.xxt.client.dao.GeelyUserDao;
import com.fsc.xxt.client.po.GeelyUser;

public class GeelyUserDaoImpl extends BaseDaoImpl implements GeelyUserDao {

	@Override
	public GeelyUser findUserById(Integer id) throws Exception {
		
		return null;
	}

	@Override
	public GeelyUser findUserByName(String userName) throws Exception {
		String hql="from GeelyUser where text='"+userName+"'";
		return (GeelyUser) getObject(hql);
	}

}
