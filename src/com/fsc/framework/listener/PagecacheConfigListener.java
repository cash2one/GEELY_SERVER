package com.fsc.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fsc.framework.config.PagecacheConfigurer;

public class PagecacheConfigListener implements ServletContextListener {
	protected Logger log = Logger.getLogger(this.getClass());
	public void contextDestroyed(ServletContextEvent arg0) {
		//System.out.println("< i am in >");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			log.info("加载页面缓存配置");
			//加载配置
			PagecacheConfigurer.init(arg0.getServletContext());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
