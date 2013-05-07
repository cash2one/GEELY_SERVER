package com.fsc.xxt.basedata.town.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.fsc.framework.base.dao.BaseDao;


/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:镇区dao</p>
 * <p>创建日期:2010-12-6</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface TownDAO extends BaseDao {
	List selectTownByAreaId(String id) throws DataAccessException;
}
