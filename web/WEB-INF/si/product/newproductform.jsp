<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/common/include.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校讯通手机服务端-新产品推荐发布</title>
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
	if (!$("#title").val()){
		$.messager.alert('信息输入未完整','请输入产品名称!!!');
   		$("#t_deptId").focus();
   		return false;
	}
	if (!$("#price").val()){
		$.messager.alert('信息输入未完整','请输入价格!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#customer").val()){
		$.messager.alert('信息输入未完整','请输入服务对象!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#intro").val()){
		$.messager.alert('信息输入未完整','请输入介绍!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#images").val() && !$("#id").val()){
		$.messager.alert('信息输入未完整','请选择产品图片!!!');
   		$("#t_login").focus();
   		return false;
	}
	if (!$("#recommend").val()){
		$.messager.alert('信息输入未完整','请输入短信推荐内容!!!');
   		$("#t_name").focus();
   		return false;
	}
	if (!$("#introimgs").val() && !$("#id").val()){
	$.messager.alert('信息输入未完整','请选择详细产品图片!!!');
   	$("#t_login").focus();
   		return false;
	}
    return true;
}

</script>
<br/>

<s:form action="product_saveproduct.do" id="versionForm" name="versionForm" onsubmit="return CheckForm(this);" method="post" theme="simple" enctype="multipart/form-data">
<!--Content start -->
<div style="width:96%;margin:0 auto;">
<fieldset class="VForm">
    <legend>新产品推荐发布</legend>
    <div class="Single">
      <label>产品名称：</label><s:textfield id="title" name="title" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入产品名称|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>title</s:param>
      </s:fielderror>
      </span>
    </div>
     <div class="Single">
      <label>价格：</label><s:textfield id="price" name="price" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入价格|用户名最大长度为8" min="1" max="8"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>price</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>服务对象：</label><s:textfield id="customer" name="customer" cssStyle="width:200px;" maxlength="30" valid="required|limit" errmsg="请输入服务对象|用户名最大长度为64" min="1" max="64"></s:textfield>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>customer</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>介绍：</label><s:textarea id="intro" name="intro" cssStyle="height:80px;width:200px;"></s:textarea>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>intro</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>产品图片：</label><s:file id="images" name="images" cssStyle="width:200px;"></s:file>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>image</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>推荐星级：</label><s:select id="star" name="star" cssStyle="width:200px;" list="{1,2,3,4,5}" onchange="aaa()"></s:select>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>star</s:param>  
      </s:fielderror>
      </span>
    </div>
    <div class="Pairs">
      <label>推荐短信内容：</label><s:textarea id="recommend" name="recommend" cssStyle="height:80px;width:200px;"></s:textarea>
      <span style="color:Red">*
      <s:fielderror>
      <s:param>recommend</s:param>
      </s:fielderror>
      </span>
    </div>
    <div class="Single">
      <label>详细产品图片：</label><s:file id="introimgs" name="introimgs" cssStyle="width:200px;"></s:file>
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