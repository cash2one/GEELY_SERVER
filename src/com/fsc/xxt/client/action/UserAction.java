package com.fsc.xxt.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.StringUtil;
import com.fsc.xxt.client.vo.UserVo;
import com.fsc.xxt.si.classes.po.Classes;
import com.fsc.xxt.si.classes.service.ClassesService;
import com.fsc.xxt.si.msg.po.NickName;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.service.StudentService;
import com.fsc.xxt.si.teacher.po.Teacher;
import com.fsc.xxt.si.teacher.service.TeacherService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:用户信息action</p>
 * <p>创建日期:Feb 3, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class UserAction extends ClientAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 2790325035676133567L;

    //教师信息服务层
    private TeacherService teacherService;
    private ClassesService classesService;
    private StudentService studentService;
    private UserVo userVo = new UserVo();

    @Override
    public BaseVo getModel() {
        return userVo;
    }

    /**
     * 通讯录列表
     * @return
     * @throws Exception
     */
    public String contactList() throws Exception {
        long now = System.currentTimeMillis();

        if (StringUtil.isEmpty(userVo.getUSER_TYPE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(userVo.getLOGIN_NAME())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户名");

            return SUCCESS;
        }
        String result ="";
        if (DictionaryConstant.USER_TEACHER.equals(userVo.getUSER_TYPE())) {
            result = teacherContactList();
            System.out.println("通讯录列表用时：" + (System.currentTimeMillis() - now));
            return result;
        }
        if (DictionaryConstant.USER_STUDENT.equals(userVo.getUSER_TYPE())) {
        	result = studentContactList();
			return result;
		}

        return "";
    }

    /**
     * 教师通讯录列表
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String teacherContactList() throws Exception {
        try {
            Map rMap = new HashMap();
            Teacher teacher = teacherService.selectTeacherByLogin(userVo.getLOGIN_NAME());
            
            //同事列表
            List<Teacher> tList = teacherService.selectOneSchoolTeacher(teacher.getSchoolid(),
                    userVo.getLOGIN_NAME());
            Map tnickmap = getNicknameMapTea(teacher, tList, DictionaryConstant.USER_TEACHER);
            List rtList = new ArrayList();

            for (Teacher t : tList) {
                Map map = new HashMap();
                map.put("id", t.getId());
                map.put("name", t.getName());
//                map.put("nickName", t.getNickname());
                map.put("nickname", tnickmap.get(t.getId()));
                map.put("loginName", t.getLoginname());
                map.put("schoolId", t.getSchoolid());
                map.put("classId", t.getClassid());
                map.put("userType", DictionaryConstant.USER_TEACHER);
                map.put("faceimg", t.getFaceimg());
                map.put("mobile", t.getMobile());
                map.put("islogin", t.getIslogin());
                rtList.add(map);
            }

            //班级列表
            List<Classes> cList = classesService.selectClassesByTId(teacher.getId());

            List classesList = new ArrayList();

            for (Classes c : cList) {
                Map map = new HashMap();
                List<Student> sList = studentService.selectStuByClass(c.getId());
                List rsList = new ArrayList();
                Map snickmap = getNicknameMapTea(teacher, sList, DictionaryConstant.USER_STUDENT);
                for (Student s : sList) {
                    Map smap = new HashMap();
                    smap.put("id", s.getId());
                    smap.put("name", s.getName());
//                    smap.put("nickName", s.getNickname());
                    smap.put("nickname", snickmap.get(s.getId()));
                    smap.put("loginName", s.getLoginname());
                    smap.put("schoolId", s.getSchoolid());
                    smap.put("classId", s.getClassid());
                    smap.put("mobile", s.getMobile());
                    smap.put("userType", DictionaryConstant.USER_STUDENT);
                    smap.put("faceimg", s.getFaceimg());
                    smap.put("classname", s.getClasses().getName());
                    smap.put("islogin", s.getIslogin());
                    rsList.add(smap);
                }

                map.put("className", c.getName());
                map.put("studentList", rsList);
                classesList.add(map);
            }

            rMap.put("classList", classesList);
            rMap.put("colleagueList", rtList);
            userVo.setReturnMap(rMap);
        } catch (Exception e) {
            e.printStackTrace();
            userVo.setResultCode("-1");
            userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return SUCCESS;
    }

    /**
     * 家长通讯录列表
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String studentContactList() throws Exception {
    	try{
    		Map map = new HashMap();
    		//老师信息
    		Student student = studentService.selectStudentByLogin(userVo.getLOGIN_NAME());
    		List<Teacher> teaList = teacherService.getTeacherByClassID(student.getClassid());
    		Map tnickmap = getNicknameMapStu(student, teaList, DictionaryConstant.USER_TEACHER);
    		
    		List rtList = new ArrayList();
    		List classlist = new ArrayList();
    		Map cmap = new HashMap();
    		Map techid = new HashMap();
            for (Teacher t : teaList) {
                Map tmap = new HashMap(); 
                tmap.put("id", t.getId());
                tmap.put("name", t.getName());
                tmap.put("mobile", t.getMobile());
//              tmap.put("nickName", teacherService.getNickName(student.getId(),DictionaryConstant.USER_STUDENT,t.getId(),DictionaryConstant.USER_TEACHER).getNickname());
                tmap.put("nickName", tnickmap.get(t.getId()));
                tmap.put("loginName", t.getLoginname());
                tmap.put("schoolId", t.getSchoolid());
                tmap.put("classId", t.getClassid());
                tmap.put("userType", DictionaryConstant.USER_TEACHER);
                tmap.put("faceimg", t.getFaceimg());
                tmap.put("islogin", t.getIslogin());
                rtList.add(tmap);
            }
           
            //班级同学信息
            List<Student> slist = studentService.selectStuList(student.getId(),student.getClassid(),student.getSchoolid());
            Map snickmap = getNicknameMapStu(student, slist, DictionaryConstant.USER_STUDENT);
            if (slist.size()>0) {
            List stulist = new ArrayList(); 
            for (Student s : slist) {
				Map smap = new HashMap();
				smap.put("id", s.getId());
				smap.put("name", s.getName());
//				smap.put("nickName", teacherService.getNickName(student.getId(),DictionaryConstant.USER_STUDENT,s.getId(),DictionaryConstant.USER_STUDENT).getNickname());
				smap.put("nickname", snickmap.get(s.getId()));
				smap.put("loginName", s.getLoginname());
				smap.put("schoolId", s.getSchoolid());
				smap.put("classid", s.getClassid());
				smap.put("mobile",s.getMobile());
				smap.put("userType",DictionaryConstant.USER_STUDENT );
				smap.put("faceimg", s.getFaceimg());
				smap.put("classname", s.getClasses().getName());
				smap.put("islogin", s.getIslogin());
				stulist.add(smap);
			}
            cmap.put("classname", slist.get(0).getClasses().getName());
            cmap.put("studentList", stulist);
            classlist.add(cmap);
            
            map.put("teacherinfo",rtList);
            map.put("studentinfo", classlist);
            }
            userVo.setReturnMap(map);
    	}catch(Exception e){
    		e.printStackTrace();
    		userVo.setResultCode("-1");
            userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
    	}
    	return SUCCESS;
    }
    
    /**
     * 获取别名的MAP(家长)
     * @return
     * @throws Exception
     */
    private Map getNicknameMapStu(Student student,List list,String type)throws Exception{
    	if (type==DictionaryConstant.USER_TEACHER) {
    		List<Teacher> tealist = list;
    		String linkmanids[] = new String[tealist.size()];
    		for (int i = 0; i < tealist.size(); i++) {
    			linkmanids[i] = tealist.get(i).getId(); 
    		}
    		List<NickName> teaList = teacherService.getNickNames(student.getId(),DictionaryConstant.USER_STUDENT,linkmanids,DictionaryConstant.USER_TEACHER);
    		Map map = new HashMap();
    		for (int i = 0; i < teaList.size(); i++) {
				map.put(teaList.get(i).getLinkmanid(), teaList.get(i).getNickname());
			}
    		return map;
		}
    	if (type==DictionaryConstant.USER_STUDENT) {
    		List<Student> stulist = list;
    		String linkmanids[] = new String[stulist.size()];
    		for (int i = 0; i < stulist.size(); i++) {
    			linkmanids[i] = stulist.get(i).getId(); 
    		} 
    		List<NickName> stuList = teacherService.getNickNames(student.getId(),DictionaryConstant.USER_STUDENT,linkmanids,DictionaryConstant.USER_STUDENT);
    		Map map = new HashMap();
    		for (int i = 0; i < stuList.size(); i++) {
				map.put(stuList.get(i).getLinkmanid(), stuList.get(i).getNickname());
			}
    		return map;
		}
    	
    	return null;
    }
    
    /**
     * 获取别名的MAP(教师)
     * @return
     * @throws Exception
     */
    private Map getNicknameMapTea(Teacher teacher,List list,String type)throws Exception{
    	if (type==DictionaryConstant.USER_TEACHER) {
    		List<Teacher> tealist = list;
    		String linkmanids[] = new String[tealist.size()];
    		for (int i = 0; i < tealist.size(); i++) {
    			linkmanids[i] = tealist.get(i).getId(); 
    		}
    		List<NickName> teaList = teacherService.getNickNames(teacher.getId(),DictionaryConstant.USER_TEACHER,linkmanids,DictionaryConstant.USER_TEACHER);
    		Map map = new HashMap();
    		for (int i = 0; i < teaList.size(); i++) {
				map.put(teaList.get(i).getLinkmanid(), teaList.get(i).getNickname());
			}
    		return map;
		}
    	if (type==DictionaryConstant.USER_STUDENT) {
    		List<Student> stulist = list;
    		String linkmanids[] = new String[stulist.size()];
    		for (int i = 0; i < stulist.size(); i++) {
    			linkmanids[i] = stulist.get(i).getId(); 
    		} 
    		List<NickName> stuList = teacherService.getNickNames(teacher.getId(),DictionaryConstant.USER_TEACHER,linkmanids,DictionaryConstant.USER_STUDENT);
    		Map map = new HashMap();
    		for (int i = 0; i < stuList.size(); i++) {
				map.put(stuList.get(i).getLinkmanid(), stuList.get(i).getNickname());
			}
    		return map;
		}
    	
    	return null;
    }
    
    /**
     * 用户个人详细信息
     * @return
     * @throws Exception
     */
    public String userInfo() throws Exception {
        if (StringUtil.isEmpty(userVo.getUSER_TYPE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(userVo.getLOGIN_NAME())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户名");

            return SUCCESS;
        }

        if (DictionaryConstant.USER_TEACHER.equals(userVo.getUSER_TYPE())) {
            return teacherInfo();
        }
        
        if (DictionaryConstant.USER_STUDENT.equals(userVo.getUSER_TYPE())) {
			return studentInfo();
		}

        return SUCCESS;
    }

    /**
     * 教师详细信息
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String teacherInfo() throws Exception {
        try {
            Teacher teacher = teacherService.selectTeacherByLogin(userVo.getLOGIN_NAME());
            Map map = new HashMap();
            map.put("loginName", teacher.getLoginname());
            map.put("name", teacher.getName());
            map.put("schoolName", teacher.getSchool().getName());
            map.put("score", teacher.getScore());
            map.put("faceImg", teacher.getFaceimg());
            map.put("mobile", teacher.getMobile());
            userVo.setReturnMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            userVo.setResultCode("-1");
            userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return SUCCESS;
    }
    
    /**
     * 家长详细信息
     * @return
     * @throws Exception
     */
    public String studentInfo()throws Exception{
    	try{
    	Student student = studentService.selectStudentByLogin(userVo.getLOGIN_NAME());
    	Map map = new HashMap();
    	map.put("loginName", student.getLoginname());
    	map.put("name", student.getName());
    	map.put("schoolName", student.getSchool().getName());
        map.put("faceImg", student.getFaceimg());
        map.put("mobile", student.getMobile());
    	userVo.setReturnMap(map);
    	}catch(Exception e){
    		e.printStackTrace();
    		userVo.setResultCode("-1");
            userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
    	}
    	return SUCCESS;
    }

    /**
     * 编辑别名
     * @return
     * @throws Exception
     */
    public String editNickName() throws Exception {
        if (StringUtil.isEmpty(userVo.getUSER_TYPE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(userVo.getUSER_ID())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户ID");

            return SUCCESS;
        }
        
        if (StringUtil.isEmpty(userVo.getLINK_MAN_ID())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：联系人ID");

            return SUCCESS;
        }
        if (StringUtil.isEmpty(userVo.getLINK_MAN_TYPE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：联系人类型");

            return SUCCESS;
        }

//        if (StringUtil.isEmpty(userVo.getNICK_NAME())) {
//            userVo.setResultCode("-1");
//            userVo.setResultDesc("缺少参数：用户昵称");
//
//            return SUCCESS;
//        }

        if (DictionaryConstant.USER_TEACHER.equals(userVo.getUSER_TYPE())) {
            return editTeacherNickName();
        }

        if (DictionaryConstant.USER_STUDENT.equals(userVo.getUSER_TYPE())) {
            return editStudentNickName();
        }

        return SUCCESS;
    }

    /**
     * 修改教师别名
     * @return
     */
    private String editTeacherNickName() throws Exception {
        boolean flag = teacherService.editNickName(userVo.getUSER_ID(), userVo.getUSER_TYPE(), userVo.getLINK_MAN_ID(), userVo.getLINK_MAN_TYPE(), userVo.getNICK_NAME());

        if (flag) {
            userVo.setResultCode("0");
            userVo.setResultDesc("别名修改成功");
        } else {
            userVo.setResultCode("-1");
            userVo.setResultDesc("修改别名出错，请稍后再试~！");
        }

        return SUCCESS;
    }

    /**
     * 修改学生别名
     * @return
     * @throws Exception
     */
    private String editStudentNickName() throws Exception {
//        boolean flag = studentService.editStudentNickName(userVo.getUSER_ID(),
//                userVo.getNICK_NAME());
        boolean flag = teacherService.editNickName(userVo.getUSER_ID(), userVo.getUSER_TYPE(), userVo.getLINK_MAN_ID(), userVo.getLINK_MAN_TYPE(), userVo.getNICK_NAME());
        if (flag) {
            userVo.setResultCode("0");
            userVo.setResultDesc("别名修改成功");
        } else {
            userVo.setResultCode("-1");
            userVo.setResultDesc("修改别名出错，请稍后再试~！");
        }

        return SUCCESS;
    }

    /**
     * 改变用户图像
     * @return
     * @throws Exception
     */
    public String updateFaceImg() throws Exception {
        if (StringUtil.isEmpty(userVo.getUSER_TYPE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(userVo.getLOGIN_NAME())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户名");

            return SUCCESS;
        }

        if (StringUtil.isEmpty(userVo.getFACE_IMG())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户图像");

            return SUCCESS;
        }

        if (DictionaryConstant.USER_TEACHER.equals(userVo.getUSER_TYPE())) {
            return updateTeacherFaceImg();
        }
        
        if (DictionaryConstant.USER_STUDENT.equals(userVo.getUSER_TYPE())) {
            return updateStudentFaceImg();
        }

        return SUCCESS;
    }

    /**
     * 更新教师图像
     * @return
     * @throws Exception
     */
    private String updateTeacherFaceImg() throws Exception {
        boolean flag = teacherService.updateFaceImg(userVo.getLOGIN_NAME(),
                userVo.getFACE_IMG());

        if (flag) {
            userVo.setResultCode("0");
            userVo.setResultDesc("更新成功");
        } else {
            userVo.setResultCode("-1");
            userVo.setResultDesc("修改图像出错，请稍后再试~！");
        }

        return SUCCESS;
    }
    
    /**
     * 更新家长图像
     * @return
     * @throws Exception
     */
    private String updateStudentFaceImg() throws Exception {
    	boolean flag = studentService.updateStudentFaceImg(userVo.getLOGIN_NAME(),
                userVo.getFACE_IMG());
    	if (flag) {
            userVo.setResultCode("0");
            userVo.setResultDesc("更新成功");
        } else {
            userVo.setResultCode("-1");
            userVo.setResultDesc("修改图像出错，请稍后再试~！");
        }

    	return SUCCESS;
    }
    
    /**
     * 获取图像
     * @return
     * @throws Exception
     */
    public String getTeacherImg() throws Exception{
    	if (StringUtil.isEmpty(userVo.getUSER_ID())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户ID");

            return SUCCESS;
        }
    	if (StringUtil.isEmpty(userVo.getUSER_TYPE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }
    	Map map = new HashMap();
    	if (DictionaryConstant.USER_TEACHER.equals(userVo.getUSER_TYPE())) {
    		 Teacher teacher = (Teacher)teacherService.getTeacherImg(userVo.getUSER_ID(),userVo.getUSER_TYPE());
    		 map.put("faceImg", teacher.getFaceimg());
    		 map.put("nickname", teacher.getNickname());
    		 userVo.setReturnMap(map);
		}
    	if (DictionaryConstant.USER_STUDENT.equals(userVo.getUSER_TYPE())) {
    		 Student student = (Student)teacherService.getTeacherImg(userVo.getUSER_ID(),userVo.getUSER_TYPE());
    		 map.put("faceImg", student.getFaceimg());
    		 map.put("nickname", student.getNickname());
    		 userVo.setReturnMap(map);
    	}
    	return SUCCESS;
    }
    
    /**
     * 根据短信码(smscode)获取教师ID,NAME,昵称,登录名
     * @return
     * @throws Exception
     */
    public String getTeainfoBySmscode()throws Exception{
    	if (StringUtil.isEmpty(userVo.getSMSCODE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：短信码");
            
            return SUCCESS;
        }
    	Map map = new HashMap();
    	try{
    	String smscode = userVo.getSMSCODE();
    	Teacher teacher = teacherService.getTeacherInfoBySmscode(smscode);
    	map.put("id", teacher.getId());
    	map.put("name", teacher.getName());
    	map.put("nickname", teacher.getNickname());
    	map.put("loginname", teacher.getLoginname());
    	userVo.setReturnMap(map);
    	}catch(Exception e){
    		e.printStackTrace();
    		userVo.setResultCode("-1");
    		userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
    	}
    	return SUCCESS;
    }
    
    /**
     * 根据手机号码获取学生ID,NAME,昵称,登录名
     * @return
     * @throws Exception
     */
    public String getStuinfoByMobile()throws Exception{
    	if (StringUtil.isEmpty(userVo.getMOBILE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：手机号码");
            
            return SUCCESS;
        }
    	Map map = new HashMap();
    	try{
    		String mobile = userVo.getMOBILE();
    		Student student = studentService.getStudentInfoBymobile(mobile);
    		map.put("id", student.getId());
        	map.put("name", student.getName());
        	//map.put("nickname", student.getNickname());
        	map.put("loginname", student.getLoginname());
        	userVo.setReturnMap(map);
    	}catch(Exception e){
    		e.printStackTrace();
    		userVo.setResultCode("-1");
    		userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
    	}
    	return SUCCESS;
    }
    
    /**
     * 根据教师手机号码获取教师ID,NAME
     * @return
     * @throws Exception
     */
    public String getTeaInfoByMobile()throws Exception{
    	if (StringUtil.isEmpty(userVo.getMOBILE())) {
            userVo.setResultCode("-1");
            userVo.setResultDesc("缺少参数：手机号码");
            
            return SUCCESS;
        }
    	try{
    	Map map = new HashMap();
    	
    	String mobile = userVo.getMOBILE();
    	Teacher teacher = teacherService.getTeacherInfoByMobile(mobile);
    	map.put("id", teacher.getId());
    	map.put("name", teacher.getName());
    	userVo.setReturnMap(map);
    	}catch(Exception e){
    		e.printStackTrace();
    		userVo.setResultCode("-1");
    		userVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
    	}
    	return SUCCESS;
    }
    
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    public void setClassesService(ClassesService classesService) {
        this.classesService = classesService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
