<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-公告管理</title> 
<link href="themes/easyui.css" rel="stylesheet" type="text/css"/>
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
<script language="javascript">
$(document).ready(function(){
		tableTrColor();//Grid表格隔行加颜色
		searchPanel();//搜索栏控制函数
});
function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
       $.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除选中的数据?', function(r){
		if (r){
		var a = "";
			for(var i =0;i<v.length;i++){
				if(i==v.length-1){
				  a+=v[i];
				}else{
					a+=v[i]+","
				}
				 
			}
			location = "bulletin_delbulletin.do?id="+a+"&pageNo=<s:property value="pageNo"/>";
		}
	});
}

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
		window.location="sysLog_list.do?redirectSign=0&pageNo="+pageNo+"&stime=<s:property value="stime" />&etime=<s:property value="etime"/>";
	}
}

//查询
function seacher(){
	var vtitle= $("#title").val();
	var vusertype = $("#userType").val();
	var vflag = $("#flag").val();
	window.location="bulletin_getbulletinlist.do?title="+vtitle+"&userType="+vusertype+"&flag="+vflag;
}

//输入框重置
function resert(){
	$("#title").val("");
	$("#userType").val('');
	$("#flag").val('');
}

//修改
function editpro(){
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
       $.messager.alert('未选中记录','请先选中需要修改的信息。');
        return;
    } 
    if(v.length > 1){
    	$.messager.alert('修改错误','每次只能修改一条产品信息');
    	return;
    }
     var url ="bulletin_edibulletin.do?id="+v[0];
     window.location=url;
}

//清空日志信息
function clearLog(){
	$.messager.confirm('确定操作', '你确定要清空日志信息？', function(r){
	   if(r){
	   	window.location="sysLog_clear.do";
	   }
	});	
}
</script>
</head>
<body>
<br/>

<s:form name="form1" id="form1" action="sysLog_list.do" method="post">
<s:hidden name="pageNo"></s:hidden>
<!--Content start -->
<div class="searchPanel" style="margin-left:12px;">
    <div class="searchPanel-header">
    	<div class="searchPanel-title">搜索</div>
        <div class="searchPanel-icon"></div>
        <div class="searchPanel-tools">
        	<div class="searchPanel-expand"></div>
        </div>
    </div>
    <div class="searchPanel-body">
		用户类型： <s:select id="userType" name="userType" list="#{'':'请选择用户类型','01':'教师','02':'家长'}"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
		发布状态： <s:select id="flag" name="flag" list="#{'':'请选择发布状态','01':'未发布','02':'已发布'}"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
		公告标题：<input type="text" id="title" name="title"/>&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href='#' class="easyui-linkbutton" iconCls="icon-search" onclick="seacher()">查询</a>
	    <a href='#' id="myresert" name="myresert" class="easyui-linkbutton" iconCls="icon-reload" onclick="resert()">重置</a>
    </div>
</div>

<br/>

<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
	        	<b><s:if test="module.moduleName!=''"><s:property value="module.moduleName"/></s:if></b>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="location='bulletin_newbulletin.do'">新公告信息发布</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="editpro()">公告信息修改</a> 				
				<a href="#" class="easyui-linkbutton" id="bt_delete"  onclick="deleteInfo()" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">公告信息删除</a>
				</div>
        	</td>
      </tr>
    </thead>
</table>
<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
	<thead>
	  <tr>
		<th scope="col" style="width:3%;"></th>
		<th scope="col">公告标题</th>
		<th scope="col" style="width:50%;">公告内容</th>
		<th scpoe="col">用户类型</th>
		<th scope="col">发布时间</th>
		<th scope="col">发布状态</th>
	  </tr>
	  </thead>
	  <tbody>
	  <s:if test="list==null || list.size()==0">
	  <tr class="trBg01" onclick="RowMouseClick(this,&quot;grid1_HeaderButton&quot;);">
	    <td class="show" align="center" colspan="10">
	      <span KeyField="0">
	      	&nbsp;
	      </span>
	    </td>
	  </s:if>
	  <s:iterator id="var" value="list" status="vStatus">
	   <tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>" onclick="RowMouseClick(this,&quot;grid1_<s:property value="#vStatus.count"/>_CheckBoxButton&quot;);">
	    <td class="show" align="center">
	      <span KeyField="<s:property value="#var.id"/>">
	      <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" />
	      </span>
	    </td>
	    <td align="center"><s:property value="#var.title"/> </td>
	    <td align="center"><s:property value="#var.content"/></td>
	    <td align="center">
	    	<s:if test="#var.userType=='01'">
	    		教师
	    	</s:if>
	    	<s:if test="#var.userType=='02'">
	    		家长
	    	</s:if>
	    </td>
	    <td align="center"><s:date name="#var.putTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	    <td align="center">
	    	<s:if test="#var.flag=='01'">
	    		未发布
	    	</s:if>
	    	<s:if test="#var.flag=='02'">
	    		已发布
	    	</s:if>
	    </td>
	  </tr>
	  </s:iterator>
	  </tbody>
	  <tfoot>
       <tr align="right">
          <td colspan="7">
          <span>第<b><s:property value="pageNo"/></b>页 | </span>
        <span>共<b><s:property value="totalPages"/></b>页 | </span>
        <span>每页<b><s:property value="pageSize"/></b>行 | </span>
        <span>共<b><s:property value="totalRecords"/></b>行 | </span>
        <s:if test="pageNo==1">
		<a>首页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'sysLog_list.do?redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'sysLog_list.do?redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'sysLog_list.do?redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'sysLog_list.do?redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
        </s:else>
		<input type="text" size="1" id="pageNo" style="height:18px;width:32px" /><strong><a onclick="turnTo();" href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong></td>
        </tr>
    </tfoot>
</table>
<br/>
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

