package com.fsc.xxt.si.classes.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.classes.dao.ClassesDao;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.school.po.School;

import java.util.List;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:班级Dao接口实现</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbe
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ClassesDaoImpl extends BaseDaoImpl implements ClassesDao {
    @Override
    public List selectClassesByTId(String teacherId) throws Exception {
        String hql = "from Classes where id in(select classId from TeacherClass where teacherId=?)";

        return selectDataByHQL(hql, new Object[] { teacherId });
    }

	@Override
	public Classes findclassesById(String id) throws Exception {
		String hql = "from Classes where id='" + id + "'";
		Object obj = this.getObject(hql);
		if (obj != null && Classes.class.equals(obj.getClass())) {
			return (Classes) obj;
		}
		return null;
	}
    
    
}
