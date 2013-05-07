<%@ page contentType="text/html;charset=UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<title>校讯通手机服务端-系统功能权限管理</title>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/ShowTree.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    //privielegeInfoFrame.location = 'privilege_add.do';
	privilegeTreeFrame.location = 'privilege_tree.do';
});
</script>
</head>
<body class="easyui-layout">
	 <div region="west" split="true" title="导航" border="false" style="width:180px;height:100%;">
        <iframe id="privilegeTreeFrame" name="privilegeTreeFrame" src="" frameborder="0" width="100%" height="100%"></iframe>
     </div>
     <div region="center" title="" border="false" style="overflow:auto;padding:0px;height:100%;">
        <iframe id="privielegeInfoFrame" name="privielegeInfoFrame" src="" frameborder="0" width="100%" height="100%"></iframe>
     </div>
</form>
</body>
</html>

