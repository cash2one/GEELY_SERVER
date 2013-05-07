package com.fsc.xxt.basedata.province.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.fsc.xxt.basedata.province.dao.ProvinceDAO;
import com.fsc.xxt.basedata.province.po.Province;
import com.fsc.xxt.basedata.province.service.ProvinceService;
import com.fsc.framework.base.dao.BaseDao;
import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.base.vo.BaseVo;


public class ProvinceServiceImpl extends BaseServiceImpl
    implements ProvinceService {
    private ProvinceDAO provinceDAO;

    public ProvinceDAO getProvinceDAO() {
        return provinceDAO;
    }

    public void setProvinceDAO(ProvinceDAO provinceDAO) {
        this.provinceDAO = provinceDAO;
    }

    public List selectAllProvince() throws Exception {
        return provinceDAO.selectAllProvince();
    }

    public void selectAllProvinceWithPageNo(BaseVo baseVo) throws Exception {
    	String hql = "from Province";
        String countHql = "select count(id) " + hql;
        super.selectPageData(baseVo, hql, countHql);
    }

    public Province selectProvinceById(String id) throws Exception {
        return (Province) provinceDAO.getObject(Province.class, id);
    }

    private String createHQLSeqment(String[] ids) {
        Map properties = new HashMap();
        provinceDAO.transArrToList(properties, "id", ids, BaseDao.STRING_TYPE);

        String hql = provinceDAO.transMapToHQL("delete from Province ",
                properties);

        return hql;
    }

    public void removeByIds(String[] ids) throws DataAccessException {
        if ((null == ids) || (0 == ids.length)) {
            return;
        }

        String hql = createHQLSeqment(ids);
        provinceDAO.executeHQL(hql);
    }

	@Override
	public String selectMaxProvinceCode() throws Exception {
		String maxCode=(String)provinceDAO.getAttributeValueByHql("select max(code) from Province");
		return maxCode==null?"00":maxCode;
	}

}
