package com.fsc.xxt.si.classes.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.classes.service.ClassesService;
import com.fsc.xxt.si.classes.vo.ClassesVo;
import com.fsc.xxt.si.school.po.School;


public class ClassesAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private ClassesVo classesvo = new ClassesVo();
    private ClassesService classesService;

    @Override
    public BaseVo getModel() {
        return classesvo;
    }

    public ClassesService getClassesService() {
        return classesService;
    }

    public void setClassesService(ClassesService classesService) {
        this.classesService = classesService;
    }
    
	/**
     * 班级信息跳转
     * @author: 刘源
     * @date：2012-5-21 上午02:26:54
     * @return：String
     * @throws: Exception
     */
    public String getclasseslist() throws Exception {
        classesService.selectPageData(classesvo);
        session.put("myid", classesvo.getId());
        return "classeslist";
    }

    /**
     * 班级信息添加跳转
     * @author: 刘源
     * @date：2012-5-21 上午02:51:01
     * @return：String
     * @throws: Exception
     */
    public String addclasses() throws Exception {
        return "addclasses";
    }
    
    /**
     * 班级信息保存
     * @author: 刘源
     * @date：2012-5-21 上午03:54:25   
     * @return：String   
     * @throws: Exception
     */
    public String saveclasses()throws Exception{
    	this.classesService.saveclassesInfo(classesvo);
    	return "saveclasses";
    }
    
    /**
     * 学校班级信息修改
     * @author: 刘源
     * @date：2012-5-21 上午04:15:31   
     * @return：String   
     * @throws: Exception
     */
    public String ediclasses()throws Exception{
    	if ((classesvo.getId() != null) && !"".equals(classesvo.getId())) {
            Classes classes = this.classesService.findclassesById(classesvo.getId());

            try {
                PropertyUtils.copyProperties(classesvo, classes);
                 
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    	return "ediclasses";
    }
    
    /**
     * 删除班级信息
     * @author: 刘源
     * @date：2012-5-21 上午10:35:49   
     * @return：String   
     * @throws: Exception
     */
    public String delclasses()throws Exception{
    	 if ((classesvo.getId() != null) && !"".equals(classesvo.getId())) {
             String[] ids = classesvo.getId().split(",");
             this.classesService.delclasses(ids);
         }

    	return "delclasses";
    }
}
