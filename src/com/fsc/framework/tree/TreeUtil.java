package com.fsc.framework.tree;

import com.fsc.util.StringUtil;

/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:树工具类</p>
 * <p>创建日期:Jul 14, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class TreeUtil {
	//id字段分隔符
    public static final String SPLIT_SYMBOL = ",";
	/**
     * 解析参数 参数格式
     * id,类型,父节点,需要忽略的id
     * @param params
     * @return
     */
    public static Param analyzeParam(String params) {
        String[] p = params.split(SPLIT_SYMBOL);
        Param param = new Param();
        param.setId((p[0].trim().length() == 0) ? null : p[0]);
        param.setType((p[1].trim().length() == 0) ? null : p[1]);
        param.setPno((p[2].trim().length() == 0) ? null : p[2]);
        param.setIgnoreId((p[3].trim().length() == 0) ? null : p[3]);
        return param;
    }

    /**
     * 创建id逗号分隔字符串
     * @param id
     * @param type
     * @param pno
     * @param ignoreId
     * @return
     */
    public static String createIdInfo(String id, String type, String pno,
        String ignoreId) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(StringUtil.isEmpty(id) ? " " : id);
        sb.append(SPLIT_SYMBOL);
        sb.append(StringUtil.isEmpty(type) ? " " : type);
        sb.append(SPLIT_SYMBOL);
        sb.append(StringUtil.isEmpty(pno) ? " " : pno);
        sb.append(SPLIT_SYMBOL);
        sb.append(StringUtil.isEmpty(ignoreId) ? " " : ignoreId);
        sb.append(SPLIT_SYMBOL);
        return sb.toString();
    }
    
    /**
     * 根据父路径和当前节点名创建当前；路径
     * @return
     */
    public static String createPath(String path, String codename) {
         return path + ">" + codename;
     }
    
    public static String getRadioHTML(String text,String value,String path,String codeno) {
    	String text1=text==null?"":text;
    	String value1=value==null?"":value;
    	String path1=path==null?"":path;
    	String codeno1=codeno==null?"":codeno;
    	return "<input name='rdgroup' type='radio' value='" +
           value1 + "' path='" + path1 +
           "' codeno='" + codeno1 +
           "' onclick='clickChk(this)'/>" + text1;
	}
}
