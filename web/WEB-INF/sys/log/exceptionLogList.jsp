<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-系统日志管理</title> 
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

function deleteInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 	    $.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除该条数据?', function(r){
		if (r){
			location = "exceptionLog_delete.do?id="+v[0]+"&pageNo=<s:property value="pageNo"/>";
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
		window.location="exceptionLog_list.do?redirectSign=0&pageNo="+pageNo+"&sTime=<s:property value="sTime" />&eTime=<s:property value="eTime"/>";
	}
}

//清空日志信息
function clearLog(){
   $.messager.confirm('确定操作', '你确定要清空日志信息？', function(r){
      if(r){
   		 window.location="exceptionLog_clear.do";
   	  }
   });	
}
</script>
</head>
<body>
<br/>
<s:form name="form1" id="form1" action="exceptionLog_list.do" method="post">
<div class="searchPanel" style="margin-left:12px;">
    <div class="searchPanel-header">
    	<div class="searchPanel-title">搜索</div>
        <div class="searchPanel-icon"></div>
        <div class="searchPanel-tools">
        	<div class="searchPanel-expand"></div>
        </div>
    </div>
    <div class="searchPanel-body">
       <label>日志时间:</label>
       <s:textfield id="t_stime" name="sTime" cssClass="easyui-datebox OnBlurCSS" cssStyle="width:80px;" onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'" ></s:textfield> 至
       <s:textfield id="t_etime" name="eTime" cssClass="easyui-datebox OnBlurCSS" cssStyle="width:80px;" onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'" ></s:textfield> 
       <input type="image" src="images/BtnSearch_out.gif" align="right" onmousemove="this.src='images/BtnSearch_hover.gif'" onmouseout="this.src='images/BtnSearch_out.gif'" style="margin-right: 25px;padding-right: 25px;"/>
    </div>
</div>
<!--Content start -->
<br>
	<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
	        	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="deleteInfo()">删除日志</a> 
	        	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="clearLog()">清空日志</a> 
				</div>
        	</td>
      </tr>
    </thead>
    </table>
	<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
	  <thead>
	  <tr>
		<th scope="col" width="3%">
			<input type='checkbox' hidefocus='true' id='grid1_HeaderButton' name='grid1_HeaderButton' onclick='CheckAll(this,"trBg01","trBg02","trBgHover"  )' style='display: none;' />
		</th>
		<th scope="col" width="30%">类名称</th>
		<th scope="col" width="15%">方法名称</th>
		<th scope="col" width="5%">行数</th>
		<th scope="col" width="40%">异常信息</th>
		<th scope="col" width="10%">日志时间</th>
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
	  </tr>
	  </s:if>
	  <s:iterator id="var" value="list" status="vStatus">
	  <tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>" onclick="RowMouseClick(this,&quot;grid1_<s:property value="#vStatus.count"/>_CheckBoxButton&quot;);">
	    <td class="show" align="center">
	      <span KeyField="<s:property value="#var.id"/>">
	      <input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="grid1$<s:property value="#vStatus.count"/>$CheckBoxButton" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" />
	      </span>
	    </td>
	    <td align="center"><s:property value="#var.className"/></td>
	    <td align="center"><s:property value="#var.methodName"/></td>
	    <td align="center"><s:property value="#var.lineNumber"/></td>
	    <td align="center"><s:property value="#var.message"/></td>
	    <td align="center"><s:date name="#var.time" format="yyyy-MM-dd"/></td>
	  </tr>
	  </s:iterator>
	  </tbody>
	  <tfoot>
       <tr align="right">
          <td colspan="6">
          <span>第<b><s:property value="pageNo"/></b>页 | </span>
        <span>共<b><s:property value="totalPages"/></b>页 | </span>
        <span>每页<b><s:property value="pageSize"/></b>行 | </span>
        <span>共<b><s:property value="totalRecords"/></b>行 | </span>
        <s:if test="pageNo==1">
		<a>首页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'exceptionLog_list.do?redirectSign=0&pageNo=1');">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'exceptionLog_list.do?redirectSign=0&pageNo=<s:property value="prevPageNo"/>');">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'exceptionLog_list.do?redirectSign=0&pageNo=<s:property value="nextPageNo"/>');">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(form1,'exceptionLog_list.do?redirectSign=0&pageNo=<s:property value="totalPages"/>')">尾页</a> &nbsp;
        </s:else>
		<input type="text" size="1" id="pageNo" style="height:18px;width:32px" /><strong><a onclick="turnTo();" href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong></td>
        </tr>
    </tfoot>
	</table>  
<br/>
</s:form>
<!--Content end -->
</body>
</html>

