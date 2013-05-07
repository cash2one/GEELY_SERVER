package com.fsc.xxt.sys.dic.service.impl;

import com.fsc.xxt.sys.dic.dao.DictionaryDao;
import com.fsc.xxt.sys.dic.po.Dictionary;
import com.fsc.xxt.sys.dic.service.DictionaryService;
import com.fsc.xxt.sys.dic.vo.DictionaryVo;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.util.MathUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: 办公设备及用品信息管理系统</p>
 * <p>Description:数据字典管理服务接口实现</p>
 * <p>创建日期:2010-10-09</p>
 * @author thh
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class DictionaryServiceImpl extends BaseServiceImpl
    implements DictionaryService {
    /** 数据字典管理DAO组件 */
    private DictionaryDao dictionaryDao;

    /** 缺省构造方法 */
    public DictionaryServiceImpl() {
        super();
    }

    /** 有参构造方法 */
    public DictionaryServiceImpl(BaseDao baseDao, DictionaryDao dictionaryDao) {
        super(baseDao);
        this.dictionaryDao = dictionaryDao;
    }

    /** 设置数据字典管理DAO组件 */
    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    /**
     * 根据id查询数据字典
     * @param id
     * @return
     * @throws Exception
     */
    public Dictionary findDictionaryById(String id) throws Exception {
        return dictionaryDao.findDictionaryById(id);
    }

    /**
     * 根据类别查询相应的数据集
     * @param String dicType
     * @return List
     * @throws Exception
     */
    public List selectDictionaries(String dicType) throws Exception {
        return dictionaryDao.selectDictionaries(dicType);
    }

    public List selectDictionariesWithOutByDicCode(String dicType,
        String dicCode) throws Exception {
        return dictionaryDao.selectDictionariesWithOutByDicCode(dicType, dicCode);
    }

    /**
     * 根据类别查询相应的数据集
     * @param dicType
     * @param dicCode
     * @return
     * @throws Exception
     */
    public Dictionary selectDictionariesWithByDicCode(String dicType,
        String dicCode) throws Exception {
        List list = dictionaryDao.selectDictionariesWithByDicCode(dicType,
                dicCode);

        if ((null != list) && (0 != list.size())) {
            Dictionary dictionary = (Dictionary) list.get(0);

            return dictionary;
        } else {
            return null;
        }
    }

    /**
    * 根据类别分页获取相应类别的数据字典对象信息
    * @param String dicType
    * @param KnowledgeVo dictionaryVo
    * @throws Exception
    */
    public void selectPerPageDictionaries(String dicType,
        DictionaryVo dictionaryVo) throws Exception {
        String hql = "from Dictionary where dicType = '" + dicType + "'";
        String countHql = "select count(dicId) " + hql;
        hql += " order by dicSortNo";
        selectPageData(dictionaryVo, hql, countHql);
    }

    /**
     * 删除数据字典对象信息
     * @param KnowledgeVo dictionaryVo
     * @param StringBuffer sbfMessage
     * @throws Exception
     */
    public void delete(DictionaryVo dictionaryVo, StringBuffer sbfMessage)
        throws Exception {
        if ("1".equals(dictionaryVo.getDeleteSign())) //删除多条记录
         {
            deleteDictionaries(dictionaryVo.getIds(), sbfMessage);
        } else //删除单条记录
         {
            Dictionary dictionary = new Dictionary();
            dictionary = (Dictionary) getObject(Dictionary.class.getName(),
                    dictionaryVo.getDicId());

            if (judgeDictionaryIfAllowDelete(dictionaryVo.getDicId(), sbfMessage)) {
                deleteObject(dictionary);
                sbfMessage.append("操作执行成功！");
            } else {
                sbfMessage.insert(0, "操作执行失败！【" + dictionary.getDicName() +
                    "】");
            }
        }
    }

    /**
     * 删除多个数据字典对象
     * @param String[] ids
     * @param StringBuffer sbfMessage
     * @throws Exception
     */
    public void deleteDictionaries(String[] ids, StringBuffer sbfMessage)
        throws Exception {
        if (ids != null) {
            boolean ifAllowDelete = true;
            List<Dictionary> dictionaries = new ArrayList<Dictionary>();

            for (int i = 0; i < ids.length; i++) {
                Dictionary dictionary = new Dictionary();
                dictionary = (Dictionary) dictionaryDao.getObject(Dictionary.class.getName(),
                        ids[i]);
                ifAllowDelete = judgeDictionaryIfAllowDelete(ids[i], sbfMessage);

                if (ifAllowDelete) {
                    dictionaries.add(dictionary);
                } else {
                    sbfMessage.insert(0, "【" + dictionary.getDicName() + "】");

                    break;
                }
            }

            if (ifAllowDelete) {
                dictionaryDao.deleteCollection(dictionaries);
                sbfMessage.append("操作执行成功！");
            }
        }
    }

    /**
     * 判断数据字典对象是否允许删除
     * @param String dicId
     * @param StringBuffer sbfMessage
     * @return Boolean true:允许删除;false:不允许删除
     * @throws Exception
     */
    public Boolean judgeDictionaryIfAllowDelete(String dicId,
        StringBuffer sbfMessage) throws Exception {
        return true;
    }

    /**
     * 以递增的方式生成代码，并返回代码
     * @param int dicCodeLen 代码长度
     * @param String dicType 类别
     * @return String
     * @throws Exception
     */
    public String getDicCode(int dicCodeLen, String dicType)
        throws Exception {
        log.info("以递增的方式生成代码，并返回代码！");

        String dicCode = ""; //代码
        String maxDicCode = ""; //同一类别中最大的代码
        String zeros = ""; //补位零

        /* 取得补位零 */
        for (int i = 0; i < dicCodeLen; i++) {
            zeros += "0";
        }

        /* 取得同一类别中最大的代码 */
        String hql = "select max(dicCode) from Dictionary where dicType = '" +
            dicType + "'";
        maxDicCode = (String) dictionaryDao.getAttributeValueByHql(hql);

        if (!MathUtil.isNumeric(maxDicCode)) {
            //非数字代码不解析
            return "";
        }

        /* 生成新的代码 */
        if ((maxDicCode == null) || (maxDicCode.length() < dicCodeLen)) {
            dicCode = zeros + 1;
        } else {
            dicCode = zeros + (Integer.parseInt(maxDicCode) + 1);
        }

        dicCode = dicCode.substring(dicCode.length() - dicCodeLen);

        return dicCode;
    }

    /**
     * 判断数据字典对象是否是唯一的（判断代码）
     * @param ReportTemplate dictionary
     * @param String operateType
     * @return Boolean true:unique;false:not unique
     * @throws Exception
     */
    public Boolean judgeDictionaryIfUnique(Dictionary dictionary,
        String operateType) throws Exception {
        Boolean returnResult = true;
        String hql = "";
        Object[] array = null;

        if ("insert".equals(operateType)) {
            hql = "from Dictionary where dicType = ? and dicCode = ?";
            array = new Object[] {
                    dictionary.getDicType(), dictionary.getDicCode()
                };
        }

        if ("update".equals(operateType)) {
            hql = "from Dictionary where dicType = ? and dicCode = ? and dicId != ?";
            array = new Object[] {
                    dictionary.getDicType(), dictionary.getDicCode(),
                    dictionary.getDicId()
                };
        }

        List list = dictionaryDao.selectDataByHQL(hql, array);

        if ((list != null) && (list.size() > 0)) {
            returnResult = false;
        }

        return returnResult;
    }

    /**
     * 判断数据字典对象是否是唯一的（判断名称）
     * @param ReportTemplate dictionary
     * @param String operateType
     * @return Boolean true:unique;false:not unique
     * @throws Exception
     */
    public Boolean judgeDictionaryIfUnique2(Dictionary dictionary,
        String operateType) throws Exception {
        Boolean returnResult = true;
        String hql = "";
        Object[] array = null;

        if ("insert".equals(operateType)) {
            hql = "from Dictionary where dicType = ? and dicName = ?";
            array = new Object[] {
                    dictionary.getDicType(), dictionary.getDicName()
                };
        }

        if ("update".equals(operateType)) {
            hql = "from Dictionary where dicType = ? and dicName = ? and dicId != ?";
            array = new Object[] {
                    dictionary.getDicType(), dictionary.getDicName(),
                    dictionary.getDicId()
                };
        }

        List list = dictionaryDao.selectDataByHQL(hql, array);

        if ((list != null) && (list.size() > 0)) {
            returnResult = false;
        }

        return returnResult;
    }

    public List selectBusinessTypeCodeList() throws Exception {
        return dictionaryDao.selectBusinessTypeCodeList();
    }
}
