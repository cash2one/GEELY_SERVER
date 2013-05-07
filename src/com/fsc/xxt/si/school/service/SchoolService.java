package com.fsc.xxt.si.school.service;



import java.util.List;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.si.school.vo.SchoolVo;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.vo.StudentVo;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学生服务接口</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface SchoolService extends BaseService {
	
	/**
	 * 查询分页
	 * @author: 刘源
	 * @date：2012-5-20 下午11:44:55   
	 * @return：void   
	 * @throws: Exception
	 */
	public void selectPageData(SchoolVo schoolvo)throws Exception;
	
	/**
	 * 学校信息添加
	 * @author: 刘源
	 * @date：2012-5-21 上午12:43:04   
	 * @return：void   
	 * @throws: Exception
	 */
	public void saveschoolInfo(SchoolVo schoolvo)throws Exception;
	
	/**
	 * 根据ID查询学校信息
	 * @author: 刘源
	 * @date：2012-5-21 上午01:08:10   
	 * @return：School   
	 * @throws: Exception
	 */
	public School findSchoolById(String id)throws Exception;
	
	/**
	 * 学校信息删除
	 * @author: 刘源
	 * @date：2012-5-21 上午02:08:10   
	 * @return：void   
	 * @throws: Exception
	 */
	public void delschool(String ids[])throws Exception;
	
}
