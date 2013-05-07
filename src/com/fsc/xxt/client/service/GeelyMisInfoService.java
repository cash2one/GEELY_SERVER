package com.fsc.xxt.client.service;

import java.util.List;
import java.util.Map;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.client.po.GeelyMisInfo;

public interface GeelyMisInfoService extends BaseService {
	/**
	 * 获取制造名称列表
	 * @return
	 * @throws Exception
	 */
	public List<Map> getZzmcList()throws Exception;
	
	/**
	 * 根据制造基地名称和mis类型获取一段时间内的MIS信息列表
	 * @param startTime
	 * @param endTime
	 * @param misType
	 * @param zzmc
	 * @return
	 * @throws Exception
	 */
	public List<GeelyMisInfo> getMisInfoListByZzmcAndType(String startTime,String endTime,String misType,String zzmc)throws Exception;
}
