package com.fsc.xxt.client.vo;

import java.util.Date;

import com.fsc.framework.base.vo.ClientVo;

public class QuestionsVo extends ClientVo {
	private String ID;
	private String CONTENT;
	private String SUBMISSION;
	private Date SUBMI_TIME;
	private String HANFLAG;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getSUBMISSION() {
		return SUBMISSION;
	}

	public void setSUBMISSION(String sUBMISSION) {
		SUBMISSION = sUBMISSION;
	}

	public Date getSUBMI_TIME() {
		return SUBMI_TIME;
	}

	public void setSUBMI_TIME(Date sUBMITIME) {
		SUBMI_TIME = sUBMITIME;
	}

	public String getHANFLAG() {
		return HANFLAG;
	}

	public void setHANFLAG(String hANFLAG) {
		HANFLAG = hANFLAG;
	}

}
