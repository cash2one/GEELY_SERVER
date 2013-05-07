package com.fsc.email.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:Email发送者</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class MailSender {
    public static final String FROM_ADDRESS = "talkwebedu@163.com";
    public static final String FROM_MAIL_USER = "talkwebedu@163.com";
    public static final String FROM_MAIL_PWD = "talkweb";
    public static final String SMTP_SERVER = "smtp.163.com";
    public static final String SMTP_PORT = "25";

    public static void sendTextMail(MailInfo mailInfo)
        throws MessagingException {
        SmtpAuthenticator authenticator = null;
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.from", FROM_ADDRESS);
        authenticator = new SmtpAuthenticator(FROM_MAIL_USER, FROM_MAIL_PWD);

        Session sendMailSession = Session.getInstance(props, authenticator);

        Message mailMessage = new MimeMessage(sendMailSession);
        Address from = new InternetAddress(FROM_ADDRESS);
        mailMessage.setFrom(from);

        Address to = new InternetAddress(mailInfo.getToAddress());
        mailMessage.setRecipient(Message.RecipientType.TO, to);
        mailMessage.setSubject(mailInfo.getSubject());
        mailMessage.setSentDate(new Date());

        String mailContent = mailInfo.getContent();
        mailMessage.setText(mailContent);
        Transport.send(mailMessage);
    }

    public static void sendHtmlMail(MailInfo mailInfo)
        throws MessagingException {
        SmtpAuthenticator authenticator = null;
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", "true");
        authenticator = new SmtpAuthenticator(FROM_MAIL_USER, FROM_MAIL_PWD);

        Session sendMailSession = Session.getDefaultInstance(props,
                authenticator);

        Message mailMessage = new MimeMessage(sendMailSession);
        Address from = new InternetAddress(FROM_ADDRESS);
        mailMessage.setFrom(from);

        Address to = new InternetAddress(mailInfo.getToAddress());
        mailMessage.setRecipient(Message.RecipientType.TO, to);
        mailMessage.setSubject(mailInfo.getSubject());
        mailMessage.setSentDate(new Date());

        Multipart mainPart = new MimeMultipart();
        BodyPart html = new MimeBodyPart();
        html.setContent("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"+mailInfo.getContent(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
    }

    public static void main(String[] args) throws Exception {
    }
}
