<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%> 

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<link href="css/Default.css" type="text/css" rel="stylesheet" />
<link href="css/dtree.css" type="text/css" rel="stylesheet" />
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
          d.add('0','-1','&nbsp;<B>数据字典管理</B>');
		  <s:iterator id="var" value="dicTypeList" status="vStatus">
		  <s:if test="#vStatus.count==1">parent.dicContentFrame.location='dictionary_list.do?superDicId=<s:property value="#var.dicId"/>'</s:if>
		  	d.add('<s:property value="#var.dicId"/>','0','<s:property value="#var.dicName"/>',
      			'dictionary_list.do?superDicId=<s:property value="#var.dicId"/>','','dicContentFrame',
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

