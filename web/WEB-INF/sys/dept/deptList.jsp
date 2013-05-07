<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css"> 
<title>校讯通手机服务端-部门信息管理</title> 
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
<script src="res/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
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
var redirectSign = "<s:property value="redirectSign"/>";
if (redirectSign!="") {
	parent.deptTreeFrame.location.reload();
}

function editInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要修改的信息。');
        return;
    }
    location = "dept_edit.do?id="+v[0]+"&pdeptNo=<s:property value="pdeptNo"/>&orgNo=<s:property value="orgNo"/>";
}

function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除该条数据？', function(r){
    	if(r){
    		location = "dept_delete.do?id="+v[0]+"&pdeptNo=<s:property value="pdeptNo"/>&orgNo=<s:property value="orgNo"/>";
    	}
    });	
}
</script>
</head>
<body>
<br>
<s:form name="form1" id="form1" action="dept_list.do" method="post">
<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="location='dept_add.do?pdeptNo=<s:property value="pdept.id"/>&orgNo=<s:property value="orgNo"/>'">新增信息</a> 
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
            <th width="3%">
			<input type='checkbox' hidefocus='true' id='grid1_HeaderButton' name='grid1_HeaderButton' onclick='CheckAll(this,"trBg01","trBg02","trBgHover"  )' style='display:none;'>
			</th>
        	<th>部门编号</th>
			<th>部门名称</th>
			<th>部门类型</th>
			<th>上级部门</th>
			<th>所属单位</th>
        </tr>
    </thead>
    <tbody>
    	<s:if test="list==null || list.size()==0">
	  	<tr class="trBg01" onclick="RowMouseClick(this,&quot;grid1_HeaderButton&quot;);">
	      <td align="center" colspan="10">
	      	<span KeyField="0">&nbsp;</span>
	      </td>
	    </tr>
	    </s:if>
	    
	    <s:iterator id="var" value="list" status="vStatus">
	    <tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>" onclick="RowMouseClick(this,&quot;grid1_<s:property value="#vStatus.count"/>_CheckBoxButton&quot;);">
	      <td align="center">
	        <span KeyField="<s:property value="#var.id"/>">
	        <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" />
	        </span>
	      </td>
	      <td align="center"><s:property value="#var.id"/></td>
	      <td align="center"><s:property value="#var.name"/></td>
	      <td align="center">
			<s:if test="#var.typeCode=='01'">部门</s:if>
			<s:if test="#var.typeCode=='02'">班组</s:if>
		  </td>
	      <td align="center"><s:if test="pdept.name!=''"><s:property value="pdept.name"/></s:if></td>
	      <td align="center"><s:property value="orgName"/></td>
	    </tr>
	    </s:iterator>
    </tbody>
</table>
</s:form>
</body>
</html>

