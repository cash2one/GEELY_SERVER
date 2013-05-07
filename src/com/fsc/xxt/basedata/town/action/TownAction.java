package com.fsc.xxt.basedata.town.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.xxt.basedata.area.service.AreaService;
import com.fsc.xxt.basedata.area.vo.AreaVo;
import com.fsc.xxt.basedata.province.service.ProvinceService;
import com.fsc.xxt.basedata.town.po.Town;
import com.fsc.xxt.basedata.town.service.TownService;
import com.fsc.xxt.basedata.town.vo.TownVo;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.StringUtil;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:镇区控制类</p>
 * <p>创建日期:2010-12-6</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class TownAction extends ManageAction {
    /** 表现层对象（用于封装请求参数和处理结果） */
    private TownVo townVo;

    /** 业务逻辑组件 */
    private TownService townService;
    private AreaService areaService;
    private ProvinceService provinceService;

    public TownAction() {
        townVo = new TownVo();
    }

    public ProvinceService getProvinceService() {
        return provinceService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public TownService getTownService() {
        return townService;
    }

    public void setTownService(TownService townService) {
        this.townService = townService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    @Override
    public BaseVo getModel() {
        return townVo;
    }

    public AreaService getAreaService() {
        return areaService;
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    /**
    * 获取所有镇区信息，进入镇区管理列表界面
    * @return
    * @throws Exception
    */
    public String list() throws Exception {
        if (!StringUtil.isEmpty(townVo.getAreaid())) {
            townService.selectTownByAreaIdWithPageNo(townVo.getAreaid(),townVo);
        }

        return "list";
    }

    /**
     * 初始化镇区信息，进入镇区新增界面
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
    	if(!StringUtil.isEmpty(townVo.getAreaid())){
    		Area area = areaService.selectAreaById(townVo.getAreaid());
    		townVo.setArea(area);
    	}else{
    		throw new RuntimeException("传入地区参数不能为空");
    	}

        return INPUT;
    }

    /**
     * 查找镇区树
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
        List result = areaService.selectAllAreas();
        townVo.setAreaList(result);
        result = provinceService.selectAllProvince();
        townVo.setProvinceList(result);

        return "tree";
    }

    /**
     * 显示框架
     * @return
     * @throws Exception
     */
    public String frame() throws Exception {
        return "frame";
    }

    /**
     * 查找镇区信息，进入镇区信息修改界面
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        Town town = townService.selectTownById(townVo.getId());
        PropertyUtils.copyProperties(townVo, town);


        return INPUT;
    }

    /**
     * 保存镇区信息的服务器验证
     */
    public void validateSave() {
        if (StringUtil.isEmpty(townVo.getTown())) {
            addFieldError("town", "镇区名不能为空");
        }

        if (StringUtil.isEmpty(townVo.getAreaid())) {
            addFieldError("areaid", "地区名不能为空");
        }
    }

    /**
     * 保存镇区信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Town town = new Town();

        PropertyUtils.copyProperties(town,townVo);

        //判断是否存在主键，即新增及保存的状态
        if (StringUtil.isEmpty(townVo.getId())) {
            town.setId(townService.getId());
        }

        townService.saveOrUpdateObject(town);

        return SUCCESS;
    }

    /**
     * 删除镇区信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        if ((null != townVo.getIds()) && (0 != townVo.getIds().length)) {
            Town town = townService.selectTownById(townVo.getIds()[0]);
            townVo.setAreaid(town.getAreaid());
            townService.removeByIds(townVo.getIds());
        }

        return SUCCESS;
    }
    
    public String getTown() throws Exception {
		List<Town> listat = (List<Town>) townService.selectTownByAreaId(townVo.getId());
		JSONArray jsonArray = JSONArray.fromObject(listat);
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
