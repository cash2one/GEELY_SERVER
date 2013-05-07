package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.po.MobileUser;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.DateUtils2;
import com.fsc.util.StringUtil;

import com.fsc.xxt.client.vo.MainVo;
import com.fsc.xxt.si.msg.po.Msg;
import com.fsc.xxt.si.msg.service.MsgService;
import com.fsc.xxt.sys.dic.constant.DictionaryConstant;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:平台主界面Action</p>
 * <p>创建日期:Dec 22, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class MainAction extends ClientAction {
    private MainVo mainVo = new MainVo();
    private MsgService msgService;

    public void setMsgService(MsgService msgService) {
        this.msgService = msgService;
    }

    @Override
    public BaseVo getModel() {
        return mainVo;
    }

    /**
     * 主界面入口
     * @return
     * @throws Exception
     */
    public String main() throws Exception {
        // Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

        // 强制参数判定
        // 用户类型
        if (StringUtil.isEmpty(mainVo.getUSER_TYPE())) {
            mainVo.setResultCode("-1");
            mainVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        // Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
        if (DictionaryConstant.USER_TEACHER.equals(mainVo.getUSER_TYPE())) {
            return teacherMain();
        }

        return null;
    }

    /**
     * 教师主界面入口
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	private String teacherMain() throws Exception {
        // Carfield 2012-01-13 [Add] 增加参数验证判定 [Start]

        // 强制参数判定
        // 用户名
        if (StringUtil.isEmpty(mainVo.getLOGIN_NAME())) {
            mainVo.setResultCode("-1");
            mainVo.setResultDesc("缺少参数：用户名");

            return SUCCESS;
        }

        // Carfield 2012-01-13 [Add] 增加参数验证判定 [End]
        MobileUser teacher = (MobileUser) getUserByLogin(mainVo.getUSER_TYPE(),
                mainVo.getLOGIN_NAME());
        List list = msgService.selectLinkMan(teacher.getId(),
                mainVo.getUSER_TYPE(), 20);

        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            String link_man_id = (String) map.get("LINK_MAN_ID");
            String link_man_type = (String) map.get("LINK_MAN_TYPE");
            List msgList = msgService.selectLinkManMsg(teacher.getId(),
                    mainVo.getUSER_TYPE(), link_man_id, link_man_type, 1);

            if ((null != msgList) && (msgList.size() > 0)) {
                Msg msg = (Msg) msgList.get(0); 

                map.put("MESSAGE_CONTENT", msg.getContent());
                map.put("MESSAGE_STATE", msg.getReadflag());

                Date motime = msg.getMotime();
                map.put("DAY", DateUtils2.getDayOfWeek(motime));
                mainVo.addListData(map);

                Integer unReadCount = msgService.selectMsgCount(teacher.getId(),
                        teacher.getUserType(), link_man_id, link_man_type, "01");
                map.put("MESSAGE_NUM", unReadCount);
            }
        }

        return "list";
    }
}
