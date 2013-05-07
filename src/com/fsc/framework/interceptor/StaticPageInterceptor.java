package com.fsc.framework.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.fsc.framework.config.CacheConfig;
import com.fsc.framework.config.PagecacheConfigurer;
import com.fsc.util.CryptoUtil;
import com.fsc.util.StringUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.DefaultActionInvocation;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * <p>Title: 答疑网</p>
 * <p>Description:静态页面生产拦截器</p>
 * <p>创建日期:2010-11-18</p>
 * @author lcb
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class StaticPageInterceptor extends AbstractInterceptor {
    public static final String LOADPAGE_SIGN = "loadpage";

    /** 生成记录器Logger类的对象 */
    protected Logger log = Logger.getLogger(getClass());
    
    private static final List ERROR_LIST=new ArrayList();
    
    static{
    	//错误页面匹配关键字，用于匹配请求出错页面以重新获取页面
    	ERROR_LIST.add("showOrHideExceptionStack");
    	ERROR_LIST.add("<title>系统提示-会话超时</title>");
    	ERROR_LIST.add("<title>会话超时提示页面</title>");
    	ERROR_LIST.add("<title>未登录超时</title>");
    }
    

    /**
         * 获取指定url页面
         * @param urlstr
         * @return
         * @throws Exception
         */
    private static StringBuilder getURLStr(String urlstr)
        throws Exception {
        BufferedReader reader = null;
        StringBuilder document = new StringBuilder(4096);
        DefaultActionInvocation a;
        try {
            URL urlx = new URL(urlstr);
            URLConnection conn = urlx.openConnection();

            reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(),"UTF-8"));

            String line = null;

            while ((line = reader.readLine()) != null) {
                document.append(line);
                document.append("\n");
            }
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return document;
    }
    
    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public String getParamStr(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Map map = request.getParameterMap();
        Set set = map.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String[] value = (String[]) map.get(key);

            for (int i = 0; i < value.length; i++) {
                sb.append(key);
                sb.append('=');
                sb.append(value[i]);
                sb.append('&');
            }
        }

        return sb.toString();
    }

    /**
    * 拦截器实现拦截的方法
    * @param ActionInvocation invocation
    * @return String
    * @throws Exception
    */
    public String intercept(ActionInvocation invocation)
        throws Exception {
    	//如果运行状态为false则关闭其运行
    	if(!PagecacheConfigurer.isOperating()){
    		return invocation.invoke();
    	}
    	
        //得到目标url
    	String dstURL = ServletActionContext.getRequest().getRequestURL().toString();
    	//得到请求action
    	String prefix = dstURL.substring(0,dstURL.lastIndexOf('/')+1);
        String action = dstURL.substring(dstURL.lastIndexOf('/')+1, dstURL.length());
        //System.out.println("---->" + action);
        CacheConfig cacheConfig = (CacheConfig) PagecacheConfigurer.getCacheConfig(action);
        //如果没有对应的配置文件则不需要进行静态化处理
        if (null != cacheConfig) {
            //取得页面读取标识
            String loadpageSign = ServletActionContext.getRequest()
                                             .getParameter(LOADPAGE_SIGN);
            //如果非页面读取则进行静态化操作，如果是则不做任何操作
            if (StringUtil.isEmpty(loadpageSign)) {
            	String suffix = null;
                if (null != cacheConfig.getParams()) {
                    suffix = getParamStr(cacheConfig.getParams());
                }
                String path=PagecacheConfigurer.getSavePath()+"/"+action + suffix+".html";
                File file = new File(ServletActionContext.getServletContext()
                        .getRealPath(path));
               
                Long curTime=System.currentTimeMillis();
                Long lasttime=null;
                //重新生产页面的标识
                boolean isReload=false;
                if (null ==(lasttime=(Long) cacheConfig.getPageNames().get(path))) {
                    isReload=true;
                }else{
                	//静态页面不存在
                	if(!file.exists()){
                		isReload=true;
                	}
                	//页面超时
                	else if((curTime-lasttime)>=cacheConfig.getLoadtime()){
                		isReload=true;
                	}else{
                		isReload=false;
                	}                	
                }
            	
            	//是否重新刷新静态页面
                if(isReload){
	                //获取页面内容
                	byte [] pageContent=getPageContect(invocation);
	                //此字符编码为本地编码。。。
	                String c=new String(pageContent);
	                //判断是否未异常页面,如果是则每次刷新直到为为异常一面，如果不是则生产一次直到配置时间之后再次生成
	               if(!isErrorPage(c)){
	            	   //获取延迟加载页面
	            	   c = getDelay(c,prefix, cacheConfig);
	            	   //先写入文件
		               writePage(path,c);
	            	   //再写入更新时间,保证文件完整读取
	                   cacheConfig.getPageNames().put(path,curTime);
	               }else{
	            	 //错误页面写入文件，不更新时间
		              writePage(path,c);
	               }
                }
                
                //转发到目标静态页面
                ServletActionContext.getRequest().getRequestDispatcher(path).forward(ServletActionContext.getRequest(),
                		ServletActionContext.getResponse());
                return null;
            } 
        }

        return invocation.invoke(); //调用下一个拦截器，或者Action的执行方法
    }
    
    /**
     * 取得页面内容
     * @return
     * @throws Exception 
     */
    private byte [] getPageContect(ActionInvocation invocation) throws Exception{
    	//执行目标action方法
    	ActionProxy actionProxy=invocation.getProxy();
        String result=actionProxy.execute();
        //得到action结果配置
        ActionConfig config = actionProxy.getConfig();
        //得到结果集
        Map results = config.getResults();
        ResultConfig resultConfig = (ResultConfig) results.get(result);
        //结果地址
        String resultLocation=(String) resultConfig.getParams().get("location");
        
        byte [] pageContent=getPageContent(resultLocation);
        return pageContent;
    }
    
    /**
     * 判断是否为出错页面
     * @param c
     * @return
     */
    private boolean isErrorPage(String c){
    	for(int i=0;i<ERROR_LIST.size();i++){
    		String err=(String)ERROR_LIST.get(i);
    		if(-1!=c.indexOf(err)){
            	return true;
            }
    		
    	}
    	
    	return false;
    }
    
    /**
     * 获取请求页面内容
     * @param path
     * @return
     * @throws Exception
     */
    private  byte [] getPageContent(String path) throws Exception{
    	//请求转发
        RequestDispatcher requestDispatcher = ServletActionContext.getServletContext().getRequestDispatcher(path);
         
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final ServletOutputStream stream = new ServletOutputStream()
        {
            public void write(byte[] data, int offset, int length)
            {
                os.write(data, offset, length);
            }

            public void write(int b) throws IOException
            {
                os.write(b);
            }
        };


        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
        HttpServletResponse rep = new HttpServletResponseWrapper(ServletActionContext.getResponse())
        {
            public ServletOutputStream getOutputStream()
            { return stream;
            }

            public PrintWriter getWriter()
            {
                return pw;
            }

        };
        
        requestDispatcher.include(ServletActionContext.getRequest(), rep);
        pw.flush(); 
        
        byte [] pageContect=os.toByteArray();
        
    	
    	return pageContect;
    }
    
    /**
     * 在Action执行前调用
     * @param ActionInvocation invocation
     * @throws Exception
     */
    protected void before(ActionInvocation invocation)
        throws Exception {
    }

    /**
     * 在Action执行之后运行
     * @param ActionInvocation invocation
     * @param String result
     * @throws Exception
     */
    protected void after(ActionInvocation invocation, String result)
        throws Exception {
    }

    
    private void writePage(String path,String context) throws Exception{
    	//检查是否存在文件夹
    	File dir = new File(ServletActionContext.getServletContext()
            .getRealPath(PagecacheConfigurer.getSavePath()));
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	
        File file = new File(ServletActionContext.getServletContext()
                                                 .getRealPath(path));
        
        FileOutputStream fos = new FileOutputStream(file);
        //System.out.println(file.getAbsolutePath());
        //fos.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">".getBytes("utf-8"));
        fos.write(context.getBytes("utf-8"));
        fos.close();
    }
    
    /**
     * 创建url连接字符串
     * @param prefix
     * @param suffix
     * @return
     */
    
    private String createURL(String prefix,String suffix){
    	StringBuilder url=new StringBuilder(prefix.length()*2);
    	
    	url.append(prefix);
    	if(!StringUtil.isEmpty(suffix)){
    		url.append('?');
    		url.append(suffix);
    		url.append(LOADPAGE_SIGN);
    		url.append("=y");
    	}else{
    		url.append('?');
    		url.append(LOADPAGE_SIGN);
    		url.append("=y");
    	}
    	
    	return url.toString();
    }
    
    /**
     * 获取参数字符串
     * @param params
     * @return
     */
    private String getParamStr(List params) {
        StringBuilder sb = new StringBuilder();
        //生产键值对字符串
        for (int i = 0; i < params.size(); i++) {
            String key = (String) params.get(i);
            String[] value = ServletActionContext.getRequest()
                                                 .getParameterValues(key);
            sb.append(key);
            
            //如果有对应参数则包含
            if(null!=value){
	            for (int j = 0; j < value.length; j++) {
	                //sb.append('=');
	                sb.append(value[j]);
	                //sb.append('&');
	            }
            }
        }

        return sb.toString();
    }

    /**
         * 从后往前找指定字符串的位置
         * @param str
         * @param regx
         * @return
         */
    public static int indexOfReverse(String str, String regx, int pos) {
        //通过反向读取匹配字符定位
        int regxIndex = regx.length() - 1;

        for (int i = pos; i >= 0; i--) {
            char sc = str.charAt(i);

            if (sc == regx.charAt(regxIndex)) {
                if (0 == regxIndex) {
                    return i;
                } else {
                    regxIndex--;
                }
            } else {
                regxIndex = regx.length() - 1;
            }
        }

        return -1;
    }
    
    /**
     * 获得延迟加载数据
     * @param html
     * @param loadConfig
     * @param invocation
     * @return
     * @throws Exception
     */
    private String getDelay(String html,String prefix,CacheConfig loadConfig)
        throws Exception {
        List arr = loadConfig.getDelays();
        if(0==arr.size()){
        	return html;
        }

        List in = new ArrayList(1);
        in.add(new StrSeg(html, true));

        for (int i = 0; i < arr.size(); i++) {
            String key = (String) arr.get(i);
            in = splitArrStr(in,prefix,key);
        }

        StringBuilder sb = new StringBuilder(html.length() * 2);

        for (int i = 0; i < in.size(); i++) {
            StrSeg ss = (StrSeg) in.get(i);
            sb.append(ss.seg);
        }

        //System.out.println(sb);
        return sb.toString();
    }

    private List splitArrStr(List list, String prefix,String regx)
        throws Exception {
        List out = new ArrayList(list.size() + 1);
        String id = null;

        for (int i = 0; i < list.size(); i++) {
            StrSeg part = (StrSeg) list.get(i);

            if (!part.isCheck) {
                out.add(part);
                continue;
            }

            int mid = part.seg.indexOf(").load(\"" + regx);

            if (-1 == mid) {
                out.add(part);
                continue;
            }
            
            int ided = part.seg.lastIndexOf("\"", mid);
            int idSt = part.seg.lastIndexOf("\"#",ided);
            
            //int ided = indexOfReverse(part.seg, "\"", mid);
            //int idSt = indexOfReverse(part.seg, "\"#", ided);
            //取得id
            id = part.seg.substring(idSt + "\"#".length(), ided);
            
            int st = part.seg.lastIndexOf('$', idSt);
            //int st = indexOfReverse(part.seg, "$", idSt);
            int ed = part.seg.indexOf(")", mid + ("load(\"" + regx).length());

            out.add(new StrSeg(part.seg.substring(0, st), true));
            out.add(new StrSeg(part.seg.substring(ed + ")".length(),
                        part.seg.length()), true));
            
            for (int j = i + 1; j < list.size(); j++) {
                out.add(list.get(j));
            }

            break;
        }

        List ot = new ArrayList(out.size() + 1);
        String idSymbol="id=\"" + id + "\"";
        
        for (int i = 0; i < out.size(); i++) {
            StrSeg part = (StrSeg) out.get(i);

            if (!part.isCheck) {
                ot.add(part);
                continue;
            }

            int divSt = part.seg.indexOf(idSymbol);

            if (-1 == divSt) {
                ot.add(part);
                continue;
            }

            //System.out.println(part.substring(0, divSt));
            String foot = null;
            /* divEd = part.seg.indexOf("</div>",
                    divSt + idSymbol.length());*/
            //div 前端
            int divEd = part.seg.indexOf(">",
                    divSt + idSymbol.length())+1;
            ot.add(new StrSeg(part.seg.substring(0, divEd), true));
            
            //div内容
            String context = null;
            try {
            	//byte [] b=getPageContect(invocation);
            	//本地字符集
            	//context=new String(b);
            	
                context = getURLStr(prefix + regx).toString();
            } catch (Exception e) {
                context = e.getMessage();
            }
            ot.add(new StrSeg(context, false));
            
            //div后端
            int eDivSt = part.seg.indexOf("</div>",divEd);
            if (-1 == eDivSt) {
            	
            } else {
                foot = part.seg.substring(eDivSt, part.seg.length());
                ot.add(new StrSeg(foot, true));
            }


            for (int j = i + 1; j < out.size(); j++) {
                ot.add(out.get(j));
            }

            break;
        }

        return ot;
    }

    
    static private class StrSeg {
        String seg;
        boolean isCheck;

        public StrSeg(String seg, boolean isCheck) {
            super();
            this.seg = seg;
            this.isCheck = isCheck;
        }
    }
}
