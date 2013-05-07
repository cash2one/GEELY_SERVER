<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><link href="css/Default.css" type="text/css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-系统用户信息管理</title> 
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
$(document).ready(function(){
	tableTrColor();//Grid表格隔行加颜色
	searchPanel();//搜索栏控制函数
});
	
function checkAll(obj) {
	if ($(obj).attr("checked") == true) { // 全选			   
		$("input[type='checkbox']",$(obj).closest('table')).each(function() {
			$(this).attr("checked", true);
		});
	} else { // 取消全选
		$("input[type='checkbox']",$(obj).closest('table')).each(function() {
			$(this).attr("checked", false);
		});
	}
}

$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
	$.messager.alert('信息提示信息',message);
	}
});

function editInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要修改的用户。');
        return;
    }
    location = "user_edit.do?id="+v[0]+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>";
}

function lockInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要冻结的用户。');
        return;
    } 
    location = "user_lock.do?id="+v[0]+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>";
}

function unlockInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要解冻的用户。');
        return;
    } 
    location = "user_unlock.do?id="+v[0]+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>";
}

function roleSetInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要分配角色的用户。');
        return;
    } 
    location = "user_roleSet.do?id="+v[0]+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>";
}

function resetPassInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要重置密码的用户。');
        return;
    } 
    location = "user_resetPass.do?id="+v[0]+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>";
}

function queryRight(){
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要配置外呼查询权限的用户。');
        return;
    } 
    location = "user_queryRight.do?id="+v[0]+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>";
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
	}else{
	$.messager.alert('页码输入错误','请输入正确的页码。');
	flag=false;
	}
	if(currentPage==pageNo){
	flag=false;
	}
	if(flag){
	window.location="user_list.do?redirectSign=0&pageNo="+pageNo+"&orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>";
}
}
</script>
</head>
<body>
<s:form name="form1" id="form1" action="user_list.do" method="post">
<div class="searchPanel" style="margin-left:12px;">
    <div class="searchPanel-header">
    	<div class="searchPanel-title">&nbsp;搜索</div>
        <div class="searchPanel-icon"></div>
        <div class="searchPanel-tools">
        	<div class="searchPanel-expand"></div>
        </div>
    </div>
    <div class="searchPanel-body">
       <label>登录帐号:</label>
       <s:textfield name="login" />
       &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="image" src="images/BtnSearch_out.gif"  onmousemove="this.src='images/BtnSearch_hover.gif'" onmouseout="this.src='images/BtnSearch_out.gif'" style="margin-right: 25px;padding-right: 25px;"/>
    </div>
</div>
<!--Content start -->

<br>

<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
	        	<b><s:if test="module.moduleName!=''"><s:property value="module.moduleName"/></s:if></b>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="location='user_add.do?orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&pageNo=<s:property value="pageNo"/>'">新增用户</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="editInfo()">编辑用户</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="lockInfo()">冻结账号</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="unlockInfo()">解冻账号</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="roleSetInfo()">角色分配</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="resetPassInfo()">重置密码</a>
				</div>
        	</td>
      </tr>
    </thead>
</table>

<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
   <thead>
	  <tr>
		<th scope="col" style="width:3%;">
		<input type='checkbox' hidefocus='true' id='grid1_HeaderButton' name='grid1_HeaderButton' onclick='CheckAll(this,"trBg01","trBg02","trBgHover"  )' style='display:none;'>
		</th>
		<th scope="col">单位</th>
		<th scope="col">部门</th>
		<th scope="col">姓名</th>
		<th scope="col">登录帐号</th>
		<th scope="col">性别</th>
		<th scope="col">手机号码</th>
		<th scope="col">状态</th>
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
	    <td align="center"><s:property value="#var.orgName"/></td>
	    <td align="center"><s:property value="#var.deptName"/></td>
	    <td align="center"><s:property value="#var.name"/></td>
	    <td align="center"><s:property value="#var.login"/></td>
	    <td align="center">
			<s:if test="#var.genDer=='01'">男</s:if>
			<s:if test="#var.genDer=='02'">女</s:if>
		</td>
	    <td align="center"><s:property value="#var.mobile"/></td>
	    <td align="center">
	    	<s:iterator id="var2" value="statusList">
			<s:if test="#var2.dicCode==#var.status"><font color="<s:if test="#var2.dicCode=='01'">green</s:if><s:else>red</s:else>"><s:property value="#var2.dicName"/></font></s:if>
			</s:iterator>
	    </td>
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
        <a href="javascript:goPage(form1,'user_list.do?orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'user_list.do?orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'user_list.do?orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'user_list.do?orgNo=<s:property value="orgNo"/>&deptId=<s:property value="deptId"/>&redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
        </s:else>
		<input type="text" size="1" id="pageNo" style="height:18px;width:32px" /><strong><a onclick="turnTo();" href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong>	   
         <!--转到第<input name="pageNo" type="text" style="height:15px; width:30px;"/> 页-->
        <!--a href="javascript:gotoPage($('#form1'),'user_list.do?orgNo=<s:property value="orgNo"/>&deptNo=<s:property value="deptNo"/>&redirectSign=0&pageNo=',form1.pageNo.value,'<s:property value="totalPages"/>')">
        <img src="res/images/workspace/arrowRight.gif" width="9" height="8" align="middle" border="0" /> 转
        </a-->
     </td>
   </tr>
      </tfoot>
	</table>
</s:form>
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
</body>
</html>

