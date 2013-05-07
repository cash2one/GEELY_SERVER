package com.fsc.email.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:邮件验证类</p>
 * <p>创建日期:Dec 26, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class SmtpAuthenticator  extends Authenticator{
	String userName=null;
	String password=null;
	 
	public SmtpAuthenticator(){
	}
	public SmtpAuthenticator(String username, String password) { 
		this.userName = username; 
		this.password = password; 
	} 
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
