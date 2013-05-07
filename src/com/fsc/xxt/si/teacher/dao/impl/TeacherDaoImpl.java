package com.fsc.xxt.si.teacher.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.msg.po.NickName;
import com.fsc.xxt.si.teacher.dao.TeacherDao;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

import java.util.List;

/**
 * <p>
 * Title:校讯通手机服务端
 * </p>
 * <p>
 * Description:教师Dao接口实现
 * </p>
 * <p>
 * 创建日期:Jan 6, 2012
 * </p>
 * 
 * @author tbw
 * @version 1.0
 *          <p>
 *          湖南家校圈科技有限公司
 *          </p>
 *          <p>
 *          http://www.139910.com
 *          </p>
 *          <p>
 *          http://wps.139910.com
 *          </p>
 */
public class TeacherDaoImpl extends BaseDaoImpl implements TeacherDao {
	@Override
	public List selectTeacherListByProp(String pName, Object pVal)
			throws Exception {
		String hql = "from Teacher where " + pName + "=?";
		return selectDataByHQL(hql, new Object[] { pVal });
	}

	@Override
	public Teacher selectTeacherByProp(String pName, Object pVal)
			throws Exception {
		List list = selectTeacherListByProp(pName, pVal);
		return (list.size() > 0) ? (Teacher) list.get(0) : null;
	}

	@Override
	public List<Teacher> selectOneSchoolTeacher(String schoolId, String userName)
			throws Exception {
		String hql = "from Teacher where schoolid='" + schoolId
				+ "' and loginname!='" + userName + "'";
		return selectDataByHQL(hql);
	}

	@Override
	public Teacher findTeacherById(String id) throws Exception {
		String hql = "from Teacher where id='" + id + "'";
		Object obj = this.getObject(hql);
		if (obj != null && Teacher.class.equals(obj.getClass())) {
			return (Teacher) obj;
		}
		return null;
	}

	@Override
	public Teacher getTeaInfo(String loginName) throws Exception {
		String hql = "from Teacher where loginname=" + "'" + loginName + "'";
		Object obj = this.getObject(hql);
		// Teacher teacher = (Teacher)this.getObject(hql);
		if (obj != null && Teacher.class.equals(obj.getClass())) {
			return (Teacher) obj;
		}
		return null;
	}

	@Override
	public Teacher getTeainfobysms(String smscode) throws Exception {
		String hql = "from Teacher where tea_smscode=" + "'" + smscode + "'";
		Teacher teacher = (Teacher) this.getObject(hql);
		if (teacher != null) {
			return teacher;
		}
		return null;
	}

	@Override
	public Teacher getTeacherinfobymobi(String mobile) throws Exception {
		String hql = "from Teacher where mobile = " + "'" + mobile + "'";
		Teacher teacher = (Teacher) this.getObject(hql);
		if (teacher != null) {
			return teacher;
		}
		return null;
	}

	@Override
	public List<Teacher> getTeacherInfoByClassId(String classid)
			throws Exception {
		String hql = "from Teacher where classid=" + classid;
		return selectDataByHQL(hql);
	}

	@Override
	public NickName getnickname(String loginid, String logintype,
			String linkmanid, String linkmantype) throws Exception {
		String hql = "from NickName where loginid=" + "'" + loginid + "'"
				+ " and logintype=" + "'" + logintype + "'" + " and linkmanid="
				+ "'" + linkmanid + "'" + " and linkmantype=" + "'"
				+ linkmantype + "'";
		NickName nn = (NickName) this.getObject(hql);

		return (this.getObject(hql) == null) ? new NickName() : nn;
	}

	@Override
	public List<NickName> getnicknames(String loginid, String logintype,
			String[] linkmanids, String linkmantype) throws Exception {
		String hql = "from NickName where loginid=" + "'" + loginid + "'"
				+ " and logintype=" + "'" + logintype + "'"+" and linkmantype="
				+ "'" + linkmantype + "'" + " and linkmanid in(";
		if (linkmanids.length > 0) {
			for (int i = 0; i < linkmanids.length; i++) {
				if (i==linkmanids.length-1) {
					hql+=" ?)";
				}else {
					hql+=" ?,";
				}
			}
		}
		return selectDataByHQL(hql,linkmanids);
	}

}
