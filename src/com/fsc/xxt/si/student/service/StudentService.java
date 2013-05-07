package com.fsc.xxt.si.student.service;

import java.util.List;

import com.fsc.framework.base.service.BaseService;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.vo.StudentVo;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:学生服务接口</p>
 * <p>创建日期:Jan 4, 2012</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface StudentService extends BaseService {
    /**
     * 查找班级技所有学生
     * @return
     * @throws Exception
     */
    List selectStuByClass(String classid) throws Exception;
    
    /**
     * 根据学生ID查找学生信息
     * @param id
     * @return
     * @throws Exception
     */
    public Student findStudentById(String id)throws Exception;
    
    /**
     * 根据学生姓名查找学生信息
     * @param name
     * @return
     * @throws Exception
     */
    public Student findStudentByName(String name)throws Exception;
    
    /**
     * 修改学生别名
     * @param userId 用户ID
     * @param nickName 修改后的别名
     * @return
     * @throws Exception
     */
    public  boolean editStudentNickName(String userId,String nickName)throws Exception;
    /**
     * 根据手机号码获取学生ID,NAME,昵称
     * @param mobile
     * @return
     * @throws Exception
     */
    public Student getStudentInfoBymobile(String mobile)throws Exception;
    /**
	 * 查找用户信息根据用户名
	 * @param loginname
	 * @return
	 * @throws Exception
	 */
    Student selectStudentByLogin(String loginname) throws Exception;
    /**
	 * 根据登录名修改家长头像
	 * @param loginname
	 * @return
	 * @throws Exception
	 */
    boolean updateStudentFaceImg(String loginame,String faceimg)throws Exception;
    /**
	 * 根据学生ID,班级ID,学校ID 获取所在班级学生
	 * @param loginname
	 * @return
	 * @throws Exception
	 */
    List<Student> selectStuList(String id,String classid,String schoolid)throws Exception;
    
    /**
     * 学生信息分页(后台)
     * @author: 刘源
     * @date：2012-5-17 下午02:44:54   
     * @return：void   
     * @throws: Exception
     */
    public void selectPageData(StudentVo studentvo)throws Exception;
    
    /**
     * 保存学生信息
     * @author: 刘源
     * @date：2012-5-18 上午12:26:41   
     * @return：void   
     * @throws: Exception
     */
    public void savestudnetInfo(StudentVo studentvo)throws Exception;
    
    /**
     * 删除学生信息
     * @author: 刘源
     * @date：2012-5-18 上午01:13:06   
     * @return：void   
     * @throws: Exception
     */
    public void delstudent(String ids[])throws Exception;
    
}
