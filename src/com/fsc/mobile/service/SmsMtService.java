package com.fsc.mobile.service;

import java.util.Date;

import com.fsc.framework.base.service.BaseService;

import com.fsc.mobile.po.SmsMt;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信下行服务接口</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public interface SmsMtService extends BaseService {
    void saveSmsMt(SmsMt smsMt) throws Exception;
    
    void sendBySI(String teacherID,String studentID,String content) throws Exception;
    
    void updateStatu(Object[] ob)throws Exception;
}
