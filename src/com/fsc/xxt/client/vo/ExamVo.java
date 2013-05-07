package com.fsc.xxt.client.vo;

import com.fsc.framework.base.vo.ClientVo;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:成绩管理视图vo</p>
 * <p>创建日期:Feb 2, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ExamVo extends ClientVo {
    private String LOGIN_NAME;
    private String LINK_MAN_ID;

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public void setLOGIN_NAME(String login_name) {
        LOGIN_NAME = login_name;
    }

    public String getLINK_MAN_ID() {
        return LINK_MAN_ID;
    }

    public void setLINK_MAN_ID(String link_man_id) {
        LINK_MAN_ID = link_man_id;
    }
}
