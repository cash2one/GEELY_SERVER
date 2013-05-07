package com.fsc.xxt.si.classes.service;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.classes.vo.ClassesVo;
import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.si.school.vo.SchoolVo;

import java.util.List;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:班级服务接口</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbe
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface ClassesService extends BaseService {
	/**
	 * 根据老师ID获取班级列表
	 * @author: 刘源
	 * @date：2012-5-21 上午02:29:44   
	 * @return：List   
	 * @throws: Exception
	 */
    List selectClassesByTId(String teacherId) throws Exception;
    
    /**
     * 查询分页
     * @author: 刘源
     * @date：2012-5-21 上午02:30:07   
     * @return：void   
     * @throws: Exception
     */
    public void selectPageData(ClassesVo classesvo)throws Exception;
    
    /**
     * 学校班级信息添加
     * @author: 刘源
     * @date：2012-5-21 上午03:38:22   
     * @return：void   
     * @throws: Exception
     */
	public void saveclassesInfo(ClassesVo classesvo)throws Exception;
	
	/**
	 * 根据ID获取学校班级信息
	 * @author: 刘源
	 * @date：2012-5-21 上午03:56:57   
	 * @return：Classes   
	 * @throws: Exception
	 */
	public Classes findclassesById(String id)throws Exception;
	
	/**
	 * 学校班级信息删除
	 * @author: 刘源
	 * @date：2012-5-21 上午04:17:57   
	 * @return：void   
	 * @throws: Exception
	 */
	public void delclasses(String ids[])throws Exception;
	
	
}
