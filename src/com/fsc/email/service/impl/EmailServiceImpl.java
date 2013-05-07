package com.fsc.email.service.impl;

import com.fsc.email.dao.EmailDao;
import com.fsc.email.po.EmailMt;
import com.fsc.email.po.EmailMtBak;
import com.fsc.email.service.EmailService;
import com.fsc.email.service.MailInfo;
import com.fsc.email.service.MailSender;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.List;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:Email服务接口实现</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class EmailServiceImpl extends BaseServiceImpl implements EmailService {
    EmailDao emailDao;

    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    public void saveEmail(EmailMt emailMt) throws Exception {
        emailDao.insertObject(emailMt);
    }

    public List selectAllEmailMt() throws Exception {
        String hql = "from EmailMo";

        return selectDataByHQL(hql);
    }

    public void sendMail(EmailMt emailMo) throws Exception {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setToAddress(emailMo.getToadd());
        mailInfo.setSubject(emailMo.getSubject());
        mailInfo.setContent(emailMo.getContent());
        MailSender.sendHtmlMail(mailInfo);

        EmailMtBak emailMoBak = new EmailMtBak();
        PropertyUtils.copyProperties(emailMoBak, emailMo);
        emailDao.insertObject(emailMoBak);
        emailDao.deleteObject(emailMo);
    }
}
