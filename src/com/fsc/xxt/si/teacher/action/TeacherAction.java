package com.fsc.xxt.si.teacher.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.CryptoUtil;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.si.teacher.service.TeacherService;
import com.fsc.xxt.si.teacher.vo.TeacherVo;


public class TeacherAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private TeacherVo teachervo = new TeacherVo();
    private TeacherService teacherService;

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @Override
    public BaseVo getModel() {
        return teachervo;
    }
    
    /**
     * 分页查询
     * @author: 刘源
     * @date：2012-5-18 下午02:53:58   
     * @return：String   
     * @throws: Exception
     */
    public String getteacherlist()throws Exception{
    	teacherService.selectPageData(teachervo);
    	return "teacherlist";
    }
    
    /**
     * 添加教师信息页面跳转
     * @author: 刘源
     * @date：2012-5-20 上午12:21:56   
     * @return：String   
     * @throws: Exception
     */
    public String addteacher()throws Exception{
    	
    	return "addteacher";
    }
    
    /**
     * 教师添加
     * @author: 刘源
     * @date：2012-5-20 上午12:35:27   
     * @return：String   
     * @throws: Exception
     */
    public String saveteacher()throws Exception{
    	this.teacherService.saveteacherInfo(teachervo);
    	return "saveteacher";
    }
    
    /**
     * 教师信息修改
     * @author: 刘源
     * @date：2012-5-20 上午12:56:43   
     * @return：String   
     * @throws: Exception
     */
    public String editeacher()throws Exception{
    	if (teachervo.getId()!=null && !"".equals(teachervo.getId())) {
    		Teacher teacher =this.teacherService.findTeacherById(teachervo.getId());
    		
    		try {
                PropertyUtils.copyProperties(teachervo, teacher);
                teachervo.setPass(CryptoUtil.desDecrypt(teacher.getPass()));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
		}
    	return "editeacher";
    }
    
    /**
     * 教师信息删除
     * @author: 刘源
     * @date：2012-5-20 下午08:43:52   
     * @return：String   
     * @throws: Exception
     */
    public String delteacher()throws Exception{
    	if ((teachervo.getId() != null) && !"".equals(teachervo.getId())) {
            String[] ids = teachervo.getId().split(",");
            this.teacherService.delteacher(ids);
        }
    	return "delteacher";
    }
    
    /**
     * 老师授课科目
     * @author: 刘源
     * @date：2012-5-20 下午08:48:38   
     * @return：String   
     * @throws: Exception
     */
    public String teachercourse()throws Exception{
    	if ((teachervo.getId() != null) && !"".equals(teachervo.getId())) {
    		Teacher teacher =this.teacherService.findTeacherById(teachervo.getId());
            try {
//                PropertyUtils.copyProperties(teachervo, teacher);
                List<Teacher> tlist = new ArrayList<Teacher>();
                tlist.add(teacher);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    	return "teachercourse";
    }
}
