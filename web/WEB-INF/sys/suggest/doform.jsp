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
<title>校讯通手机服务端后台管理系统</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
	$.messager.alert('信息提示信息',message);
	}
	
	var t_doContent = document.all.t_doContent;
	t_doContent.setAttribute("valid","required");
	t_doContent.setAttribute("errmsg","请输入问题处理内容");
});
</script>

<s:form action="suggest_doSave.do" id="suggestForm" name="suggestForm" onsubmit="return validator(this);" method="post">
<s:hidden name="id"></s:hidden>
<br/>	

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>反馈问题解决</legend>
    <div class="Single" >
      <label>问题类别：</label>
      <s:iterator id="var" value="questionCodeList">
      <s:if test="dicCode==qtype"><s:property value="#var.dicName"/></s:if>
      </s:iterator>
    </div>
    <div class="Pairs">
      <label>问题标题：</label>
      <s:property value="title"/>
    </div>
    <div class="Single">
      <label>问题描述：</label>
      <s:property value="content" escape="false"/>
    </div>
    <div class="Pairs">
      <label>问题处理：</label>
      <s:textarea name="doContent" id="t_doContent" cols="80" rows="10"></s:textarea>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>doContent</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>处理人：</label>
      <s:textfield name="doMan" id="t_doMan" valid="required|limit" errmsg="请输入处理人|处理人最大长度为50" min="1" max="50" cssStyle="width:200px;"></s:textfield>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>doMan</s:param>
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
    </td>
  </tr>
</table>
<%--s:hidden name="qtype"/--%>
<s:hidden name="status"/>
</s:form>
</body>
</html>
