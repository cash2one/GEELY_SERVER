<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-编辑用户</title>
<link href="themes/easyui.css" rel="stylesheet" type="text/css"/>
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script type="text/javascript" src="js/valid/FormValid.js"></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	
	var message = "<s:property value="message"/>";
	if (message!="") {
		 $.messager.alert('信息提示',message);
	}
});

//获取部门下拉列表
function getDeptList(orgNo) {
	$.ajax({
   		type: "POST",
   		url: "user_deptList.do",
   		data: "orgNo="+orgNo+"&deptNo=<s:property value="deptId"/>",
   		success: function(msg){
     		$('#s_deptNo').html(msg);
   		}
   	});
}

function CheckForm(form) {
	/*if (!$("#t_orgNo").val()){
		$.messager.alert('信息输入未完整','请选择所在单位!!!');
   		$("#t_orgNo").focus();
   		return false;
	}*/
	if (!$("#t_deptId").val()){
		$.messager.alert('信息输入未完整','请选择所在部门!!!');
   		$("#t_deptId").focus();
   		return false;
	}
	if (!$("#t_login").val()){
		$.messager.alert('信息输入未完整','请输入登录帐号!!!');
   		$("#t_login").focus();
   		return false;
	}
	/*if (!$("#t_userType").val()){
		$.messager.alert('信息输入未完整','请选择用户类型!!!');
   		$("#t_userType").focus();
   		return false;
	}*/
	if (!$("#t_name").val()){
		$.messager.alert('信息输入未完整','请输入用户名称!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#t_abc").val()){
		$.messager.alert('信息输入未完整','请输入简码!!!');
   		$("#t_abc").focus();
   		return false;
	}
	if (!$("#t_mobile").val()){
		$.messager.alert('信息输入未完整','请输入手机号码!!!');
   		$("#t_mobile").focus();
   		return false;
	}else{
		var req=/^((\(\d{2,3}\))|(\d{3}\-))?(13|15|18)\d{9}$/;
		if(!req.test($("#t_mobile").val())){
		$.messager.alert('信息输入未完整','请输入正确的手机号码!!!');
   		$("#t_mobile").focus();
   		return false;
		}
		
	}
    return true;
}

function checkLogin(){
	var empNo=document.getElementById("empNo").value;
	if(empNo !=null && "" !=empNo){
		return ;
	}
	if ($("#t_login").val()){
		$.ajax({
   			type: "POST",
   			url: "validateLoginName.do",
   			dataType:"json",
   			data: "loginName="+$("#t_login").val(),
   			success: function(data,type){
   				if(!data){
     				$('#login_info').html("*该账号已经注册，请换个账号注册！");
     				return false;
     			}else{
     				$('#login_info').html("*");
     				return true;
     			}
   			}
   		});
   	} else {
   		$('#login_info').html("*");
   		return false;
   	}
}
</script>
<br/>

<s:form action="user_save.do" id="userForm" name="userForm" onsubmit="return CheckForm(this);" method="post" theme="simple">
<s:hidden name="id" id="id"></s:hidden>
<s:hidden name="orgNo" id="t_orgNo"></s:hidden>
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>用户信息<s:if test="id==null || id==''"><font color="red">(新建用户密码初始为"<s:property value="initPass"/>")</font></s:if></legend>
    <%--div class="Single">
      <label>所在单位：</label><s:select id="t_orgNo" name="orgNo" list="orgList" listKey="orgNo" listValue="orgName" headerKey="" headerValue="--请选择所在单位--" cssStyle="width:200px;" onchange="getDeptList(this.value)"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>orgNo</s:param>
      </s:fielderror>
      </span>
    </div--%>
    <div class="Pairs">
      <label>所在部门：</label><span id="s_deptNo"><s:select id="t_deptId" name="deptId" list="deptList" listKey="id" listValue="name" headerKey="" headerValue="--请选择所在部门--" cssStyle="width:200px;"></s:select></span>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>deptId</s:param>
      </s:fielderror>
      </span>
    </div>
    <!--div class="Single">
      <label>工号：</label><s:textfield id="t_staffNo" name="staffNo" cssStyle="width:200px;" maxlength="16"></s:textfield>
    </div-->
    <div class="Pairs">
      <label>登录帐号：</label><s:if test="empNo==null || empNo==''"><s:textfield id="t_login" name="login" cssStyle="width:200px;" maxlength="32" valid="required|limit" errmsg="请输入登录账号|登录账号最大长度为32" min="1" max="32" onblur="checkLogin();"></s:textfield></s:if><s:else><s:textfield id="t_login" name="login" cssStyle="width:200px;" maxlength="32" readonly="true"></s:textfield></s:else>
      <span style="color:Red" id="login_info">*
      <s:fielderror>
      <s:param>login</s:param>
      </s:fielderror>
      </span>
    </div>
     
    <div class="Single">
      <label>用户名称：</label><s:textfield id="t_name" name="name" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入用户名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>name</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>简码：</label><s:textfield id="t_abc" name="abc" cssStyle="width:200px;" maxlength="16" valid="requird" errmsg="请输入用户简码"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>abc</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>性别：</label><s:select id="t_genDer" name="genDer" list="#{'01':'男','02':'女'}" listKey="key" listValue="value" cssStyle="width:200px;"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>genDer</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>手机号码：</label><s:textfield id="t_mobile" name="mobile" cssStyle="width:200px;" maxlength="20" valid="required|isMobile" errmsg="请输入手机号码|请输入正确的手机号码"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>mobile</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>办公室电话：</label><s:textfield id="t_officeTelNo" name="officeTelNo" cssStyle="width:200px;" maxlength="20"></s:textfield>
    </div>
    <div class="Single">
      <label>邮箱：</label><s:textfield id="t_email" name="email" cssStyle="width:200px;" maxlength="50"></s:textfield>
    </div>
    <div class="Pairs">
      <label>备注：</label><s:textarea id="t_remark" name="remark" cssStyle="height:80px;width:200px;"></s:textarea>
    </div>
</fieldset>
</div>
<br/>
<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
    <input name="" type="submit" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left top; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-22px';" onmouseout="this.style.backgroundPositionY='0px';" /> 
	<input name="" type="button" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left -44px; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-66px';" onmouseout="this.style.backgroundPositionY='-44px';" onclick="history.back();" /> 
    </td>
  </tr>
</table>
</s:form>
<!--Content end-->
</body>
</html>