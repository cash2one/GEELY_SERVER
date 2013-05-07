package com.fsc.xxt.sys.dic.service;

import com.fsc.xxt.sys.dic.po.Dictionary;
import com.fsc.xxt.sys.dic.vo.DictionaryVo;
import com.fsc.framework.base.service.BaseService;


import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据字典管理服务接口</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface DictionaryService extends BaseService {
    /**
     * 根据id查询数据字典
     * @param id
     * @return
     * @throws Exception
     */
    public Dictionary findDictionaryById(String id) throws Exception;

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
    Dictionary selectDictionariesWithByDicCode(String dicType, String dicCode)
        throws Exception;

    /**
     * 根据类别分页获取相应类别的数据字典对象信息
     * @param String dicType
     * @param KnowledgeVo dictionaryVo
     * @throws Exception
     */
    void selectPerPageDictionaries(String dicType, DictionaryVo dictionaryVo)
        throws Exception;

    /**
     * 删除数据字典对象信息
     * @param KnowledgeVo dictionaryVo
     * @param StringBuffer sbfMessage
     * @throws Exception
     */
    void delete(DictionaryVo dictionaryVo, StringBuffer sbfMessage)
        throws Exception;

    /**
     * 以递增的方式生成代码，并返回代码
     * @param int dicCodeLen 代码长度
     * @param String dicType 类别
     * @return String
     * @throws Exception
     */
    String getDicCode(int dicCodeLen, String dicType) throws Exception;

    /**
     * 判断数据字典对象是否是唯一的（判断代码）
     * @param ReportTemplate dictionary
     * @param String operateType
     * @return Boolean true:unique;false:not unique
     * @throws Exception
     */
    Boolean judgeDictionaryIfUnique(Dictionary dictionary, String operateType)
        throws Exception;

    /**
     * 判断数据字典对象是否是唯一的（判断名称）
     * @param ReportTemplate dictionary
     * @param String operateType
     * @return Boolean true:unique;false:not unique
     * @throws Exception
     */
    Boolean judgeDictionaryIfUnique2(Dictionary dictionary, String operateType)
        throws Exception;

    /**
     * 得到定义了流程的实际业务类型的数据集
     * @param String dicType
     * @return List
     * @throws Exception
     */
    List selectBusinessTypeCodeList() throws Exception;
}
