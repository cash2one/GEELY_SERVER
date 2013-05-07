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

function checkByCode(menuByCode){
  alert(menuByCode);
  var menuCode = document.getElementById(menuByCode);
  alert(menuCode.length);
}
</script>
</head>

<body onload="dynamicChangeFontColor();">
<table cellSpacing="0" cellPadding="0" width="100%" border="0" align="center" class="navTable">
  <tr>
     <td class="navInfo" colspan="2">&nbsp; <img id="showTreeBtn" alt="隐藏树形菜单" src="images2/navIco.gif" onclick="ShowTree();" style="cursor:pointer;"/>&nbsp;
     	<span id="LocationNavigation1">您的当前位置：首页<span>
           <img src="images2/right_disabled.gif" align="absmiddle" alt="" />
        </span><a>用户区域权限分配</a></span>
     </td>
  </tr>
</table>

<s:form action="user_saveCounty.do" id="unitForm" name="unitForm" onsubmit="return CheckForm(this);" method="post">
<s:hidden name="empNo"></s:hidden>
<s:hidden name="deptNo"/>
<s:hidden name="orgNo"/>
<!--<s:if test="roleId!=null">-->
<!--<s:hidden name="roleId"></s:hidden>-->
<!--</s:if>-->
<br/>	

<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
      <input id="btn_ok" class="btn02" type="submit" name="btn_ok" value="提交" onmouseover="this.className='btn02a';" onmouseout="this.className='btn02';" />
      <input id="btn_back" class="btn02" type="button" name="btn_back" value="返回" onclick="location='user_list.do?orgNo=<s:property value="orgNo"/>&deptNo=<s:property value="deptNo"/>&pageNo=<s:property value="pageNo"/>'" onmouseover="this.className='btn02a';" onmouseout="this.className='btn02';" />
    </td>
  </tr>
</table>

<br/>

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset>
    <legend>用户 (<s:property value="name"/>) 区域管理权限分配信息</legend>
    <div class="Single" style="width: 100%">
      <table width="100%">
        <tr>
        	<td class="tdEidtStaticText" style="text-align: left;" width="100%">
        	  <input id="menu" name="checkAll" type="checkbox" value="" class="checkboxEdit" onclick="checkById(this.form,this)"/>
        	  <span class="fontEditTop"><b>全选/反选</b></span><br/>
        	</td>
        </tr>
      	<!-- 城市 -->
        <s:iterator value="cityList" id="var">
        <tr id="tr_menu<s:property value="#var.moduleCode"/>">
        	<td class="tdEidtStaticText" style="text-align: left;" width="100%">
<!--              <input id="menu<s:property value="#var.cityCode"/>" name="module" type="checkbox" value="<s:property value="#var.cityCode"/>" class="checkboxEdit" onclick="checkByCode('menu<s:property value="#var.cityCode"/>')" />-->
<!--              <font id="font_menu<s:property value="#var.cityCode"/>"><b><s:property value="#var.cityName"/></b></font>-->
              <input id="menu<s:property value="#var.cityCode"/>" name="module" type="checkbox" value="<s:property value="#var.cityCode"/>" class="checkboxEdit" onclick="checkById(this.form,this)" />
              <font id="font_menu<s:property value="#var.cityCode"/>"><b><s:property value="#var.cityName"/></b></font>
               <!-- 区县 -->
               <s:if test="#var.countys.size>0">
                    <br/>
                    <table width="100%">
                      <tr class="trEdit">
                        <td class="tdEidtStaticText" style="text-align: left;" width="20">&nbsp;</td>
                        <td class="tdEidtStaticText" style="text-align: left;">
           
                          <s:iterator value="#var.countys" id="var3" status="st">
                          <!-- 已分配的 区县-->
                          <s:set name="checkedValue" value='""'></s:set> 
                          <s:iterator value="userCountyPrivites" id="rolePriv" status="st">
                            <s:if test="#rolePriv.countyCode == #var3.countyCode">
                              <s:set name="checkedValue" value='"checked"'></s:set>
                            </s:if>
                          </s:iterator>
                          <input id="menu<s:property value="#var.cityCode"/>" name="countyCodes" type="checkbox" value="<s:property value="#var3.countyCode"/>" class="checkboxEdit" <s:property value="checkedValue"/> />
                          <font id="font_menu<s:property value="#var.cityCode"/>"><s:property value="#var3.countyName"/></font>  
                          <s:if test="#st.count%5==0"><br/></s:if>
                          </s:iterator>
                        </td>
                      </tr>
                    </table>
               </s:if>
            </td>
        </tr>
        </s:iterator>
      </table>
    </div>
</fieldset>
</div>
<!--Content end-->

<br/>
</s:form>
</body>
</html>