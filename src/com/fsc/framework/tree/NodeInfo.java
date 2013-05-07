package com.fsc.framework.tree;

import java.util.HashMap;
import java.util.Map;
/**
*
* <p>Title: 校讯通手机服务端</p>
* <p>Description:树信息</p>
* <p>创建日期:2011-1-25</p>
* @author lcb
* @version 1.0
* <p>湖南家校圈科技有限公司</p>
* <p>http://www.139910.com/</p>
* <p>http://old.139910.com:8080/</p>
*/
public class NodeInfo {

    //默认map大小，一般为5
    public static final int DEFAULT_MAP_SIZE = 4;

    //节点Id值
    public static final String KEY_ID = "id";

    //节点文字
    public static final String KEY_TEXT = "text";

    //节点状态（开，闭）
    public static final String KEY_STATE = "state";

    //节点附加属性
    public static final String KEY_ATTR = "attributes";

    //节点url链接
    public static final String KEY_URL = "url";

    //节点年级
    public static final String KEY_GRADE = "grade";

    //节点父节点
    public static final String KEY_PNO = "pno";

    //节点类型
    public static final String KEY_TYPE = "type";
    
    //节点类型
    public static final String KEY_PATH = "path";

    //地区id
    public static final String KEY_KNOWLEDGE_ID = "kid";
    private Map item;

    public NodeInfo() {
        item = new HashMap(DEFAULT_MAP_SIZE);
    }

    /**
         * 设置基础参数
         * @param id
         * @param text
         * @param state
         */
    public void putKeys(String id, String text, String state) {
        item.put(KEY_ID, id);
        
        //item.put(KEY_TEXT, StringUtil.toLength(StringUtil.delHTML(text), 30));
        item.put(KEY_TEXT,text);
        item.put(KEY_STATE, state);
    }

    /**
         * 设置带年级的地区属性
         * @param url
         * @param grade
         * @param pno
         */
    public void putGradeAttr(String url, String grade, String pno,
        String kid, String type) {
        Map attr = new HashMap(3);
        attr.put(KEY_URL, url);
        attr.put(KEY_GRADE, grade);
        attr.put(KEY_PNO, pno);
        attr.put(KEY_TYPE, type);
        attr.put(KEY_KNOWLEDGE_ID, kid);
        item.put(KEY_ATTR, attr);
    }

    /**
         * 设置不带年级的地区属性
         * @param url
         * @param type
         * @param pno
         */
    public void putAttr(String url, String type, String pno, String kid,String path) {
        Map attr = new HashMap(3);
        attr.put(KEY_URL, url);
        attr.put(KEY_TYPE, type);
        attr.put(KEY_PNO, pno);
        attr.put(KEY_KNOWLEDGE_ID, kid);
        attr.put(KEY_PATH, path);
        item.put(KEY_ATTR, attr);
    }

    /**
     * 获取组件
     * @return
     */
    public Map getItem() {
        return item;
    }

}
