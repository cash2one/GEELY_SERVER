<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左边导航菜单</title>
<link href="ui_themes/easyui.css" rel="stylesheet" type="text/css">
<link href="css/page_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
        $(document).ready(function() {
            $(".leftMenu02").bind('mouseover', function() { $(this).addClass("leftMenu02Hover"); });
            $(".leftMenu02").bind('mouseout', function() { $(this).removeClass("leftMenu02Hover"); });
            $(".leftMenu02").click(function() {
                $(".leftMenu02").removeClass("leftMenu02Hover");
                $(this).unbind("mouseover");
                $(this).unbind("mouseout");
                $(this).addClass("leftMenu02Hover");
            });           
        });

</script>
</head>

<body>
	<table cellspacing="0" cellpadding="0" border="0" height="100%" bgcolor="#F6F9FD">
        <tr>
            <td valign="top">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                    <tr>
                        <td height="9"></td>
                    </tr>
                    <!--后台生成二级菜单-->
					<s:iterator id="var" value="list">
                    <tr>
                        <td class='leftMenu02' onclick="parent.addTab('<s:property value="#var.moduleName"/>','<s:property value="#var.moduleLink"/>','<s:property value="#var.count"/>','<s:property value="#var.moduleId"/>')">
                        	<img alt='' src='images/ico01.gif' align='absmiddle' /> <a href='javascript:void(0);' onfocus='this.blur()'><s:property value="#var.moduleName"/></a>
                        </td>
                    </tr>					
					</s:iterator>                   
                </table>
            </td>
        </tr>
    </table>
</body>
</html>
