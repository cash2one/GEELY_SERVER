package com.fsc.xxt.basedata.area.dao.impl;

import java.util.List;

import com.fsc.xxt.basedata.area.dao.AreaDAO;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


public class AreaDAOImpl extends BaseDaoImpl implements AreaDAO {
	public List selectAreaByProvinceId(String id) throws Exception {
    	String hql="from Area where province='" + id+"'";
        return super.selectDataByHQL(hql);
    }
}
