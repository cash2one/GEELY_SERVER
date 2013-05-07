package com.fsc.email.service;

import java.util.List;

import com.fsc.email.po.EmailMt;
import com.fsc.framework.base.service.BaseService;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:Email服务接口</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface EmailService extends BaseService {
	void saveEmail(EmailMt emailMo) throws Exception;
	
	List selectAllEmailMt() throws Exception;
	
	void sendMail(EmailMt emailMo) throws Exception;
}
