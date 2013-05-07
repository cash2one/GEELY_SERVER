package com.fsc.xxt.si.msg.action;

import com.fsc.framework.base.action.ManageAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.si.msg.service.MsgService;
import com.fsc.xxt.si.msg.vo.MsgVo;


public class MsgAction extends ManageAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 1L;
    private MsgVo msgvo = new MsgVo();
    private MsgService msgService;

    public MsgService getMsgService() {
        return msgService;
    }

    public void setMsgService(MsgService msgService) {
        this.msgService = msgService;
    }
    
    @Override
    public BaseVo getModel() {
        return msgvo;
    }
    
    /**
     * 跳转
     * @author: 刘源
     * @date：2012-5-15 下午05:02:59   
     * @return：String   
     * @throws: Exception
     */
    public String getmsglist()throws Exception{
    	this.msgService.selectPageData(msgvo);
    	return "msglist";
    }
    
    
}
