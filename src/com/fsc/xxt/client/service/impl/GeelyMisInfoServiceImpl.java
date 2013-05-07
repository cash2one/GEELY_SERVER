package com.fsc.xxt.client.service.impl;

import java.util.List;
import java.util.Map;

import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.xxt.client.po.GeelyMisInfo;
import com.fsc.xxt.client.service.GeelyMisInfoService;

public class GeelyMisInfoServiceImpl extends BaseServiceImpl implements
		GeelyMisInfoService {

	@Override
	public List<Map> getZzmcList() throws Exception {
		String sql ="select zzmc,max(zzdm) as zzdm from bi_misinfo group by zzmc";
		return this.selectDataBySQLMap(sql);
	}

	@Override
	public List<GeelyMisInfo> getMisInfoListByZzmcAndType(String startTime,
			String endTime, String misType, String zzmc) throws Exception {
		String sql="from GeelyMisInfo where rq between '"+startTime+"' and '"+endTime+"' and zbmc='"+misType+"' and zzmc='"+zzmc+"' order by rq desc";
		return selectDataByHQL(sql);
	}

}
