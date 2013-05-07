package com.fsc.xxt.si.school.dao.imp;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.xxt.si.school.dao.SchoolDao;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.si.teacher.po.Teacher;

public class SchoolDaoImpl extends BaseDaoImpl implements SchoolDao {

	@Override
	public School findSchoolById(String id) throws Exception {
		String hql = "from School where id='" + id + "'";
		Object obj = this.getObject(hql);
		if (obj != null && School.class.equals(obj.getClass())) {
			return (School) obj;
		}
		return null;
	}



}
