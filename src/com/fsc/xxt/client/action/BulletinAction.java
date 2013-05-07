package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.util.DateUtils2;
import com.fsc.util.StringUtil;

import com.fsc.xxt.client.vo.BulletinVo;
import com.fsc.xxt.si.bulletin.po.Bulletin;
import com.fsc.xxt.si.bulletin.service.BulletinService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:公告管理控制类</p>
 * <p>创建日期:Feb 10, 2012</p>
 * @author ZhouChao
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class BulletinAction extends ClientAction {
    /** <code>serialVersionUID</code> 的注释 */
    private static final long serialVersionUID = 2752706864967659353L;
    private BulletinVo bulletinVo = new BulletinVo();
    private BulletinService bulletinService;

    public void setBulletinService(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @Override
    public BaseVo getModel() {
        return bulletinVo;
    }

    /**
     * 最近公告信息
     * @return
     * @throws Exception
     */
    public String latestBulletin() throws Exception {
        if (StringUtil.isEmpty(bulletinVo.getUSER_TYPE())) {
            bulletinVo.setResultCode("-1");
            bulletinVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        try {
            Bulletin bulletin = bulletinService.selectLatestBulletin(bulletinVo.getUSER_TYPE());
            if (bulletin!=null) {
            	bulletinVo.setResultCode("0");
                bulletinVo.setResultData("title", bulletin.getTitle());
                bulletinVo.setResultData("content", bulletin.getContent());
                bulletinVo.setResultData("putUser", bulletin.getUserName());
                bulletinVo.setResultData("putTime",
                DateUtils2.formatDate3(bulletin.getPutTime()));
			}
        } catch (Exception e) {
            e.printStackTrace();
            bulletinVo.setResultCode("-1");
            bulletinVo.setResultDesc("程序出错，请联系管理员或稍后再试。");
        }

        return SUCCESS;
    }

    /**
     * 公告列表
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String bulletinList() throws Exception {
        if (StringUtil.isEmpty(bulletinVo.getUSER_TYPE())) {
            bulletinVo.setResultCode("-1");
            bulletinVo.setResultDesc("缺少参数：用户类型");

            return SUCCESS;
        }

        try {
            List<Bulletin> list = bulletinService.selectBulletinListByUserType(bulletinVo.getUSER_TYPE());
            List rList = new ArrayList();

            for (Bulletin b : list) {
                Map map = new HashMap();
                map.put("title", b.getTitle());
                map.put("content", b.getContent());
                map.put("putUser", b.getUserName());
                map.put("putTime", DateUtils2.formatDate3(b.getPutTime()));
                rList.add(map);
            }

            bulletinVo.setReturnList(rList);
            bulletinVo.setResultCode("0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "list";
    }
}
