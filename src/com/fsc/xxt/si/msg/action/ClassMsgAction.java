package com.fsc.xxt.si.msg.action;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.msg.service.MsgService;
import com.fsc.xxt.si.msg.vo.ClassMsgVo;


public class ClassMsgAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private ClassMsgVo classmsgvo = new ClassMsgVo();
    private MsgService msgService;

    @Override
    public BaseVo getModel() {
        return classmsgvo;
    }

    public MsgService getMsgService() {
        return msgService;
    }

    public void setMsgService(MsgService msgService) {
        this.msgService = msgService;
    }
    
    public String getclassmsglist()throws Exception{
    	this.msgService.selectPageData(classmsgvo);
    	return "classmsglist";
    }
}
