package com.fsc.framework.tree;

/**
*
* <p>Title: 校讯通手机服务端</p>
* <p>Description:树型菜单基本参数，逗号分隔参数对象</p>
* <p>创建日期:2011-1-26</p>
* @author lcb
* @version 1.0
* <p>湖南家校圈科技有限公司</p>
* <p>http://www.139910.com/</p>
* <p>http://old.139910.com:8080/</p>
*/
public class Param {
    //节点id
    private String id;

    //下一层节点解析器类型
    private String type;

    //父节点
    private String pno;

    //需要忽略的节点号
    private String ignoreId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getIgnoreId() {
        return ignoreId;
    }

    public void setIgnoreId(String ignoreId) {
        this.ignoreId = ignoreId;
    }
}
