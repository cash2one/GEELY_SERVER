package com.fsc.xxt.main.vo;

import com.fsc.framework.base.vo.BaseVo;

import java.util.List;


/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:系统主页桌面Vo</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class MainVo extends BaseVo {
    private List moduleList;

    public List getModuleList() {
        return moduleList;
    }

    public void setModuleList(List moduleList) {
        this.moduleList = moduleList;
    }
}
