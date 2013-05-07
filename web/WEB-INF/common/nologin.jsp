<%@ page contentType="text/html; charset=utf-8" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires",0);

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 异常输出页面 -->
<html>
<head>
<base href="<%=basePath %>"/>
<title>未登录超时</title>
<!-- link href="css/globalnologin.css" rel="stylesheet" type="text/css" /-->
</head>
<center>
<body>
	<!-- div class="all_x">
    	<span ><img src="images/donghua.gif" align="absbottom" /></span>
        <span class="errorf">用户未登录！</span>
        <div class="error2"><p>请您点击&nbsp;<a href="index.jsp" target="_top" style="color:red;font-size:16px;font-weight:bold;">返回主界面！</a></p></div>
        <div class="error2"><p>请您点击&nbsp;<a href="register!register.do" target="_top" style="color:red;font-size:16px;font-weight:bold;">注册！</a></p></div>
        
      
    </div -->
    <script>window.location='front_loginView.do';</script>
    
</body>
</center>
</html>
