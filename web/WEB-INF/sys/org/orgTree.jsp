<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>培训学校综合管理平台--校区单位机构</title>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="themes/icon.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link href="css/dtree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
<div>
</div>
<div align="left">
<script type="text/javascript">
          d = new dTree('d','.');	
          d.icon.root = 'images/root.gif';
          d.add('<s:property value="rootUnitNo"/>','-1','&nbsp;<B>培训学校单位机构</B>','org_list.do?porgNo=<s:property value="rootUnitNo"/>','','orgInfo');
		  <s:iterator id="var" value="orgList" status="vStatus">
		  	d.add('<s:property value="#var.orgNo"/>','<s:property value="#var.porgNo"/>','<s:property value="#var.orgName"/>',
      			'org_list.do?porgNo=<s:property value="#var.orgNo"/>','','orgInfo',
      			'<s:if test="#var.picUrl != null"><s:property value="#var.picUrl"/></s:if><s:else>images/dtree/ico01.gif</s:else>');
     	  </s:iterator>

          document.write(d);     //显示菜单
          //d.openAll();           //展开所有的菜单
          //d.closeAll();        //收缩所有的菜单
</script>
</div>
<br/>
</body>
</html>
