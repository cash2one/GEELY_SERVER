package com.fsc.xxt.sys.dic.dao.impl;

import com.fsc.xxt.sys.dic.dao.DictionaryDao;
import com.fsc.xxt.sys.dic.po.Dictionary;
import com.fsc.framework.base.dao.impl.BaseDaoImpl;


import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据字典管理DAO组件实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DictionaryDaoImpl extends BaseDaoImpl implements DictionaryDao {
    /**
    * 根据id查询数据字典
    * @param id
    * @return
    * @throws Exception
    */
    public Dictionary findDictionaryById(String id) throws Exception {
        return (Dictionary) getObject(Dictionary.class, id);
    }

    /**
     * 根据类别和代码获取数据字典对象
     * @param String dicType
     * @param String dicCode
     * @return Object
     * @throws Exception
     */
    public Dictionary getDictionary(String dicType, String dicCode)
        throws Exception {
        String hql = "from Dictionary where dicType = '" + dicType +
            "' and dicCode = '" + dicCode + "'";

        return (Dictionary) getObject(hql);
    }

    /**
     * 根据类别和代码获取ID
     * @param String dicType
     * @param String dicCode
     * @return Object
     * @throws Exception
     */
    public Object getDicId(String dicType, String dicCode)
        throws Exception {
        String hql = "select dicId from Dictionary " + "where dicType = '" +
            dicType + "' and dicCode = '" + dicCode + "'";

        return getAttributeValueBySql(hql);
    }

    /**
     * 根据类别和代码获取名称
     * @param String dicType
     * @param String dicCode
     * @return Object
     * @throws Exception
     */
    public Object getDicName(String dicType, String dicCode)
        throws Exception {
        String hql = "select dicName from Dictionary " + "where dicType = '" +
            dicType + "' and dicCode = '" + dicCode + "'";

        return getAttributeValueByHql(hql);
    }

    /**
     * 根据类别和代码更新名称
     * @param String dicType
     * @param String dicCode
     * @param String dicName
     * @throws Exception
     */
    public void updateDicName(String dicType, String dicCode, String dicName)
        throws Exception {
        Dictionary dictionary = getDictionary(dicType, dicCode);
        dictionary.setDicName(dicName);
        updateObject(dictionary);
    }

    /**
     * 根据类别查询相应的数据集
     * @param String dicType
     * @return List
     * @throws Exception
     */
    public List selectDictionaries(String dicType) throws Exception {
        String hql = "from Dictionary where sign = 1 and dicType = '" +
            dicType + "' " + "order by dicSortNo";

        return selectDataByHQL(hql);
    }

    public List selectDictionariesWithByDicCode(String dicType, String dicCode)
        throws Exception {
        String hql = "from Dictionary where sign = 1 and dicType = '" +
            dicType + "' and dicCode = '" + dicCode + "' " +
            "order by dicCode";

        return selectDataByHQL(hql);
    }

    public List selectDictionariesWithOutByDicCode(String dicType,
        String dicCode) throws Exception {
        String hql = "from Dictionary where sign = 1 and dicType = '" +
            dicType + "' and dicCode <> '" + dicCode + "' " +
            "order by dicCode";

        return selectDataByHQL(hql);
    }

    public List selectBusinessTypeCodeList() throws Exception {
        String hql = "from Dictionary a where a.sign = 1 and a.dicType = '07' and exists (select b.flowId from FlowInfo b where b.appTypeCode = a.dicCode  ) order by dicCode";

        return selectDataByHQL(hql);
    }
}
