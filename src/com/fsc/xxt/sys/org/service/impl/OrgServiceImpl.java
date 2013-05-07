package com.fsc.xxt.sys.org.service.impl;

import com.fsc.xxt.sys.org.dao.OrgDao;
import com.fsc.xxt.sys.org.po.Org;
import com.fsc.xxt.sys.org.service.OrgService;
import com.fsc.framework.base.service.impl.BaseServiceImpl;
import com.fsc.framework.constant.CommonConstants;


import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: E电通智能电网门户系统</p>
 * <p>Description:供电单位管理服务接口实现</p>
 * <p>创建日期:2010-07-09  16:17:17</p>
 * @author thh
 * @version 1.0
 * <p>湖南雁能博世科技有限公司</p>
 * <p>http://demo.hnynbs.com</p>
 */
public class OrgServiceImpl extends BaseServiceImpl implements OrgService {
    private OrgDao orgDao;

    public void setOrgDao(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

    public Org findOrgById(String id) throws DataAccessException {
        return orgDao.findOrgById(id);
    }

    public Org findOrgByOrgNo(String orgNo) throws DataAccessException {
        return orgDao.findOrgByOrgNo(orgNo);
    }

    public List selectOrg() throws DataAccessException {
        return orgDao.selectOrg();
    }

    public List selectOrgSort(String orgNo) throws DataAccessException {
        String hql = "from Org c where 1=1 ";

        if ((orgNo != null) && !"".equals(orgNo)) {
            hql += (" and c.porgNo='" + orgNo + "'");
        } else {
            hql += " and c.porgNo is null";
        }

        hql += " order by sortNo";

        List list = orgDao.selectDataByHQL(hql);

        for (int i = 0; i < list.size(); i++) {
            Org u = (Org) list.get(i);
            selectOrgSort(list, u.getOrgNo());
        }

        return list;
    }

    public void selectOrgSort(List list, String orgNo)
        throws DataAccessException {
        String hql = "from Org c where porgNo=? order by sortNo";
        List subList = orgDao.selectDataByHQL(hql, new String[] { orgNo });

        for (int i = 0; i < subList.size(); i++) {
            Org u = (Org) subList.get(i);
            list.add(u);
            selectOrgSort(list, u.getOrgNo());
        }
    }

    public List selectOrgByOrgNo(String orgNo) throws Exception {
        String hql = "from Org c where 1=1 ";

        if ((orgNo != null) && !"".equals(orgNo)) {
            hql += (" and c.porgNo='" + orgNo + "'");
        } else {
            hql += " and c.porgNo is null";
        }

        hql += " order by sortNo";

        return orgDao.selectDataByHQL(hql);
    }

    public List selectFormatOrg() throws DataAccessException {
        List list = new ArrayList();
        //selectSubFormatOrg(list, "", "");
        selectSubFormatOrg(list, CommonConstants.ROOTUNITNO, "");

        return list;
    }

    public List selectFormatOrg(String id) throws DataAccessException {
        List list = new ArrayList();
        //selectSubFormatOrg(list, id, "", "");
        selectSubFormatOrg(list, id, CommonConstants.ROOTUNITNO, "");

        return list;
    }

    public void selectSubFormatOrg(List list, String porgNo, String formatStr)
        throws DataAccessException {
        String hql = "from Org c where 1=1 ";

        if ((porgNo != null) && !"".equals(porgNo)) {
            hql += (" and c.porgNo='" + porgNo + "'");
        } else {
            hql += " and c.porgNo is null";
        }

        hql += " order by sortNo";

        List subList = orgDao.selectDataByHQL(hql);

        for (int i = 0; i < subList.size(); i++) {
            Org org = (Org) subList.get(i);
            org.setOrgName(formatStr + org.getOrgName());
            list.add(org);
            selectSubFormatOrg(list, org.getOrgNo(), formatStr + "----");
        }
    }

    public void selectSubFormatOrg(List list, String orgNo, String porgNo,
        String formatStr) throws DataAccessException {
        String hql = "from Org c where 1=1 ";

        if ((orgNo != null) && !"".equals(orgNo)) {
            hql += (" and c.orgNo<>'" + orgNo + "'");
        }

        if ((porgNo != null) && !"".equals(porgNo)) {
            hql += (" and c.porgNo='" + porgNo + "'");
        } else {
            hql += " and c.porgNo is null";
        }

        hql += " order by sortNo";

        List subList = orgDao.selectDataByHQL(hql);

        for (int i = 0; i < subList.size(); i++) {
            Org org = (Org) subList.get(i);
            org.setOrgName(formatStr + org.getOrgName());
            list.add(org);
            selectSubFormatOrg(list, orgNo, org.getOrgNo(), formatStr + "----");
        }
    }

    public boolean ifExistsSameNameInOneOrg(String porgNo, String name)
        throws Exception {
        String hql = "from Org u where u.porgNo='" + porgNo +
            "' and u.orgName='" + name + "'";
        Object obj = this.getObject(hql);

        if (Org.class.equals(obj.getClass())) {
            return true;
        }

        return false;
    }
}
