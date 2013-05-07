<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-教师管理</title> 
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
		
		$.post("province!getProvince.do", function (data) {  
         var jsonObj = eval("(" + data + ")");  
         for (var i = 0; i < jsonObj.length; i++) {  
             var $option = $("<option></option>");  
             $option.attr("value", jsonObj[i].id);  
             $option.text(jsonObj[i].province);  
             $("#province").append($option);  
         }  
		}); 
		
		 $("#province").change(function () {  
         $.post("area!getArea.do", {id:$("#province").val()}, function (data) {  
             /* 清空城市 */  
             $("#city option[value!='']").remove();  
            /* 清空乡镇 */  
             $("#town option[value!='']").remove();  
             var jsonObj = eval("(" + data + ")");  
            for (var i = 0; i < jsonObj.length; i++) {  
                 var $option = $("<option></option>");  
                 $option.attr("value", jsonObj[i].id);  
                $option.text(jsonObj[i].area);  
                 $("#city").append($option);  
            }  
         });  
    });  
    
    /* 根据城市获取乡镇 */  
    $("#city").change(function () {  
        $.post("town!getTown.do", {id:$("#city").val()}, function (data) {  
            /* 清空乡镇 */  
            $("#town option[value!='']").remove();  
            var jsonObj = eval("(" + data + ")");  
            for (var i = 0; i < jsonObj.length; i++) {  
                var $option = $("<option></option>");  
                $option.attr("value", jsonObj[i].id);  
                $option.text(jsonObj[i].town);  
                $("#town").append($option);  
            }  
         });  
    });  
		
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
			location = "teacher_delteacher.do?id="+a+"&pageNo=<s:property value="pageNo"/>";
		}
	});
}

function courseInfo() {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
       $.messager.alert('未选中记录','请先选中需要查看的老师信息。');
        return;
    } 
			location = "teacher_teachercourse.do?id="+v[0]+"&pageNo=<s:property value="pageNo"/>";
}


//修改
function editpro(){
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
       $.messager.alert('未选中记录','请先选中需要修改的信息。');
        return;
    } 
    if(v.length > 1){
    	$.messager.alert('修改错误','每次只能修改一条教师信息');
    	return;
    }
     var url ="teacher_editeacher.do?id="+v[0];
     window.location=url;
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
		form1.submit();
	}
}


function goPage(pageNo) {
	$("#pageNo").val(pageNo);
	turnTo();
}
</script>
</head>
<body>
<br/>

<s:form name="form1" id="form1" action="teacher_getteacherlist.do" method="post">
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
    	  省:<select id="province" name="province">  
            <option value="">请选择</option>  
            </select> 
           市:<select id="city" name="city">  
            <option value="">请选择</option>  
            </select> 
           区:<select id="town" name="town">  
            <option value="">请选择</option>  
            </select>

		学校名字:<s:textfield id="schoolname" name="schoolname" size="12"></s:textfield>&nbsp;&nbsp;
		班级名字:<s:textfield id="classname" name="classname" size="12"></s:textfield>
		教师名字：<s:textfield id="name" name="name" size="12"></s:textfield>&nbsp;&nbsp;
		<a href='#' class="easyui-linkbutton" iconCls="icon-search" onclick="form1.submit()">查询</a>
	    <a href='#' id="myresert" name="myresert" class="easyui-linkbutton" iconCls="icon-reload" onclick="form1.reset()">重置</a>
    </div>
</div>

<br/>
<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center" class="infoList">
	<thead>
	  <tr>
        	<td colspan="10">
	        	<div style="padding:5px;background:#efefef;"> 
	        	<b><s:if test="module.moduleName!=''"><s:property value="module.moduleName"/></s:if></b>&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="location='teacher_addteacher.do'">新增教师信息</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" onclick="editpro()">教师信息修改</a> 	
				<a href="#" class="easyui-linkbutton" id="bt_delete"  onclick="deleteInfo()" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">教师信息删除</a>
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="courseInfo()">教师授课科目</a> 
				<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="editpro()">教师授课班级</a> 
				</div>
        	</td>
      </tr>
    </thead>
</table>



<table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="width:98%;" align="center">
	<thead>
	  <tr>
	    <th scope="col"></th>
		<th scope="col">教师姓名</th>
		<th scope="col">教师工号</th>
		<th scpoe="col">所在学段</th>
		<th scope="col">教师角色</th>
		<th scope="col">教师积分</th>
		<th scope="col">所在班级</th>
		<th scope="col">所在学校</th>
		<th scope="col">手机号码</th>
		<th scope="col">注册时间</th>
		<th scope="col">登录帐号</th>
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
	    
	    <td align="center"><s:property value="#var.name"/> </td>
	    <td align="center"><s:property value="#var.workno"/></td>
	    <td align="center">
	    <s:if test="#var.spart=='01'">
	     	幼儿园
	     </s:if>
	     <s:if test="#var.spart=='02'">
	     	小学
	     </s:if>
	     <s:if test="#var.spart=='03'">
	     	初中
	     </s:if>
	     <s:if test="#var.spart=='04'">
	     	高中
	     </s:if>
	     <s:if test="#var.spart=='05'">
	     	其它
	     </s:if>
	    </td>
	     <td align="center">
	     <s:if test="#var.role=='01'">
	     	班主任
	     </s:if>
	     <s:if test="#var.role=='02'">
	     	任课老师
	     </s:if>
	     <s:if test="#var.role=='03'">
	     	学校管理员
	     </s:if>
	     </td>
	     <td align="center"><s:property value="#var.score"/></td>
	      <td align="center"><s:property value="#var.classes.name"/></td>
	       <td align="center"><s:property value="#var.school.name"/></td>
	        <td align="center"><s:property value="#var.mobile"/></td>
	    <td align="center"><s:date name="#var.regtime" format="yyyy-MM-dd HH:mm:ss"/></td>
	      <td align="center"><s:property value="#var.loginname"/></td>
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
        <a href="javascript:goPage(1);">首页</a> | 
        </s:else>
		<s:if test="pageNo==1">
		<a>上一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(<s:property value="prevPageNo"/>);">上一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>下一页</a>|
		</s:if>
		<s:else>
        <a href="javascript:goPage(<s:property value="nextPageNo"/>);">下一页</a> | 
        </s:else>
		<s:if test="pageNo==totalPages">
		<a>尾页</a>&nbsp;
		</s:if>
		<s:else>
        <a href="javascript:goPage(<s:property value="totalPages"/>)">尾页</a> &nbsp;
        </s:else>
		<s:textfield id="pageNo" name="pageNo" cssStyle="height:18px;width:32px" size="1"></s:textfield><strong><a onclick="turnTo();" href="javascript:void(0);" style="display:inline;font-size:12pt">Go</a></strong></td>
        </tr>
    </tfoot>
</table>
<br/>
</s:form>
<!--Content end -->
<br />
</body>
</html>

