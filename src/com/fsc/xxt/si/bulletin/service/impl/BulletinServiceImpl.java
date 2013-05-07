package com.fsc.xxt.si.bulletin.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.client.vo.BulletinVo;
import com.fsc.xxt.si.bulletin.dao.BulletinDao;
import com.fsc.xxt.si.bulletin.po.Bulletin;
import com.fsc.xxt.si.bulletin.service.BulletinService;
import com.fsc.xxt.si.product.po.Product;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.Date;
import java.util.List;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:系统公告服务层接口实现</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class BulletinServiceImpl extends BaseServiceImpl
    implements BulletinService {
    //系统公告DAO层
    private BulletinDao bulletinDao;

    public void setBulletinDao(BulletinDao bulletinDao) {
        this.bulletinDao = bulletinDao;
    }

    @Override
    public List<Bulletin> selectBulletinListByUserType(String userType)
        throws Exception {
        return bulletinDao.selectBulletinListByUserType(userType);
    }

    @Override
    public Bulletin selectLatestBulletin(String userType)
        throws Exception {
        return bulletinDao.selectLatestBulletin(userType);
    }

    @Override
    public void selectPageData(BulletinVo bulletinvo) throws Exception {
        String hql = "from Bulletin where 1=1";

        if ((bulletinvo.getUserType() != null) &&
                !"".equals(bulletinvo.getUserType())) {
            hql += (" and usertype ='" + bulletinvo.getUserType() + "'");
        }

        if ((bulletinvo.getTitle() != null) &&
                !"".equals(bulletinvo.getTitle())) {
            hql += (" and title like'%" + bulletinvo.getTitle() + "%'");
        }

        if ((bulletinvo.getFlag() != null) && !"".equals(bulletinvo.getFlag())) {
            hql += (" and flag ='" + bulletinvo.getFlag() + "'");
        }

        hql += " order by puttime desc";

        String hqlcount = "select count(*) " + hql;
        this.selectPageData(bulletinvo, hql, hqlcount);
    }

    @Override
    public void saveBulletin(BulletinVo bulletinvo) throws Exception {
        Bulletin bulletin = new Bulletin();

        if ((bulletinvo.getId() == null) || "".equals(bulletinvo.getId())) {
            PropertyUtils.copyProperties(bulletin, bulletinvo);
            bulletin.setId(getId());
            bulletin.setPutTime(new Date());
        } else {
            PropertyUtils.copyProperties(bulletin, bulletinvo);
            bulletin.setPutTime(new Date());
        }

        this.saveOrUpdateObject(bulletin);
    }

    @Override
    public Bulletin getBulletinById(BulletinVo bulletinvo)
        throws Exception {
        Object obj = bulletinDao.getObject(Bulletin.class, bulletinvo.getId());

        if (Bulletin.class.equals(obj.getClass())) {
            return (Bulletin) obj;
        }

        return null;
    }

    @Override
    public void delBulletinById(String[] ids) throws Exception {
        try {
            String hql = "from Bulletin where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            List<Bulletin> listpro = this.selectDataByHQL(hql, ids);
            this.deleteCollection(listpro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
