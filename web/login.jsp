<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.setHeader("Pragma","no-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
%>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>校讯通手机服务端后台登录</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
<link href="css/style_login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
   function CheckForm() {
            if (!$("#t_userName").val()) {
                alert("登录用户名必须填写!");
                return false;
            }
            if (!$("#t_pwd").val()) {
                alert("登录用户密码必须填写!");
                return false;
            }
            if (!$("#verifyCode").val()) {
                alert("验证码必须填写!");
                return false;
            }
        }

        RefereshParent();
        function RefereshParent() {
            if (parent.parent != null && parent.parent != this) {
                parent.parent.location.reload();
                parent.parent.location = parent.location.href;
            }
            if (parent != null && parent != this) {
                parent.location.reload();
                parent.location = parent.location.href;
            }
        }
        
        $(document).ready(function() {
        	var message = "<s:property value="message"/>";
        	if (message!="") {
        		alert(message);
        	}
        	$("#t_userName").focus();
        });
</script>
</head>


<body>
<center>
<div  class="da">
	<div class="top">&nbsp;
    	<div class="top_l"><a href="<%=basePath%>">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a target="_blank" href="kf/help.jsp">帮助中心</a></div>
    </div>
    <div class="main">
    	<table  cellpadding="0" cellspacing="0" width="670" border="0">
        	<tr>
            	<td height="25" class="login">用户登录</td>
            </tr>
            <tr>
            	<td>
            	 <s:form action="login.do" id="form_login" name="loginForm" method="post" onsubmit="return CheckForm();">
                	<table class="info" cellpadding="0" cellspacing="10" border="0">
        		    	<tr>
            				<td width="10%">用户名：</td>
             				<td width="50%">
                            	  <s:textfield name="loginName" id="t_userName" cssClass="input_name" size="80"></s:textfield>
                            	  &nbsp;&nbsp;
                            </td>
                            <td>	
                                
                            </td>
          			   </tr>
         			   <tr>
            				<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
            				<td>
               				<input name="pass" type="password" id="t_pwd" class="input_name" size="80" />
               				</td>
         			   </tr>
         			   <tr>
           				    <td>验证码：</td>
               				<td><input id="verifyCode" value="" size="80" name="verifyCode" class="input_name" />&nbsp;
               				&nbsp;&nbsp;
               				<img src="imageVerifyCode" id="imageVerifyCode" width="58" height="22" title="看不清，换一张" style="cursor: pointer;" align="middle" onclick="this.src='imageVerifyCode?'+Math.random();"/>
               				</td>
           			  </tr>
                      <tr>
                      	<td colspan="2" class="botton_td">
                        <input type="submit" id="login" name="login" class="botton_n" value=""/>&nbsp;&nbsp;
                        <input type="reset" id="restart" name="restart" class="botton_c" value=""/>
                        </td>
                      </tr>
                      
    			  </table>
            </s:form>
              </td>
            </tr>
        </table>
    </div>
    
    
</div>
</center>
</body>

</html>
