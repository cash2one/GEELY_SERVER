/**
 * 
 */
package com.fsc.xxt.si.msg.dao.impl;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.framework.constant.CommonConstants;
import com.fsc.xxt.si.msg.dao.ClassMsgDao;

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
public class ClassMsgDaoImpl  extends BaseDaoImpl implements ClassMsgDao {
	
    /**
     * 获取班级发送信息历史记录
     * @param teacherId
     * @param classId
     * @throws Exception
     */
	@Override
	public List getClassMsgList(String teacherId, String classId)
			throws Exception {
		StringBuffer hql = new StringBuffer( "from ClassMsg where 1=1 ");
		hql.append(" and delFlag = '").append(CommonConstants.DELFLAG_UNDEL).append("' ");
		hql.append(" and teacherId = '").append(teacherId).append("'");
		hql.append(" and classId = '").append(classId).append("'");
		hql.append(" order by sendTime ASC");
		
		return selectDataByHQL(hql.toString());
	}

}
