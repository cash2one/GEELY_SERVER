package com.fsc.xxt.si.school.dao;

import com.fsc.framework.base.dao.BaseDao;

import com.fsc.xxt.si.school.po.School;
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
public interface SchoolDao extends BaseDao {
	/**
	 * 根据ID 查询学校信息
	 * @author: 刘源
	 * @date：2012-5-21 上午01:09:57   
	 * @return：School   
	 * @throws: Exception
	 */
	public School findSchoolById(String id)throws Exception;
}
