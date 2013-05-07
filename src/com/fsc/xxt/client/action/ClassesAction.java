package com.fsc.xxt.client.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.StringUtil;
import com.fsc.xxt.client.vo.ClassesVo;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.classes.service.ClassesService;

/**
 * <p>
 * Title:校讯通手机服务端
 * </p>
 * <p>
 * Description:班级Action
 * </p>
 * <p>
 * 创建日期:Jan 4, 2012
 * </p>
 * 
 * @author tbw
 * @version 1.0
 * <p>
 * 湖南家校圈科技有限公司
 * </p>
 * <p>
 * http://www.139910.com
 * </p>
 * <p>
 * http://wps.139910.com
 * </p>
 */
public class ClassesAction extends ClientAction {
	private ClassesVo classesVo = new ClassesVo();
	private ClassesService classesService;

	@Override
	public BaseVo getModel() {
		return classesVo;
	}

	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

	/**
	 * 获得班级列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listClasses() throws Exception {
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

		// 强制参数判定
		// 用户类型
		if (StringUtil.isEmpty(classesVo.getUSER_TYPE())) {
			classesVo.setResultCode("-1");
			classesVo.setResultDesc("缺少参数：用户类型");

			return SUCCESS;
		}

		// 用户名
		if (StringUtil.isEmpty(classesVo.getLOGIN_NAME())) {
			classesVo.setResultCode("-1");
			classesVo.setResultDesc("缺少参数：用户名");

			return SUCCESS;
		}
		// Carfield 2012-01-13 [Add] 增加参数验证判定 [End]

		String tid = userService.getUserIdByLogin(classesVo.getUSER_TYPE(),
				classesVo.getLOGIN_NAME());
		List list = classesService.selectClassesByTId(tid);
		
		for (int i = 0; i < list.size(); i++) {
			Classes classes = (Classes) list.get(i);
			Map map = new HashMap();
			map.put("CLASSID", classes.getId());
			map.put("CLASSNAME", classes.getName());
			classesVo.addListData(map);
		} 

		return "list";
	}
}
