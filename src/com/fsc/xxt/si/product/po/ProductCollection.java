package com.fsc.xxt.si.product.po;

import com.fsc.framework.base.po.Base;

import java.util.Date;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品收藏表对应的POJO类</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ProductCollection extends Base {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = -3994840004910636264L;
    private String id;

    //收藏用户ID
    private String userId;

    //用户类型
    private String userType;

    //产品ID
    private String productId;

    //收藏时间
    private Date colTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }
}
