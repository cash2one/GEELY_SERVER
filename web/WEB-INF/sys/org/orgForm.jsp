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
<title>单位信息管理</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
		alert(message);
	}

	var redirectSign = "<s:property value="redirectSign"/>";
	if (redirectSign!="") {
		parent.midFrame.location.reload();
	}
	
	var t_orgType = document.all.t_orgType;
	t_orgType.setAttribute("valid","required");
	t_orgType.setAttribute("errmsg","请选择单位类别");
	
});

function CheckForm(form) {
	if (!validator(form)) {
		return false;
	}
    return true;
}
</script>

<s:form action="org_save.do" id="orgForm" name="orgForm" onsubmit="return CheckForm(this);" method="post">
<s:hidden name="logOut"></s:hidden>
<s:if test="orgNo!=null">
<s:hidden name="orgNo"></s:hidden>
</s:if>
<br/>	

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend for="name">单位信息</legend>
    <div class="Single">
      <label>单位名称：</label><s:textfield id="t_orgName" name="orgName" cssClass="OnBlurCSS easyui-validatebox" cssStyle="width:200px;" valid="required|limit" min="1" max="128" errmsg="请输入供电单位名称|供电单位名称最大长度为128"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>orgName</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>简码：</label><s:textfield id="t_abc" name="abc" cssClass="OnBlurCSS easyui-validatebox" cssStyle="width:200px;" valid="required|limit" min="1" max="128" errmsg="请输入单位简码|单位简码最大长度为128"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>abc</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>上级单位：</label><s:select id="t_porgNo" name="porgNo" list="list" listKey="orgNo" listValue="orgName"  cssStyle="width:200px;" headerKey="" headerValue="--请选择上级单位--"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>porgNo</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>单位类别：</label><s:select id="t_orgType"  name="orgType" list="orgTypeList" listKey="dicCode" listValue="dicName"  cssStyle="width:200px;"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>orgType</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>排序号：</label><s:textfield id="t_sortNo" name="sortNo" cssClass="OnBlurCSS easyui-validatebox" cssStyle="width:200px;" maxlength="5" cssStyle="width:100px;" valid="required|isInt" errmsg="请输入排序号|排序号应为整形数字"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>sortNo</s:param>
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