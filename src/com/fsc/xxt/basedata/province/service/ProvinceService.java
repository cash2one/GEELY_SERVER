package com.fsc.xxt.basedata.province.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.fsc.xxt.basedata.province.po.Province;
import com.fsc.framework.base.service.BaseService;
import com.fsc.framework.base.vo.BaseVo;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:省份服务</p>
 * <p>创建日期:2010-12-6</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface ProvinceService extends BaseService {
    /**
     * 查询所有省份
     * @return
     * @throws Exception
     */
    List selectAllProvince() throws Exception;

    /**
     * 查询所有省份
     * @return
     * @throws Exception
     */
    void selectAllProvinceWithPageNo(BaseVo baseVo) throws Exception;

    /**
     * 通过主键查找省份
     * @param id
     * @throws DataAccessException
     */
    Province selectProvinceById(String id) throws Exception;

    /**
     * 根据id删除
     * @param ids
     * @throws DataAccessException
     */
    void removeByIds(String[] ids) throws DataAccessException;
    /**
     * 得到最大省份编号
     * @return
     * @throws Exception
     */
    String selectMaxProvinceCode() throws Exception;
    
}
