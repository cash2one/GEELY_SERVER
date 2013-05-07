package com.fsc.xxt.si.teacher.dao;

import java.util.List;

import com.fsc.framework.base.dao.BaseDao;
import com.fsc.xxt.si.msg.po.NickName;
import com.fsc.xxt.si.teacher.po.Teacher;
/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:教师Dao接口</p>
 * <p>创建日期:Jan 6, 2012</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public interface TeacherDao extends BaseDao {
	/**
	 * 按属性查找教师列表
	 * @param pName
	 * @param pVal
	 * @return
	 * @throws Exception
	 */
	List selectTeacherListByProp(String pName,Object pVal) throws Exception;
	/**
	 * 按属性查找教师
	 * @param pName
	 * @param pVal
	 * @return
	 * @throws Exception
	 */
	Teacher selectTeacherByProp(String attName,Object pVal) throws Exception;
	
	 /**
     * 查找同一所学校的同事
     * @param schoolId
     * @param userName
     * @return
     * @throws Exception
     */
    public List<Teacher> selectOneSchoolTeacher(String schoolId,String userName)throws Exception;
    
    /**
     * 根据ID查找老师信息
     * @param id
     * @return
     * @throws Exception
     */
    public Teacher findTeacherById(String id)throws Exception;
    /**
     * 根据name,type获取教师信息
     * @param login_name
     * @param user_type
     * @return
     * @throws Exception
     */
    public Teacher getTeaInfo(String login_name)throws Exception;
    /**
     * 根据短信码获取教师ID,NAME,昵称
     * @param smscode
     * @return
     * @throws Exception
     */
    public Teacher getTeainfobysms(String smscode)throws Exception;
    /**
     * 根据教师手机号码获取教师ID,NAME,昵称
     * @param smscode
     * @return
     * @throws Exception
     */
    public Teacher getTeacherinfobymobi(String mobile)throws Exception;
    /**
     * 根据CLASSID获取教师信息
     * @param smscode
     * @return
     * @throws Exception
     */
    List<Teacher> getTeacherInfoByClassId(String classid) throws Exception;
    /**
     * 获取别名集合
     * @return
     * @throws Exception
     */
    List<NickName> getnicknames(String loginid,String logintype,String linkmanids[],String linkmantype)throws Exception;
    /**
     * 获取别名
     * @return
     * @throws Exception
     */
    NickName getnickname(String loginid,String logintype,String linkmanid,String linkmantype)throws Exception;
}
