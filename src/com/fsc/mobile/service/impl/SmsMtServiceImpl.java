package com.fsc.mobile.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.mobile.dao.SmsMtDAO;
import com.fsc.mobile.po.SmsMt;
import com.fsc.mobile.service.SmsMtService;

import com.fsc.util.FileUtil;

import com.sun.org.apache.bcel.internal.generic.NEW;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信下行接口实现</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SmsMtServiceImpl extends BaseServiceImpl implements SmsMtService {
    private SmsMtDAO smsMtDAO;

    public void setSmsMtDAO(SmsMtDAO smsMtDAO) {
        this.smsMtDAO = smsMtDAO;
    }

    public void saveSmsMt(SmsMt smsMt) throws Exception {
        smsMtDAO.saveOrUpdateObject(smsMt);
    }

    @Override
    public void sendBySI(String teacherID, String studentID,String content)
        throws Exception {
//        String url = "http://218.77.75.165:88/XXT_Assist/Servlet/XXT_SendSms?mobiles=" +
//            mobile + "&content=" + URLEncoder.encode(content, "gb2312");
    	String url = "http://wps.139910.com:88/XXT_Assist/servlet/XXT_TeacherSms?TeacherID="+teacherID+"&StudentID="+studentID+"&Message="+URLEncoder.encode(content, "gb2312");
//    	System.out.println(teacherID);
//    	System.out.println(studentID);
//    	System.out.println(content);
//    	System.out.println(url);
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHostConfiguration().setHost("wps.139910.com", 88);

        //        PostMethod method = new PostMethod(url);
        //        post.setParameter("mobiles", mobile);
        //        post.setParameter("content", content);
        GetMethod method = new GetMethod(url);
        int statusCode = client.executeMethod(method);

        try {
            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception("SI外挂教育OA短信发送接口访问失败");
            }

            InputStream responseStream = method.getResponseBodyAsStream();
            String res = FileUtil.inputStream2String(responseStream, "utf-8");
            System.out.println("========================>"+res);
            if (res.indexOf("发送成功") == -1) {
                throw new Exception("SI外挂教育OA短信发送接口发送失败");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
    }

    public static void main(String[] args) throws Exception {
        String cpath = ClassLoader.getSystemResource("").getPath();
        PropertyConfigurator.configure(cpath + "../log4j.properties");

        SmsMtServiceImpl mtServiceImpl = new SmsMtServiceImpl();
//        mtServiceImpl.sendBySI("13574883006", "看到请吱声-谭校长");
        mtServiceImpl.sendBySI("229059", "1537417", "测试信息33333");

    }

	@Override
	public void updateStatu(Object[] ob) throws Exception {
		this.smsMtDAO.updateSMSStetu(ob);
		System.out.println(ob.length);
	}
}
