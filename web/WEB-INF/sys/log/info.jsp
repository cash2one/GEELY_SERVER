<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="css/page_style.css" rel="stylesheet" type="text/css" />
        <link href="css/form_style.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>校讯通手机服务端-密码修改</title>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.p.js"></script>
		<script type="text/javascript" language="javascript" src="js/Validate.js"></script>
		<script src="res/js/common.js" type="text/javascript"></script>
		<script src="js/ExtendedGridView.js" type="text/javascript"></script>
		<script language="javascript"></script>
	</head>
	<body>
		<center>
			<table cellpadding="4" cellspacing="1" border="0" width="90%" class="border" align="center" bgcolor="#0099cc">
                <tr align="center" class="title">
                    <td align="left" bgcolor="#FFFfff" style="background:url(images2/tab_14.gif) repeat-x left center;">
                        <strong>::: 信息提示 :::</strong></td>
                </tr>
                <tr>
                    <td valign="top" height="100" style="background:url(images2/Msg02.gif) no-repeat left center #F7FAFE; padding-left:95px;">
                        <br />
                        <b></b>系统提示信息如下：<br /><br/>
						<s:property value="message"/>
					</td>
                </tr>
                <tr>
                    <td class="tdToolsCenter" style="border:none">
                    <input name="" type="button" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left -44px; cursor:pointer;text-align: center;" onmouseover="this.style.backgroundPositionY='-66px';" onmouseout="this.style.backgroundPositionY='-44px';" onclick="location='ChangePwd.do'" /> 
                    </td>
                </tr>
            </table>
            
			<!--Content end -->

			<br />
			<script src="js/date/WdatePicker.js" type="text/javascript"></script>
		</center>
	</body>
</html>
