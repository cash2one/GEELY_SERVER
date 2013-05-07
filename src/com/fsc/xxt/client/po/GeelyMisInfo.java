package com.fsc.xxt.client.po;

import com.fsc.framework.base.po.Base;


/**
 *
 *
 * <p>Title:G-MCS服务端</p>
 * <p>Description:MIS生产信息表</p>
 * <p>创建日期:2013-4-10</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class GeelyMisInfo extends Base {
    private static final long serialVersionUID = 2638176522902020965L;

    //ID
    private Integer id;

    //日期
    private String rq;

    //制造基地代码
    private String zzdm;

    //制造基地名称
    private String zzmc;

    //质量信息代码
    private String zbdm;

    //质量信息名称
    private String zbmc;

    //数据值
    private Double sjz;
    private Double mbz;
    private Double bgz;

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

    public String getZzdm() {
        return zzdm;
    }

    public void setZzdm(String zzdm) {
        this.zzdm = zzdm;
    }

    public String getZzmc() {
        return zzmc;
    }

    public void setZzmc(String zzmc) {
        this.zzmc = zzmc;
    }

    public String getZbdm() {
        return zbdm;
    }

    public void setZbdm(String zbdm) {
        this.zbdm = zbdm;
    }

    public String getZbmc() {
        return zbmc;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    public Double getSjz() {
        return sjz;
    }

    public void setSjz(Double sjz) {
        this.sjz = sjz;
    }

    public Double getMbz() {
        return mbz;
    }

    public void setMbz(Double mbz) {
        this.mbz = mbz;
    }

    public Double getBgz() {
        return bgz;
    }

    public void setBgz(Double bgz) {
        this.bgz = bgz;
    }
}
