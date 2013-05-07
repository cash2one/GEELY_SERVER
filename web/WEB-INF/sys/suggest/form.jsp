<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/valid/FormValid.js"></script>
<script src="res/js/common.js" type="text/javascript"></script>
<title>办公设备及用品信息管理系统</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
	$.messager.alert('信息提示信息',message);
	}
	
	var t_content = document.all.t_content;
	t_content.setAttribute("valid","required");
	t_content.setAttribute("errmsg","请输入问题描述");
});
function submitForm(){
t_content___Frame.FCK.UpdateLinkedField();
if(validator(document.suggestForm)){ 
	document.suggestForm.submit();
}
}
</script>

<s:form action="suggest_save.do" id="suggestForm" name="suggestForm" onsubmit="return validator(this);" method="post">
<s:if test="id!=null">
<s:hidden name="id"></s:hidden>
</s:if>
<s:if test="useCode!=null">
<s:hidden name="useCode"></s:hidden>
</s:if>
<br/>	

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>问题反馈</legend>
    <div class="Single" >
      <label>问题类别：</label>
      <s:select name="qtype" list="questionCodeList" listKey="dicCode" listValue="dicName" id="t_qtype"></s:select>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>qtype</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>问题标题：</label>
      <s:textfield name="title" id="t_title" valid="required|limit" errmsg="请输入问题标题|问题标题最大长度为32" min="1" max="32" cssStyle="width:300px;"></s:textfield>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>title</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>问题描述：</label>
      <s:hidden id="t_content" name="content"></s:hidden>
	  <input id="t_content___Config" value="SkinPath=skins%2Foffice2003%2F" type="hidden" />
	  <iframe id="t_content___Frame" frameborder="no" height="300" scrolling="no" width="98%" src="fckeditor/editor/fckeditor.html?InstanceName=t_content&Toolbar=Default"> 
	  </iframe>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>content</s:param>
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
    <input name="" type="button" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left top; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-22px';" onmouseout="this.style.backgroundPositionY='0px';" onclick="submitForm()"/> 
    </td>
  </tr>
</table>
</s:form>
</body>
</html>
