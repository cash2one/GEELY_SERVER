<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-系统功能权限信息管理</title> 
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css"> 
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
<script type="text/javascript"  language="javascript" src="js/Validate.js"></script>
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

function editInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	    $.messager.alert('未选中记录','请先选中需要修改的记录。');
        return;
    }
    location = "privilege_edit.do?privId="+v[0]+"&moduleId=<s:property value="moduleId"/>";
}

function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	$.messager.alert('未选中记录','请先选中需要删除的记录。');
        return;
    } 
   $.messager.confirm('确定操作', '你确定要删除该条数据?', function(r){
			if (r){
			location = "privilege_delete.do?privId="+v[0]+"&moduleId=<s:property value="moduleId"/>";
			}
		});
}
</script>
</head>
<body>
<br/>
<!--Content start -->
<s:form name="form1" id="form1" action="dept_list.do" method="post">
<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
	        	<b><s:if test="module.moduleName!=''"><s:property value="module.moduleName"/></s:if></b>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="location='privilege_add.do?moduleId=<s:property value="moduleId"/>'">新增信息</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="editInfo()">编辑信息</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="deleteInfo()">删除信息</a> 
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
		<th scope="col">所属模块类名</th>
		<th scope="col">模块描述</th>
		<th scope="col">功能权限方法名称</th>
		<th scope="col">功能权限描述</th>
		<th scope="col">标志状态</th>
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
	      <span KeyField="<s:property value="#var.privId"/>">
	      <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" />
	      </span>
	    </td>
	    <td align="center"><s:property value="#var.className"/></td>
	    <td align="center"><s:property value="#var.classDesc"/></td>
	    <td align="center"><s:property value="#var.methodName"/></td>
	    <td align="center"><s:property value="#var.methodDesc"/></td>
	    <td align="center">
	    	<s:if test="#var.sign==0">不保存日志</s:if>
	    	<s:if test="#var.sign==1">保存日志</s:if>
	    	<s:if test="#var.sign==2">隐藏不显示</s:if>
	    </td>
	  </tr>
	  </s:iterator>
	  </tbody>
	</table>
</s:form>
</body>
</html>

