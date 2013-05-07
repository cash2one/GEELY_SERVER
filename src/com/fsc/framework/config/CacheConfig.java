package com.fsc.framework.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:缓存配置文件配置存放类</p>
 * <p>创建日期:2011-1-1</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class CacheConfig {
	//请求名
	private String actionName;
	//重加载时间
	private long loadtime;
	//上次重加载时间
	private long lasttime;
	//延迟加载的请求地址
	private List delays;
	//请求参数名
	private List params;
	//缓存页面路径
	private Map pageNames=new HashMap();
	
	public String toString(){
		
		return actionName+":"+loadtime;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public long getLoadtime() {
		return loadtime;
	}

	public void setLoadtime(long loadtime) {
		this.loadtime = loadtime;
	}

	public long getLasttime() {
		return lasttime;
	}

	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}

	public List getDelays() {
		return delays;
	}

	public void setDelays(List delays) {
		this.delays = delays;
	}

	public List getParams() {
		return params;
	}

	public void setParams(List params) {
		this.params = params;
	}

	public Map getPageNames() {
		return pageNames;
	}

	public void setPageNames(Map pageNames) {
		this.pageNames = pageNames;
	}

}
