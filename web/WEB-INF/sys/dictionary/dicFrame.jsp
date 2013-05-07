<%@ page contentType="text/html;charset=UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>名校资源网-数据字典管理</title>
<link href="themes/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/page_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/ShowTree.js"></script>
</head>
<body class="easyui-layout">
	 <div region="west" split="true" title="导航" border="false" style="width:180px;height:100%;">
        <iframe frameborder="0" id="dicTreeFrame" name="dicTreeFrame" src="dictionary_tree.do?dicType=0" class="iframeManage"></iframe>
    </div>
    <div region="center" title="" border="false" style="overflow:auto;padding:0px;height:100%;">
        <iframe frameborder="0" id="dicContentFrame" name="dicContentFrame" src="" class="iframeManage"></iframe>
    </div>
</body>
</html>