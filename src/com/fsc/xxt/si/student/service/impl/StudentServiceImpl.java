package com.fsc.xxt.si.student.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.util.CryptoUtil;
import com.fsc.xxt.si.bulletin.po.Bulletin;
import com.fsc.xxt.si.student.dao.StudentDao;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.service.StudentService;
import com.fsc.xxt.si.student.vo.StudentVo;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学生服务接口实现</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class StudentServiceImpl extends BaseServiceImpl
    implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List selectStuByClass(String classid) throws Exception {
        return studentDao.selectStuListByProp("classid", classid);
    }

    @Override
    public Student findStudentById(String id) throws Exception {
        return studentDao.findStudentById(id);
    }

    @Override
    public Student findStudentByName(String name) throws Exception {
        return studentDao.findStudentByName(name);
    }

    @Override
    public boolean editStudentNickName(String userId, String nickName)
        throws Exception {
        try {
            Student student = findStudentById(userId);
            student.setNickname(nickName);
            studentDao.updateObject(student);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Student getStudentInfoBymobile(String mobile)
        throws Exception {
        return this.studentDao.getStuInfoBymobili(mobile);
    }

    @Override
    public Student selectStudentByLogin(String loginname)
        throws Exception {
        Student student = null;
        List<Student> list = studentDao.selectStudentListByProp("loginname",
                loginname);

        if (list.size() > 0) {
            student = list.get(0);
            Hibernate.initialize(student.getSchool());
        }

        return student;
    }

    @Override
    public boolean updateStudentFaceImg(String loginame, String faceimg)
        throws Exception {
        try {
            List list = studentDao.selectStudentListByProp("loginname", loginame);
            Student student = null;

            for (Object object : list) {
                student = (Student) object;
            }

            student.setFaceimg(faceimg);
            studentDao.updateObject(student);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<Student> selectStuList(String id, String classid,
        String schoolid) throws Exception {
        return studentDao.getStudentList(id, classid, schoolid);
    }

    @Override
    public void selectPageData(StudentVo studentvo) throws Exception {
        try {
            String hql = " from Student where 1=1";

            if ((studentvo.getCity() != null) &&
                    !"".equals(studentvo.getCity())) {
                hql += (" and school.areaid='" + studentvo.getCity() + "'");
            }

            if ((studentvo.getTown() != null) &&
                    !"".equals(studentvo.getTown())) {
                hql += (" and school.townid='" + studentvo.getTown() + "'");
            }

            if ((studentvo.getSchoolname() != null) &&
                    !"".equals(studentvo.getSchoolname())) {
                hql += (" and school.name like '%" + studentvo.getSchoolname() +
                "%'");
            }

            if ((studentvo.getClassname() != null) &&
                    !"".equals(studentvo.getClassname())) {
                hql += (" and classes.name like '%" + studentvo.getClassname() +
                "%'");
            }

            if ((studentvo.getName() != null) &&
                    !"".equals(studentvo.getName())) {
                hql += (" and name like '%" + studentvo.getName() + "%'");
            }

            hql += " order by regtime desc";

            String hqlcount = "select count(*) " + hql;
            this.selectPageData(studentvo, hql, hqlcount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void savestudnetInfo(StudentVo studentvo) throws Exception {
		 Student student = new Student();

        if ((studentvo.getId() == null) || "".equals(studentvo.getId())) {
            PropertyUtils.copyProperties(student, studentvo);
            student.setId(getId());
            student.setRegtime(new Date());
            student.setIslogin("01");
            student.setPass(CryptoUtil.desEncrypt(studentvo.getPass()));
        } else {
            PropertyUtils.copyProperties(student, studentvo);
            student.setRegtime(new Date());
            student.setPass(CryptoUtil.desEncrypt(studentvo.getPass()));
        }

        this.saveOrUpdateObject(student);
    }

	@Override
	public void delstudent(String[] ids) throws Exception {
		try {
            String hql = "from Student where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            List<Student> listpro = (List<Student>)this.selectDataByHQL(hql, ids);
            this.deleteCollection(listpro);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
		
}
