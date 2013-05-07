package com.fsc.xxt.basedata.area.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.fsc.xxt.basedata.area.dao.AreaDAO;
import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.xxt.basedata.area.service.AreaService;
import com.fsc.framework.base.dao.BaseDao;
import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.base.vo.BaseVo;


public class AreaServiceImpl extends BaseServiceImpl implements AreaService {
    private AreaDAO areaDAO;

    public AreaDAO getAreaDAO() {
        return areaDAO;
    }

    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    public List selectAllAreas() throws Exception {
    	String hql="from Area";
        return super.selectDataByHQL(hql);
    }

    public void selectAllCourses(BaseVo baseVo) throws Exception {
    	   String hql = "from Course";
           String countHql = "select count(id) " + hql;
           super.selectPageData(baseVo, hql, countHql);
    }

    public List selectAreaByProvinceId(String id) throws Exception {
        return areaDAO.selectAreaByProvinceId(id);
    }
    
    public void selectAreaByProvinceIdWithPageNo(String id,BaseVo baseVo) throws Exception {
    	String hql="from Area where province='" + id+"' order by area asc";
    	String countHql = "select count(id) " + hql;
    	super.selectPageData(baseVo, hql, countHql);
    }

    private String createHQLSeqment(String[] ids) {
        Map properties = new HashMap();
        areaDAO.transArrToList(properties, "id", ids, BaseDao.STRING_TYPE);

        String hql = areaDAO.transMapToHQL("delete from Area ", properties);

        return hql;
    }

    public void removeByIds(String[] ids) throws DataAccessException {
        if ((null == ids) || (0 == ids.length)) {
            return;
        }

        String hql = createHQLSeqment(ids);
        areaDAO.executeHQL(hql);
    }

    public Area selectAreaById(String id) throws DataAccessException {
         return (Area) areaDAO.getObject(Area.class, id);
    }
}
