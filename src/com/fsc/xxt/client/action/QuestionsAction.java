package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;
import com.fsc.util.StringUtil;
import com.fsc.xxt.client.vo.QuestionsVo;
import com.fsc.xxt.si.questions.service.QuestionsService;


/**
*
* <p>Title:校讯通手机服务端</p>
* <p>Description:问题反馈action</p>
* <p>创建日期:Feb 3, 2012</p>
* @author ZhouChao
* @version 1.0
* <p>湖南家校圈科技有限公司</p>
* <p>http://www.139910.com</p>
* <p>http://wps.139910.com</p>
*/

public class QuestionsAction extends ClientAction{
	private static final long serialVersionUID = 1L;
	private QuestionsVo questionsVo = new QuestionsVo();
	private QuestionsService questionservice;
	
	public QuestionsService getQuestionservice() {
		return questionservice;
	}


	public void setQuestionservice(QuestionsService questionservice) {
		this.questionservice = questionservice;
	}


	@Override
    public BaseVo getModel() {
        return questionsVo;
    }
	
	
	 /**
     * 保存问题反馈信息
     * @return
     * @throws Exception
     */
	public String SaveQuestion()throws Exception{
		if (StringUtil.isEmpty(questionsVo.getCONTENT())) {
			questionsVo.setResultCode("-1");
			questionsVo.setResultDesc("缺少参数：问题反馈内容");
			return SUCCESS;
		 }
		if (StringUtil.isEmpty(questionsVo.getSUBMISSION())) {
			questionsVo.setResultCode("-1");
			questionsVo.setResultDesc("缺少参数：问题反馈人");
			return SUCCESS;
		 }
		
		String content  = questionsVo.getCONTENT();
		String submission = questionsVo.getSUBMISSION();
		this.questionservice.SaveQuestionInfo(content,submission);
		questionsVo.setResultCode("0");
		questionsVo.setResultDesc("操作成功");
		return SUCCESS;
		
	}
	
}
