package com.fsc.framework.base.vo;

import java.util.List;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:表现层对象（值对象），通常用于业务层之间的数据传递，主要对应界面显示的数据对象</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class BaseVo {
    /** 记录标识集 */
    protected String[] ids;

    /** 删除标识（“1”：删除多条记录；“0”或其它：删除单条记录；） */
    protected String deleteSign;

    /** 重置向标识（“1”：重置向；“0”或其它：非重置向） */
    protected String redirectSign;

    /** 检验未通过时返回页面的名称 */
    protected String inputPageName;

    /** 记录集合 */
    protected List list;

    /** 当前页码 */
    protected int pageNo;

    /** 每页显示记录数 */
    protected int pageSize;

    /** 总记录数 */
    protected int totalRecords;

    /** 总页面数 */
    protected int totalPages;

    /** 下一页页码 */
    protected int nextPageNo;

    /** 上一页页码 */
    protected int prevPageNo;

    /** 提示消息（String型） */
    protected String message;

    /** 提示消息（StringBuffer型） */
    protected StringBuffer sbfMessage = new StringBuffer("");

    /** 查询条件中的属性名 */
    private String attributeName;

    /** 查询条件中的属性值 */
    private String attributeValue;

    /** 查询条件中的运算符 */
    private String operator;

    /** 验证码 */
    private String verifyCode;

    /** 列表类型，用于确定每个列表的唯一性*/
    private String listType;

    /** default constructor */
    public BaseVo() {
        init();
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    /** reset */
    public void reset() {
        init();
    }

    /** initialize */
    public void init() {
        this.ids = null;
        this.deleteSign = "";
        this.redirectSign = "";
        this.list = null;
        this.pageNo = 1;
        this.pageSize = 15;
        this.totalRecords = 0;
        this.totalPages = 1;
        this.nextPageNo = 0;
        this.prevPageNo = 0;
        this.message = "";
        this.sbfMessage = new StringBuffer("");
        this.attributeName = "";
        this.attributeValue = "";
        this.operator = "";
    }

    /** initialize message */
    public void initMessage() {
        this.message = "";
        this.sbfMessage = new StringBuffer("");
    }

    /** initialize queryCondition */
    public void initQueryCondition() {
        this.attributeName = "";
        this.attributeValue = "";
        this.operator = "";
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getDeleteSign() {
        return deleteSign;
    }

    public void setDeleteSign(String deleteSign) {
        this.deleteSign = deleteSign;
    }

    public String getRedirectSign() {
        return redirectSign;
    }

    public void setRedirectSign(String redirectSign) {
        this.redirectSign = redirectSign;
    }

    public String getInputPageName() {
        return inputPageName;
    }

    public void setInputPageName(String inputPageName) {
        this.inputPageName = inputPageName;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getPrevPageNo() {
        return prevPageNo;
    }

    public void setPrevPageNo(int prevPageNo) {
        this.prevPageNo = prevPageNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StringBuffer getSbfMessage() {
        return sbfMessage;
    }

    public void setSbfMessage(StringBuffer sbfMessage) {
        this.sbfMessage = sbfMessage;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
