package com.fsc.xxt.si.product.action;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.FileUtil;

import com.fsc.xxt.client.vo.ProductVo;
import com.fsc.xxt.si.product.po.Product;
import com.fsc.xxt.si.product.service.ProductService;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts2.ServletActionContext;

import java.io.File;

import java.util.Date;


/**
 *
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:添加类描述信息</p>
 * <p>创建日期:2012-5-9</p>
 * @author 刘源
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ProductAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 4850894073979487877L;
    private ProductVo productvo = new ProductVo();
    private ProductService productService;

    @Override
    public BaseVo getModel() {
        return productvo;
    }

    /**
     * 获得产品列表
     * @author: 刘源
     * @date：2012-5-9 下午03:38:43
     * @return：String
     * @throws: Exception
     */
    public String productlist() throws Exception {
        productService.selectPageData(productvo);

        return "list";
    }

    /**
     * 产品搜索
     * @author: 刘源
     * @date：2012-5-10 下午03:59:27
     * @return：String
     * @throws: Exception
     */
    public String seacher() throws Exception {
        productService.selectPageData(productvo);

        return "seacher";
    }

    /**
     * 发布新产品推荐
     * @author: 刘源
     * @date：2012-5-10 下午10:34:36
     * @return：String
     * @throws: Exception
     */
    public String saveproduct() throws Exception {
        String imagepath = ServletActionContext.getServletContext()
                                               .getRealPath("/productImg");

        try {
            if ((productvo.getId() == null) || "".equals(productvo.getId())) {
                if ((productvo.getImagesFileName() != null) &&
                        !"".equals(productvo.getImagesFileName())) {
                    String imapath = "";
                    String imafilename = "";
                    String imatype = productvo.getImagesFileName()
                                              .substring(productvo.getImagesFileName()
                                                                  .lastIndexOf("."),
                            productvo.getImagesFileName().length());
                    imafilename = new Date().getTime() + imatype;
                    imapath = imagepath + "\\" + imafilename;
                    FileUtil.copyFile(productvo.getImages(), new File(imapath));
                    productvo.setImage("productImg/" + imafilename);
                }

                if ((productvo.getIntroimgsFileName() != null) &&
                        !"".equals(productvo.getIntroimgsFileName())) {
                    String imatype = productvo.getIntroimgsFileName()
                                              .substring(productvo.getIntroimgsFileName()
                                                                  .lastIndexOf("."),
                            productvo.getIntroimgsFileName().length());
                    String imafilename = new Date().getTime() + imatype;
                    String intropath = imagepath + "\\" + imafilename;
                    FileUtil.copyFile(productvo.getIntroimgs(),
                        new File(intropath));
                    productvo.setIntroimg("productImg/" + imafilename);
                }
            }

            this.productService.saveNewProduct(productvo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "saveproduct";
    }

    /**
     * 修改产品信息
     * @author: 刘源
     * @date：2012-5-13 下午11:08:28
     * @return：String
     * @throws: Exception
     */
    public String editproduct() throws Exception {
        if ((productvo.getId() != null) && !"".equals(productvo.getId())) {
            Product product = this.productService.findProductById(productvo.getId());

            try {
                PropertyUtils.copyProperties(productvo, product);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        return "editproduct";
    }

    /**
     * 发布新产品信息跳转
     * @author: 刘源
     * @date：2012-5-14 上午10:04:04
     * @return：String
     * @throws: Exception
     */
    public String newproduct() throws Exception {
        return "newproduct";
    }

    /**
     * 新产品信息删除
     * @author: 刘源
     * @date：2012-5-14 下午04:19:08
     * @return：String
     * @throws: Exception
     */
    public String delproduct() throws Exception {
        if ((productvo.getId() != null) && !"".equals(productvo.getId())) {
            String[] ids = productvo.getId().split(",");
            this.productService.delProductById(ids);
        }

        return "delproduct";
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
