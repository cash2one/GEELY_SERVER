<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.setHeader("Pragma","no-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>" />
    <title>正在处理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  <table height="100%" width="100%">
  <tr><td valign="middle" align="center">
   <img src="res/colorbox/images/loading.gif"/><br/>
   正在处理关键业务，请不要关闭当前窗口，请稍候... 
</td>   </tr>
   </table>
  </body>
</html>
