<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/Default.css" type="text/css" rel="stylesheet" />
	<title>E电通智能电网</title>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.p.js"></script>
</head>
<body>
<script type="text/javascript">
var message = "<s:property value="message"/>";
if (message!="") {
	alert(message);
}

function CheckForm(form) {
        if (!$("#t_oldPassword").val()) {
            alert("原始密码不能为空!!!");
            $("#t_oldPassword").focus();
            return false;
        }
        if (!$("#t_passwordOne").val()){
            alert("新密码不能为空!!!");
            $("#t_passwordOne").focus();
            return false;
        }
        if (!$("#t_passwordTwo").val()) {
            alert("请再次输入新密码!!!");
            $("#t_passwordTwo").focus();
            return false;
        }
        if ($("#t_passwordOne").val() != $("#t_passwordTwo").val()) {
            alert("两次输入的密码不相同!!!");
            $("#t_passwordTwo").val("");
            $("#t_passwordTwo").focus();
            return false;
        }
        form.submit();
        return true;
}
</script>
<s:form action="savePass.do" id="passForm" name="passForm" method="post">
	<!--main Nav start -->
	<table cellSpacing="0" cellPadding="0" width="100%" border="0" align="center" class="navTable">
		<tr>
			<td width="40">&nbsp;<img src="images2/navIco.gif" border="0"></td>
			<td class="navInfo">您的位置：首页 <img src="images2/right_disabled.gif" align="absmiddle"/> 系统帐户 <img src="images2/right_disabled.gif" align="absmiddle"/> <span> 密码修改</span></td>
		</tr>
		<tr>
			<td colspan="2" height="20"></td>
		</tr>
	</table>
	<!--main Nav end -->
	<!--Content start -->
		<div style="width:96%;margin:0 auto;">
			<fieldset class="VForm" style="width:92%;text-align:left;">
				<legend>密码修改</legend>
				<p>
					<label>原始密码：</label>
					<input name="oldPwd" type="password" id="t_oldPassword" class="OnBlurCSS" onpaste="return true" ondrop="return true" onfocus="this.className='OnFocusCSS'" onblur="this.className='OnBlurCSS';" style="width:200px;" />
				</p>
				<p>
					<label>新密码：</label>
					<input name="pwd" type="password" id="t_passwordOne" class="OnBlurCSS" onpaste="return true" ondrop="return true" onfocus="this.className='OnFocusCSS'" onblur="this.className='OnBlurCSS';" style="width:200px;" />
				</p>
				<p>
					<label>验证新密码：</label>
					<input name="pwdTwo" type="password" id="t_passwordTwo" class="OnBlurCSS" onpaste="return true" ondrop="return true" onfocus="this.className='OnFocusCSS'" onblur="this.className='OnBlurCSS';" style="width:200px;" />
				</p>
				<p> </p>
			</fieldset>
			<br />
			<br />
			<table width="94%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
				<tr>
					<td class="tdToolsCenter">
						<input id="btn_ok" class="btn01" type="button" name="btn_modify" value="修改" onclick="CheckForm(this.form);" onmouseover="this.className='btn01a';" onmouseout="this.className='btn01';" />
						<input id="btn_back" class="btn01" type="button" name="btn_modify" value="返回" onclick="javascript:history.back();" onmouseover="this.className='btn01a';" onmouseout="this.className='btn01';" />
					</td>
				</tr>
			</table>
		</div>

	<!--Content end-->
</s:form>
</body>
</html>