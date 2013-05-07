package com.fsc.xxt.sys.dept.service.impl;

import com.fsc.xxt.sys.dept.dao.DeptDao;
import com.fsc.xxt.sys.dept.po.Dept;
import com.fsc.xxt.sys.dept.service.DeptService;
import com.fsc.xxt.sys.org.dao.OrgDao;
import com.fsc.framework.base.service.impl.BaseServiceImpl;


import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:部门信息管理服务接口实现</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class DeptServiceImpl extends BaseServiceImpl implements DeptService {
    private DeptDao deptDao;
    private OrgDao orgDao;

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    public void setOrgDao(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

    public Dept findDeptById(String id) throws DataAccessException {
        Dept dept = deptDao.findDeptById(id);

        return dept;
    }

    public List selectDeptByPdeptNo(String orgNo, String deptId)
        throws DataAccessException {
        String hql = "from Dept c where 1=1 ";

        if ((orgNo != null) && !"".equals(orgNo)) {
            hql += (" and c.orgNo='" + orgNo + "'");
        }

        if ((deptId != null) && !"".equals(deptId)) {
            hql += (" and c.pdeptNo='" + deptId + "'");
        } else {
            hql += " and c.pdeptNo is null";
        }

        hql += " order by dispSN";

        return deptDao.selectDataByHQL(hql);
    }

    public List selectDeptSort(String orgNo, String deptId)
        throws DataAccessException {
        String hql = "from Dept c where 1=1 ";

        if ((orgNo != null) && !"".equals(orgNo)) {
            hql += (" and c.orgNo='" + orgNo + "'");
        }

        if ((deptId != null) && !"".equals(deptId)) {
            hql += (" and c.pdeptNo='" + deptId + "'");
        } else {
            hql += " and c.pdeptNo is null";
        }

        hql += " order by dispSN";

        List list = deptDao.selectDataByHQL(hql);

        for (int i = 0; i < list.size(); i++) {
            Dept d = (Dept) list.get(i);
            selectDeptSort(list, "", d.getId());
        }

        return list;
    }

    public List selectDeptSort(String id, String orgNo, String deptId)
        throws DataAccessException {
        String hql = "from Dept c where 1=1 ";

        if ((id != null) && !"".equals(id)) {
            hql += (" and c.id<>'" + id + "'");
        }

        if ((orgNo != null) && !"".equals(orgNo)) {
            hql += (" and c.orgNo='" + orgNo + "'");
        }

        if ((deptId != null) && !"".equals(deptId)) {
            hql += (" and c.pdeptNo='" + deptId + "'");
        } else {
            hql += " and c.pdeptNo is null";
        }

        hql += " order by dispSN";

        List list = deptDao.selectDataByHQL(hql);

        for (int i = 0; i < list.size(); i++) {
            Dept d = (Dept) list.get(i);
            selectDeptSort(list, id, d.getId());
        }

        return list;
    }

    public void selectDeptSort(List list, String id, String deptNo)
        throws DataAccessException {
        String hql = "from Dept c where pdeptNo=?";

        if ((id != null) && !"".equals(id)) {
            hql += (" and id<>'" + id + "'");
        }

        hql += " order by dispSN";

        List subList = deptDao.selectDataByHQL(hql, new String[] { deptNo });

        for (int i = 0; i < subList.size(); i++) {
            Dept d = (Dept) subList.get(i);
            list.add(d);
            selectDeptSort(list, id, d.getId());
        }
    }

    public List selectDeptList() throws DataAccessException {
        return deptDao.selectDeptList();
    }

    public List transformList(List list) throws SQLException {
        List newList = new ArrayList();
        List tempList = new ArrayList();
        tempList.addAll(list);

        boolean isTopNode = true; //是否顶级结点

        if (list.size() > 0) {
            String strPno = ""; //父结点编号

            /* 获取顶级结点ID */
            for (int i = 0; i < list.size(); i++) {
                Dept dept = (Dept) list.get(i);
                strPno = dept.getPdeptNo();
                isTopNode = true;

                for (int j = 0; j < list.size(); j++) {
                    Dept dept2 = (Dept) list.get(j);

                    if ((strPno != null) && strPno.equals(dept2.getId())) {
                        isTopNode = false;

                        break;
                    }
                }

                /* 把顶级结点添加到新的实体集中，并从临时实体集中移除相应的结点 */
                if (isTopNode) {
                    newList.add(dept);
                    tempList.remove(dept);
                }
            }

            /* 循环转化除顶级结点之外的实体对象，并添加至新的实体集 */
            int x = 0;

            while (newList.size() < list.size()) {
                x++;
                newList = transformList(newList, tempList, x);
            }
        }

        return newList;
    }

    public List transformList(List parentList, List list, int x)
        throws SQLException {
        List newList = new ArrayList();
        List tempList = new ArrayList();
        newList.addAll(parentList);
        tempList.addAll(list);

        /* 生成前缀格式符 */
        String strPrefix = "";

        for (int i = 0; i < x; i++) {
            strPrefix = strPrefix + "----";
        }

        int y = 0;
        String strPno = "";
        Dept dept;

        /* 循环转化尚未转化的实体对象，并添加至新的实体集 */
        for (int i = 0; i < parentList.size(); i++) {
            dept = (Dept) parentList.get(i);
            strPno = dept.getId();

            for (int j = 0; j < tempList.size(); j++) {
                Dept dept2 = (Dept) tempList.get(j);

                if ((strPno != null) && strPno.equals(dept2.getPdeptNo())) {
                    y++;
                    dept2.setName(strPrefix + dept2.getName());
                    newList.add(i + y, dept2);
                    list.remove(dept2);
                }
            }
        }

        return newList;
    }

    public List transformList(List list, String prefix)
        throws SQLException {
        List newList = new ArrayList();
        List tempList = new ArrayList();
        tempList.addAll(list);

        boolean isTopNode = true; //是否顶级结点

        if (list.size() > 0) {
            String strPno = ""; //父结点ID

            /* 获取顶级结点ID */
            for (int i = 0; i < list.size(); i++) {
                Dept dept = (Dept) list.get(i);
                strPno = dept.getPdeptNo();
                isTopNode = true;

                for (int j = 0; j < list.size(); j++) {
                    Dept dept2 = (Dept) list.get(j);

                    if ((strPno != null) && strPno.equals(dept2.getId())) {
                        isTopNode = false;

                        break;
                    }
                }

                /* 把顶级结点添加到新的实体集中，并从临时实体集中移除相应的结点 */
                if (isTopNode) {
                    newList.add(dept);
                    tempList.remove(dept);
                }
            }

            /* 循环转化除顶级结点之外的实体对象，并添加至新的实体集 */
            int x = 0;

            while (newList.size() < list.size()) {
                x++;
                newList = transformListNothingStrPreFix(newList, tempList, x,
                        prefix);
            }
        }

        return newList;
    }

    public List transformListNothingStrPreFix(List parentList, List list,
        int x, String prefix) throws SQLException {
        List newList = new ArrayList();
        List tempList = new ArrayList();
        newList.addAll(parentList);
        tempList.addAll(list);

        /* 生成前缀格式符 */
        String strPrefix = "";

        for (int i = 0; i < x; i++) {
            strPrefix = strPrefix + prefix;
        }

        int y = 0;
        String strPno = "";
        Dept dept;

        /* 循环转化尚未转化的实体对象，并添加至新的实体集 */
        for (int i = 0; i < parentList.size(); i++) {
            dept = (Dept) parentList.get(i);
            strPno = dept.getPdeptNo();

            for (int j = 0; j < tempList.size(); j++) {
                Dept dept2 = (Dept) tempList.get(j);

                if ((strPno != null) && strPno.equals(dept2.getPdeptNo())) {
                    y++;
                    dept2.setName(strPrefix + dept2.getName());
                    newList.add(i + y, dept2);
                    list.remove(dept2);
                }
            }
        }

        return newList;
    }

    public String genDeptNo(String orgNo) throws Exception {
        String deptNo = "";
        String hql = "select max(deptNo) from Dept where substr(orgNo,1,5)='" +
            orgNo.substring(0, 5) + "'";
        String maxDeptNo = (String) deptDao.getAttributeValueByHql(hql);

        if ((maxDeptNo != null) && !"".equals(maxDeptNo)) {
            deptNo = String.valueOf((Long.parseLong(maxDeptNo) + 1));
        } else {
            java.text.DecimalFormat df = (java.text.DecimalFormat) java.text.DecimalFormat.getInstance();
            df.applyPattern("0000000000");
            deptNo = orgNo.substring(0, 5) + df.format(1);
        }

        return deptNo;
    }

    /**
     * 根据供电单位获取部门列表
     * @param orgNo
     * @return
     * @throws Exception
     */
    public List<Dept> selectDeptByPreviousOrgNo(String orgNo)
        throws Exception {
        String hql = "from Dept c where c.orgNo= '" + orgNo + "'";

        return selectDataByHQL(hql);
    }

    public boolean ifExistsSameNameInOneDept(String pdeptNo, String name)
        throws Exception {
        String hql = "from Dept d where d.pdeptNo='" + pdeptNo +
            "' and d.name='" + name + "'";
        Object obj = this.getObject(hql);

        if (Dept.class.equals(obj.getClass())) {
            return true;
        }

        return false;
    }
}
