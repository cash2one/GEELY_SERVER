package com.fsc.framework.base.dao.impl;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.IdGenerator;

import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.dao.DataAccessException;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:DAO组件（数据访问对象实现类），用于访问数据库，包含了各种数据库的操作方法</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
    /** 生成记录器Logger类的对象 */
    protected Logger log = Logger.getLogger(this.getClass());

    /** 获取连接对象
     *
     * @return Connection
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    public Connection getConnection() throws Exception {
        Session session = getHibernateTemplate().getSessionFactory()
                              .openSession();

        return session.connection();
    }

    /**
     * 根据HQL语句取某个对象
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author Long XiangYong 2008-11-15
     */
    public Object getObject(String hql) throws Exception {
        Object object = null;
        Session session = getSession(); //取得session    
        try{
        Query query = session.createQuery(hql);
        List list = query.list();
        if (list.size() > 0) {
            object = list.get(0);
        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        releaseSession(session); //关闭session

        return object;
    }

    /**
     * 根据实体名（POJO类名）和ID获取某个对象
     * @param Class entity
     * @param String id
     * @return Object
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public Object getObject(Class entity, String id) throws DataAccessException {
        return getHibernateTemplate().get(entity, id);
    }

    /**
     * 根据实体名（POJO类名）和ID获取某个对象
     * @param Class entity
     * @param Integer id
     * @return Object
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public Object getObject(Class entity, Integer id)
        throws DataAccessException {
        return getHibernateTemplate().get(entity, id);
    }

    /**
     * 根据实体名（POJO类名）和ID获取某个对象
     *
     * @param String entityName (全路径POJO类名，例如：com.yizhi.javaeeframework.base.po.Base)
     * @param String id
     * @return Object
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public Object getObject(String entityName, String id)
        throws DataAccessException {
        return getHibernateTemplate().get(entityName, id);
    }

    /**
     * 插入单个对象
     *
     * @param Object entity
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void insertObject(Object entity) throws DataAccessException {
        getHibernateTemplate().save(entity);
    }

    /**
     * 更新单个对象
     *
     * @param Object entity
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void updateObject(Object entity) throws DataAccessException {
        getHibernateTemplate().update(entity);
    }

    /**
     * 保存（插入或更新）单个对象
     *
     * @param Object entity
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void saveOrUpdateObject(Object entity) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void saveOrUpdateCollection(Collection entities)
        throws DataAccessException {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }
    
    /**
     * 保存单个对象
     * @param Object entity
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void saveObject(Object entity)throws DataAccessException{
    	getHibernateTemplate().save(entity);
    }
    
    /**
     * 删除单个对象
     *
     * @param Object entity
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void deleteObject(Object entity) throws DataAccessException {
        getHibernateTemplate().delete(entity); 
    }

    /**
     * 删除多个对象
     *
     * @param Collection entities
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public void deleteCollection(Collection entities)
        throws DataAccessException {
        getHibernateTemplate().deleteAll(entities);
    }

    /**
     * 执行HQL语句
     *
     * @param String hql
     * @throws DataAccessException
     * @author Long XiangYong 2008-11-28
     */
    public void executeHQL(String hql) throws DataAccessException {
        Session session = getSession();

        Query query = session.createQuery(hql);
        query.executeUpdate();

        releaseSession(session);
    }

    /**
     * 执行SQL语句
     *
     * @param String sql
     * @throws DataAccessException
     * @author Long XiangYong 2008-11-28
     */
    public void executeSQL(String sql) throws DataAccessException {
        Session session = getSession();

        Query query = session.createSQLQuery(sql);
        query.executeUpdate();

        releaseSession(session);
    }

    /**
     * 根据HQL语句获取相应的记录数
     *
     * @param String hql HQL语句
     * @return int 记录数
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public int getDataSizeByHQL(String hql) throws DataAccessException {
        int amount = 0;

        Session session = getSession(); //取得session
        Query query = session.createQuery(hql);
        List list = query.list();

        if (!list.isEmpty()) {
            amount = Integer.parseInt(list.get(0).toString());
        }

        releaseSession(session); //关闭session

        return amount;
    }

    /**
     * 根据实体名（POJO类名）获取相应的数据集
     *
     * @param String entityName 实体名（POJO类名）
     * @param String orderDescription 排序描述
     * @return List 数据集
     * @throws Exception
     * @author Long XiangYong 2008-11-07
     */
    public List selectDataByEntityName(String entityName,
        String orderDescription) throws Exception {
        String hql = "from " + entityName + " order by " + orderDescription;

        return getHibernateTemplate().find(hql);
    }

    /**
     * 通过特定的HQL语句获取相应的数据集
     *
     * @param String hql HQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List selectDataByHQL(String hql) throws DataAccessException {
        return getHibernateTemplate().find(hql);
    }

    /**
     * 通过特定的HQL语句和条件值获取相应的数据集
     *
     * @param String hql HQL语句
     * @param Object[] values 查询条件值
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List selectDataByHQL(String hql, Object[] values)
        throws DataAccessException {
        return getHibernateTemplate().find(hql, values);
    }

    /**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List selectDataBySQL(String sql) throws DataAccessException {
        Session session = getSession(); //取得session    

        Query query = session.createSQLQuery(sql);
        List list = query.list();

        releaseSession(session); //关闭session

        return list;
    }
    public List selectDataBySQLMap(String sql,Map params) throws DataAccessException {
        Session session = getSession(); //取得session    

        Query query = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        prepareParam(params, query);
        List list = query.list();
        releaseSession(session); //关闭session

        return list;
    }

    /**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List<Map> selectDataBySQLMap(String sql) throws DataAccessException {
        Session session = getSession(); //取得session    

        Query query = session.createSQLQuery(sql)
                             .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Map> list = query.list();

        releaseSession(session); //关闭session

        return list;
    }

    /**
     * 取从某条记录开始到某条记录结束之间的数据
     *
     * @param String hql
     * @param int beginNum 纪录开始条
     * @param int endNum  纪录结束条
     * @return List
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List selectBeginNumToEndNumByHQL(String hql, int beginNum, int endNum)
        throws DataAccessException {
        List list = new ArrayList();

        Session session = getSession(); //取得session
        Query query = null;
        query = session.createQuery(hql); //查询符合条件的数据      

        query.setFirstResult(beginNum);
        query.setMaxResults(endNum);
        list = query.list();
        releaseSession(session); //关闭session

        return list;
    }
    
    public List selectBeginNumToEndNumByHQL(String hql, int beginNum, int endNum,Map params)
	    throws DataAccessException {
	    List list = new ArrayList();
	
	    Session session = getSession(); //取得session
	    Query query = null;
	    query = session.createQuery(hql); //查询符合条件的数据      
	    prepareParam(params, query);
	    query.setFirstResult(beginNum);
	    query.setMaxResults(endNum);
	    list = query.list();
	    releaseSession(session); //关闭session
	
	    return list;
	}

    /**
     * 分页获取数据
     *
     * @param String hql 查询语句
     * @param int pageSize 每页记录数
     * @param int pageNo 当前页号
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List selectPageDataByHQL(String hql, int pageSize, int pageNo)
        throws DataAccessException {
        List list = new ArrayList();
        int totalPage = 0; //总页数
        int totalRecord = getHibernateTemplate().find(hql).size(); //总记录数

        //取得总页数
        if ((totalRecord % pageSize) == 0) {
            totalPage = totalRecord / pageSize;
        } else {
            totalPage = (totalRecord / pageSize) + 1;
        }

        //有效化页号
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }

        int start = (pageNo - 1) * pageSize; //开始数据的位置
        int rowNum = pageSize; //每页显示数据

        Session session = getSession(); //取得session
        Query query = null;
        query = session.createQuery(hql);
        query.setFirstResult(start);
        query.setMaxResults(rowNum);
        list = query.list();
        releaseSession(session); //关闭session

        return list;
    }

    /**
     * 分页获取数据
     *
     * @param String hql 查询语句
     * @param int pageSize 每页记录数
     * @param int pageNo 当前页号
     * @param int totalRecords
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List selectPageDataByHQL(String hql, int pageSize, int pageNo,
        int totalRecords) throws DataAccessException {
        List list = new ArrayList();

        if (totalRecords > 0) {
            int start = (pageNo - 1) * pageSize; //开始数据的位置
            int rowNum = pageSize; //每页显示的记录数
            Session session = getSession(); //取得session
            Query query = session.createQuery(hql);
            query.setFirstResult(start);
            query.setMaxResults(rowNum);
            list = query.list();
            releaseSession(session); //关闭session
        }

        return list;
    }

    /**
     * 根据HQL语句取某实体的某属性值
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    public Object getAttributeValueByHql(String hql) throws Exception {
        Object object = "";
        Session session = getSession(); //取得session    

        Query query = session.createQuery(hql);
        List list = query.list();

        if (list.size() > 0) {
            object = (Object) list.get(0);
        }

        releaseSession(session); //关闭session

        if (object == null) {
            object = "";
        }

        return object;
    }
    
    /**
     * 根据SQL语句取某实体的某属性值
     *
     * @param String sql
     * @return Object
     * @throws Exception
     * @author Long XiangYong 2008-09-16
     */
    public Object getAttributeValueBySql(String sql) throws Exception {
        Object object = "";
        Session session = getSession(); //取得session    

        Query query = session.createSQLQuery(sql);
        List list = query.list();

        if (list.size() > 0) {
            object = (Object) list.get(0);
        }

        releaseSession(session); //关闭session

        if (object == null) {
            object = "";
        }

        return object;
    }

    /**
     * 判断满足某条件的实体对象是否已存在
     *
     * @param String entityName 实体名
     * @param String attributeName 属性名
     * @param Object attributeValue 属性值
     * @return Boolean
     * @throws DataAccessException
     * @author Long XiangYong 2008-11-19
     */
    public Boolean ifExist(String entityName, String attributeName,
        Object attributeValue) throws DataAccessException {
        String hql = "from " + entityName + " where " + attributeName + "=?";
        List list = selectDataByHQL(hql, new Object[] { attributeValue });

        if ((list != null) && (list.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断满足某条件的实体对象是否已存在
     *
     * @param String entityName 实体名
     * @param String attributeName 属性名
     * @param Object attributeValue 属性值
     * @param String attributeName2 属性名2
     * @param Object attributeValue2 属性值2
     * @return Boolean
     * @throws DataAccessException
     * @author Long XiangYong 2008-11-19
     */
    public Boolean ifExist(String entityName, String attributeName,
        Object attributeValue, String attributeName2, Object attributeValue2)
        throws DataAccessException {
        String hql = "from " + entityName + " where " + attributeName +
            "=? and " + attributeName2 + "=?";
        List list = selectDataByHQL(hql,
                new Object[] { attributeValue, attributeValue2 });

        if ((list != null) && (list.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

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
     * @throws DataAccessException
     * @author Long XiangYong 2008-11-19
     */
    public Boolean ifExist(String entityName, String attributeName,
        Object attributeValue, String attributeName2, Object attributeValue2,
        String attributeName3, Object attributeValue3)
        throws DataAccessException {
        String hql = "from " + entityName + " where " + attributeName +
            "=? and " + attributeName2 + "=? and " + attributeName3 + "!=?";
        List list = selectDataByHQL(hql,
                new Object[] { attributeValue, attributeValue2, attributeValue3 });

        if ((list != null) && (list.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

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
        Object[] attributeValues, String[] operates) throws DataAccessException {
        if ((entityName == null) || (attributeNames == null) ||
                (attributeValues == null) || (operates == null)) {
            return true;
        } else {
            if ((attributeNames.length != attributeValues.length) ||
                    (attributeNames.length != operates.length)) {
                return true;
            }

            String hql = "from " + entityName + " where 1=1 ";

            for (int i = 0; i < attributeNames.length; i++) {
                hql += (" and " + attributeNames[i] + operates[i] + "? ");
            }

            List list = selectDataByHQL(hql, attributeValues);

            if ((list != null) && (list.size() > 0)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 判断某HQL查询语句的查询结果是否存在
     *
     * @param String hql
     * @return Boolean
     * @throws DataAccessException
     * @author Long XiangYong 2008-11-19
     */
    public Boolean ifExist(String hql) throws DataAccessException {
        List list = selectDataByHQL(hql);

        if ((list != null) && (list.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    /** 主方法，用于测试 */
    public static void main(String[] args) {
    }

    public String getId() throws Exception {
        String id = "";
        IdGenerator idGenerator = new IdGenerator();
        id = idGenerator.getId();

        return id;
    }

    public void transArrToList(Map map, String key, String[] arr, int valueType) {
        if (null == arr) {
            return;
        }

        List list = new ArrayList(arr.length);

        for (int i = 0; i < arr.length; i++) {
            if (0 == arr[i].length()) {
                continue;
            }

            if (INT_TYPE == valueType) {
                list.add(Integer.valueOf(arr[i]));
            } else {
                list.add(arr[i]);
            }
        }

        //如果没有具体值则返回
        if (0 == list.size()) {
            return;
        }

        map.put(key, list);
    }

    public String transMapToHQL(String prefix, Map properties) {
        String whereStr = " where ";
        boolean isAppendWhereStr = false;

        StringBuilder hqlBuff = new StringBuilder(512);
        hqlBuff.append(prefix);

        Set set = properties.keySet();
        Iterator iterator = set.iterator();
        int keyNum = 0;

        while (iterator.hasNext()) {
            keyNum++;

            String type = (String) iterator.next();
            Object obj = properties.get(type);

            if (null == obj) {
                continue;
            }

            //如果传入的值为List型
            if (obj instanceof List) {
                List list = (List) obj;

                //如果没有参数则忽略此项
                if (0 == list.size()) {
                    continue;
                }

                //一但list中有数据则加入where 字符，isAppendWhereStr控制仅加入一次
                if (!isAppendWhereStr) {
                    hqlBuff.append(whereStr);
                    isAppendWhereStr = true;
                }

                hqlBuff.append("(");

                for (int i = 0; i < list.size(); i++) {
                    Object value = list.get(i);
                    elementFill(type, value, hqlBuff);

                    //如果是最后一项则不加or
                    if (i != (list.size() - 1)) {
                        hqlBuff.append(" or");
                    }
                }

                hqlBuff.append(")");
            } else {
                hqlBuff.append(whereStr);
                isAppendWhereStr = true;
                hqlBuff.append("(");
                elementFill(type, obj, hqlBuff);
                hqlBuff.append(")");
            }

            //如果是最后一项则不加and
            if (keyNum != properties.size()) {
                hqlBuff.append("and");
            }
        }

        return hqlBuff.toString();
    }

    private void elementFill(String key, Object obj, StringBuilder hqlBuff) {
        //如果是string类型的值则需要加入前后引号
        if (obj instanceof String) {
            obj = "'" + obj.toString() + "'";
        }

        //hqlBuff.append(" model.");
        hqlBuff.append(" ");
        hqlBuff.append(key);
        hqlBuff.append("= ");
        hqlBuff.append(obj.toString());
    }

    /**
     * 按SQL分页查询
     */
    public List<Map> selectPageDataBySQL(String sql, int pageSize, int pageNo,
        int total) throws DataAccessException {
        List list = new ArrayList();
        int totalPage = 0; //总页数
        int totalRecord = total; //总记录数

        //取得总页数
        if ((totalRecord % pageSize) == 0) {
            totalPage = totalRecord / pageSize;
        } else {
            totalPage = (totalRecord / pageSize) + 1;
        }

        //有效化页号
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }

        int start = (pageNo - 1) * pageSize; //开始数据的位置
        int rowNum = pageSize; //每页显示数据

        Session session = getSession(); //取得session
        Query query = null;
        query = session.createSQLQuery(sql)
                       .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(start);
        query.setMaxResults(rowNum);
        list = query.list();
        releaseSession(session); //关闭session

        return list;
    }

    public void selectPageDataBySQL(BaseVo baseVo, String sql, String countSql)
        throws Exception {
        baseVo.setTotalRecords(Integer.valueOf(selectDataBySQL(countSql).get(0)
                                                   .toString())); //总记录数
        baseVo.setTotalPages(((baseVo.getTotalRecords() + baseVo.getPageSize()) -
            1) / baseVo.getPageSize()); //总页数
        baseVo.setPageNo((baseVo.getPageNo() > baseVo.getTotalPages())
            ? baseVo.getTotalPages() : baseVo.getPageNo()); //页号
        baseVo.setPageNo((baseVo.getPageNo() <= 0)
            ? ((baseVo.getTotalPages() > 0) ? 1 : 0) : baseVo.getPageNo());
        baseVo.setList(selectPageDataBySQL(sql, baseVo.getPageSize(),
                baseVo.getPageNo(), baseVo.getTotalRecords())); //数据列表
        baseVo.setPrevPageNo((baseVo.getPageNo() > 2) ? (baseVo.getPageNo() -
            1) : 1); //上一页
        baseVo.setNextPageNo((baseVo.getPageNo() < baseVo.getTotalPages())
            ? (baseVo.getPageNo() + 1) : baseVo.getTotalPages()); //下一页
    }

    public List selectPageDataByHQL(String hql, Map params, int page_size,
        int pageNo) throws DataAccessException {
        int start = (pageNo - 1) * page_size; //开始数据的位置
        start = (start < 0) ? 0 : start;

        Session session = getSession(); //取得session
        Query query = null;
        query = session.createQuery(hql); //查询符合条件的数据          
        query.setFirstResult(start);
        query.setMaxResults(page_size);
        prepareParam(params, query);

        List list = query.list();
        releaseSession(session);

        return list;
    }

    public List selectDataByHQL(String hql, Map params)
        throws DataAccessException {
        Session session = getSession(); //取得session
        Query query = session.createQuery(hql); //查询符合条件的数据          
        prepareParam(params, query);

        List list = query.list();
        releaseSession(session);

        return list;
    }

    private void prepareParam(Map<String, Object> params, Query query) {
        for (String key : params.keySet()) {
            Object object = params.get(key);

            if (object instanceof Collection<?>) {
                query.setParameterList(key, (Collection<?>) object);
            } else if (object instanceof Object[]) {
                query.setParameterList(key, (Object[]) object);
            } else {
                query.setParameter(key, object);
            }
        }
    }
}
