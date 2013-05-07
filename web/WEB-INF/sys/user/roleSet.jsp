<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
<title>E电通智能电网</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var message = "<s:property value="message"/>";
	if (message!="") {
		//alert(message);
	}
	
	$("#idAll").click(function(){
		var c = $(this).attr("checked");
		$("[name='ids']").each(function(){
			if(c){
                $(this).attr("checked",'true');
            }
            else{
            	$(this).removeAttr("checked");
            }
         })
    });    
});

function checkRole(roleId) {
	$("[name='ids']").each(function(){
		var value = $(this).attr("value");
		var checked = $(this).attr("checked");
		if (roleId==value) {
			if ($(this).attr("checked")) {
				$(this).removeAttr("checked");
			} else {
				$(this).attr("checked",'true');
			}
		}
    });
}

function CheckForm(form) {
    return true;
}
</script>
<br/>

<s:form action="user_saveRoleSet.do" id="userForm" name="userForm" onsubmit="return CheckForm(this);" method="post">
<s:hidden name="orgNo"></s:hidden>
<s:hidden name="deptNo"></s:hidden>
<s:hidden name="id"></s:hidden>
<br/>
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset>
    <legend>系统用户(<s:property value="name"/>)角色分配信息</legend>
    <div class="Single">
      <table class="infoList" cellspacing="1" cellpadding="0" rules="all" border="0" id="grid1" style="border-width:0px;width:100%;">
	    <tr>
		  <th scope="col" style="width:3%;">
		    <input type='checkbox' hidefocus='true' id='idAll' name='idAll'/>
		  </th>
		  <th scope="col">角色名称</th>
	    </tr>
	    <s:iterator id="var" value="roleList" status="vStatus">
	    <tr class="<s:if test="#vStatus.count%2==1">trBg01</s:if><s:else>trBg02</s:else>" onclick="RowMouseClick(this,&quot;grid1_<s:property value="#vStatus.count"/>_CheckBoxButton&quot;);">
	      <td class="show" align="center">
	      	<span KeyField="<s:property value="#var.roleId"/>">
	      	<s:set name="role_flag" value="'0'"></s:set>
	        <s:iterator id="var2" value="userRoleList">
	          <s:if test="#var.roleId==#var2.roleId"><s:set name="role_flag" value="'1'"/></s:if>
	        </s:iterator>
	      	<input id="grid1_<s:property value="#vStatus.count"/>_CheckBoxButton" type="checkbox" name="ids" value="<s:property value="#var.roleId"/>" onclick="CheckItem(this,<s:property value="#vStatus.count-1"/>,&quot;grid1_HeaderButton&quot;,&quot;trBg01&quot;,&quot;trBg02&quot;,&quot;trBgHover&quot;,false,<s:property value="totalRecords"/>,false,arguments);" <s:if test="#role_flag=='1'">checked</s:if>/>
	      	</span>
	      </td>
	      <td align="center"><s:property value="#var.roleName"/></td>
	    </tr>
	    </s:iterator>
	  </table>
    </div>
</fieldset>
</div>
<!--Content end-->
<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
    <input name="" type="submit" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left top; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-22px';" onmouseout="this.style.backgroundPositionY='0px';" /> 
	<input name="" type="button" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left -44px; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-66px';" onmouseout="this.style.backgroundPositionY='-44px';" onclick="location='user_list.do?orgNo=<s:property value="orgNo"/>&deptNo=<s:property value="deptNo"/>'" /> 
    </td>
  </tr>
</table>
</s:form>

<s:if test="message!=''">
<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
      <font color="red"><s:property value="message"/></font>
    </td>
  </tr>
</table>
</s:if>
</body>
</html>