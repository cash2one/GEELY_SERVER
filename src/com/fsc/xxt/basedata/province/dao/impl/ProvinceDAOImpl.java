package com.fsc.xxt.basedata.province.dao.impl;

import java.util.List;

import com.fsc.xxt.basedata.province.dao.ProvinceDAO;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;

public class ProvinceDAOImpl extends BaseDaoImpl implements ProvinceDAO {
	public List selectAllProvince() throws Exception {
        return super.selectDataByHQL("from Province");
    }
}
