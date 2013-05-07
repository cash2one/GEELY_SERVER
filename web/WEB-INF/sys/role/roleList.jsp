<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-系统角色管理</title> 
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="themes/icon.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
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
	
	var message = "<s:property value="message"/>";
	if (message!="") {
		$.messager.alert('信息提示信息',message);
	}
});

function editInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
    	$.messager.alert('未选中记录','请先选中需要修改的信息。');
        return;
    }
    location = "role_edit.do?roleId="+v[0]+"&pageNo=<s:property value="pageNo"/>";
}

function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
    	$.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除该条数据？', function(r){
    	if(r){
    		location = "role_delete.do?roleId="+v[0]+"&pageNo=<s:property value="pageNo"/>";
    	}
    });	
}

function privsetInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要分配权限的角色。');
        return;
    } 
    location = "role_privSet.do?roleId="+v[0]+"&pageNo=<s:property value="pageNo"/>";
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
		window.location="role_list.do?redirectSign=0&pageNo="+pageNo;
	}
}
</script>
</head>
<body>
<br/>
<!--Content start -->
<s:form name="form1" id="form1" action="role_list.do" method="post">
<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
			      <a id="bt_add" href="role_add.do?pageNo=<s:property value="pageNo"/>" class="easyui-linkbutton" plain="true"  iconCls="icon-add">新增角色</a>
			      <a id="bt_edit"  onclick="editInfo()" class="easyui-linkbutton" plain="true" iconCls="icon-edit">编辑角色</a>
			      <a id="bt_delete"  onclick="deleteInfo()" class="easyui-linkbutton" plain="true" iconCls="icon-remove">删除角色</a>
			      <a id="bt_privset"  onclick="privsetInfo()" class="easyui-linkbutton" plain="true" iconCls="icon-redo">权限分配</a>
				</div>
        	</td>
      </tr>
    </thead>
</table>
<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
  <tr>
	<th scope="col" style="width:3%;">
	<input type='checkbox' hidefocus='true' id='grid1_HeaderButton' name='grid1_HeaderButton' onclick='CheckAll(this,"trBg01","trBg02","trBgHover"  )' style='display:none;'>
	</th>
	<th scope="col" style="width:27%;">角色名称</th>
	<th scope="col" style="width:70%;">角色描述</th>
  </tr>
  <s:if test="list==null || list.size()==0">
  <tr class="trBg01" onclick="RowMouseClick(this,&quot;grid1_HeaderButton&quot;);">
    <td class="show" align="center" colspan="10">
      <span KeyField="0">
      	&nbsp;
      </span>
    </td>
  </tr>
  </s:if>
  <s:iterator id="var" value="list" status="vStatus">
  <tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>" onclick="RowMouseClick(this,&quot;grid1_<s:property value="#vStatus.count"/>_CheckBoxButton&quot;);">
    <td class="show" align="center">
      <span KeyField="<s:property value="#var.roleId"/>">
      <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" />
      </span>
    </td>
    <td align="center"><s:property value="#var.roleName"/></td>
    <td align="center"><s:property value="#var.roleDesc"/></td>
  </tr>
  </s:iterator>
</table>
    
<br/>
<table width="96%" class="tableListBorderNone">
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
        <a href="javascript:goPage(form1,'role_list.do?redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'role_list.do?redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'role_list.do?redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'role_list.do?redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
        </s:else>
		<input type="text" size="1" id="pageNo" style="height:18px;width:32px" /><strong><a onclick="turnTo();" href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong>	   
         <!--转到第<input name="pageNo" type="text" style="height:15px; width:30px;"/> 页-->
        <!--a href="javascript:gotoPage($('#form1'),'info_list.do?itemId=<s:property value="itemId"/>redirectSign=0&pageNo=',form1.pageNo.value,'<s:property value="totalPages"/>')">
        <img src="res/images/workspace/arrowRight.gif" width="9" height="8" align="middle" border="0" /> 转
        </a-->
     </td>
   </tr>
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

