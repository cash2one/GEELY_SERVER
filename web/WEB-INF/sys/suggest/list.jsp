<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><link href="css/Default.css" type="text/css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-问题反馈</title> 
<link href="themes/easyui.css" rel="stylesheet" type="text/css"/>
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script type="text/javascript"  language="javascript" src="js/Validate.js"></script>
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
<script language="javascript">
//跳转到指定页面
function turnTo(){
	var flag=true;
	var pageNo=document.getElementById('pageNo').value;
	var req=/^[0-9]*[1-9][0-9]*$/;
	var totalPages="<s:property value="totalPages"/>";
	var currentPage="<s:property value="pageNo"/>";
	if(req.test(pageNo)){
		var t=parseInt(totalPages,0);
		var p=parseInt(pageNo,0);
		if(p>t){
			flag=false;
			$.messager.alert('页码输入错误','请输入正确的页码。');
	 	}
	} else {
		$.messager.alert('页码输入错误','请输入正确的页码。');
		flag=false;
	}
	if(currentPage==pageNo){
		flag=false;
	}
	if(flag){
		window.location="suggest_list.do?redirectSign=0&pageNo="+pageNo;
	}
}

function editInfo(id) {
    location = "suggest_doForm.do?id="+id+"&pageNo=<s:property value="pageNo"/>&status=<s:property value="status"/>&qtype=<s:property value="qtype"/>";
}
</script>
</head>
<body>
<!--Content start -->
<br/>
<s:form name="form1" id="form1" action="suggest_list.do" method="post">
<s:hidden name="pageNo"></s:hidden>
<div class="searchPanel" style="margin-left:12px;">
    <div class="searchPanel-header">
    	<div class="searchPanel-title">搜索</div>
        <div class="searchPanel-icon"></div>
        <div class="searchPanel-tools">
        	<div class="searchPanel-expand"></div>
        </div>
    </div>
    <div class="searchPanel-body">
       <label>问题类别:</label>
       <s:select id="t_qtype" name="qtype" list="questionCodeList" headerKey="" headerValue="所有" listKey="dicCode" listValue="dicName"></s:select>
       &nbsp;&nbsp;&nbsp;&nbsp;
       <label>处理标志:</label>
       <s:select id="t_status" name="status" list="#{'':'所有','0':'未处理','1':'已处理'}"></s:select>
       <input type="image" src="images/BtnSearch_out.gif" align="right" onmousemove="this.src='images/BtnSearch_hover.gif'" onmouseout="this.src='images/BtnSearch_out.gif'" style="margin-right: 25px;padding-right: 25px;"/>
    </div>
</div>
<!--Content start -->
<br/>
<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
	<thead>
	  <tr>
		<th scope="col">反馈问题标题</th>
		<th scope="col" width="50%">反馈问题描述</th>
		<th scope="col">反馈时间</th>
		<th scope="col">反馈人</th>
		<th scope="col">问题类别</th>
		<th scope="col">状态</th>
		<th scope="col">&nbsp;</th>
	  </tr>
	</thead>
	<tbody>
	  <s:if test="list==null || list.size()==0">
	  <tr class="trBg01">
	    <td class="show" align="center" colspan="5">
	      <span KeyField="0">
	      	&nbsp;
	      </span>
	    </td>
	  </s:if>
	  <s:iterator id="var" value="list" status="vStatus">
	  <tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>">
	    <td align="center"><s:property value="#var.title"/> </td>
	    <td align="center"><s:property value="%{@com.fsc.util.StringUtil@delHTML(#var.content)}" escape="false"/> </td>
	    <td align="center"><s:date name="#var.putTime" format="yyyy-MM-dd"/> </td>
	    <td align="center"><s:property value="#var.putManName"/> </td>
	    <td align="center">
	     <s:if test="questionCodeList.size>0">
	    	<s:iterator id="var2" status="questionCodeListStatus" value="questionCodeList">
	    		<s:if test="#var.qtype==dicCode">
	    			<s:property value="dicName"/>
	    		</s:if>
	    	</s:iterator>
	    </s:if>
	   </td>
	   <td align="center">
	   	<s:if test="status==0"><font color="red">待解决</font></s:if>
	   	<s:if test="status==1"><font color="green">已解决</font></s:if>
	   </td>
	   <td><a id="bt_edit"  onclick="editInfo('<s:property value="id"/>')" class="easyui-linkbutton" plain="true" iconCls="icon-edit">处理</a></td>
	  </tr>
	  </s:iterator>
	 </tbody>
	  <tfoot>
       <tr align="right">
        <td colspan="10">
        <span>第<b><s:property value="pageNo"/></b>页 | </span>
        <span>共<b><s:property value="totalPages"/></b>页 | </span>
        <span>每页<b><s:property value="pageSize"/></b>行 | </span>
        <span>共<b><s:property value="totalRecords"/></b>行 | </span>
        <s:if test="pageNo==1">
		<a>首页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'suggest_list.do?redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'suggest_list.do?redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'suggest_list.do?redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'suggest_list.do?redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
        </s:else>
		<input type="text" size="1" id="pageNo" style="height:18px;width:32px" /><strong><a onclick="turnTo();" href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong></td>
        </tr>
      </tfoot>
	</table>
</s:form>
<!--Content end -->
<script language="javascript">
function goPage(form,url) {
	if (form==null) {
		location = url;
	} else {
		form.action = url;
		form.submit();
	}
}
</script>
<br />
</body>
</html>

