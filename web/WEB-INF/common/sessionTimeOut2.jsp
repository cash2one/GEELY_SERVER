<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- 会话超时提示页面 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会话超时提示页面</title>
<link href="css/Default.css" rel="stylesheet" type="text/css" />
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
               <td valign="top" height="100" style="background:#F7FAFE url(images2/Msg02.gif) no-repeat 20px center; padding-left:135px;">
                   <br />
                   <b>系统提示信息如下：</b><br /><br/>
               		会话超时，请重新<a href="#" onclick="top.location.href='login.jsp'">登录！</a>
</td>
           </tr>
           <tr>
               <td class="tdToolsCenter" style="border:none">
                   <input id="btn_ok" class="btn02" type="submit" name="btn_ok" value="返回" onclick="history.back();" onmouseover="	this.className = 'btn02a';" onmouseout="	this.className = 'btn02';" />
         </td>
     </tr>
 </table>


</body>
</html>