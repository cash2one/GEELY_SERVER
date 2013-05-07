package com.fsc.xxt.version.dao.impl;

import com.fsc.framework.base.dao.impl.BaseDaoImpl;

import com.fsc.util.StringUtil;
import com.fsc.xxt.version.dao.VersionDao;
import com.fsc.xxt.version.po.Version;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:客户端apk版本信息DAO接口实现</p>
 * <p>创建日期:Feb 27, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class VersionDaoImpl extends BaseDaoImpl implements VersionDao {
    @Override
    public boolean deleteVersionInfo(String id) throws Exception {
        String hql = "delete Version where id='" + id + "'";

        try {
            this.executeHQL(hql);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Version findVersionById(String id) throws Exception {
        String hql = "from Version where id='" + id + "'";
        Object obj = this.getObject(hql);

        if ((obj != null) && Version.class.equals(obj.getClass())) {
            return (Version) obj;
        }

        return null;
    }

    @Override
    public Integer selectMaxVersionInnerId() throws Exception {
        String hql = "select max(versionInnerId) from Version";
        Object obj = getAttributeValueByHql(hql);

        if (obj != null && !StringUtil.isEmpty(obj.toString())) {
            return new Integer(obj.toString());
        }
        return 0;
    }

	@Override
	public Version selectLatestVersion(String projectname) throws Exception {
		String hql="from Version v where v.id=(select max(id) from Version where projectname="+projectname+")";
		Object obj = getObject(hql);
		if(obj!=null && Version.class.equals(obj.getClass())){
			return (Version) obj;
		}
		return null;
	}
}
