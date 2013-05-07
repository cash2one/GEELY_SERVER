package com.fsc.xxt.si.school.action;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.CryptoUtil;

import com.fsc.xxt.si.school.po.School;
import com.fsc.xxt.si.school.service.SchoolService;
import com.fsc.xxt.si.school.vo.SchoolVo;
import com.fsc.xxt.si.teacher.po.Teacher;

import org.apache.commons.beanutils.PropertyUtils;


public class SchoolAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private SchoolVo schoolvo = new SchoolVo();
    private SchoolService schoolService;

    @Override
    public BaseVo getModel() {
        return schoolvo;
    }

    public SchoolService getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    /**
     * 查询分页
     * @author: 刘源
     * @date：2012-5-21 上午12:41:08
     * @return：String
     * @throws: Exception
     */
    public String getschoollist() throws Exception {
        schoolService.selectPageData(schoolvo);

        return "schoollist";
    }

    /**
     * 学校添加跳转
     * @author: 刘源
     * @date：2012-5-21 上午01:05:26
     * @return：String
     * @throws: Exception
     */
    public String addSchool() throws Exception {
        return "addschool";
    }

    /**
     * 学校信息保存
     * @author: 刘源
     * @date：2012-5-21 上午01:05:39
     * @return：String
     * @throws: Exception
     */
    public String saveschool() throws Exception {
        this.schoolService.saveschoolInfo(schoolvo);

        return "saveschool";
    }

    /**
     * 学校信息编辑
     * @author: 刘源
     * @date：2012-5-21 上午02:04:42
     * @return：String
     * @throws: Exception
     */
    public String edischool() throws Exception {
        if ((schoolvo.getId() != null) && !"".equals(schoolvo.getId())) {
            School school = this.schoolService.findSchoolById(schoolvo.getId());

            try {
                PropertyUtils.copyProperties(schoolvo, school);
                schoolvo.setSprovince(school.getArea().getProvinceid());
                schoolvo.setScity(school.getAreaid());
                schoolvo.setStown(school.getTownid());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        return "edischool";
    }

    /**
     * 学校信息删除
     * @author: 刘源
     * @date：2012-5-21 上午02:18:10
     * @return：String
     * @throws: Exception
     */
    public String delschool() throws Exception {
        if ((schoolvo.getId() != null) && !"".equals(schoolvo.getId())) {
            String[] ids = schoolvo.getId().split(",");
            this.schoolService.delschool(ids);
        }

        return "delschool";
    }
}
