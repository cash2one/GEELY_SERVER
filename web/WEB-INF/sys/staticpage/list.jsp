<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><link href="css/Default.css" type="text/css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端后台管理</title> 
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="themes/icon.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
<script src="js/popup_layer.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript" src="js/dtree.js"></script>

<script language="javascript">
$.ajaxSetup ({
    cache: false //关闭AJAX相应的缓存
});

$(document).ready(function(){
		tableTrColor();//Grid表格隔行加颜色
		searchPanel();//搜索栏控制函数
		loadOfResult();
});

//加载查询结果
function loadOfResult(suffix){
	var qtype=$("#t_qtype").val();
	var segno=$("#t_segno").val();
	var courseno=$("#t_courseno").val();
	var status=$("#t_status").val();
	
	var uri="netqstmanage!list.do?qtype="+qtype+"&grade="+segno+"&course="+courseno+"&status="+status;
	if(suffix){
		uri+=suffix;
	}
	
	$("#list").html("<img src='img/load.gif'/>");
	$("#list").load(uri);
	return false;
}

function reload() {
    location = "staticpage!reloadConfig.do";
}

function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除该条数据？', function(r){
    	if(r){
    	
    		var i=0
    		var param="";
    		for(;i<v.length;i++){
    			param +="ids="+v[i];
    			if(i!=v.length-1){
    				param+="&";
    			}
    		}
    		
    		location="staticpage!delete.do?"+param;
    	}
    });	
}

function changeStatus(status){
	location="staticpage!changeStatus.do?status="+status;
}

</script>
</head>
<body>
<br/>



<s:form name="form1" id="form1" action="ofuRequireSearch_search.do" method="post" onsubmit="return loadOfResult();">

<!--Content start -->
<br>
<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;">
			          <a id="del" href="#" onclick="deleteInfo()" class="easyui-linkbutton" plain="true" iconCls="icon-remove">删除缓存页面</a>
			          <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="reload()">重新加载配置</a> 
			          
			          <s:if test="status == true">
			          	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="changeStatus('false')">关闭页面缓存</a> 
			          </s:if>
			          <s:else>
			          	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="changeStatus('true')">开启页面缓存</a> 
			          </s:else>
			          
				</div>
        	</td>
      </tr>
    </thead>
</table>
<div style="float: left;padding-right: 10px;padding-left: 10px;padding-top:3px">


		<!--Content start -->
<s:form name="form1" id="form1" action="course!list.do" method="post">
<br>

<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:100%;" align="center">
	  <tr>
	  	<th scope="col" style="width:3%;">
		<input type='checkbox' hidefocus='true' id='grid1_HeaderButton' name='grid1_HeaderButton' onclick='CheckAll(this,"trBg01","trBg02","trBgHover"  )' style='display:block;'>
		</th>
		<th scope="col">文件名</th>
		<th scope="col">创建时间</th>
	  </tr>
	  <s:if test="list==null || list.size()==0">
	  <tr class="trBg01" onclick="RowMouseClick(this,&quot;grid1_HeaderButton&quot;);">
	    <td class="show" align="center" colspan="10">
	      <span KeyField="0">
	      	&nbsp;
	      </span>
	    </td>
	  </s:if>
	  <s:iterator id="var" value="list" status="vStatus">
	  	<tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>">
	    
	    <td class="show" align="center">
	      <span KeyField="<s:property value="#var.fileName"/>">
	      <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton"/>
	      </span>
	    </td>
	    
	    <td align="center"><s:property value="fileName"/></td>
	    <td align="center"><s:property value="createTime==null?'':createTime.toLocaleString()"/></td>
	       
	  </tr>
	  </s:iterator>
</table>

<br/>

</s:form>
</div>
</s:form>

</body>
</html>
	