package com.fsc.mobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.fsc.mobile.po.SmsMt;
import com.fsc.mobile.po.SmsMtBak;
import com.fsc.util.DateUtils2;
import com.fsc.xxt.si.msg.po.Msg;
import com.fsc.xxt.si.msg.po.MsgInfo;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.teacher.po.Teacher;


/**
 * <p>Title: 短信模块</p>
 * <p>Description:短信定时任务服务</p>
 * <p>创建日期:2010-05-25  16:10:52</p>
 * @author thh
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class SmsTaskService extends TimerTask {
    private Logger log = Logger.getLogger(this.getClass());
    private SmsMtService smsMtService;
    private static int LENTH =70;

    public void run() {
        try {
            runDownMobileService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runDownMobileService() throws Exception {
    	System.out.println("定时器。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
    	System.out.println(System.currentTimeMillis());
    	long now = System.currentTimeMillis();
    	String hql = "from MsgInfo";
        List downMobileList = smsMtService.selectDataByHQL(hql);
        if (downMobileList.size()!=0) {
        	List alist = new ArrayList() ; 
        for (int i = 0; i < downMobileList.size(); i++) {
        	MsgInfo msginfo = (MsgInfo)downMobileList.get(i);
        	String mess = msginfo.getContent();
            List list = new ArrayList();
            splitStr(list, mess, LENTH);
            
            for (Object object : list) {
                String info = object.toString();
                try{
                smsMtService.sendBySI(msginfo.getTeacherid(), msginfo.getStudentid(),mess);
                System.out.println(msginfo.getId());
                alist.add(msginfo.getId());
                System.out.println(System.currentTimeMillis()-now);
                }catch(Exception e){
                log.info(e.getMessage());
                }
            }
            
        }
        Object[] ob = alist.toArray();
        if (ob.length!=0) {
        	 this.smsMtService.updateStatu(ob);
		}
        
      }
      System.out.println(System.currentTimeMillis()-now);
    }

    /**
     * 分割字符串
     * @param list
     * @param info
     */
    public void splitStr(List list, String info, int len) {
        if (info != null) {
            if (info.length() <= len) {
                list.add(info);
            } else {
                String s = info.substring(0, len);
                list.add(s);
                splitStr(list, info.substring(len, info.length()), len);
            }
        }
    }

    public void setSmsMtService(SmsMtService smsMtService) {
        this.smsMtService = smsMtService;
    }
}
