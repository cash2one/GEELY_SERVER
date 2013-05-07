package com.fsc.xxt.basedata.area.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.xxt.basedata.area.po.Area;
import com.fsc.xxt.basedata.area.service.AreaService;
import com.fsc.xxt.basedata.area.vo.AreaVo;
import com.fsc.xxt.basedata.province.po.Province;
import com.fsc.xxt.basedata.province.service.ProvinceService;
import com.fsc.xxt.basedata.province.vo.ProvinceVo;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.StringUtil;

/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:添加类描述信息</p>
 * <p>创建日期:Apr 29, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 */
public class AreaAction extends ManageAction {
	/** 表现层对象（用于封装请求参数和处理结果） */
	private AreaVo areaVo;
	/** 业务逻辑组件 */
	/** 地区服务*/
	private AreaService areaService;
	/** 省份服务*/
	private ProvinceService provinceService;

	public AreaAction() {
		areaVo = new AreaVo();
	}

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	/** 实现ModelDriven接口必须实现的方法 */
	@Override
	public BaseVo getModel() {
		return areaVo;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	/**
	 * 获取所有地区信息，进入地区管理列表界面
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (!StringUtil.isEmpty(areaVo.getProvinceid())) {
			areaService.selectAreaByProvinceIdWithPageNo(
					areaVo.getProvinceid(), areaVo);
		}

		return "list";
	}

	/**
	 * 初始化地区信息，进入地区新增界面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (!StringUtil.isEmpty(areaVo.getProvinceid())) {
			Province province = provinceService.selectProvinceById(areaVo
					.getProvinceid());
			areaVo.setProvince(province);
		} else {
			throw new RuntimeException("新增地区省份信息不能为空");
		}

		return INPUT;
	}

	/**
	 * 查找地区树
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		List result = provinceService.selectAllProvince();
		areaVo.setProvinceList(result);

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
	 * 查找地区信息，进入地区信息修改界面
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Area area = areaService.selectAreaById(areaVo.getId());
		PropertyUtils.copyProperties(areaVo, area);

		return INPUT;
	}

	/**
	 * 保存地区信息的服务器验证
	 */
	public void validateSave() {
		if (StringUtil.isEmpty(areaVo.getArea())) {
			addFieldError("area", "地区名不能为空");
		}

		if (StringUtil.isEmpty(areaVo.getProvinceid())) {
			addFieldError("provinceid", "省份名不能为空");
		}
	}

	/**
	 * 保存地区信息
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		Area area = new Area();
		PropertyUtils.copyProperties(area, areaVo);

		//判断是否存在主键，即新增及保存的状态
		if (StringUtil.isEmpty(area.getId())) {
			area.setId(areaService.getId());
		}

		areaService.saveOrUpdateObject(area);

		return SUCCESS;
	}

	/**
	 * 删除地区信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if ((null != areaVo.getIds()) && (0 != areaVo.getIds().length)) {
			//地区信息
			Area area = areaService.selectAreaById(areaVo.getIds()[0]);
			areaVo.setProvinceid(area.getProvinceid());

			areaService.removeByIds(areaVo.getIds());
		}

		return SUCCESS;
	}
	
	public String getArea() throws Exception {
		List<Area> lista = (List<Area>) areaService.selectAreaByProvinceId(areaVo.getId());
		JSONArray jsonArray = JSONArray.fromObject(lista);
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	

}
