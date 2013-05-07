package com.fsc.xxt.si.school.vo;

import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.xxt.basedata.town.po.Town;


public class SchoolVo extends BaseVo {
    private String id;
    private Town town;
    private String townid;
    private Area area;
    private String areaid;
    private String name;
    private String schoolname;
    
    private String sprovince;
    private String scity;
    private String stown;
    
    
    

    public String getSprovince() {
		return sprovince;
	}

	public void setSprovince(String sprovince) {
		this.sprovince = sprovince;
	}

	public String getScity() {
		return scity;
	}

	public void setScity(String scity) {
		this.scity = scity;
	}

	public String getStown() {
		return stown;
	}

	public void setStown(String stown) {
		this.stown = stown;
	}

	public String getId() {
        return id;
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

    public String getTownid() {
        return townid;
    }

    public void setTownid(String townid) {
        this.townid = townid;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
    
}
