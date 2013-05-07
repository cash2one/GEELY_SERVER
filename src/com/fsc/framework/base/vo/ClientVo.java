package com.fsc.framework.base.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title:校讯通手机服务端</p>
 * <p>Description:手机客户端交互VO</p>
 * <p>创建日期:Dec 29, 2011</p>
 * @author tbw
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com</p>
 * <p>http://wps.139910.com</p>
 */
public class ClientVo extends BaseVo {
    private Map<String, Object> returnMap = new HashMap<String, Object>();
    private List<Object> returnList = new ArrayList<Object>();

    public List getReturnList() {
        return returnList;
    }

    public void setReturnList(List returnList) {
        this.returnList = returnList;
    }

    public void addListData(Object val) {
        returnList.add(val);
    }

    public Map<String, Object> getReturnMap() {
        return returnMap;
    }

    public void setReturnMap(Map<String, Object> returnMap) {
        this.returnMap = returnMap;
    }

    public void setResultCode(String resultCode) {
        returnMap.put("RESULTCODE", resultCode);
    }

    public void setResultDesc(String resultDesc) {
        returnMap.put("RESULTDESC", resultDesc);
    }

    public void setMessageNo(String messageNo) {
        returnMap.put("MESSAGENO", messageNo);
    }

    public void setResultData(String key, Object val) {
        returnMap.put(key, val);
    }
}
