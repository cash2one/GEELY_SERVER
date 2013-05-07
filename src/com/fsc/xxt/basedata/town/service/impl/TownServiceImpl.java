package com.fsc.xxt.basedata.town.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.fsc.xxt.basedata.town.dao.TownDAO;
import com.fsc.xxt.basedata.town.po.Town;
import com.fsc.xxt.basedata.town.service.TownService;
import com.fsc.framework.base.dao.BaseDao;
import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.base.vo.BaseVo;


public class TownServiceImpl extends BaseServiceImpl implements TownService {
    private TownDAO townDAO;

    public TownDAO getTownDAO() {
        return townDAO;
    }

    public void setTownDAO(TownDAO townDAO) {
        this.townDAO = townDAO;
    }

    public List selectAllTowns() throws Exception {
        return townDAO.selectDataByHQL("from Town");
    }

    public List selectTownByAreaId(String id) throws DataAccessException {
        return townDAO.selectTownByAreaId(id);
    }
    
    public void selectTownByAreaIdWithPageNo(String id,BaseVo baseVo) throws Exception {
    	String hql="from Town where area='" + id+"'";
    	String countHql = "select count(id) " + hql;
    	super.selectPageData(baseVo, hql, countHql);
    }

    public Town selectTownById(String id) throws Exception {
        // TODO Auto-generated method stub
        return (Town) townDAO.getObject(Town.class, id);
    }

    private String createHQLSeqment(String[] ids) {
        Map properties = new HashMap();
        townDAO.transArrToList(properties, "id", ids, BaseDao.STRING_TYPE);
        String hql = townDAO.transMapToHQL("delete from Town ", properties);
        return hql;
    }

    public void removeByIds(String[] ids) throws DataAccessException {
        if ((null == ids) || (0 == ids.length)) {
            return;
        }

        String hql = createHQLSeqment(ids);
        townDAO.executeHQL(hql);
    }
}
