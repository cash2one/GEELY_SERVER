<%@ page contentType="text/html;charset=UTF-8"%> 

<table width="100%" class="tableListBorderNone">
  <tr>
    <td width="100%" align="right" valign="top" class="textListBottom">
    	<span>第<b><s:property value="pageNo"/></b>页 | </span>
        <span>共<b><s:property value="totalPages"/></b>页 | </span>
        <span>每页<b><s:property value="pageSize"/></b>行 | </span>
        <span>共<b><s:property value="totalRecords"/></b>行 | </span>
        <s:if test="pageNo==1">
		<a>首页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form_<s:property value="listType"/>,'redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form_<s:property value="listType"/>,'redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form_<s:property value="listType"/>,'redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form_<s:property value="listType"/>,'redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
        </s:else>
		<input type="text" size="1" id="pageNo_<s:property value="listType"/>" style="height:18px;width:32px" /><strong>
		<a onclick='turnTo(form_<s:property value="listType"/>,"pageNo_<s:property value="listType"/>","<s:property value="totalPages"/>","<s:property value="pageNo"/>");' href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong>	   
         
     </td>
   </tr>
</table>