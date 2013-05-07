package com.fsc.xxt.basedata.town.vo;

import java.util.List;

import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.framework.base.vo.BaseVo;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:城镇信息</p>
 * <p>创建日期:2010-11-18</p>
 * @author lcb
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class TownVo extends BaseVo {
    // 镇区id
    private String id;
    // 所属地区
    private Area area;
    //地区id
    private String areaid;
    //镇区名
    private String town;
    //地区列表
    private List areaList;
    //省份列表
    private List provinceList;

    /** default constructor */
    public TownVo() {
    }

    // Constructors
    public List getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List provinceList) {
        this.provinceList = provinceList;
    }

    public List getAreaList() {
        return areaList;
    }

    public void setAreaList(List areaList) {
        this.areaList = areaList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
}
