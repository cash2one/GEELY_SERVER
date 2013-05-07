package com.fsc.mobile.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;
import com.fsc.mobile.dao.SmsMtDAO;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:短信下行DAO接口实现</p>
 * <p>创建日期:2011-01-11</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SmsMtDAOImpl extends BaseDaoImpl implements SmsMtDAO {
    public void saveMobileSms(String phoneNo, String content)
        throws Exception {
        Connection con = super.getConnection();
        String procedure = "{call PRO_SEND_LONG_SMS(?,?)}";
        CallableStatement cstmt = con.prepareCall(procedure);
        cstmt.setString(1, phoneNo);
        cstmt.setString(2, content);
        cstmt.executeUpdate();
        con.close();
    }

//	@Override
//	public void updateSMSStetu(Date d,Object[] ob) throws Exception {
//		
//		String hql = "update Msg set mttime = ";
//		
//	}

	@Override
	public void updateSMSStetu(Object[] ob) throws Exception {
		String time =new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		String hql = "update Msg set MTTIME = sysdate , MTFLAG ="+"'02'"+" where ID in(" ;
		for (int i = 0; i < ob.length; i++) {
			if (i==ob.length-1) {
				hql+="? )";
			}
			else{
				hql+=" ? ,";
			}
		}
		
		System.out.println(hql); 
		Session session = this.getSession();
		Query query = null;
		query = session.createQuery(hql);
		for (int i = 0; i < ob.length; i++) {
			query.setString(i, ob[i].toString());
		}
		query.executeUpdate();
		releaseSession(session);
	}
    
}
