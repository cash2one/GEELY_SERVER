<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script type="text/javascript" src="js/valid/FormValid.js"></script>
<title>E电通智能电网</title>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
		alert(message);
	}
});
</script>
</head>
<body>
<br/>	
<s:form action="role_save.do" id="unitForm" name="unitForm" onsubmit="return validator(this);" method="post">
<s:hidden name="pageNo"></s:hidden>
<s:if test="roleId!=null">
<s:hidden name="roleId"></s:hidden>
</s:if>

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend for="name">系统角色信息</legend>
    <div class="Single">
      <label>角色名称：</label><s:textfield id="t_roleName" name="roleName" cssClass="OnBlurCSS easyui-validatebox"  onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'" cssStyle="width:200px;" maxlength="16" errmsg="请输入系统角色名称|角色名称最大长度为32" valid="required|limit" min="1" max="32"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>roleName</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>角色描述：</label><s:textarea id="t_roleDesc" name="roleDesc" rows="4" cssClass="OnBlurCSS" onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'" cssStyle="height:80px;width:200px;"></s:textarea>
    </div>
    <div class="Single">
      <label>排序号：</label><s:textfield id="t_orderNum" name="orderNum" cssClass="OnBlurCSS easyui-validatebox"  onblur="this.className='OnBlurCSS'" onfocus="this.className='OnFocusCSS'" cssStyle="width:200px;" maxlength="5" errmsg="请输入排序号|排序号不能为非数字|排序号最大长度为5" valid="required|isNumber|limit" max="5" min="1"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>orderNum</s:param>
      </s:fielderror>
      </span>
    </div>
</fieldset>
</div>
<!--Content end-->

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
</body>
</html>