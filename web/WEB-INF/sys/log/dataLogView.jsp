<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-数据日志发布管理</title> 
<link href="themes/easyui.css" rel="stylesheet" type="text/css"/>
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script src="js/ExtendedGridView.js" type="text/javascript"></script>
</head>
<body>
<br/>

<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>日志信息</legend>
    
    <TABLE cellSpacing=0 cellPadding=3 width=100% border=0 style="text-align: left">
	    <TR class="Pairs">
	    	<TD width="12%">
	    		类名：
	    	</TD>
	    	<TD width="18%">
			    <s:property value="className"/>
	    	</TD>
	    	<TD width="12%">
			      方法名：
	    	</TD>
	    	<TD width="18%">
			       <s:property value="methodName"/>
	    	</TD>
	    	<TD>
	    	日志时间：
	    	</TD>
	    	<td>
	    	<s:date name="time" format="yyyy-MM-dd HH:mm"/>
	    	</td>
	    	</tr>
	    	<tr>
	    	<TD width="12%">
	    		日志信息：
	    	</TD>
	    	<TD colspan="3">
						<s:property value="message"/>
			</TD>
	    	
	    	</TR>
    </TABLE>
</fieldset>
</div>
<br/>
<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
	<input name="" type="button" style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left -44px; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-66px';" onmouseout="this.style.backgroundPositionY='-44px';" onclick="window.close()" /> 
    </td>
  </tr>
</table>
<!--Content end-->
</body>
</html>
