package com.fsc.framework.tree;

import java.util.List;
import java.util.Map;

/**
*
* <p>Title: 校讯通手机服务端</p>
* <p>Description:解析节点接口类</p>
* <p>创建日期:2011-1-26</p>
* @author lcb
* @version 1.0
* <p>湖南家校圈科技有限公司</p>
* <p>http://www.139910.com/</p>
* <p>http://old.139910.com:8080/</p>
*/
public interface NodeParser {
   public static final String STATE_CLOSED = "closed";
   public static final String STATE_OPEN = "open";

   /**
    * 解析节点参数
    * @param params
    * @return
    * @throws Exception
    */
   public List parseParams(Param params,Map paramMap) throws Exception;
}

