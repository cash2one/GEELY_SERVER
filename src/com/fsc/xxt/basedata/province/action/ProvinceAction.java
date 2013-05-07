package com.fsc.xxt.basedata.province.action;

import java.io.IOException;
import java.util.List;

import com.fsc.xxt.basedata.province.po.Province;
import com.fsc.xxt.basedata.province.service.ProvinceService;
import com.fsc.xxt.basedata.province.vo.ProvinceVo;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.StringUtil;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.PropertyUtils;


/**
 *
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:省份控制类</p>
 * <p>创建日期:2010-12-6</p>
 * @author lcb
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class ProvinceAction extends ManageAction {
    /** 表现层对象（用于封装请求参数和处理结果） */
    private ProvinceVo provinceVo;

    /** 业务逻辑组件 */
    private ProvinceService provinceService;

    public ProvinceAction() {
        provinceVo = new ProvinceVo();
    }

    public ProvinceService getPublisherService() {
        return provinceService;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    /** 实现ModelDriven接口必须实现的方法 */
    @Override
    public BaseVo getModel() {
        return provinceVo;
    }

    /**
     * 获取所有科目信息，进入科目管理列表界面
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        provinceService.selectAllProvinceWithPageNo(provinceVo);

        return "list";
    }

    /**
     * 初始化科目信息，进入科目新增界面
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
//        Integer code = Integer.parseInt(provinceService.selectMaxProvinceCode()) +
//            1;
//        provinceVo.setCode(StringUtil.intToString(code,2));

        return INPUT;
    }

    /**
     * 查找科目信息，进入科目信息修改界面
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        Province province = provinceService.selectProvinceById(provinceVo.getId());
        PropertyUtils.copyProperties(provinceVo, province);

        return INPUT;
    }

    /**
     * 保存科目信息的服务器验证
     */
    public void validateSave() {
        if (StringUtil.isEmpty(provinceVo.getProvince())) {
            addFieldError("province", "省份名不能为空");
        }
    }

    public String valiProvince() throws Exception {
        if (provinceService.ifExist("Province", "province",
                    provinceVo.getProvince())) {
            provinceVo.setMessage("省份名已存在");
        }

        if (provinceService.ifExist("Province", "code", provinceVo.getCode())) {
            provinceVo.setMessage("省份代码已存在");
        }

        return SUCCESS;
    }

    /**
     * 保存科目信息
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        Province province = new Province();
        PropertyUtils.copyProperties(province, provinceVo);

        if (!StringUtil.isEmpty(provinceVo.getId())) {
            province.setId(provinceVo.getId());
        } else {
            province.setId(provinceService.getId());
        }

        provinceService.saveOrUpdateObject(province);

        return SUCCESS;
    }

    /**
     * 删除科目信息
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        if ((null != provinceVo.getIds()) && (0 != provinceVo.getIds().length)) {
            provinceService.removeByIds(provinceVo.getIds());
        }

        return SUCCESS;
    }
    
    public String getProvince() throws Exception {
		List<Province> listp = (List<Province>) provinceService.selectAllProvince();
		JSONArray jsonArray = JSONArray.fromObject(listp);
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
