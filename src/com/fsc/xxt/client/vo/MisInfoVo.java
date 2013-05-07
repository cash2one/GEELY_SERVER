package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


public class MisInfoVo extends ClientVo {
    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //mis类型
    private String misType;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMisType() {
        return misType;
    }

    public void setMisType(String misType) {
        this.misType = misType;
    }
}
