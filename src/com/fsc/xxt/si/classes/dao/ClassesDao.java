package com.fsc.xxt.si.classes.dao;

import java.util.List;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.school.po.School;
/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:班级Dao接口</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbe
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface ClassesDao extends BaseDao {
	/**
	 * 查找老师的班级信息
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	List selectClassesByTId(String teacherId) throws Exception;
	
	/**
	 * 根据ID 获取学校班级信息
	 * @author: 刘源
	 * @date：2012-5-21 上午03:59:08   
	 * @return：Classes   
	 * @throws: Exception
	 */
	public Classes findclassesById(String id)throws Exception;
}
