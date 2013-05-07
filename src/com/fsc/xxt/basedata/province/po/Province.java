package com.fsc.xxt.basedata.province.po;

// default package
import com.fsc.framework.base.po.Base;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:省份信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author lcb
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class Province extends Base {
    // 省份id
    private String id;
    // 省份名
    private String province;
    
    private String code;

    /** default constructor */
    public Province() {
    }

    // Constructors
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
