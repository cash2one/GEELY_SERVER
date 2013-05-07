<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-学校信息修改</title>
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
	$.post("sprovince!getProvince.do", function (data) {  
         var jsonObj = eval("(" + data + ")");  
         for (var i = 0; i < jsonObj.length; i++) {  
             var $option = $("<option></option>");  
             $option.attr("value", jsonObj[i].id);  
             $option.text(jsonObj[i].province);  
             $("#sprovince").append($option);  
         }  
		}); 
		
		 $("#sprovince").change(function () {  
         $.post("area!getArea.do", {id:$("#sprovince").val()}, function (data) {  
             /* 清空城市 */  
             $("#scity option[value!='']").remove();  
            /* 清空乡镇 */  
             $("#stown option[value!='']").remove();  
             var jsonObj = eval("(" + data + ")");  
            for (var i = 0; i < jsonObj.length; i++) {  
                 var $option = $("<option></option>");  
                 $option.attr("value", jsonObj[i].id);  
                $option.text(jsonObj[i].area);  
                 $("#scity").append($option);  
            }  
         });  
    });  
    
    /* 根据城市获取乡镇 */  
    $("#scity").change(function () {  
        $.post("town!getTown.do", {id:$("#scity").val()}, function (data) {  
            /* 清空乡镇 */  
            $("#stown option[value!='']").remove();  
            var jsonObj = eval("(" + data + ")");  
            for (var i = 0; i < jsonObj.length; i++) {  
                var $option = $("<option></option>");  
                $option.attr("value", jsonObj[i].id);  
                $option.text(jsonObj[i].town);  
                $("#stown").append($option);  
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


function CheckForm(form) {
	
	if (!$("#name").val()){
		$.messager.alert('信息输入未完整','请输入教师姓名!!!');
   		$("#t_name").focus();
   		return false;
	}
	
    return true;
}

</script>
<br/>

<s:form action="school_saveschool.do" id="versionForm" name="versionForm" onsubmit="return CheckForm(this);" method="post" theme="simple" enctype="multipart/form-data">
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>学校信息修改</legend>
    <div class="Single">
          <s:hidden id="id" name="id"></s:hidden>
           <label>省:</label><select id="sprovince" name="sprovince">  
            <option value="">请选择</option>  
            </select> 
            <br/>
           <label>市:</label><select id="scity" name="scity">  
            <option value="">请选择</option>  
            </select>
            <br/>
           <label>区:</label><select id="stown" name="stown">  
            <option value="">请选择</option>  
            </select>
            <br />
            </div>
     <div class="Single">
      <label>学校名字：</label><s:textfield id="name" name="name" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>name</s:param>
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