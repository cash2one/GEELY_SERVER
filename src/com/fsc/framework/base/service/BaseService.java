package com.fsc.framework.base.service;

import com.fsc.framework.base.vo.BaseVo;

import org.springframework.dao.DataAccessException;

import java.sql.Connection;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:业务逻辑组件工厂（接口）</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface BaseService {
    /** 获取连接对象
     *
     * @return Connection
    * @throws Exception
    * @author Long XiangYong 2008-09-16
     */
    Connection getConnection() throws Exception;

    /**
     * 根据HQL语句取某个对象
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author Long XiangYong 2008-11-15
     */
    Object getObject(String hql) throws Exception;

    /**
     * 根据实体名（POJO类名）和ID获取某个对象
     *
     * @param String entityName (全路径POJO类名，例如：com.yizhi.javaeeframework.base.po.Base)
     * @param String id
     * @return Object
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    Object getObject(String entityName, String id) throws Exception;

    /**
     * 根据实体CLASS和ID获取某个对象
     *
     * @param String entityName (全路径POJO类名，例如：com.yizhi.javaeeframework.base.po.Base)
     * @param String id
     * @return Object
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    Object getObject(Class class1, String id) throws Exception;

    /**
    * 插入单个对象
    *
    * @param Object entity
    * @throws Exception
    * @author Long XiangYong 2008-09-16
    */
    void insertObject(Object entity) throws Exception;

    /**
     * 更新单个对象
     *
     * @param Object entity
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    void updateObject(Object entity) throws Exception;

    /**
     * 保存单个对象
     *
     * @param Object entity
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    void saveOrUpdateObject(Object entity) throws Exception;

    /**
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    void saveOrUpdateCollection(Collection entities) throws Exception;

    /**
     * 删除单个对象
     *
     * @param Object entity
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    void deleteObject(Object entity) throws Exception;

    /**
     * 删除多个对象
     *
     * @param Collection entities
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    void deleteCollection(Collection entities) throws Exception;

    /**
     * 执行HQL语句
     *
     * @param String hql
     * @throws Exception
     * @author Long XiangYong 2008-11-28
     */
    void executeHQL(String hql) throws Exception;

    /**
     * 执行SQL语句
     *
     * @param String sql
     * @throws Exception
     * @author Long XiangYong 2008-11-28
     */
    void executeSQL(String sql) throws Exception;

    /**
    * 根据HQL语句获取相应的记录数
    *
    * @param String hql HQL语句
    * @return int 记录数
    * @throws Exception
    * @author Long XiangYong 2008-09-16
    */
    int getDataSizeByHQL(String hql) throws Exception;

    /**
    * 根据实体名等分页获取数据，并把相应的值设置给表现层对象
    *
    * @param String entityName 实体名（POJO类名）
    * @param String orderDescription 排序描述
    * @param String countAttributeName 用于统计数据记录数的属性名
    * @param BaseVo baseVO 表现层对象
    * @throws Exception
    * @author Long XiangYong 2008-11-06
    */
    void selectPageData(String entityName, String orderDescription,
        String countFieldName, BaseVo baseVo) throws Exception;

    /**
     * 根据数据对象查询语句和数据记录统计语句等获取分页显示数据，并把相应的值设置给表现层对象
     *
     * @param BaseVo baseVO 表现层对象
     * @param String hql 数据对象查询语句
     * @param String countHql 数据记录统计语句
     * @throws Exception
     * @author Long XiangYong 2008-11-19
     */
    void selectPageData(BaseVo baseVo, String hql, String countHql)
        throws Exception;

    /**
     * 根据数据对象查询语句和数据记录统计语句等获取分页显示数据，并把相应的值设置给表现层对象
     * @param baseVo
     * @param sql
     * @param countSql
     * @throws Exception
     */
    void selectPageDataBySQL(BaseVo baseVo, String sql, String countSql)
        throws Exception;

    /**
     * 根据实体名（POJO类名）获取相应的数据集
     *
     * @param String entityName 实体名（POJO类名）
     * @param String orderDescription 排序描述
     * @return List 数据集
     * @throws Exception
     * @author Long XiangYong 2008-11-07
     */
    List selectDataByEntityName(String entityName, String orderDescription)
        throws Exception;

    /**
    * 通过特定的HQL语句获取相应的数据集
    *
    * @param String hql HQL语句
    * @return List 数据集
    * @throws Exception
    * @author Long XiangYong 2008-09-16
    */
    List selectDataByHQL(String hql) throws Exception;

    /**
     * 通过特定的HQL语句和条件值获取相应的数据集
     *
     * @param String hql HQL语句
     * @param Object[] values 查询条件值
     * @return List 数据集
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    List selectDataByHQL(String hql, Object[] values) throws Exception;

    /**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    List selectDataBySQL(String sql) throws Exception;

    /**
     * 通过特定的SQL语句获取相应的数据集
     * @param String sql SQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List<Map> selectDataBySQLMap(String sql) throws DataAccessException;

    /**
     * 取从某条记录开始到某条记录结束之间的数据
     *
     * @param String hql
     * @param int beginNum 纪录开始条
     * @param int endNum  纪录结束条
     * @return List
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    List selectBeginNumToEndNumByHQL(String hql, int beginNum, int endNum)
        throws Exception;

    /**
    * 分页获取数据
    *
    * @param String hql 查询语句
    * @param int pageSize 每页记录数
    * @param int pageNo 当前页号
    * @return List 数据集
    * @throws Exception
    * @author Long XiangYong 2008-09-16
    */
    List selectPageDataByHQL(String hql, int pageSize, int pageNo)
        throws Exception;

    /**
     * 分页获取数据
     * @param hql
     * @param pageSize
     * @param pageNo
     * @param totalRecords
     * @return
     * @throws Exception
     */
    public List selectPageDataByHQL(String hql, int pageSize, int pageNo,
        int totalRecords) throws Exception;

    /**
     * 根据HQL语句取某实体的某属性值
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    Object getAttributeValueByHql(String hql) throws Exception;

    /**
     * 根据SQL语句取某实体的某属性值
     *
     * @param String sql
     * @return Object
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    Object getAttributeValueBySql(String sql) throws Exception;

    /**
     * 生成ID并将其返回
     *
     * @return String
     * @throws Exception
     */
    String getId() throws Exception;

    /**
     * 判断满足某条件的实体对象是否已存在
     *
     * @param String entityName 实体名
     * @param String attributeName 属性名
     * @param String attributeValue 属性值
     * @return Boolean
     * @throws Exception
     * @author Long XiangYong 2008-11-19
     */
    Boolean ifExist(String entityName, String attributeName,
        String attributeValue) throws Exception;

    /**
     * 判断满足某条件的实体对象是否已存在
     *
     * @param String entityName 实体名
     * @param String attributeName 属性名
     * @param Object attributeValue 属性值
     * @param String attributeName2 属性名2
     * @param Object attributeValue2 属性值2
     * @return Boolean
     * @throws Exception
     * @author Long XiangYong 2008-11-19
     */
    Boolean ifExist(String entityName, String attributeName,
        Object attributeValue, String attributeName2, Object attributeValue2)
        throws Exception;

    /**
     * 判断满足某条件的实体对象是否已存在
     *
     * @param String entityName 实体名
     * @param String attributeName 属性名
     * @param Object attributeValue 属性值
     * @param String attributeName2 属性名2
     * @param Object attributeValue2 属性值2
     * @param String attributeName3 属性名3
     * @param Object attributeValue3 属性值3
     * @return Boolean
     * @throws Exception
     * @author Long XiangYong 2008-11-19
     */
    public Boolean ifExist(String entityName, String attributeName,
        Object attributeValue, String attributeName2, Object attributeValue2,
        String attributeName3, Object attributeValue3)
        throws Exception;

    /**
     * 判断满足某条件的实体对象是否存在
     * @param entityName 实体名
     * @param attributeNames 属性名
     * @param attributeValues 属性值
     * @param operates 操作符
     * @return
     * @throws DataAccessException
     */
    public Boolean ifExist(String entityName, String[] attributeNames,
        Object[] attributeValues, String[] operates) throws DataAccessException;

    /**
     * 判断某HQL查询语句的查询结果是否存在
     *
     * @param String hql
     * @return Boolean
     * @throws Exception
     * @author Long XiangYong 2008-11-19
     */
    Boolean ifExist(String hql) throws Exception;

    void selectPageData(BaseVo baseVo, String hql, String countHql, Map param)
        throws Exception;
}
