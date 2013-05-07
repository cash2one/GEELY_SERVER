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
          //d.add('U_<s:property value="rootUnitNo"/>','-1','&nbsp;<B>校讯通手机服务端单位机构</B>','','','userInfoFrame');
		  <s:iterator id="var" value="orgList" status="vStatus">
		  	d.add('U_<s:property value="#var.orgNo"/>','<s:property value="#var.porgNo==rootUnitNo?-1:'U_'+#var.porgNo"/>','<s:property value="#var.orgName"/>',
      			'user_list.do','','userInfoFrame',
      			'<s:if test="#var.picUrl != null"><s:property value="#var.picUrl"/></s:if><s:else>images/dtree/ico01.gif</s:else>');
      		<s:iterator id="var2" value="deptList" status="vStatus2">
      		<s:if test="#var.orgNo==#var2.orgNo">
      			<s:if test="#var2.pdeptNo!=null && #var2.pdeptNo!=''">
      				d.add('D_<s:property value="#var2.id"/>','D_<s:property value="#var2.pdeptNo"/>','<s:property value="#var2.name"/>',
      				'user_list.do?orgNo=<s:property value="#var2.orgNo"/>&deptId=<s:property value="#var2.id"/>','','userInfoFrame',
      				'<s:if test="#var2.picUrl != null"><s:property value="#var2.picUrl"/></s:if><s:else>images/dtree/user-group.png</s:else>',
      				'<s:if test="#var2.picUrl != null"><s:property value="#var2.picUrl"/></s:if><s:else>images/dtree/user-group.png</s:else>');
      			</s:if>
      			<s:else>
      				d.add('D_<s:property value="#var2.id"/>','U_<s:property value="#var2.orgNo"/>','<s:property value="#var2.name"/>',
      				'user_list.do?orgNo=<s:property value="#var2.orgNo"/>&deptId=<s:property value="#var2.id"/>','','userInfoFrame',
      				'<s:if test="#var2.picUrl != null"><s:property value="#var2.picUrl"/></s:if><s:else>images/dtree/user-group.png</s:else>',
      				'<s:if test="#var2.picUrl != null"><s:property value="#var2.picUrl"/></s:if><s:else>images/dtree/user-group.png</s:else>');
      			</s:else>
      		</s:if>
      		</s:iterator>
     	  </s:iterator>

          document.write(d);     //显示菜单
          //d.openAll();           //展开所有的菜单
          //d.closeAll();        //收缩所有的菜单
</script>
</div>
<br/>
</body>
</html>
