package com.fsc.xxt.sys.dic.po;

import com.fsc.framework.base.po.Base;

import com.googlecode.jsonplugin.annotations.JSON;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据字典信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class Dictionary extends Base {
    private static final long serialVersionUID = 3638147067264663826L;

    /** ID */
    private String dicId;

    /** 类别 */
    private String dicType;

    /** 代码 */
    private String dicCode;

    /** 名称 */
    private String dicName;

    /** 备注 */
    private String dicRemark;

    /** 代码长度 */
    private Integer dicCodeLen;

    /** 代码类型（0：表示数字型代码；1：表示非数字型代码） */
    private Integer dicCodeType;

    /** 显示标志(0-隐藏；1-显示) */
    private Integer sign;

    /** 代码长度 */
    private Integer dicSortNo;

    // Constructors

    /** default constructor */
    public Dictionary() {
    }

    /** minimal constructor */
    public Dictionary(String dicId, String dicType, String dicCode) {
        this.dicId = dicId;
        this.dicType = dicType;
        this.dicCode = dicCode;
    }

    /** full constructor */
    public Dictionary(String dicId, String dicType, String dicCode,
        String dicName, String dicRemark, Integer dicCodeLen,
        Integer dicCodeType, Integer sign) {
        this.dicId = dicId;
        this.dicType = dicType;
        this.dicCode = dicCode;
        this.dicName = dicName;
        this.dicRemark = dicRemark;
        this.dicCodeLen = dicCodeLen;
        this.dicCodeType = dicCodeType;
        this.sign = sign;
    }

    public Dictionary(String dicCode, String dicName) {
        this.dicName = dicName;
        this.dicCode = dicCode;
    }

    @JSON(serialize=false)
    public String getDicId() {
        return this.dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    @JSON(serialize=false)
    public String getDicType() {
        return this.dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }
    @JSON(serialize=false)
    public String getDicCode() {
        return this.dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }
    @JSON(serialize=false)
    public String getDicName() {
        return this.dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    @JSON(serialize=false)
    public String getDicRemark() {
        return this.dicRemark;
    }

    public void setDicRemark(String dicRemark) {
        this.dicRemark = dicRemark;
    }

    @JSON(serialize=false)
    public Integer getDicCodeLen() {
        return this.dicCodeLen;
    }

    public void setDicCodeLen(Integer dicCodeLen) {
        this.dicCodeLen = dicCodeLen;
    }

    @JSON(serialize=false)
    public Integer getDicCodeType() {
        return this.dicCodeType;
    }

    public void setDicCodeType(Integer dicCodeType) {
        this.dicCodeType = dicCodeType;
    }

    @JSON(serialize=false)
    public Integer getSign() {
        return this.sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @JSON(serialize=false)
    public Integer getDicSortNo() {
        return dicSortNo;
    }
    @JSON(serialize=false)
    public void setDicSortNo(Integer dicSortNo) {
        this.dicSortNo = dicSortNo;
    }
}
