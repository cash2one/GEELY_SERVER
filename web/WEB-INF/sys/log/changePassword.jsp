<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预购电客户管理系统-更改密码</title>
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
<script type="text/javascript">
function check(){
	if(!$('#pass').val()){
	 $.messager.alert('输入不合法','原始密码不能为空','error');
	 return false;
	}
	if(!$('#newPass').val()){
	 $.messager.alert('输入不合法','新密码不能为空','error');
	 return false;
	}
	if($('#newPass').val()!=$('#rePass').val()){
	 $.messager.alert('输入不合法','两次输入的密码不相同','error');
	 return false;
	}
	$.ajax({
   		type: "POST",
   		url: "PasswordChange.do",
   		dataType:"json",
   		data: "loginName="+$("#t_login").val()+"&pass="+$('#pass').val()+"&newPass="+$('#newPass').val(),
   		success: function(data,type){
   			if(data=="1"){
   			$.messager.confirm('密码修改', '密码修改成功，请重新登录', function(r){
			if (r){
			if(typeof(window.parent.opener) == 'undefined'){
	    	window.returnValue = "login";
	    	
	        }else{
	        window.parent.opener.document.location='login.jsp';
	        }
				window.parent.close();
			}
		    });
     		return false;
     		}
     		if(data=="2"){
     		$.messager.alert('密码修改','原始密码不正确，修改失败!','error');
     		return false;
     		}
     		if(data=="3"){
     		$.messager.alert('密码修改','修改失败,请稍后再试！','info');
     		return false;
     		}
   		}
   	});
}
</script>
</head>
<body style="background: #F2F0E3;">
<table cellpadding="0" cellspacing="0" border="0" class="DialogTableHeader">
    <tr>
        <td class="logoTit">
           密码修改
        </td>
        <td width="50">
            <a href="javascript:help();">
                <img alt="帮助" src="images/IcoHelp.gif" /></a>
        </td>
    </tr>
</table>
<br/>
<div style="height: 200px;">
            <fieldset class="VForm">
                <legend>密码修改</legend>
                <div class="Single">
                    <label for="name">登录用户名:</label>
                    <s:hidden name="loginName" id="t_login"></s:hidden>
                    <s:label><s:property value="loginName"/></s:label>
                </div>
                <br/>
                <div class="Pairs">
                    <label for="email">原始密码:</label>
                    <s:password  name="pass" id="pass" cssClass="OnBlurCSS easyui-validatebox" onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'"/>
                    <span style="color:Red">*</span>
                </div>
                <br/>
                <div class="Single">
                    <label for="name">新密码:</label>
                    <s:password  name="newPass" id="newPass" cssClass="OnBlurCSS easyui-validatebox" onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'"/>
                    <span style="color:Red">* </span>
                </div>
                <br/>
                <div class="Pairs">
                    <label for="message">确认新密码:</label>
                    <s:password  name="rePass" id="rePass" cssClass="OnBlurCSS easyui-validatebox" onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'"/>
               		<span style="color:Red">* </span>
                </div>
            </fieldset>
</div>
<div class="DialogDivFooter">
    <input type="button" id="ButOk" value="确定" class="btn01" onmouseover="this.className='btn01a'" onmouseout="this.className='btn01'" onclick="check();"/>&nbsp;
    <input type="button" value="取消" class="btn01" onmouseover="this.className='btn01a'" onmouseout="this.className='btn01'" onclick="window.parent.close();" />
</div>
</body>
</html>