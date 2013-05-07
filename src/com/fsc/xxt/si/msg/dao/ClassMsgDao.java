/**
 * 
 */
package com.fsc.xxt.si.msg.dao;

import java.util.List;

import com.fsc.framework.base.dao.BaseDao;

/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:添加类描述信息</p>
 * <p>创建日期:Jan 13, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface ClassMsgDao extends BaseDao {
	
    /**
     * 获取班级发送信息历史记录
     * @param teacherId
     * @param classId
     * @throws Exception
     */
    List getClassMsgList(String teacherId, String classId) throws Exception;
    
	
}
