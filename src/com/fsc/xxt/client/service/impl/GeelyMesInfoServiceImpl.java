package com.fsc.xxt.client.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.client.service.GeelyMesInfoService;

import java.util.List;
import java.util.Map;


public class GeelyMesInfoServiceImpl extends BaseServiceImpl
    implements GeelyMesInfoService {
    @Override
    public List<Map> selectMesInfo(String startTime, String endTime)
        throws Exception {
        String sql = "select sum(qtys) as qtys,caption from mes_vehstate where rq between '" +
            startTime + "' and '" + endTime +
            "' group by caption order by caption";

        return this.selectDataBySQLMap(sql);
    }

    @Override
    public List<Map> selectMesDetailInfo(String startTime, String endTime,
        String lb) throws Exception {
        String sql = "select sum(qtys) as qtys,caption from MES_VEHSTATEDT where rq between '" +
            startTime + "' and '" + endTime + "' and lb='" + lb +
            "' group by caption order by caption";

        return this.selectDataBySQLMap(sql);
    }
}
