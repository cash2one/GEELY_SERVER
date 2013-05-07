package com.fsc.xxt.sys.dic.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:数据字典表现层对象</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DictionaryVo extends BaseVo {
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

    /** 类别List */
    private List dicTypeList;

    /** 上级ID */
    private String superDicId;

    /** 上级名称 */
    private String superDicName;
    /** 代码长度 */
    private Integer dicSortNo;
    public Integer getDicSortNo() {
		return dicSortNo;
	}

	public void setDicSortNo(Integer dicSortNo) {
		this.dicSortNo = dicSortNo;
	}

	/** default constructor */
    public DictionaryVo() {
        init();
    }

    /** reset */
    public void reset() {
        init();
    }

    /** initialize */
    public void init() {
        super.init();
        this.dicId = "";
        this.dicName = "";
        this.dicRemark = "";
    }

    public String getDicId() {
        return this.dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getDicType() {
        return this.dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getDicCode() {
        return this.dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return this.dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getDicRemark() {
        return this.dicRemark;
    }

    public void setDicRemark(String dicRemark) {
        this.dicRemark = dicRemark;
    }

    public Integer getDicCodeLen() {
        return this.dicCodeLen;
    }

    public void setDicCodeLen(Integer dicCodeLen) {
        this.dicCodeLen = dicCodeLen;
    }

    public Integer getDicCodeType() {
        return this.dicCodeType;
    }

    public void setDicCodeType(Integer dicCodeType) {
        this.dicCodeType = dicCodeType;
    }

    public Integer getSign() {
        return this.sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public List getDicTypeList() {
        return dicTypeList;
    }

    public void setDicTypeList(List dicTypeList) {
        this.dicTypeList = dicTypeList;
    }

    public String getSuperDicId() {
        return superDicId;
    }

    public void setSuperDicId(String superDicId) {
        this.superDicId = superDicId;
    }

    public String getSuperDicName() {
        return superDicName;
    }

    public void setSuperDicName(String superDicName) {
        this.superDicName = superDicName;
    }
}
