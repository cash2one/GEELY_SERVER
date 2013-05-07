package com.fsc.xxt.client.service;

import org.apache.commons.httpclient.HttpClient;

import com.fsc.framework.base.service.BaseService;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:手机登录登出服务接口</p>
 * <p>创建日期:Dec 26, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface LoginService extends BaseService{

	String getQttid(String account) throws Exception;

	HttpClient getSession(String account) throws Exception;

	HttpClient login(String account, String pwd);

	void logout(String account) throws Exception;

}