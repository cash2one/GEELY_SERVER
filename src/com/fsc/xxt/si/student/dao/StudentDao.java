package com.fsc.xxt.si.student.dao;

import com.fsc.framework.base.dao.BaseDao;

import com.fsc.xxt.si.student.po.Student;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学生dao接口</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface StudentDao extends BaseDao {
	/**
	 * 按属性查找学生列表
	 * @param pName
	 * @param pVal
	 * @return
	 * @throws Exception
	 */
    List selectStuListByProp(String pName, Object pVal)
        throws Exception;
    /**
	 * 按属性查找学生
	 * @param pName
	 * @param pVal
	 * @return
	 * @throws Exception
	 */
    Student selectStuByProp(String attName, Object pVal)
        throws Exception;
    
    /**
     * 根据学生ID查找学生信息
     * @param id
     * @return
     * @throws Exception
     */
    public Student findStudentById(String id)throws Exception;
    
    /**
     * 根据学生姓名查找学生信息
     * @param name
     * @return
     * @throws Exception
     */
    public Student findStudentByName(String name)throws Exception;
    /**
     * 根据学生手机号获取学生ID,NAME,昵称
     * @param mobile
     * @return
     * @throws Exception
     */
    public Student getStuInfoBymobili(String mobile)throws Exception;
    /**
	 * 按属性查找家长信息
	 * @param pName
	 * @param pVal
	 * @return
	 * @throws Exception
	 */
	List selectStudentListByProp(String pName,Object pVal) throws Exception;
	/**
	 * 
	 * 根据学生ID,班级ID,学校ID 获取所在班级学生
	 * @param loginname
	 * @return
	 * @throws Exception
	 */
	List<Student> getStudentList(String id,String classid,String schoolid)throws Exception;
}
