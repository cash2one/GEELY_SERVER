<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/Default.css" type="text/css" rel="stylesheet" />
<title>E电通智能电网</title>
<script src="js/htmldom.js" type="text/javascript"></script>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script type="text/javascript" src="js/valid/FormValid.js"></script>
<script type="text/javascript">
function CheckForm(form) {
    return true;
}

//动态改变复选框文字的颜色
function dynamicChangeFontColor()
{
    for(var i = 0; i < document.getElementsByTagName("font").length; i++)
    {
        var e = document.getElementsByTagName("font")[i];
        var chk = document.getElementById(e.id.substring(5));
        
        if(chk.checked == true)
        {
            e.color = "blue";
        }
        else
        {
            e.color = "black"
        }
    }
}
</script>
</head>

<body onload="dynamicChangeFontColor();">
<br/>	
<s:form action="role_saveRolePriv.do" id="unitForm" name="unitForm" onsubmit="return CheckForm(this);" method="post">
<s:hidden name="pageNo"></s:hidden>
<s:if test="roleId!=null">
<s:hidden name="roleId"></s:hidden>
</s:if>
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>系统角色(<s:property value="roleName"/>)权限分配信息</legend>
    <div class="Single" style="width: 100%">
      <table width="100%">
        <tr>
        	<td class="tdEidtStaticText" style="text-align: left;" width="100%">
        	  <input id="menu" name="checkAll" type="checkbox" value="" class="checkboxEdit" onclick="checkById(this.form,this)"/>
        	  <span class="fontEditTop"><b>全选/反选</b></span><br/>
        	</td>
        </tr>
      	<!-- 第一级菜单 -->
        <s:iterator value="moduleList" id="var">
        <tr id="tr_menu<s:property value="#var.moduleCode"/>">
        	<td class="tdEidtStaticText" style="text-align: left;" width="100%">
              <input id="menu<s:property value="#var.moduleCode"/>" name="module" type="checkbox" value="<s:property value="#var.moduleId"/>" class="checkboxEdit" onclick="checkById(this.form,this)" />
              <font id="font_menu<s:property value="#var.moduleCode"/>"><b><s:property value="#var.moduleName"/></b></font>
              <table width="100%">
              	<!-- 第二级菜单 -->
                <s:iterator value="#var.childList" id="var2">
                <tr class="trEdit" id="tr_menu<s:property value="#var2.moduleCode"/>" style="display: block">
                  <td class="tdEidtStaticText" style="text-align: left;" width="20">&nbsp;</td>
                  <td class="tdEidtStaticText" style="text-align: left;">
                    <input id="menu<s:property value="#var2.moduleCode"/>" name="module" type="checkbox" value="<s:property value="#var2.moduleId"/>" class="checkboxEdit" onclick="checkById(this.form,this)" />
                    <font id="font_menu<s:property value="#var2.moduleCode"/>"><b><s:property value="#var2.moduleName"/></b></font>
                    <s:if test="#var2.privList.size>0">
                    <br/>
                    <table width="100%">
                      <tr class="trEdit">
                        <td class="tdEidtStaticText" style="text-align: left;" width="20">&nbsp;</td>
                        <td class="tdEidtStaticText" style="text-align: left;">
                          <!-- 功能权限 -->
                          <s:iterator value="#var2.privList" id="var3" status="st">
                          <!-- 已分配的功能权限 -->
                          <s:set name="checkedValue" value='""'></s:set>
                          <s:iterator value="rolePrivList" id="rolePriv" status="st">
                            <s:if test="#rolePriv.privId == #var3.privId">
                              <s:set name="checkedValue" value='"checked"'></s:set>
                            </s:if>
                          </s:iterator>
                          <input id="menu<s:property value="#var2.moduleCode"/>_privilege<s:property value="#var3.privId"/>" name="ids" type="checkbox" value="<s:property value="#var3.privId"/>" class="checkboxEdit" <s:property value="checkedValue"/> />
                          <font id="font_menu<s:property value="#var2.moduleCode"/>_privilege<s:property value="#var3.privId"/>"><s:property value="#var3.methodDesc"/></font>  
                          <s:if test="#st.count%8==0"><br/></s:if>
                          </s:iterator>
                        </td>
                      </tr>
                    </table>
                    </s:if>
                  </td>
                </tr>
                </s:iterator>
              </table>
            </td>
        </tr>
        </s:iterator>
      </table>
    </div>
</fieldset>
</div>
<br/>
<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
    <input name="" type="submit" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left top; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-22px';" onmouseout="this.style.backgroundPositionY='0px';" /> 
	<input name="" type="button" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left -44px; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-66px';" onmouseout="this.style.backgroundPositionY='-44px';" onclick="location='role_list.do?pageNo=<s:property value="pageNo"/>'" /> 
    </td>
  </tr>
</table>
<!--Content end-->
<br/>
</s:form>
</body>
</html>