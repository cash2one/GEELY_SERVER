<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><link href="css/Default.css" type="text/css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-数据字典管理</title> 
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
function editInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要修改的信息。');
        return;
    }
    location = "dictionary_edit.do?dicId="+v[0]+"&superDicId=<s:property value="superDicId"/>";
}

function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除该条数据?', function(r){
		if (r){
		location = "dictionary_delete.do?dicId="+v[0]+"&superDicId=<s:property value="superDicId"/>";
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
		window.location="dictionary_list.do?redirectSign=0&pageNo="+pageNo+"&superDicId=<s:property value="superDicId"/>";
	}
}
</script>
</head>
<body>
<!--Content start -->
<s:form name="form1" id="form1" action="dictionary_list.do" method="post">
<br>

<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
	        	<b><s:property value="superDicName"/></b>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="location='dictionary_add.do?superDicId=<s:property value="superDicId"/>'">新增信息</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="editInfo()">修改信息</a> 
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
		<th scope="col" style="width:10%;">序号</th>
		<th scope="col" style="width:10%;">编码</th>
		<th scope="col" style="width:27%;">名称</th>
		<th scope="col">备注</th>
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
	      <span KeyField="<s:property value="#var.dicId"/>">
	      <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" />
	      </span>
	    </td>
	     <td align="center"><s:property value="#var.dicSortNo"/></td>
	    <td align="center"><s:property value="#var.dicCode"/></td>
	    <td align="center"><s:property value="#var.dicName"/></td>
	    <td align="center"><s:property value="#var.dicRemark"/></td>
	  </tr>
	  </s:iterator>
	 </tbody>
	  <tfoot>
       <tr align="right">
        <td colspan="5">
        <span>第<b><s:property value="pageNo"/></b>页 | </span>
        <span>共<b><s:property value="totalPages"/></b>页 | </span>
        <span>每页<b><s:property value="pageSize"/></b>行 | </span>
        <span>共<b><s:property value="totalRecords"/></b>行 | </span>
        <s:if test="pageNo==1">
		<a>首页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'dictionary_list.do?superDicId=<s:property value="superDicId"/>&redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'dictionary_list.do?superDicId=<s:property value="superDicId"/>&redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'dictionary_list.do?superDicId=<s:property value="superDicId"/>&redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'dictionary_list.do?superDicId=<s:property value="superDicId"/>&redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
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

