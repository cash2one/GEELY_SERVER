<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css"/> 
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<script src="res/js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/valid/FormValid.js"></script>
<title>E电通智能电网</title>
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
		parent.unitTreeFrame.location.reload();
	}
	
	var t_typeCode = document.all.t_typeCode;
	t_typeCode.setAttribute("valid","required");
	t_typeCode.setAttribute("errmsg","请选择部门类型");
	
});

</script>

<s:form action="dept_save.do" id="deptForm" name="deptForm" onsubmit="return validator(this);" method="post">
<s:hidden name="orgNo"></s:hidden>
<s:hidden name="logOut"></s:hidden>
<s:if test="id!=null">
<s:hidden name="id"></s:hidden>
</s:if>
<br/>	

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>部门信息</legend>
    <!--div class="Single">
      <label>部门编号：</label><s:textfield id="t_deptNo" name="deptNo" cssStyle="width:200px;" maxlength="16" readonly="true"></s:textfield>
      <span style="color:Red">*部门编号由系统自动生成</span>
    </div-->
    <div class="Single">
      <label>部门名称：</label><s:textfield id="t_name" name="name" cssStyle="width:200px;" valid="required|limit" min="1" max="256" errmsg="请输入部门名称|部门名称最大长度为256"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>name</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>简称：</label><s:textfield id="t_abbr" name="abbr" cssStyle="width:200px;" valid="required|limit" min="1" max="256" errmsg="请输入部门简称|部门简称最大长度为256"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>abbr</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>部门类型：</label><s:select id="t_typeCode" name="typeCode" list="#{'01':'部门','02':'班组'}" listKey="key" listValue="value" cssStyle="width:200px;"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>typeCode</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>上级部门：</label><s:select id="t_pdeptNo" name="pdeptNo" list="deptList" listKey="id" listValue="name" headerKey="" headerValue="无上级部门" cssStyle="width:200px;"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>pdeptNo</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>所属单位：</label><input type="text" id="t_orgName" name="orgName" style="width:200px;" value="<s:property value="org.orgName"/>" disabled="disabled"/>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>orgName</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>排序号：</label><s:textfield id="t_dispSN" name="dispSN" cssStyle="width:200px;" maxlength="5" cssStyle="width:100px;" min="1" max="5" valid="required|isInt|limit" errmsg="请输入排序号|排序号应为整形数字|排序号最大长度为5"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>dispSN</s:param>
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