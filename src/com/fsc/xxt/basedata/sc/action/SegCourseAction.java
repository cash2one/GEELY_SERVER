package com.fsc.xxt.basedata.sc.action;

import java.util.List;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.StringUtil;
import com.fsc.xxt.basedata.sc.service.SegCourseService;
import com.fsc.xxt.basedata.sc.vo.SegCourseVo;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;
import com.fsc.xxt.sys.dic.service.DictionaryService;

/**
 * 
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:学科学段关联控制类/p>
 * <p>创建日期:2010-12-23</p>
 * @author lcb
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class SegCourseAction extends ManageAction{
	private SegCourseVo segCourseVo;
	//数据字典信息服务
    private DictionaryService dictionaryService;
    
    private SegCourseService segCourseService;
	
	public SegCourseAction(){
		segCourseVo=new SegCourseVo();
	}
	@Override
    public BaseVo getModel() {
        return segCourseVo;
    }

    /**
     * 题库信息管理框架
     * @return
     */
    public String frame() {
        return "frame";
    }

    /**
     * 树型列表
     * @return
     * @throws Exception
     */
    public String tree() throws Exception {
    	//学段信息
    	List list=dictionaryService.selectDictionaries(DictionaryConstant.SPART);
    	segCourseVo.setSpartList(list);
        return "tree";
    }
    
    /**
     *	学科列表
     */
    public String list() throws Exception{
    	List list=segCourseService.selectSegCourseBySegno(segCourseVo.getSpart());
    	segCourseVo.setScList(list);
    	
    	 //学科信息
        list=dictionaryService.selectDictionaries(DictionaryConstant.COURSE);
        segCourseVo.setCourseList(list);
    	return "list";
    }
    
    /**
     * 通过学段获得学科信息
     * @return
     * @throws Exception
     */
    public String getCourse() throws Exception{
    	String segno=segCourseVo.getSpart();
    	List list;
		if (!StringUtil.isEmpty(segno)) {
			list = segCourseService.selectCourseBySegno(segno);
		}else {
			list=dictionaryService.selectDictionaries(DictionaryConstant.COURSE);
		}
    	segCourseVo.setCourseList(list);
    	return SUCCESS;
    }
    
    /**
     * 通过学段获得学科信息
     * @return
     * @throws Exception
     */
    public String getCourseBySegnos() throws Exception{
    	List list=segCourseService.selectCourseBySegnos(segCourseVo.getSpart());
    	segCourseVo.setCourseList(list);
    	return SUCCESS;
    }
    
    /**
     * 保存对应关系
     * @return
     * @throws Exception 
     */
    public String save() throws Exception{
    	
    	segCourseService.updateSegCourse(segCourseVo.getIds(), segCourseVo.getSpart());
    	
    	return SUCCESS;
    }
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	public SegCourseService getSegCourseService() {
		return segCourseService;
	}
	public void setSegCourseService(SegCourseService segCourseService) {
		this.segCourseService = segCourseService;
	}
    
}
