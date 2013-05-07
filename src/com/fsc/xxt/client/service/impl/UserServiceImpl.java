package com.fsc.xxt.client.service.impl;

import com.fsc.framework.base.po.MobileUser;
import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.client.service.UserService;
import com.fsc.xxt.si.student.dao.StudentDao;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.teacher.dao.TeacherDao;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:手机客户服务接口实现</p>
 * <p>创建日期:Jan 5, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private StudentDao studentDao;
    private TeacherDao teacherDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public Object getUserById(String userType, String id)
        throws Exception {
        if (DictionaryConstant.USER_TEACHER.equals(userType)) {
            return teacherDao.getObject(Teacher.class, id);
        } else if (DictionaryConstant.USER_STUDENT.equals(userType)) {
            return studentDao.getObject(Student.class, id);
        } else if (DictionaryConstant.USER_SCHADMIN.equals(userType)) {
        } else {
            throw new Exception("未知的用户类型参数");
        }

        return null;
    }

    public Object getUserByLogin(String userType, String loginname)
        throws Exception {
        if (DictionaryConstant.USER_TEACHER.equals(userType)) {
            return teacherDao.selectTeacherByProp("loginname", loginname);
        } else if (DictionaryConstant.USER_STUDENT.equals(userType)) {
            return studentDao.selectStuByProp("loginname", loginname);
        } else if (DictionaryConstant.USER_SCHADMIN.equals(userType)) {
        } else {
            throw new Exception("未知的用户类型参数");
        }

        return null;
    }

    public String getUserIdByLogin(String userType, String loginname)
        throws Exception {
        MobileUser user = (MobileUser) getUserByLogin(userType, loginname);
        String id = user.getId();

        return id;
    }

    @Override
    public Object getUserByMobile(String mobile) throws Exception {
        Object object = studentDao.selectStuByProp("mobile", mobile);
        object = (object == null)
            ? teacherDao.selectTeacherByProp("mobile", mobile) : object;

        return object;
    }
}
