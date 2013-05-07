package com.fsc.xxt.si.teacher.service;

import java.util.List;

import com.fsc.framework.base.service.BaseService;

import com.fsc.xxt.si.msg.po.NickName;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.vo.StudentVo;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.si.teacher.vo.TeacherVo;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:教师服务接口</p>
 * <p>创建日期:Dec 28, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface TeacherService extends BaseService {
	/**
	 * 查找用户信息根据用户名
	 * @param loginname
	 * @return
	 * @throws Exception
	 */
    Teacher selectTeacherByLogin(String loginname) throws Exception;
    
    /**
     * 查找同一所学校的同事
     * @param schoolId
     * @param userName
     * @return
     * @throws Exception
     */
    public List<Teacher> selectOneSchoolTeacher(String schoolId,String userName)throws Exception;
    
    /**
     * 修改别名
     * @param loginName 用户ID
     * @param nickName 新昵称
     * @return
     * @throws Exception
     */
    public boolean editNickName(String userId,String usertype,String linkmanid,String linkmantype,String nickName)throws Exception;
    
    
    /**
     * 改变教师图像
     * @param loginName
     * @param faceImg
     * @return
     * @throws Exception
     */
    public boolean updateFaceImg(String loginName,String faceImg)throws Exception;
    /**
     * 获取教师信息
     * @param loginName
     * @param faceImg
     * @return
     * @throws Exception
     */
    public Teacher getTeacherInfo(String name)throws Exception;
    /**
     * 获取图像
     * @param loginName
     * @param faceImg
     * @return
     * @throws Exception
     */
    public Object getTeacherImg(String USER_ID,String USER_TYPE)throws Exception;
    /**
     * 根据短信码获取教师ID，NAME,昵称
     * @param smscode
     * @return
     * @throws Exception
     */
    public Teacher getTeacherInfoBySmscode(String smscode)throws Exception;
    /**
     * 根据教师手机号码获取教师ID，NAME
     * @param smscode
     * @return
     * @throws Exception
     */
    public Teacher getTeacherInfoByMobile(String mobile)throws Exception;
    /**
     * 根据CLASSID获取教师信息
     * @param classid
     * @return
     * @throws Exception
     */
    List<Teacher> getTeacherByClassID(String cliassid)throws Exception;
    /**
     * 获取别名集合
     * @return
     * @throws Exception
     */
    List<NickName> getNickNames(String loginid,String logintype,String linkmanids[],String linkmantype)throws Exception;
    /**
     * 获取单个别名
     * @return
     * @throws Exception
     */
    NickName getNickName(String loginid,String logintype,String linkmanid,String linkmantype)throws Exception;
    
    /**
     * 查询分页
     * @author: 刘源
     * @date：2012-5-18 上午10:46:27   
     * @return：void   
     * @throws: Exception
     */
    public void selectPageData(TeacherVo teachervo)throws Exception;
    
    /**
     * 保存教师信息
     * @author: 刘源
     * @date：2012-5-20 上午12:23:56   
     * @return：void   
     * @throws: Exception
     */
    public void saveteacherInfo(TeacherVo teachervo)throws Exception;
    
    /**
     * 根据教师ID获取教师信息
     * @author: 刘源
     * @date：2012-5-20 上午12:41:37   
     * @return：Teacher   
     * @throws: Exception
     */
    public Teacher findTeacherById(String id)throws Exception;
    
    /**
     * 删除教师信息 根据ID
     * @author: 刘源
     * @date：2012-5-20 上午12:59:05   
     * @return：void   
     * @throws: Exception
     */
    public void delteacher(String ids[])throws Exception;
    
}
