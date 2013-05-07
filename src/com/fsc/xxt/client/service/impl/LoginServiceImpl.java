package com.fsc.xxt.client.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.util.FileUtil;
import com.fsc.xxt.client.service.LoginService;
import com.fsc.xxt.client.service.XXT_VerifyCode;
import com.fsc.xxt.si.teacher.dao.TeacherDao;



/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:教师平台基本操作：登录登出，HttpClient连接池</p>
 * <p>创建日期:Dec 26, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	private String LOGON_SITE = "www.xxt.hn.chinamobile.com";
	private String VeriCode_URL = "http://www.xxt.hn.chinamobile.com/validate";
	private String Login_URL = "http://www.xxt.hn.chinamobile.com/login.do?type=1";
	private String Logout_URL = "http://www.xxt.hn.chinamobile.com/logout.do?type=0";
	private String Get_Qttid_URL = "http://www.xxt.hn.chinamobile.com/jzdx/parentcommunicate/sendmessage.do";
	private int LOGON_PORT = 80;
    private static Map clientMap = new HashMap();

	public String getQttid(String account) throws Exception {
        HttpClient client = getSession(account);
        GetMethod getMethod = new GetMethod(Get_Qttid_URL);
        int statusCode = client.executeMethod(getMethod);

        if (statusCode != HttpStatus.SC_OK) {
            log.error("Method failed in getQttid require: " +
                getMethod.getStatusLine());
        }

        String res = FileUtil.inputStream2String(getMethod.getResponseBodyAsStream());
        getMethod.releaseConnection();
        String key = "qttid=";
        String qttid = res.substring(res.indexOf(key) + key.length(),
                res.indexOf("\" method=\"post\""));

        return qttid;
    }

    public HttpClient getSession(String account) throws Exception {
        HttpClient client = (HttpClient) clientMap.get(account);

        if (client == null) {
            throw new Exception("用户尚未登录");
        }

        return client;
    }

    public HttpClient login(String account, String pwd) {
        String res = "";
        int statusCode;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        boolean isLogin = false;

        try {
            // 识别验证码
            GetMethod getMethod = new GetMethod(VeriCode_URL);
            statusCode = client.executeMethod(getMethod);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed in validate code require: " +
                    getMethod.getStatusLine());
            }

            InputStream responseStream = getMethod.getResponseBodyAsStream();
            String vcode = XXT_VerifyCode.getValidate(responseStream);
            // 登录
            client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);

            PostMethod post = new PostMethod(Login_URL);
            NameValuePair name = new NameValuePair("userid", account);
            NameValuePair pass = new NameValuePair("psw", pwd);
            NameValuePair code = new NameValuePair("validate", vcode);
            post.setRequestBody(new NameValuePair[] { name, pass, code });
            statusCode = client.executeMethod(post);

            if (statusCode != HttpStatus.SC_OK) {
                log.error("Method failured in login process: " +
                    getMethod.getStatusLine());
            }

            res = post.getResponseBodyAsString();
            post.releaseConnection();

            if (res.indexOf("index?type=1") > 0) {
                isLogin = true;
            }
        } catch (Exception e) {
            System.err.println(account + " login failed!");
        }

        if (isLogin) {
            log.info(account + " login OK.");
            clientMap.put(account, client);

            return client;
        } else {
            log.error(account + " response incorrect! ");

            return null;
        }
    }

    public void logout(String account) throws Exception {
        HttpClient client = (HttpClient) clientMap.get(account);

        if (client != null) {
            GetMethod getMethod = new GetMethod(Logout_URL);
            int statusCode = client.executeMethod(getMethod);
            getMethod.releaseConnection();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("Method failed in logout require: " +
                    getMethod.getStatusLine());
            } else {
                clientMap.remove(account);
                log.info(account + " Logout OK");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LoginServiceImpl client = new LoginServiceImpl();

        try {
            client.login("csxiedage", "xrs123456");
            System.out.println(client.getQttid("csxiedage"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.logout("csxiedage");
        }
    }
}
