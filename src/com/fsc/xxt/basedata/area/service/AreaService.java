package com.fsc.xxt.basedata.area.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.framework.base.service.BaseService;
import com.fsc.framework.base.vo.BaseVo;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:地区服务</p>
 * <p>创建日期:2010-12-6</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface AreaService extends BaseService {
    /**
     * 查询所有地区
     * @return
     * @throws DataAccessException
     */
    List selectAllAreas() throws Exception;

    /**
     * 查询所有地区
     * @return
     * @throws DataAccessException
     */
    void selectAllCourses(BaseVo baseVo) throws Exception;

    /**
     * 根据省份id查找地区
     * @param id
     * @return
     * @throws DataAccessException
     */
    List selectAreaByProvinceId(String id) throws Exception;
    /**
     * 根据省份id查找地区，分页
     * @param id
     * @return
     * @throws DataAccessException
     */
     void selectAreaByProvinceIdWithPageNo(String id,BaseVo baseVo) throws Exception;

    /**
     * 根据地区id查找
     * @param id
     * @return
     * @throws DataAccessException
     */
    Area selectAreaById(String id) throws Exception;

    /**
     * 根据id删除
     * @param ids
     * @throws DataAccessException
     */
    void removeByIds(String[] ids) throws DataAccessException;
}
