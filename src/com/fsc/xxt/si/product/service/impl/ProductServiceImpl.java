package com.fsc.xxt.si.product.service.impl;

import com.fsc.framework.base.service.impl.BaseServiceImpl;

import com.fsc.xxt.client.vo.MsgVo;
import com.fsc.xxt.client.vo.ProductVo;
import com.fsc.xxt.si.product.dao.ProductDao;
import com.fsc.xxt.si.product.po.Product;
import com.fsc.xxt.si.product.po.ProductCollection;
import com.fsc.xxt.si.product.service.ProductService;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts2.ServletActionContext;

import java.io.File;

import java.util.Date;
import java.util.List;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品管理服务层接口实现</p>
 * <p>创建日期:Feb 9, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ProductServiceImpl extends BaseServiceImpl
    implements ProductService {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void selectProList(ProductVo productVo) throws Exception {
        String hql = "from Product order by putTime desc";
        String countHql = "select count(*) " + hql;
        this.selectPageData(productVo, hql, countHql);
    }

    @Override
    public Product findProductById(String id) throws Exception {
        Object obj = productDao.getObject(Product.class, id);

        if (Product.class.equals(obj.getClass())) {
            return (Product) obj;
        }

        return null;
    }

    @Override
    public boolean saveProCollect(String userId, String userType,
        String productId) throws Exception {
        try {
            ProductCollection pc = new ProductCollection();
            pc.setId(productDao.getId());
            pc.setColTime(new Date());
            pc.setProductId(productId);
            pc.setUserId(userId);
            pc.setUserType(userType);
            productDao.insertObject(pc);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public Long selectUserCollectCount(String userType, String userId)
        throws Exception {
        return productDao.selectUserCollectCount(userType, userId);
    }

    @Override
    public void selectProCollectPageData(MsgVo msgVo, String userId)
        throws Exception {
        String hql = "from ProductCollection where userId='" + userId +
            "' and userType='" + msgVo.getUSER_TYPE() + "'";
        String countHql = "select count(*) " + hql;
        hql += " order by colTime desc";
        this.selectPageData(msgVo, hql, countHql);
    }

    @Override
    public void selectPageData(ProductVo productvo) throws Exception {
        String hql = "from Product where 1=1 ";

        if ((productvo.getTitle() != null) && !"".equals(productvo.getTitle())) {
            hql += ("and title like'%" + productvo.getTitle() + "%'");
        }

        if ((productvo.getCustomer() != null) &&
                !"".equals(productvo.getCustomer())) {
            hql += (" and customer='" + productvo.getCustomer() + "'");
        }

        if ((productvo.getStar() != null) && !"".equals(productvo.getStar())) {
            hql += (" and star=" + productvo.getStar() + "");
        }

        hql += " order by puttime desc";

        String hqlcount = "select count(*) " + hql;
        this.selectPageData(productvo, hql, hqlcount);
    }

    /* (non-Javadoc)
     * @see com.fsc.xxt.si.product.service.ProductService#delProCoolect(java.lang.String)
     */
    @Override
    public boolean delProCoolect(String id) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void saveNewProduct(ProductVo productvo) throws Exception {
        Product product = new Product();

        if ((productvo.getId() == null) || "".equals(productvo.getId())) {
            PropertyUtils.copyProperties(product, productvo);
            product.setId(getId());
            product.setPutTime(new Date());
        } else {
            PropertyUtils.copyProperties(product, productvo);
            product.setPutTime(new Date());
        }

        this.productDao.saveOrUpdateObject(product);
    }

    @Override
    public void delProductById(String[] ids) throws Exception {
        try {
            String hql = "from Product where id in(";

            for (int i = 0; i < ids.length; i++) {
                if (i == (ids.length - 1)) {
                    hql += "?)";
                } else {
                    hql += "? ,";
                }
            }

            List<Product> listpro = this.selectDataByHQL(hql, ids);
            String path = ServletActionContext.getServletContext()
                                              .getRealPath("productImg");

            for (int i = 0; i < listpro.size(); i++) {
                //删除服务器上的产品图片
                String imafilename = myspilt(listpro.get(i).getImage());
                File imafile = new File(path + "\\" + imafilename);

                if (imafile.exists()) {
                    imafile.delete();
                }

                //删除服务器上的详细产品图片
                String introfilename = myspilt(listpro.get(i).getIntroimg());
                File introfile = new File(path + "\\" + introfilename);

                if (introfile.exists()) {
                    introfile.delete();
                }
            }

            this.deleteCollection(listpro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String myspilt(String spl) {
        if ((spl == null) || "".equals(spl)) {
            return "";
        } else {
            String sub = spl.substring(spl.lastIndexOf("/") + 1, spl.length());

            return sub;
        }
    }
}
