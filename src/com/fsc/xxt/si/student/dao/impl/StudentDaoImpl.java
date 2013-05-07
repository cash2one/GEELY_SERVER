package com.fsc.xxt.si.student.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.student.dao.StudentDao;
import com.fsc.xxt.si.student.po.Student;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学生Dao接口实现</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {
    @Override
    public List selectStuListByProp(String pName, Object pVal)
        throws Exception {
        String hql = "from Student where " + pName + "=?";

        return selectDataByHQL(hql,new Object[] { pVal });
    }

    @Override
    public Student selectStuByProp(String pName, Object pVal)
        throws Exception {
        List list = selectStuListByProp(pName, pVal);

        return (list.size() > 0) ? (Student) list.get(0) : null;
    }

	@Override
	public Student findStudentById(String id) throws Exception {
		return (Student)getObject(Student.class, id);
	}

	@Override
	public Student findStudentByName(String name) throws Exception {
		String hql="from Student where name='"+name+"'";
		return (Student) getObject(hql);
	}

	@Override
	public Student getStuInfoBymobili(String mobile) throws Exception {
		String hql = "from Student where mobile="+"'"+mobile+"'";
		Student student = (Student)this.getObject(hql);
		if (student!=null) {
			return student;
		}
		return null;
	}

	@Override
	public List selectStudentListByProp(String pName, Object pVal)
			throws Exception {
		String hql ="from Student where " + pName + "=?";
        return selectDataByHQL(hql,new Object[] {pVal});
	}

	@Override
	public List<Student> getStudentList(String id, String classid,
			String schoolid) throws Exception {
		String hql ="from Student where classid="+classid+" and schoolid="+schoolid+" and id<>"+id;
		List<Student> list = selectDataByHQL(hql);
		return list;
	}
}
