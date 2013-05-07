package com.fsc.xxt.basedata.sc.service.impl;

import java.util.List;

import com.fsc.xxt.basedata.sc.po.SegCourse;
import com.fsc.xxt.basedata.sc.service.SegCourseService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;
import com.fsc.framework.base.service.impl.BaseServiceImpl;

public class SegCourseServiceImpl extends BaseServiceImpl implements SegCourseService{

	public List selectAllSegCourse() throws Exception {
		String hql="from SegCourse";
		return super.selectDataByHQL(hql);
	}

	public List selectSegCourseBySegno(String spart) throws Exception {
		String hql="from SegCourse where spart='"+spart+"'";
		return super.selectDataByHQL(hql);
	}
	
	public List selectCourseBySegno(String spart) throws Exception{
		String hql="from Dictionary a where exists( select course from SegCourse b where a.dicCode=b.course and b.spart='"+spart+"') and dicType='"+DictionaryConstant.COURSE+"'";
		return super.selectDataByHQL(hql);
	}

	public void updateSegCourse(String[] courses, String spart)
			throws Exception {
		String hql="delete from SegCourse where spart='"+spart+"'";
		super.executeHQL(hql);
		
		for(int i=0;i<courses.length;i++){
			SegCourse segCourse =new SegCourse();
			segCourse.setSpart(spart);
			segCourse.setCourse(courses[i]);
			segCourse.setId(this.getId());
			this.saveOrUpdateObject(segCourse);
		}
	}

	public List selectCourseBySegnos(String spart) throws Exception {
		String hql="select new Dictionary(dicCode,dicName) "+
		"from Dictionary a where exists"+
		"( select course from SegCourse b where a.dicCode=b.course and b.spart in("+spart+")) and dicType='"+DictionaryConstant.COURSE+"'"
		+" group by dicCode,dicName order by dicCode";
		return super.selectDataByHQL(hql);
	}

}
