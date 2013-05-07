package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


public class MesInfoVo extends ClientVo {
    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //详细类别
    private String lb;

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

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
}
