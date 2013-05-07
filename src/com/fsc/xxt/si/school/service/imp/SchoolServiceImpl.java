package com.fsc.xxt.si.school.service.imp;

import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.CryptoUtil;

import com.fsc.xxt.si.school.dao.SchoolDao;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.si.school.service.SchoolService;
import com.fsc.xxt.si.school.vo.SchoolVo;
import com.fsc.xxt.si.teacher.po.Teacher;

import org.apache.commons.beanutils.PropertyUtils;

import org.springframework.dao.DataAccessException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class SchoolServiceImpl extends BaseServiceImpl implements SchoolService {
    private SchoolDao schoolDao;

    public SchoolDao getSchoolDao() {
        return schoolDao;
    }

    public void setSchoolDao(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public void selectPageData(SchoolVo schoolvo) throws Exception {
        String hql = " from School where 1=1";

        if ((schoolvo.getSprovince() != null) &&
                !"".equals(schoolvo.getSprovince())) {
            hql += (" and area.provinceid='" + schoolvo.getSprovince() + "'");
        }

        if ((schoolvo.getScity() != null) && !"".equals(schoolvo.getScity())) {
            hql += (" and areaid='" + schoolvo.getScity() + "'");
        }

        if ((schoolvo.getStown() != null) && !"".equals(schoolvo.getStown())) {
            hql += (" and townid='" + schoolvo.getStown() + "'");
        }

        if ((schoolvo.getSchoolname() != null) &&
                !"".equals(schoolvo.getSchoolname())) {
            hql += (" and name like '%" + schoolvo.getSchoolname() + "%'");
        }

        hql += " order by id desc";

        String hqlcount = "select count(*) " + hql;
        this.selectPageData(schoolvo, hql, hqlcount);
    }

    @Override
    public void saveschoolInfo(SchoolVo schoolvo) throws Exception {
        if ((schoolvo.getId() == null) || "".equals(schoolvo.getId())) {
            School school = new School();
            PropertyUtils.copyProperties(school, schoolvo);
            school.setId(getId());
            school.setAreaid(schoolvo.getScity());
            school.setTownid(schoolvo.getStown());
            this.saveOrUpdateObject(school);
        } else {
            School school = findSchoolById(schoolvo.getId());

            if ((schoolvo.getSprovince() == null) ||
                    "".equals(schoolvo.getSprovince())) {
                school.setName(schoolvo.getName());
                this.saveOrUpdateObject(school);
            } else {
                if ((schoolvo.getScity() == null) ||
                        "".equals(schoolvo.getScity())) {
                    school.setName(schoolvo.getName());
                    this.saveOrUpdateObject(school);
                } else {
                    if ((schoolvo.getStown() == null) ||
                            "".equals(schoolvo.getStown())) {
                        school.setName(schoolvo.getName());
                        this.saveOrUpdateObject(school);
                    } else {
                        school.setAreaid(schoolvo.getScity());
                        school.setTownid(schoolvo.getStown());
                        this.saveOrUpdateObject(school);
                    }
                }
            }
        }
    }

    @Override
    public School findSchoolById(String id) throws Exception {
        return schoolDao.findSchoolById(id);
    }

	@Override
	public void delschool(String[] ids) throws Exception {
		try {
            String hql = "from School where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            List<School> listpro = (List<School>)this.selectDataByHQL(hql, ids);
            this.deleteCollection(listpro);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
