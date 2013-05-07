package com.fsc.xxt.basedata.province.dao;

import java.util.List;

import com.fsc.framework.base.dao.BaseDao;


/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:省份dao</p>
 * <p>创建日期:2010-12-6</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface ProvinceDAO extends BaseDao {
	List selectAllProvince() throws Exception;
}
