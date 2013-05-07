package com.fsc.framework.listener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fsc.framework.config.CacheConfig;
import com.fsc.framework.config.PagecacheConfigurer;
import com.fsc.util.FileUtil;

public class PageStaticServlet extends HttpServlet {
	protected Logger log = Logger.getLogger(this.getClass());
	public PageStaticServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	public void readURL(String url){
		try {
			URL urlx = new URL(url);
			URLConnection conn = urlx.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(1000);
			conn.connect();
			InputStream stream=conn.getInputStream();
			stream.read();
			stream.close();
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		log.info("Init static page...");
		if (PagecacheConfigurer.isDelHTML()) {
			String path=getServletContext().getRealPath(PagecacheConfigurer.getSavePath());
			File file = new File(path);
			FileUtil.deleteSubs(file);
			Map map=PagecacheConfigurer.getConfig();
			if (map!=null&&!map.isEmpty()) {
				for (Object o : map.values()) {
					CacheConfig u=(CacheConfig)o;
					String s=u.getActionName();
					String cp=getServletContext().getContextPath();
					s="http://www.mingxiao100.com"+cp+"/"+s;
					readURL(s);
				}
			}
			
		}
	}

}
