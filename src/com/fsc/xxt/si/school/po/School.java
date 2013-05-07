package com.fsc.xxt.si.school.po;

import com.fsc.framework.base.po.Base;

import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.xxt.basedata.town.po.Town;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学校PO</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class School extends Base {
    private String id;
    private Town town;
    private String townid;
    private Area area;
    private String areaid;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownid() {
        return townid;
    }

    public void setTownid(String townid) {
        this.townid = townid;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
}
