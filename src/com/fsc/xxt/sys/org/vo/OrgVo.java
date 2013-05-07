package com.fsc.xxt.sys.org.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:单位机构信息VO</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class OrgVo extends BaseVo {
    /** 供电单位编号 */
    private String orgNo;

    /** 供电单位名称 */
    private String orgName;

    /** 供电单位简码 */
    private String abc;

    /** 上级单位编号 */
    private String porgNo;

    /** 供电单位类别 */
    private String orgType;

    /** 排序序号 */
    private Integer sortNo;

    /** 注销标志(01:正常；02：注销) */
    private String logOut;

    /** 供电单位信息列表 */
    private List orgList;

    /** 供电单位类别选择列表 */
    private List orgTypeList;

    /** 上级单位编号 */
    private String superOrgNo;

    /** JSON树 */
    private String treeJson;

    // Constructors
    /** default constructor */
    public OrgVo() {
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public String getPorgNo() {
        return porgNo;
    }

    public void setPorgNo(String porgNo) {
        this.porgNo = porgNo;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getLogOut() {
        return logOut;
    }

    public void setLogOut(String logOut) {
        this.logOut = logOut;
    }

    public List getOrgList() {
        return orgList;
    }

    public void setOrgList(List orgList) {
        this.orgList = orgList;
    }

    public List getOrgTypeList() {
        return orgTypeList;
    }

    public void setOrgTypeList(List orgTypeList) {
        this.orgTypeList = orgTypeList;
    }

    public String getSuperOrgNo() {
        return superOrgNo;
    }

    public void setSuperOrgNo(String superOrgNo) {
        this.superOrgNo = superOrgNo;
    }

    public String getTreeJson() {
        return treeJson;
    }

    public void setTreeJson(String treeJson) {
        this.treeJson = treeJson;
    }
}
