package com.fsc.xxt.basedata.town.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.fsc.xxt.basedata.town.dao.TownDAO;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


public class TownDAOImpl extends BaseDaoImpl implements TownDAO {
	  public List selectTownByAreaId(String id) throws DataAccessException {
	    	String hql="from Town where area='" + id+"'";
	        return selectDataByHQL(hql);
	    }
}
