package com.fsc.xxt.client.service;

import java.util.List;
import java.util.Map;

import com.fsc.framework.base.service.BaseService;

/**
 * 
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:MES信息服务接口层</p>
 * <p>创建日期:2013-4-18</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface GeelyMesInfoService extends BaseService {
	/**
	 * 根据时间段查询MESINFO
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws Exception
	 */
	public List<Map> selectMesInfo(String startTime,String endTime)throws Exception;
	
	/**
	 * 根据时间段和类别查找MES详细信息
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param lb 类别
	 * @return
	 * @throws Exception
	 */
	public List<Map> selectMesDetailInfo(String startTime,String endTime,String lb)throws Exception;
}
