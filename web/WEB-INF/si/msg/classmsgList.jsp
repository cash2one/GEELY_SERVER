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
	var vsusertype= $("#susertype").val();
	var vsusername = $("#susername").val();
	var vrusertype = $("#rusertype").val();
	var vrusername = $("#rusername").val();
	window.location="msg_getmsglist.do?susertype="+vsusertype+"&susername="+vsusername+"&rusertype="+vrusertype+"&rusername="+vrusername;
}

//输入框重置
function resert(){
	$("#susertype").val('');
	$("#susername").val("");
	$("#rusertype").val('');
	$("#rusername").val("");
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
<br>
<!--  
<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
				</div>
        	</td>
      </tr>
    </thead>
</table>-->
<div class="searchPanel" style="margin-left:12px;">
    <div class="searchPanel-header">
    	<div class="searchPanel-title">搜索</div>
        <div class="searchPanel-icon"></div>
        <div class="searchPanel-tools">
        	<div class="searchPanel-expand"></div>
        </div>
    </div>
    <div class="searchPanel-body">
    <!--  
     <select id="myselected" name="myselected" onchange="myselect()" >
              <option value="0" selected="selected">请选择查询条件</option>
              <option value="1">标题</option>
              <option value="2">服务对象</option>
              </select>
              -->
              
				<!--  
		          发送人类型:<s:select id="susertype" name="susertype" list="#{'':'请选择用户类型','01':'教师','02':'家长'}"></s:select>
		          发送人姓名:<s:textfield id="susername" name="susername" size="12" ></s:textfield>
		          接收人类型:<s:select id="rusertype" name="ruser" list="#{'':'请选择用户类型','01':'教师','02':'家长'}"></s:select>
				 接收人姓名:<s:textfield id="rusername" name="rusername" size="12"></s:textfield> 
				 -->
			<a href='#' class="easyui-linkbutton" iconCls="icon-search" onclick="seacher()">查询</a>
	        	<a href='#' id="myresert" name="myresert" class="easyui-linkbutton" iconCls="icon-reload" onclick="resert()">重置</a>
    </div>
</div>



<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
	<thead>
	  <tr>
		<th scope="col" style="width:3%;">
		
		</th>
		<th scope="col">发送人</th>
		<th scope="col">发送人类型</th>
		<th scpoe="col">接收人</th>
		<th scope="col">接收人类型</th>
		<th scope="col">信息内容</th>
		<th scope="col">信息类型</th>
		<th scope="col">读取状态</th>
		<th scope="col">推送</th>
		<th scope="col">接收状态</th>
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
	    <td align="center"><s:property value="#var.susername"/> </td>
	    <td align="center">
	    <s:if test="#var.susertype=='01'">
	    	教师
	    </s:if>
	    <s:if test="#var.susertype=='02'">
	    	家长
	    </s:if>
	    </td>
	    <td align="center"><s:property value="#var.rusername"/></td>
	    <td align="center">
	    <s:if test="#var.rusertype=='01'">
	    	教师
	    </s:if>
	    <s:if test="#var.rusertype=='02'">
	    	家长
	    </s:if>
	    </td>
	    <td align="center"><s:property value="#var.content"/></td>
	     <td align="center"> 
	     	<s:if test="#var.type=='01'">
	     	学生通知
	     	</s:if>
	     	<s:if test="#var.type=='02'">
	     	学习成绩
	     	</s:if>
	     	<s:if test="#var.type=='03'">
	     	家庭作业
	     	</s:if>
	     	<s:if test="#var.type=='04'">
	     	家教教育
	     	</s:if>
	     	<s:if test="#var.type=='05'">
	     	教育产品 
	     	</s:if>
	     </td>
	     <td align="center">
	     	<s:if test="#var.readflag=='01'">
	     		未读
	     	</s:if>
	     	<s:if test="#var.readflag=='02'">
	     		已读
	     	</s:if>
	     </td>
	     <td align="center">
	     	<s:if test="#var.pullflag=='01'">
	     		未推送
	     	</s:if>
	     	<s:if test="#var.pullflag=='02'">
	     		已推送
	     	</s:if>
	     </td>
	     <td align="center">
	     	<s:if test="#var.mtflag=='01'">
	     		未接收
	     	</s:if>
	     	<s:if test="#var.mtflag=='02'">
	     		已接收
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

