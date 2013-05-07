<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/valid/FormValid.js"></script>
<title>E电通智能电网</title>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
		alert(message);
	}
	var t_sign = document.all.t_sign;
	t_sign.setAttribute("valid","required");
	t_sign.setAttribute("errmsg","请选择功能标志");
});

</script>
</head>
<body>
<br/>

<s:form action="privilege_save.do" id="privilegeForm" name="privilegeForm" onsubmit="return CheckForm(this);" method="post">
<s:hidden name="moduleId"></s:hidden>
<s:if test="privId!=null">
<s:hidden name="privId"></s:hidden>
</s:if>
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>系统功能权限信息</legend>
    <div class="Single">
      <label>所属模块类名：</label><s:textfield id="t_className" name="className" cssStyle="width:200px;" maxlength="160" valid="required" errmsg="请输入所属模块类名"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>className</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>所属模块描述：</label><s:textfield id="t_classDesc" name="classDesc" cssStyle="width:200px;" valid="required" errmsg="请输入所属模块描述"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>classDesc</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>功能权限方法名称：</label><s:textfield id="t_methodName" name="methodName" cssStyle="width:200px;" valid="required" errmsg="请输入功能权限方法名称"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>methodName</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>功能权限方法描述：</label><s:textfield id="t_methodDesc" name="methodDesc" cssStyle="width:200px;" valid="required" errmsg="请输入功能权限方法描述"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>methodDesc</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>排序号：</label><s:textfield id="t_orderNum" name="orderNum" cssStyle="width:200px;" maxlength="5" cssStyle="width:100px;" valid="required|isInt" errmsg="请输入排序号|排序号应为整形数字"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>orderNum</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>功能标志：</label><s:select id="t_sign" name="sign" list="#{'0':'不保存日志','1':'保存日志','2':'隐藏屏蔽该功能'}" listKey="key" listValue="value" cssStyle="width:200px;"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>sign</s:param>
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