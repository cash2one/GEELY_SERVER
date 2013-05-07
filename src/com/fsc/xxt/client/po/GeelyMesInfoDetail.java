package com.fsc.xxt.client.po;

import com.fsc.framework.base.po.Base;


/**
 *
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:MES生产趋势统计图明细表</p>
 * <p>创建日期:2013-4-10</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class GeelyMesInfoDetail extends Base {
    private static final long serialVersionUID = -4625434514571048944L;

    //ID
    private Integer id;

    //类别
    private String lb;

    //日期
    private String rq;

    //标题
    private String caption;

    //数量
    private Double qtys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Double getQtys() {
        return qtys;
    }

    public void setQtys(Double qtys) {
        this.qtys = qtys;
    }
}
