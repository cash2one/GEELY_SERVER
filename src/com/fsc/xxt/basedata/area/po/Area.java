package com.fsc.xxt.basedata.area.po;

// default package
import java.util.List;

import com.fsc.xxt.basedata.province.po.Province;
import com.fsc.framework.base.po.Base;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:地区信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author lcb
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class Area extends Base {
	//地区id
    private String id;
    //地区上级省份
    private Province province;
    //省份id
    private String provinceid;
    //地区名
    private String area;
    //省份列表
    private List provinceList;

    // Constructors

    /** default constructor */
    public Area() {
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
}
