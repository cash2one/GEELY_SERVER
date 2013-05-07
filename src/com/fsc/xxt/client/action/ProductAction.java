package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.StringUtil;

import com.fsc.xxt.client.vo.ProductVo;
import com.fsc.xxt.si.product.po.Product;
import com.fsc.xxt.si.product.po.ProductCollection;
import com.fsc.xxt.si.product.service.ProductService;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.service.StudentService;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.si.teacher.service.TeacherService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:产品信息管理控制类</p>
 * <p>创建日期:Feb 8, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ProductAction extends ClientAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 8383579959357973241L;
    private ProductVo productVo = new ProductVo();
    private ProductService productService;
    private TeacherService teacherService;
    private StudentService studentService;

    @Override
    public BaseVo getModel() {
        return productVo;
    }

    /**
     * 产品列表
     * @return
     * @throws Exception
     */
    public String proList() throws Exception {
        try {
            productVo.setPageNo(1);
            productVo.setPageSize(20);
            productService.selectProList(productVo);

            List rList = new ArrayList();

            for (Product product : (List<Product>) productVo.getList()) {
                Map map = new HashMap();
                map.put("id", product.getId());
                map.put("tile", product.getTitle());
                map.put("price", product.getPrice());
                map.put("star", product.getStar());
                map.put("image", product.getImage());
                rList.add(map);
            }

            productVo.setReturnList(rList);
        } catch (Exception e) {
            e.printStackTrace();
            productVo.setResultCode("-1");
            productVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return "list";
    }

    /**
     * 查找产品详细信息
     * @return
     * @throws Exception
     */
    public String proDetail() throws Exception {
        try {
            Product product = productService.findProductById(productVo.getID());
            productVo.setResultCode("0");
            productVo.setResultData("intro", product.getIntro());
            productVo.setResultData("tile", product.getTitle());
            productVo.setResultData("price", product.getPrice() + "");
            productVo.setResultData("customer", product.getCustomer());
            productVo.setResultData("recommend", product.getRecommend());
            productVo.setResultData("introimg", product.getIntroimg());
            productVo.setResultData("smscode", product.getSmscode());
            productVo.setResultData("smscontent", product.getSmscontent());
        } catch (Exception e) {
            e.printStackTrace();
            productVo.setResultCode("-1");
            productVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return SUCCESS;
    }

    /**
     * 产品收藏
     * @return
     * @throws Exception
     */
    public String collectPro() throws Exception {
        if (StringUtil.isEmpty(productVo.getLOGIN_NAME())) {
            productVo.setResultCode("-1");
            productVo.setResultDesc("缺少参数：用户名");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(productVo.getUSER_TYPE())) {
            productVo.setResultCode("-1");
            productVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(productVo.getPRODUCT_ID())) {
            productVo.setResultCode("-1");
            productVo.setResultDesc("缺少参数：产品ID");

            return SUCCESS;
        }

        if (DictionaryConstant.USER_TEACHER.equals(productVo.getUSER_TYPE())) {
            return teacherProCollect();
        }

        if (DictionaryConstant.USER_STUDENT.equals(productVo.getUSER_TYPE())) {
            return studentProCollect();
        }

        return SUCCESS;
    }

    private String teacherProCollect() throws Exception {
        Teacher teach = teacherService.selectTeacherByLogin(productVo.getLOGIN_NAME());
        boolean exist = productService.ifExist("ProductCollection",
                new String[] { "userId", "userType", "productId" },
                new String[] {
                    teach.getId(), productVo.getUSER_TYPE(),
                    productVo.getPRODUCT_ID()
                }, new String[] { "=", "=", "=" });

        if (exist) {
            productVo.setResultCode("-2");
            productVo.setResultDesc("改产品已经收藏~！");
        } else {
            productVo.setResultCode("-1");
            productVo.setResultDesc("产品收藏失败，请稍后再试！");

            boolean flag = productService.saveProCollect(teach.getId(),
                    productVo.getUSER_TYPE(), productVo.getPRODUCT_ID());

            if (flag) {
                productVo.setResultCode("0");
                productVo.setResultDesc("产品收藏成功");
            } else {
                productVo.setResultCode("-1");
                productVo.setResultDesc("产品收藏失败，请稍后再试！");
            }
        }

        return SUCCESS;
    }

    private String studentProCollect() throws Exception {
        Student stuch = studentService.selectStudentByLogin(productVo.getLOGIN_NAME());
        boolean exist = productService.ifExist("ProductCollection",
                new String[] { "userId", "userType", "productId" },
                new String[] {
                    stuch.getId(), productVo.getUSER_TYPE(),
                    productVo.getPRODUCT_ID()
                }, new String[] { "=", "=", "=" });

        if (exist) {
            productVo.setResultCode("-2");
            productVo.setResultDesc("改产品已经收藏~！");
        } else {
            productVo.setResultCode("-1");
            productVo.setResultDesc("产品收藏失败，请稍后再试！");

            boolean flag = productService.saveProCollect(stuch.getId(),
                    productVo.getUSER_TYPE(), productVo.getPRODUCT_ID());

            if (flag) {
                productVo.setResultCode("0");
                productVo.setResultDesc("产品收藏成功");
            } else {
                productVo.setResultCode("-1");
                productVo.setResultDesc("产品收藏失败，请稍后再试！");
            }
        }

        return SUCCESS;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
