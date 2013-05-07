package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.StringUtil;

import com.fsc.xxt.basedata.sc.service.SegCourseService;
import com.fsc.xxt.client.vo.ExamVo;
import com.fsc.xxt.si.student.po.Student;
import com.fsc.xxt.si.student.service.StudentService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;
import com.fsc.xxt.sys.dic.po.Dictionary;
import com.fsc.xxt.sys.dic.service.DictionaryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:成绩管理action</p>
 * <p>创建日期:Feb 2, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ExamAction extends ClientAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = -1359570111912290911L;

    //数据字典服务层
    private DictionaryService dictionaryService;

    //学生信息服务层
    private StudentService studentService;

    //学段学科对应关系服务层
    private SegCourseService segCourseService;

    //成绩管理视图vo
    private ExamVo examVo = new ExamVo();

    @Override
    public BaseVo getModel() {
        return examVo;
    }

    /**
     * 考试列表
     * @return
     * @throws Exception
     */
    public String examNameList() throws Exception {
        try {
            List<Dictionary> list = dictionaryService.selectDictionaries(DictionaryConstant.EXAMTYPE);
            List rList = new ArrayList();

            for (Dictionary d : list) {
                Map map = new HashMap();
                map.put("examCode", d.getDicCode());
                map.put("examName", d.getDicName());
                rList.add(map);
            }

            examVo.setReturnList(rList);
        } catch (Exception e) {
            e.printStackTrace();
            examVo.setResultCode("-1");
            examVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return "list";
    }
    
    
    /**
     * 科目列表
     * @return
     * @throws Exception
     */
    public String courseList() throws Exception {
        if (StringUtil.isEmpty(examVo.getLINK_MAN_ID())) {
            examVo.setResultCode("-1");
            examVo.setResultDesc("缺少参数：用户ID");

            return SUCCESS;
        }

        try {
            Student student = studentService.findStudentById(examVo.getLINK_MAN_ID());
            int grade = new Integer(student.getGrade());
            String spart = "01";

            if ((grade > 6) && (grade < 10)) {
                spart = "02";
            } else if ((grade > 9) && (grade < 13)) {
                spart = "03";
            }
            
            List<Dictionary> dList = dictionaryService.selectDictionaries(DictionaryConstant.GRADE);
            for(Dictionary d:dList){
            	if(d.getDicCode().equals(student.getGrade())){ 
            		student.setGrade(d.getDicName());
            		break;
            	}
            }

            List<Dictionary> list = segCourseService.selectCourseBySegno(spart);
            List rList=new ArrayList();
            for(Dictionary d:list){
            	Map map=new HashMap();
            	map.put("courseCode", d.getDicCode());
            	map.put("courseName", d.getDicName());
            	rList.add(map);
            }
            Map map=new HashMap();
            map.put("courseList", rList);
            map.put("grade", student.getGrade());
            map.put("stdNo", student.getStdno());
            examVo.setReturnMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            examVo.setResultCode("-1");
            examVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return SUCCESS;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setSegCourseService(SegCourseService segCourseService) {
        this.segCourseService = segCourseService;
    }
}
