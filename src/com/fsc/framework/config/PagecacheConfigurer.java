package com.fsc.framework.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fsc.util.StringUtil;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:解析页面缓存配置文件</p>
 * <p>创建日期:2011-1-1</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class PagecacheConfigurer {
	private static Map config;
	private static String savePath;
	//是否运行静态化
	private static boolean operating=true;
	private static boolean delHTML=false;
	
	public static final String PAGECACHE_KEY="pageCache";
	public static final String PAGECACHE_XML_ATTR_NAME="name";
	public static final String PAGECACHE_XML_ATTR_RELOADTIME="reloadtime";
	public static final String PAGECACHE_XML_ATTR_DELAY="delay";
	public static final String PAGECACHE_XML_ATTR_PARAM="param";
	public static final String PAGECACHE_XML_ATTR_SAVEPATH="savepath";
	public static final String PAGECACHE_XML_ATTR_OPERATING="status";
	
	public static void init(ServletContext servletContext) throws Exception{
		//得到web.xml配置路径
    	String path=servletContext.getInitParameter(PAGECACHE_KEY);
    	//得到配置文件真实路径
    	String rpath=servletContext.getRealPath(path);
    	parseXML(rpath);
	}
	
	/**
	 * 得到配置文件Map
	 * @return
	 * @throws Exception
	 */
	public static Map getConfig(){
		return config;
	}
	
	/**
	 * 得到配置文件
	 * @param key
	 * @return
	 */
	public static CacheConfig getCacheConfig(String key){
		return (CacheConfig)config.get(key);
	}
	
	private static void parseXML(String path) throws Exception{
		config=new HashMap();
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(path);
		Element root = document.getRootElement();
		
		for (Iterator i = root.elementIterator(); i.hasNext();) {			
			Element element = (Element) i.next();
			if(element.getName().equals("file")){
				String savepath=element.attributeValue(PAGECACHE_XML_ATTR_SAVEPATH);
				if(StringUtil.isEmpty(savepath)){
					throw new RuntimeException("file 节点  "+savepath+" 属性 为空");				
				}
				savePath=savepath;
			}else if(element.getName().equals("operating")){
				String op=element.attributeValue(PAGECACHE_XML_ATTR_OPERATING);
				if(StringUtil.isEmpty(op)){
					throw new RuntimeException("operating 节点  "+op+" 属性 为空");				
				}
				operating=Boolean.valueOf(op);
			}else if(element.getName().equals("delHTML")){
				String op=element.attributeValue(PAGECACHE_XML_ATTR_OPERATING);
				if(!StringUtil.isEmpty(op)){
					delHTML=Boolean.valueOf(op);			
				}
			}else{
			
				CacheConfig loadConfig=new CacheConfig();
				
				String actionName=element.attributeValue(PAGECACHE_XML_ATTR_NAME);
				if(StringUtil.isEmpty(actionName)){
					throw new RuntimeException("action 节点  "+PAGECACHE_XML_ATTR_NAME+" 属性 为空");				
				}
				
				loadConfig.setActionName(actionName);
				
				if(StringUtil.isEmpty(actionName)){
					throw new RuntimeException("action 节点  "+PAGECACHE_XML_ATTR_NAME+" 属性 为空");				
				}
				try{
					String attr=element.attributeValue(PAGECACHE_XML_ATTR_RELOADTIME);
					loadConfig.setLoadtime(transDate(attr));
				}catch(Exception e){
					throw new RuntimeException("action ["+actionName+"] "+PAGECACHE_XML_ATTR_RELOADTIME+" 属性"+e.getMessage());				
				}
				
				//解析action子节点
				parseChild(element,loadConfig);
				
				config.put(actionName,loadConfig);
			}
		}
	}
	
	/**
	 * 解析子节点
	 * @param element
	 * @param loadConfig
	 */
	private static void parseChild(Element element,CacheConfig loadConfig){
		List list=new ArrayList(0);
		List plist=new ArrayList(0);
		for (Iterator i = element.elementIterator(); i.hasNext();) {
			Element child = (Element) i.next();
			String name=child.attributeValue(PAGECACHE_XML_ATTR_NAME);
			if(child.getName().equals(PAGECACHE_XML_ATTR_DELAY)){
				if(!name.endsWith(".do")){
					throw new RuntimeException("节点 "+PAGECACHE_XML_ATTR_DELAY+" 属性 "+PAGECACHE_XML_ATTR_NAME+" 必须以.do结尾");
				}
				list.add(name);
			}else if(child.getName().equals(PAGECACHE_XML_ATTR_PARAM)){
				plist.add(name);
			}else{
				
			}
		}
		
		//延迟加载项
		loadConfig.setDelays(list);
		//参数项
		loadConfig.setParams(plist);
	}
	
	
	/**
	 * 转换时间
	 * @param date
	 * @return
	 */
	private static long transDate(String date){
		if(StringUtil.isEmpty(date)||date.length()<2){
			throw new RuntimeException("时间格式不正确 请参照 24H 5M 4S");
		}
		
		char un=date.charAt(date.length()-1);
		String numStr=date.substring(0, date.length()-1);
		int num=Integer.parseInt(numStr);
		long time=0;
		switch(un){
			case	'H':;
			case	'h':time=num*60*60*1000;break;
			case	'M':;
			case	'm':time=num*60*1000;break;
			case	'S':;
			case	's':time=num*1000;break;
			default	: throw new RuntimeException("最后一位时间单位不正确，请填写H/h/M/m/S/s");
		}
		
		return time;
	}

	public static String getSavePath() {
		return savePath;
	}

	public static boolean isOperating() {
		return operating;
	}
	
	public static boolean isDelHTML() {
		return delHTML;
	}

	public static void setOperating(boolean operating) {
		PagecacheConfigurer.operating = operating;
	}
	
	
}
