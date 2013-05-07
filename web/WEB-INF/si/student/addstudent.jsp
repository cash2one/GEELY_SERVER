<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-新增学生信息</title>
<link href="themes/easyui.css" rel="stylesheet" type="text/css"/>
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>
<script type="text/javascript" src="js/valid/FormValid.js"></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	
	$.post("province!getProvince.do", function (data) {  
         var jsonObj = eval("(" + data + ")");  
         for (var i = 0; i < jsonObj.length; i++) {  
             var $option = $("<option></option>");  
             $option.attr("value", jsonObj[i].id);  
             $option.text(jsonObj[i].province);  
             $("#province").append($option);  
         }  
		}); 
		
		 $("#province").change(function () {  
         $.post("area!getArea.do", {id:$("#province").val()}, function (data) {  
             /* 清空城市 */  
             $("#city option[value!='']").remove();  
            /* 清空乡镇 */  
             $("#town option[value!='']").remove();  
             var jsonObj = eval("(" + data + ")");  
            for (var i = 0; i < jsonObj.length; i++) {  
                 var $option = $("<option></option>");  
                 $option.attr("value", jsonObj[i].id);  
                $option.text(jsonObj[i].area);  
                 $("#city").append($option);  
            }  
         });  
    });  
    
    /* 根据城市获取乡镇 */  
    $("#city").change(function () {  
        $.post("town!getTown.do", {id:$("#city").val()}, function (data) {  
            /* 清空乡镇 */  
            $("#town option[value!='']").remove();  
            var jsonObj = eval("(" + data + ")");  
            for (var i = 0; i < jsonObj.length; i++) {  
                var $option = $("<option></option>");  
                $option.attr("value", jsonObj[i].id);  
                $option.text(jsonObj[i].town);  
                $("#town").append($option);  
            }  
         });  
    });  
	
	var message = "<s:property value="message"/>";
	if (message!="") {
		 $.messager.alert('信息提示',message);
	}
});

//获取部门下拉列表
function getDeptList(orgNo) {
	$.ajax({
   		type: "POST",
   		url: "user_deptList.do",
   		data: "orgNo="+orgNo+"&deptNo=<s:property value="deptId"/>",
   		success: function(msg){
     		$('#s_deptNo').html(msg);
   		}
   	});
}

function mysubmit(){
	//$("#versionForm").submit(); 
	//window.location="product_seacher.do";
	alert($("#images").val());
}

function CheckForm(form) {
	if (!$("#province").val()){
		$.messager.alert('信息输入未完整','请选择省份!!!');
   		$("#t_deptId").focus();
   		return false;
	}
	if (!$("#city").val()){
		$.messager.alert('信息输入未完整','请选择城市!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#town").val()){
		$.messager.alert('信息输入未完整','请选择区域!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#name").val()){
		$.messager.alert('信息输入未完整','请输入学生姓名!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#parent").val()){
		$.messager.alert('信息输入未完整','请输入家长姓名!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#mobile").val()){
		$.messager.alert('信息输入未完整','请输入手机号码!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#pass").val()){
		$.messager.alert('信息输入未完整','请输入客户端登录密码!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#service").val()){
		$.messager.alert('信息输入未完整','请输入订购服务!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#loginname").val()){
		$.messager.alert('信息输入未完整','请输入登录帐号!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#status").val()){
		$.messager.alert('信息输入未完整','请输入帐号状态!!!');
   		$("#t_name").focus();
   		return false;
	}
	
	
    return true;
}

</script>
<br/>

<s:form action="student_savestudent" id="versionForm" name="versionForm" onsubmit="return CheckForm(this);" method="post" theme="simple" enctype="multipart/form-data">
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>新增学生信息</legend>
    <div class="Single">
      <s:hidden id="id" name="id"></s:hidden>
           <label>省:</label><select id="province" name="province">  
            <option value="">请选择</option>  
            </select> 
            <br/>
           <label>市:</label><select id="city" name="city">  
            <option value="">请选择</option>  
            </select>
            <br/>
           <label>区:</label><select id="town" name="town">  
            <option value="">请选择</option>  
            </select>
            <br />
            </div>
      <div class="Single">
      <label>学生姓名：</label><s:textfield id="name" name="name" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>name</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>学生号：</label><s:textfield id="stdno" name="stdno" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>stdno</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>家长姓名：</label><s:textfield id="parent" name="parent" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>parent</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>所在年级：</label><s:select id="grade" name="grade" cssStyle="width:200px;" list="#{'01':'小一','02':'小二','03':'小三','04':'小四','05':'小五','06':'小六','07':'初一','08':'初二','09':'初三','10':'高一','11':'高二','12':'高三','13':'其它'}"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>star</s:param>  
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>手机号码：</label><s:textfield id="mobile" name="mobile" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>mobile</s:param>
      </s:fielderror>
      </span>
    </div>
     <div class="Single">
      <label>客户端登录密码：</label><s:textfield id="pass" name="pass" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>pass</s:param>
      </s:fielderror>
      </span>
    </div>
     <div class="Single">
      <label>订购服务：</label><s:textfield id="service" name="service" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>service</s:param>
      </s:fielderror>
      </span>
    </div>
     <div class="Single">
      <label>登录帐号：</label><s:textfield id="loginname" name="loginname" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>loginname</s:param>
      </s:fielderror>
      </span>
    </div>
     <div class="Single">
      <label>帐号状态：</label><s:select id="status" name="status" cssStyle="width:200px;" list="#{'01':'正常','02':'注销','03':'锁定'}"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>star</s:param>  
      </s:fielderror>
      </span>
    </div>
    
   
  
</fieldset>
</div>
<br/>
<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="table">
  <tr>
    <td class="tdToolsCenter" style="text-align:left;padding-left:20px;">
   <input name="" type="submit" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left top; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-22px';" onmouseout="this.style.backgroundPositionY='0px';" /> 
	<input name="" type="button" value=" " style="width:75px;height:22px;border:none;background:url(images/Btn.gif) no-repeat left -44px; cursor:pointer;" onmouseover="this.style.backgroundPositionY='-66px';" onmouseout="this.style.backgroundPositionY='-44px';" onclick="history.back();" /> 
    </td>
  </tr>
</table>
</s:form>
<!--Content end-->
</body>
</html>