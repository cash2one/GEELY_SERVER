package com.fsc.xxt.client.po;

import com.fsc.framework.base.po.Base;


/**
 *
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:MES生产趋势统计图主表</p>
 * <p>创建日期:2013-4-10</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class GeelyMesInfo extends Base {
    private static final long serialVersionUID = 3954008040976036919L;

    //ID
    private Integer id;

    //日期
    private String rq;

    //序号
    private String xh;

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

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
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
