<%@ page contentType="text/html;charset=UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办公设备及用品信息管理系统-部门信息管理</title>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/ShowTree.js"></script>
<script type="text/javascript">
window.onload=function() {
    //deptInfoFrame.location = 'dept_add.do';
	deptTreeFrame.location = 'dept_tree.do';
};
</script>
</head>

<body class="easyui-layout">

    <div region="west" split="true" title="导航" border="false" style="width:180px;height:100%;">
        <iframe frameborder="0" id="deptTreeFrame" name="deptTreeFrame" src="" class="iframeManage"></iframe>
    </div>
    <div region="center" title="" border="false" style="overflow:auto;padding:0px;height:100%;">
        <iframe frameborder="0" id="deptInfoFrame" name="deptInfoFrame" src="" class="iframeManage"></iframe>
    </div>

</body>
</html>
