package com.fsc.xxt.basedata.town.service;

import com.fsc.xxt.basedata.town.po.Town;

import com.fsc.framework.base.service.BaseService;
import com.fsc.framework.base.vo.BaseVo;

import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:镇区服务</p>
 * <p>创建日期:2010-12-6</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface TownService extends BaseService {
    /**
     * 查询所有镇区
     * @return
     * @throws DataAccessException
     */
    List selectAllTowns() throws Exception;

    /**
     * 根据地区id查找镇区
     * @param id
     * @return
     * @throws DataAccessException
     */
    List selectTownByAreaId(String id) throws DataAccessException;
    /**
     * 根据地区id查找镇区，分页
     * @param id
     * @return
     * @throws DataAccessException
     */
    void selectTownByAreaIdWithPageNo(String id,BaseVo baseVo) throws Exception;
    /**
     * 通过主键查找城镇
     * @param id
     * @throws DataAccessException
     */
    Town selectTownById(String id) throws Exception;

    /**
     * 根据id删除
     * @param ids
     * @throws DataAccessException
     */
    void removeByIds(String[] ids) throws DataAccessException;
}
