package com.fsc.xxt.si.msg.po;

import java.util.Date;

import com.fsc.framework.base.po.Base;

public class ClassMsg extends Base {

	//-- Filed Region Begin --
	
	/** 主键 */
	private String id;
	
	/** 班级ID */
	private String classId;
	
	/** 教师ID */
	private String teacherId;
	
	/** 消息内容 */
	private String content;
	
	/** 发送时间 */
	private Date sendTime;
	
	/** 删除标识 */
	private String delFlag;
	
	/** 消息类别 */
	private String msgType;
	
	//-- Filed Region End --

	//-- Method Region Begin --

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	
	
	//-- Method Region End --
	
	
	
}
