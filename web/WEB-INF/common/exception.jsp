<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- 异常输出页面 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>异常输出页面</title>
<link href="css/Default.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
<!--
function showOrHideExceptionStack()
{	
	var content_stack=document.getElementById("content_stack");
	var btnShow=document.getElementById("btnShow");
	if (content_stack.style.display == "none") {
		content_stack.style.display = "block";
		btnShow.value = "隐藏详细信息";
	} else {
		content_stack.style.display = "none";
		btnShow.value = "显示详细信息";
	}
	/*
    if (document.all.trExceptionStack.style.display == "none")
    {
        document.all.trExceptionStack.style.display = "block";
        document.all.btnShow.value = "隐藏详细信息";
    }
    else
    {
        document.all.trExceptionStack.style.display = "none";
        document.all.btnShow.value = "显示详细信息";
    }*/    
}
//-->
</script>
</head>

<body>
<br/>
<br/>
<table cellpadding="4" cellspacing="1" border="0" width="90%" class="border" align="center" bgcolor="#0099cc">
     <tr align="center" class="title">
         <td align="left" bgcolor="#FFFfff" style="background:url(images2/tab_14.gif) repeat-x left center;">
             <strong>::: 异常提示 :::</strong></td>
           </tr>
           <tr>
               <td valign="top"  height="100" style="background:#F7FAFE url(images2/Msg02.gif) no-repeat 20px center; padding-left:135px;">
                   <br />
                   <b>系统处理过程中发生异常，信息如下:</b><br /><br/>
               		<s:property value="exception.message"/><!-- 输出异常对象本身 -->
               		<br/>
               		<span id="content_stack" style="display:none"><s:property value="exceptionStack"/></span><!-- 输出异常堆栈信息 -->
</td>
           </tr>
           <tr>
               <td class="tdToolsCenter" style="border:none">
               		<input id="btnShow" class="btn02" type="button" name="btn_ok" value="查看详细信息" onclick="javascrip:showOrHideExceptionStack();" onmouseover="	this.className = 'btn02a';" onmouseout="	this.className = 'btn02';" />
                   <input id="btn_ok" class="btn02" type="button" name="btn_ok" value="返回" onclick="history.back();" onmouseover="	this.className = 'btn02a';" onmouseout="	this.className = 'btn02';" />
         </td>
     </tr>
 </table>
</body>
</html>