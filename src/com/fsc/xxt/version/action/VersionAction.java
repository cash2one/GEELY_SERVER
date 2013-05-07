package com.fsc.xxt.version.action;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.FileUtil;

import com.fsc.xxt.version.po.Version;
import com.fsc.xxt.version.service.VersionService;
import com.fsc.xxt.version.vo.VersionVo;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts2.ServletActionContext;

import java.io.File;

import java.util.Date;

/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端apk版本控制类</p>
 * <p>创建日期:Feb 27, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class VersionAction extends ManageAction {
	/** <code>serialVersionUID</code> 的注释 */
	private static final long serialVersionUID = 6215431639450477499L;
	private VersionVo versionVo = new VersionVo();
	private VersionService versionService;

	@Override
	public BaseVo getModel() {
		return versionVo;
	}

	/**
	 * 版本列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		versionService.selectPageData(versionVo);

		return "list";
	}

	/**
	 * apk版本表单
	 * @return
	 * @throws Exception
	 */
	public String form() throws Exception {
		return "form";
	}

	/**
	 * 编辑apk版本信息
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Version version = versionService.findVersionById(versionVo.getId());
		PropertyUtils.copyProperties(versionVo, version);

		return form();
	}

	/**
	 * 保存apk版本信息
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			String path = "";
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("/apkupload");
			String fileName = "";

			if (!"".equals(versionVo.getFileFileName())
					&& (versionVo.getFileFileName() != null)) {
				String type = versionVo.getFileFileName().substring(
						versionVo.getFileFileName().lastIndexOf("."),
						versionVo.getFileFileName().length());
				fileName = new Date().getTime() + type;
				path = filePath + "\\" + fileName;
			}

			System.out.println(versionVo.getFile().length());
			FileUtil.copyFile(versionVo.getFile(), new File(path));
			versionVo.setFileFileName(fileName);
			versionService.saveVersion(versionVo);
		} catch (Exception e) {
			e.printStackTrace();
			versionVo.setMessage("保存失败，请稍后再试！");

			return "form";
		}

		return list();
	}

	/**
	 * 删除apk版本信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		Version version = versionService.findVersionById(versionVo.getId());

		try {
			versionService.deleteObject(version);
		} catch (Exception e) {
			e.printStackTrace();
			versionVo.setMessage("删除失败，请稍后再试！");

			return list();
		}

		String path = ServletActionContext.getServletContext().getRealPath(
				"apkupload");
		File file = new File(path + "\\" + version.getApkFile());

		if (file.exists()) {
			file.delete();
		}

		return list();
	}

	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}
}
