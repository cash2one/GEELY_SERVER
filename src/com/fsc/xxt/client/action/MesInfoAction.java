package com.fsc.xxt.client.action;

import com.fsc.framework.base.action.ClientAction;
import com.fsc.framework.base.vo.BaseVo;

import com.fsc.xxt.client.service.GeelyMesInfoService;
import com.fsc.xxt.client.vo.MesInfoVo;

import java.util.List;
import java.util.Map;


public class MesInfoAction extends ClientAction {
    private static final long serialVersionUID = 1082978974382759700L;
    private MesInfoVo mesInfoVo = new MesInfoVo();
    private GeelyMesInfoService geelyMesInfoService;

    @Override
    public BaseVo getModel() {
        return mesInfoVo;
    }

    /**
     * 获取MES生产信息
     * @return
     * @throws Exception
     */
    public String getMesInfo() throws Exception {
        try {
            List<Map> list = geelyMesInfoService.selectMesInfo(mesInfoVo.getStartTime(),
                    mesInfoVo.getEndTime());
            mesInfoVo.setResultCode("200");
            mesInfoVo.setResultDesc("获取数据成功");
            mesInfoVo.setResultData("dataList", list);
        } catch (Exception e) {
            mesInfoVo.setResultCode("400");
            mesInfoVo.setResultDesc("获取数据失败");
        }

        return SUCCESS;
    }

    /**
     * 获取MES生产详细信息
     * @return
     * @throws Exception
     */
    public String getMesDetailInfo() throws Exception {
        try {
            List<Map> list = geelyMesInfoService.selectMesDetailInfo(mesInfoVo.getStartTime(),
                    mesInfoVo.getEndTime(), mesInfoVo.getLb());
            mesInfoVo.setResultCode("200");
            mesInfoVo.setResultDesc("获取数据成功");
            mesInfoVo.setResultData("dataList", list);
        } catch (Exception e) {
            mesInfoVo.setResultCode("400");
            mesInfoVo.setResultDesc("获取数据失败");
        }

        return SUCCESS;
    }

    public void setGeelyMesInfoService(GeelyMesInfoService geelyMesInfoService) {
        this.geelyMesInfoService = geelyMesInfoService;
    }
}
