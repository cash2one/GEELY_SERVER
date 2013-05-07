package com.fsc.xxt.si.student.action;

import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.CryptoUtil;
import com.fsc.xxt.si.bulletin.po.Bulletin;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.service.StudentService;
import com.fsc.xxt.si.student.vo.StudentVo;


public class StudentAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private StudentVo studentvo = new StudentVo();
    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public BaseVo getModel() {
        return studentvo;
    }
    
    /**
     * 查询分页
     * @author: 刘源
     * @date：2012-5-17 下午11:35:29   
     * @return：String   
     * @throws: Exception
     */
    public String getStudentlist() throws Exception {
    	studentService.selectPageData(studentvo);
        return "studentlist";
    }
    
    /**
     * 页面跳转
     * @author: 刘源
     * @date：2012-5-18 上午12:23:26   
     * @return：String   
     * @throws: Exception
     */
    public String addStudent()throws Exception{
    	
    	return "addstudent";
    }
    
    /**
     * 添加学生信息
     * @author: 刘源
     * @date：2012-5-18 上午12:46:51   
     * @return：String   
     * @throws: Exception
     */
    public String savestudent()throws Exception{
    	this.studentService.savestudnetInfo(studentvo);
    	return "savestudent";
    }
    
    /**
     * 学生信息修改
     * @author: 刘源
     * @date：2012-5-18 上午12:58:49   
     * @return：String   
     * @throws: Exception
     */
    public String edistudent()throws Exception{
    	if (studentvo.getId()!=null && !"".equals(studentvo.getId())) {
    		Student student =this.studentService.findStudentById(studentvo.getId());
    		
    		try {
                PropertyUtils.copyProperties(studentvo, student);
                studentvo.setPass(CryptoUtil.desDecrypt(student.getPass()));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
		}
    	return "edistudent";
    }
    
    /**
     * 学生信息删除
     * @author: 刘源
     * @date：2012-5-20 上午12:57:53   
     * @return：String   
     * @throws: Exception
     */
    public String delstudent()throws Exception{
    	if ((studentvo.getId() != null) && !"".equals(studentvo.getId())) {
            String[] ids = studentvo.getId().split(",");
            this.studentService.delstudent(ids);
        }
    	return "delstudent";
    }
    
}
