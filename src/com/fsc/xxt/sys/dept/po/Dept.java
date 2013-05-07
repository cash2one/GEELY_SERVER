package com.fsc.xxt.sys.dept.po;

import com.fsc.xxt.sys.org.po.Org;
import com.fsc.framework.base.po.Base;



/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统部门基本信息表相映象的POJO类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class Dept extends Base {
    /** 标识ID */
    private String id;

    /** 供电单位编号 */
    private String orgNo;

    /** 部门名称 */
    private String name;

    /** 简称 */
    private String abbr;

    /** 部门类型（01：部门；02：班组） */
    private String typeCode;

    /** 上级部门编号 */
    private String pdeptNo;

    /** 显示序号 */
    private Integer dispSN;

    /** 注销标志（01：正常；02：注销） */
    private String logout;

    /** 所属单位名称 */
    private String orgName;

    /** 部门所属单位 */
    private Org org;

    /** 上级部门 */
    private Dept pdpet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getPdeptNo() {
        return pdeptNo;
    }

    public void setPdeptNo(String pdeptNo) {
        this.pdeptNo = pdeptNo;
    }

    public Integer getDispSN() {
        return dispSN;
    }

    public void setDispSN(Integer dispSN) {
        this.dispSN = dispSN;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Dept getPdpet() {
        return pdpet;
    }

    public void setPdpet(Dept pdpet) {
        this.pdpet = pdpet;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
