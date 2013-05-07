<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<link href="css/dtree.css" type="text/css" rel="stylesheet" />
<link href="ui_themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<title></title>
<script type="text/javascript" src="js/dtree.js"></script>
</head>
<body>
<div>
</div>
<div align="left">
<script type="text/javascript">
          d = new dTree('d','.');	
          d.icon.root = 'images/dtree/root.gif';
          d.add('0','-1','&nbsp;<B>系统功能模块</B>','','','deptInfoFrame');
		  <s:iterator id="var" value="moduleList" status="vStatus">
		  	<s:property value="#var.parentModuleCode"/>
		  	<s:if test="#var.parentModuleCode!=0">
		  	d.add('<s:property value="#var.moduleCode"/>','<s:property value="#var.parentModuleCode"/>','<s:property value="#var.moduleName"/>',
      			'privilege_list.do?moduleId=<s:property value="#var.moduleId"/>','','privielegeInfoFrame',
      			'<s:if test="#var.picUrl != null"><s:property value="#var.picUrl"/></s:if><s:else>images/dtree/ico01.gif</s:else>');
      		</s:if>
      		<s:else>
      		d.add('<s:property value="#var.moduleCode"/>','<s:property value="#var.parentModuleCode"/>','<s:property value="#var.moduleName"/>',
      			'','','privielegeInfoFrame',
      			'<s:if test="#var.picUrl != null"><s:property value="#var.picUrl"/></s:if><s:else>images/dtree/ico01.gif</s:else>');
      		</s:else>
     	  </s:iterator>

          document.write(d);     //显示菜单
          //d.openAll();           //展开所有的菜单
          //d.closeAll();        //收缩所有的菜单
</script>
</div>
<br/>
</body>
</html>
