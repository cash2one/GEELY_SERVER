package com.fsc.xxt.version.service;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.version.po.Version;
import com.fsc.xxt.version.vo.VersionVo;

/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端apk版本服务层接口</p>
 * <p>创建日期:Feb 27, 2012</p>
 * @author ZhouChao
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface VersionService extends BaseService {
	/**
	 * 保存apk版本信息
	 * @param version
	 * @throws Exception
	 */
	public void saveVersion(VersionVo versionVo)throws Exception;
	
	/**
	 * 分页查找apk版本信息
	 * @param versionVo
	 * @throws Exception
	 */
	public void selectPageData(VersionVo versionVo)throws Exception;
	
	/**
	 * 根据ID查找版本信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Version findVersionById(String id)throws Exception;
	
	/**
	 * 删除apk版本信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteVersionInfo(String id)throws Exception;
	
	/**
	 * 查找最大内部版本ID
	 * @return
	 * @throws Exception
	 */
	public Integer selectMaxVersionInnerId()throws Exception;
	
	/**
	 * 查找最新的版本信息
	 * @return
	 * @throws Exception
	 */
	public Version selectLatestVersion(String projectname)throws Exception;
}
