package com.fsc.xxt.basedata.province.service;

import java.util.List;

import com.fsc.xxt.basedata.area.dao.AreaDAO;
import com.fsc.xxt.basedata.province.dao.ProvinceDAO;
import com.fsc.xxt.basedata.town.dao.TownDAO;

public interface ProvinceTreeService {
	/**
	 * 获取地区树结点
	 * @return
	 * @throws Exception
	 */
	List selectProvinceTree(String params) throws Exception;
}