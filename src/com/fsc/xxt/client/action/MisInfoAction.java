package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.client.po.GeelyMisInfo;
import com.fsc.xxt.client.service.GeelyMisInfoService;
import com.fsc.xxt.client.vo.MisInfoVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MisInfoAction extends ClientAction {
    private static final long serialVersionUID = 1082978974382759700L;
    private MisInfoVo misInfoVo = new MisInfoVo();
    private GeelyMisInfoService geelyMisInfoService;

    @Override
    public BaseVo getModel() {
        return misInfoVo;
    }

    /**
     * 获取MIS生产信息
     * @return
     * @throws Exception
     */
    public String getMisInfo() throws Exception {
        try {
        	System.out.println("测试信息");
            List<Map> zzmcList = geelyMisInfoService.getZzmcList();
            List<Map> returnMap = new ArrayList<Map>();
            Map tempMap = null;

            for (Map map : zzmcList) {
                List<GeelyMisInfo> list = geelyMisInfoService.getMisInfoListByZzmcAndType(misInfoVo.getStartTime(),
                        misInfoVo.getEndTime(), misInfoVo.getMisType(),
                        map.get("ZZMC").toString());
                tempMap = new HashMap();
                tempMap.put("ZZMC", map.get("ZZMC"));
                tempMap.put("ZZDM", map.get("ZZDM"));
                tempMap.put("misList", list);
                returnMap.add(tempMap);
            }

            misInfoVo.setResultCode("200");
            misInfoVo.setResultDesc("获取数据成功");
            misInfoVo.setResultData("dataList", returnMap);
        } catch (Exception e) {
        	e.printStackTrace();
            misInfoVo.setResultCode("400");
            misInfoVo.setResultDesc("获取数据失败");
        }

        return SUCCESS;
    }

    public void setGeelyMisInfoService(GeelyMisInfoService geelyMisInfoService) {
        this.geelyMisInfoService = geelyMisInfoService;
    }
}
