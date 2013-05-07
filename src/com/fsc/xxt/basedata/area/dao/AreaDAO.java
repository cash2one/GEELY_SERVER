package com.fsc.xxt.basedata.area.dao;

import com.fsc.framework.base.dao.BaseDao;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:地区dao</p>
 * <p>创建日期:2010-12-6</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface AreaDAO extends BaseDao {
	 /**
     * 根据省份id查找地区
     * @param id
     * @return
     * @throws DataAccessException
     */
    List selectAreaByProvinceId(String id) throws Exception;
}
