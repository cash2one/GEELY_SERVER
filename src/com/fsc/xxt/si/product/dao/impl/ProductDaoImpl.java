package com.fsc.xxt.si.product.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.xxt.si.product.dao.ProductDao;

import java.math.BigDecimal;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品管理Dao层接口实现</p>
 * <p>创建日期:Feb 9, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    @Override
    public Long selectUserCollectCount(String userType, String userId)
        throws Exception {
        String hql = "select count(*) from ProductCollection where userId='" +
            userId + "' and userType='" + userType + "'";

        return (Long) this.getAttributeValueByHql(hql);
    }

    @Override
    public boolean delProCoolect(String id) throws Exception {
        try {
            String hql = "delete from ProductCollection where id='" + id + "'";
            this.executeHQL(hql);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    
    
}
