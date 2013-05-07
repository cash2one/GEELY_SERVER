<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.setHeader("Pragma","no-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
%>
<base href="<%=basePath%>">
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="css/Style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript">
$.ajaxSetup ({
    cache: false //关闭AJAX相应的缓存
});

$(document).ready(function() {
	$(":input").focus(function() {
		//$(this).addClass("OnBlurCSS");
	}).blur(function() {
		//$(this).removeClass("OnBlurCSS");
	});
});
</script>