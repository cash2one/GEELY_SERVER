package com.fsc.xxt.si.questions.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.util.Date;


public class QuestionsVo extends BaseVo {
    /**  主键*/
    private String id;

    /**  反馈内容*/
    private String content;

    /** 提交人 */
    private String submission;

    /** 提交时间 */
    private Date submi_time;

    /** 处理状态 */
    private String hanflag;
    
    /** 开始时间 */
    private String startime;

    /** 截止时间 */
    private String endtime;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public Date getSubmi_time() {
        return submi_time;
    }

    public void setSubmi_time(Date submi_time) {
        this.submi_time = submi_time;
    }

    public String getHanflag() {
        return hanflag;
    }

    public void setHanflag(String hanflag) {
        this.hanflag = hanflag;
    }

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
    
    
}
