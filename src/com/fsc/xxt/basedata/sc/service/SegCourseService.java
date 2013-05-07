package com.fsc.xxt.basedata.sc.service;

import java.util.List;

import com.fsc.framework.base.service.BaseService;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:学段学科关联服务</p>
 * <p>创建日期:2010-12-22</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface SegCourseService extends BaseService {
    /**
     * 查询所关系
     * @return
     * @throws Exception
     */
    List selectAllSegCourse() throws Exception;
    
    /**
     * 通过学段信息找到相应的学科信息
     * @param spart
     * @return
     * @throws Exception
     */
    List selectSegCourseBySegno(String spart) throws Exception;
    
    /**
     * 更新学科学段对应信息
     * @param coursenos
     */
    void updateSegCourse(String [] coursenos,String spart) throws Exception;
    
    /**
     * 通过学段信息获得学科信息列表
     * @param spart
     * @return
     * @throws Exception
     */
    public List selectCourseBySegno(String spart) throws Exception;
    
    /**
     * 通过学段信息获得学科信息列表
     * @param spart
     * @return
     * @throws Exception
     */
    public List selectCourseBySegnos(String spart) throws Exception;
}
