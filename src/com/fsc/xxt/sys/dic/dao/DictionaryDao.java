package com.fsc.xxt.sys.dic.dao;


import com.fsc.xxt.sys.dic.po.Dictionary;
import com.fsc.framework.base.dao.BaseDao;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据字典管理DAO组件接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface DictionaryDao extends BaseDao {
    /**
    * 根据id查询数据字典
    * @param id
    * @return
    * @throws Exception
    */
    public Dictionary findDictionaryById(String id) throws Exception;

    /**
     * 根据类别和代码获取数据字典对象
     * @param String dicType
     * @param String dicCode
     * @return Object
     * @throws Exception
     */
    Object getDictionary(String dicType, String dicCode)
        throws Exception;

    /**
     * 根据类别和代码获取ID
     * @param String dicType
     * @param String dicCode
     * @return Object
     * @throws Exception
     */
    Object getDicId(String dicType, String dicCode) throws Exception;

    /**
     * 根据类别和代码获取名称
     * @param String dicType
     * @param String dicCode
     * @return Object
     * @throws Exception
     */
    Object getDicName(String dicType, String dicCode) throws Exception;

    /**
     * 根据类别和代码更新名称
     * @param String dicType
     * @param String dicCode
     * @param String dicName
     * @throws Exception
     */
    void updateDicName(String dicType, String dicCode, String dicName)
        throws Exception;

    /**
     * 根据类别查询相应的数据集
     * @param String dicType
     * @return List
     * @throws Exception
     */
    List selectDictionaries(String dicType) throws Exception;

    /**
     * 根据类别查询相应的数据集(出去dicCode)
     * @param String dicType
     * @param String dicCode
     * @return List
     * @throws Exception
     */
    List selectDictionariesWithOutByDicCode(String dicType, String dicCode)
        throws Exception;
    
    /**
     * 根据类别查询相应的数据集
     * @param dicType
     * @param dicCode
     * @return
     * @throws Exception
     */
    List selectDictionariesWithByDicCode(String dicType,
            String dicCode) throws Exception;
    /**
     * 得到定义了流程的实际业务类型的数据集
     * @param String dicType
     * @return List
     * @throws Exception
     */
    List selectBusinessTypeCodeList() throws Exception;
}
