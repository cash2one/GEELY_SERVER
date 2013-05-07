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
<title>E电通智能电网</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
		$.messager.alert('信息提示信息',message);
	}
	$("#t_dicRemark").attr("valid","limit");
	$("#t_dicRemark").attr("min","0");
	$("#t_dicRemark").attr("max","512");
	$("#t_dicRemark").attr("errmsg","备注最大长度为512");
});
</script>

<s:form action="dictionary_save.do" id="dicForm" name="dicForm" onsubmit="return validator(this);" method="post">
<s:hidden id="t_dicType" name="dicType"></s:hidden>
<s:hidden id="t_dicCodeType" name="dicCodeType"></s:hidden>
<s:hidden name="dicCodeLen"></s:hidden>
<s:hidden name="sign"></s:hidden>
<s:hidden name="superDicId"></s:hidden>
<s:if test="dicId!=null">
<s:hidden name="dicId"></s:hidden>
</s:if>
<br/>	

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>数据字典信息</legend>
    <div class="Single">
      <label>数据类别：</label>
      <s:textfield id="t_superDicName" name="superDicName" cssStyle="width:200px;" readonly="true"></s:textfield>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>superDicName</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>数据代码：</label>
      <s:textfield id="t_dicCode" name="dicCode" cssStyle="width:200px;" maxlength="8" valid="required|limit" errmsg="请输入数据代码|数据代码最大长度为16" min="1" max="16"></s:textfield>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>dicCode</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>数据名称：</label>
      <s:textfield id="t_dicName" name="dicName" cssStyle="width:200px;" maxlength="90" valid="required|limit" errmsg="请输入数据名称|数据名称最大长度为96" min="1" max="96"></s:textfield>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>dicName</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>备注：</label>
      <s:textarea id="t_dicRemark" name="dicRemark" cssStyle="width:300px;" rows="3"></s:textarea>
    </div>
     <div class="Single">
      <label>序号：</label>
      <s:textfield id="t_dicSortNo" name="dicSortNo" cssStyle="width:200px;" maxlength="2" valid="required|isInt|limit" errmsg="请输入序号|序号必须为数字|数据代码最大长度为2" min="1" max="2"></s:textfield>
      <span style="color:Red">*
      <s:fielderror theme="simple">
      <s:param>dicSortNo</s:param>
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
