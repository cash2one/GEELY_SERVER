package com.fsc.email.service;

import com.fsc.email.po.EmailMt;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.TimerTask;


/**
 * <p>Title: 邮件模块</p>
 * <p>Description:邮件定时任务服务</p>
 * <p>创建日期:2010-05-25  16:10:52</p>
 * @author thh
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class EmailTaskService extends TimerTask {
    protected Logger log = Logger.getLogger(this.getClass());
    private EmailService emailService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run() {
        try {
            sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmail() throws Exception {
        List list = emailService.selectAllEmailMt();

        for (int i = 0; i < list.size(); i++) {
            EmailMt emailMt = (EmailMt) list.get(i);
            log.info("Send 1 Email...,主题:" + emailMt.getSubject());
            emailService.sendMail(emailMt);
        }
    }
}
