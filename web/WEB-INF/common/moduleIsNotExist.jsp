<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- 功能模块不存在提示页面 -->
<!-- Author: Long XiangYong 2008.12.09 -->

<html>
<head>
<title>功能模块不存在提示页面</title>
<link href="css/Default.css" type="text/css" rel="stylesheet" />
</head>

<body>
<br/>
<br/>

<table cellpadding="4" cellspacing="1" border="0" width="90%" class="border" align="center" bgcolor="#0099cc">
    <tr align="center" class="title">
        <td align="left" bgcolor="#FFFfff" style="background:url(images2/tab_14.gif) repeat-x left center;">
            <strong>::: 信息提示 :::</strong></td>
           </tr>
           <tr>
               <td valign="top" height="100" style="background:url(images2/Msg02.gif) no-repeat left center #F7FAFE; padding-left:95px;">
                   <br />
                   <b>系统提示信息如下：</b><br /><br/>
               	对不起，您访问的功能模块不存在或尚未完成！
</td>
           </tr>
           <tr>
               <td class="tdToolsCenter" style="border:none">
                   <input id="btn_ok" class="btn02" type="submit" name="btn_ok" value="返回" onclick="history.back();" onmouseover="	this.className = 'btn02a';" onmouseout="	this.className = 'btn02';" />
        </td>
    </tr>
</table>

<!--table width="100%" align="center" class="tableEditBorderNone">
  <tr>
    <td height="30">
      <table width="100%" class="tableEditBorderNone">
        <tr>
          <td class="tdEditTopLeft">&nbsp;</td>
          <td class="tdEditTopCenter">
            <img src="res/images/workspace/titleLog.gif" width="16" height="16" /> 
            <span class="fontEditTop">友情提示</span>
          </td>
          <td class="tdEditTopRight">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
      <table width="100%" class="tableEditBorderNone">
        <tr>
          <td class="tdEditMiddleLeft">&nbsp;</td>
          <td class="tdEditMiddleCenter">
            <table width="100%" align="center" class="tableEditOuter">
              <tr class="trEdit">
                <td class="tdEidt">
                  <table width="70%" align="center" class="tableEdit">
                    <tr class="trEdit">
                      <td class="tdEidtDynamicText" width="100%" height="30" style="padding-left: 5;">系统提示信息如下：</td>
                    </tr>
                    <tr class="trEdit">
                      <td class="tdEidtDynamicText" width="100%" height="60" valign="top" style="padding-top: 5; padding-left: 5;">
                        <span>对不起，您访问的功能模块不存在或尚未完成！</span>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>               
          </td>
          <td class="tdEditMiddleRight">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height="29">
      <table width="100%" class="tableEditBorderNone">
        <tr>
          <td class="tdEditBottomLeft">&nbsp;</td>
          <td class="tdEditBottomCenter">
            <table width="100%" class="tableEditBorderNone">
              <tr>
                <td width="100%" height="100%" align="center">
                  <input type="button" class="button" style="width:60" value="返回" onclick="history.back();">
                </td>
              </tr>
            </table>
          </td>
          <td class="tdEditBottomRight">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table-->
</body>
</html>