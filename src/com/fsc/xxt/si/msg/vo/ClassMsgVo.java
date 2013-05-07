package com.fsc.xxt.si.msg.vo;

import com.fsc.framework.base.vo.BaseVo;
import com.fsc.framework.constant.CommonConstants;
import com.fsc.xxt.si.constant.MsgConstant;

import java.util.Date;


/**
 * 
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:添加类描述信息</p>
 * <p>创建日期:2012-5-16</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ClassMsgVo extends BaseVo {
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
	
	
    
}
