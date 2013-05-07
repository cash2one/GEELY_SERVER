package com.fsc.framework.tree;

import java.util.List;
import java.util.Map;

import com.fsc.util.StringUtil;

/**
 * <p>Title: 校讯通手机服务端</p>
 * <p>Description:树解析</p>
 * <p>创建日期:Jul 14, 2011</p>
 * @author tbw
 * @version 1.0 
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class TreeParser {
	//默认解析器
	private NodeParser defaultParser;
	//解析器存放
	private Map parserMap;
	
	/**
     * 获取权限树节点信息
     * @return
     * @throws Exception
     */
    public List selectTree(String params,Map paramMap) throws Exception {
        List list = null;
        
        //如果参数为空则为顶级节点
        if (StringUtil.isEmpty(params)) {
        	Param param = new Param();
            list = defaultParser.parseParams(param,paramMap);
        } else {
        	//分解参数
            Param param = TreeUtil.analyzeParam(params);
            //获取解析器
            NodeParser nodeParser = (NodeParser) parserMap.get(param.getType());
            //无法获取解析器返回空
            if(null!=nodeParser){
            	list = nodeParser.parseParams(param,paramMap);
            }
        }

        return list;
    }

	public NodeParser getDefaultParser() {
		return defaultParser;
	}

	public void setDefaultParser(NodeParser defaultParser) {
		this.defaultParser = defaultParser;
	}

	public Map getParserMap() {
		return parserMap;
	}

	public void setParserMap(Map parserMap) {
		this.parserMap = parserMap;
	}
}
