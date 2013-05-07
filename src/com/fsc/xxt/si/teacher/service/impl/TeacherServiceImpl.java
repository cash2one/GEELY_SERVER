package com.fsc.xxt.si.teacher.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.util.CryptoUtil;
import com.fsc.xxt.si.msg.po.NickName;
import com.fsc.xxt.si.msg.service.MsgService;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.teacher.dao.TeacherDao;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.si.teacher.service.TeacherService;
import com.fsc.xxt.si.teacher.vo.TeacherVo;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * Title:校讯通手机服务端
 * </p>
 * <p>
 * Description:教师服务接口实现技
 * </p>
 * <p>
 * 创建日期:Dec 28, 2011
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
public class TeacherServiceImpl extends BaseServiceImpl
    implements TeacherService {
    private TeacherDao teacherDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher selectTeacherByLogin(String loginname)
        throws Exception {
        Teacher teacher = null;
        List<Teacher> list = teacherDao.selectTeacherListByProp("loginname",
                loginname);

        if (list.size() > 0) {
            teacher = list.get(0);
            Hibernate.initialize(teacher.getSchool());
        }

        return teacher;
    }

    @Override
    public List<Teacher> selectOneSchoolTeacher(String schoolId, String userName)
        throws Exception {
        return teacherDao.selectOneSchoolTeacher(schoolId, userName);
    }

    @Override
    public boolean editNickName(String userId, String usertype,
        String linkmanid, String linkmantype, String nickName)
        throws Exception {
        try {
            NickName nname = teacherDao.getnickname(userId, usertype,
                    linkmanid, linkmantype);

            if (nname.getId() == null) {
                nname.setId(this.getId());
                nname.setLoginid(userId);
                nname.setLogintype(usertype);
                nname.setLinkmanid(linkmanid);
                nname.setLinkmantype(linkmantype);
                nname.setNickname(nickName);
                teacherDao.saveObject(nname);
            } else {
                nname.setLoginid(userId);
                nname.setLogintype(usertype);
                nname.setLinkmanid(linkmanid);
                nname.setLinkmantype(linkmantype);
                nname.setNickname(nickName);
                teacherDao.updateObject(nname);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean updateFaceImg(String loginName, String faceImg)
        throws Exception {
        try {
            Teacher teacher = teacherDao.selectTeacherByProp("loginName",
                    loginName);
            teacher.setFaceimg(faceImg);
            teacherDao.updateObject(teacher);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public Teacher getTeacherInfo(String loginname) throws Exception {
        return this.teacherDao.getTeaInfo(loginname);
    }

    @Override
    public Object getTeacherImg(String USER_ID, String USER_TYPE)
        throws Exception {
        if (DictionaryConstant.USER_TEACHER.equals(USER_TYPE)) {
            return this.getObject("from Teacher where id=" + USER_ID);
        }

        if (DictionaryConstant.USER_STUDENT.equals(USER_TYPE)) {
            return this.getObject("from Student where id=" + USER_ID);
        }

        return null;
    }

    @Override
    public Teacher getTeacherInfoBySmscode(String smscode)
        throws Exception {
        return this.teacherDao.getTeainfobysms(smscode);
    }

    @Override
    public Teacher getTeacherInfoByMobile(String mobile)
        throws Exception {
        return this.teacherDao.getTeacherinfobymobi(mobile);
    }

    @Override
    public List<Teacher> getTeacherByClassID(String classid)
        throws Exception {
        return teacherDao.getTeacherInfoByClassId(classid);
    }

    // public NickName getNickName(String loginid, String logintype,
    // String linkmanid, String linkmantype) throws Exception {
    @Override
    public List<NickName> getNickNames(String loginid, String logintype,
        String[] linkmanids, String linkmantype) throws Exception {
        //		
        return teacherDao.getnicknames(loginid, logintype, linkmanids,
            linkmantype);
    }

    @Override
    public NickName getNickName(String loginid, String logintype,
        String linkmanid, String linkmantype) throws Exception {
        return teacherDao.getnickname(loginid, logintype, linkmanid, linkmantype);
    }

    @Override
    public void selectPageData(TeacherVo teachervo) throws Exception {
        String hql = " from Teacher where 1=1";

        if ((teachervo.getCity() != null) && !"".equals(teachervo.getCity())) {
            hql += (" and school.areaid='" + teachervo.getCity() + "'");
        }

        if ((teachervo.getTown() != null) && !"".equals(teachervo.getTown())) {
            hql += (" and school.townid='" + teachervo.getTown() + "'");
        }

        if ((teachervo.getSchoolname() != null) &&
                !"".equals(teachervo.getSchoolname())) {
            hql += (" and school.name like '%" + teachervo.getSchoolname() +
            "%'");
        }

        if ((teachervo.getClassname() != null) &&
                !"".equals(teachervo.getClassname())) {
            hql += (" and classes.name like '%" + teachervo.getClassname() +
            "%'");
        }

        if ((teachervo.getName() != null) && !"".equals(teachervo.getName())) {
            hql += (" and name like '%" + teachervo.getName() + "%'");
        }

        hql += " order by regtime desc";

        String hqlcount = "select count(*) " + hql;
        this.selectPageData(teachervo, hql, hqlcount);
    }

	@Override
	public void saveteacherInfo(TeacherVo teachervo) throws Exception {
		Teacher teacher = new Teacher();
		 if ((teachervo.getId() == null) || "".equals(teachervo.getId())) {
	            PropertyUtils.copyProperties(teacher, teachervo);
	            teacher.setId(getId());
	            teacher.setRegtime(new Date());
	            teacher.setIslogin("01");
	            teacher.setPass(CryptoUtil.desEncrypt(teachervo.getPass()));
	        } else {
	            PropertyUtils.copyProperties(teacher, teachervo);
	            teacher.setRegtime(new Date());
	            teacher.setPass(CryptoUtil.desEncrypt(teachervo.getPass()));
	        }
		 this.saveOrUpdateObject(teacher);
	}

	@Override
	public Teacher findTeacherById(String id) throws Exception {
		
		return teacherDao.findTeacherById(id);
	}

	@Override
	public void delteacher(String[] ids) throws Exception {
		try {
            String hql = "from Teacher where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            List<Teacher> listpro = (List<Teacher>)this.selectDataByHQL(hql, ids);
            this.deleteCollection(listpro);
        } catch (Exception e) {
            e.printStackTrace();
        }	
	}
}
