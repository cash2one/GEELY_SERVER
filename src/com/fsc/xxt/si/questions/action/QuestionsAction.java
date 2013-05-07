package com.fsc.xxt.si.questions.action;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.questions.service.QuestionsService;
import com.fsc.xxt.si.questions.vo.QuestionsVo;


public class QuestionsAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private QuestionsVo questionsvo = new QuestionsVo();
    private QuestionsService questionservice;

    @Override
    public BaseVo getModel() {
        return questionsvo;
    }

    public QuestionsService getQuestionservice() {
        return questionservice;
    }

    public void setQuestionservice(QuestionsService questionservice) {
        this.questionservice = questionservice;
    }
    
    /**
     * 意见反馈查询
     * @author: 刘源
     * @date：2012-5-16 上午11:00:13   
     * @return：String   
     * @throws: Exception
     */
    public String getquestionslist()throws Exception{
    	this.questionservice.selectPageData(questionsvo);
    	return "questionslist";
    }
    
    /**
     * 标记为已读
     * @author: 刘源
     * @date：2012-5-16 下午04:25:43   
     * @return：String   
     * @throws: Exception
     */
    public String setmyflag()throws Exception{
    	if ((questionsvo.getId() != null) && !"".equals(questionsvo.getId())) {
            String[] ids = questionsvo.getId().split(",");
            this.questionservice.setmyflag(ids);
        }
    	return "setmyflag";
    }
    
}
