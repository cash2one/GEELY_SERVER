<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>校讯通手机服务端</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
<link href="css/page_style.css" rel="stylesheet" type="text/css">
<link href="themes/easyui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.p.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.p.js"></script>

<style type="text/css">
	ul, li { list-style: none; margin: 0px; padding: 0px; }
	#TopBtn { height: 28px; display: block; top: -12px; top: 0px\9; top: 0px !important; position: absolute; right: 5px; }
	#TopBtn li { float: left; height: 28px; cursor: pointer; }
	/*Menu */.menuUl, .menuUlHover { float: left; margin: 0px 0px 0px 2px; position: relative; padding-left: 3px; cursor: pointer; height: 26px; cursor: pointer; }
	.menuUl li { height: 24px; float: left; padding: 2px 5px 0px 4px; }
	.menuUl li a { font-size: 12px; color: #15428b; text-decoration: none; height: 24px; line-height: 24px; }
	.menuUl li a:hover { color: #F00; text-decoration: underline; }
	.menuUlHover { background: url(images/MenuBgHover.gif) no-repeat 0px left; }
	.menuUlHover li { background: url(images/MenuBgHover.gif) no-repeat -26px right; height: 26px; float: left; padding: 0px 5px 0px 4px; }
	.menuUlHover li a { font-size: 12px; color: #f60; font-weight: bold; text-decoration: none; height: 26px; line-height: 26px; }
	.menuUlHover li a:hover { color: #F00; text-decoration: underline; }
</style>
<script type="text/javascript">
	//一级菜单
	function ShowMenu(ID, url) {
		var ParentObj = document.getElementById("outDv");
		for (var i = 0; i <= ParentObj.getElementsByTagName("ul").length - 1; i++) {
			ParentObj.getElementsByTagName("ul")[i].className = "menuUl";
		}
		ParentObj.getElementsByTagName("ul")[ID].className = "menuUlHover";
		document.getElementById("mainFrame").src = url;
		//设置左边菜单顶显示：第一个为“快捷菜单”，否则为此菜单的名称
		var menuName = $("a", $("#outDv>ul").eq(ID)).text();
		if (ID == 0) {
			parent.$(".layout-split-west .panel-title").text('快捷菜单');
		}
		else {
			parent.$(".layout-split-west .panel-title").text(menuName);
		}
		//点击一级菜单后，加载桌面页
		$('#mainFrame').attr('src', 'mainInfo.do');
		$('#leftFrame').attr('src', url);		
	}
    $(function() {
        tabClose();
        tabCloseEven();
    })
</script>
</head>

<body class="easyui-layout">
	<div id="top" region="north" split="false" style="height:75px; overflow: hidden; border-bottom: solid 1px #8DB2E3; background: url(images/topBg02.gif) repeat-x top left;">
        <div style="float: left; height: 48px; width: 400px;"><img alt="" src="images/login_XXT.jpg" /></div>
        <div style="float: right; height: 48px; width: 600px; background: url(images/banner01.gif) no-repeat right top;">
            <ul id="TopBtn">
                <li onClick="location.href='desktop.do'">
                    <img alt="" src="images/TopMenu01.gif" onMouseOver="this.src='images/TopMenu01a.gif'" onMouseOut="this.src='images/TopMenu01.gif'" /></li>
                
                <li onClick="help()"><!--帮助-->
                    <img alt="" src="images/TopMenu08.gif" onMouseOver="this.src='images/TopMenu08a.gif'" onMouseOut="this.src='images/TopMenu08.gif'" /></li>
                      
                <li onClick="ChangePwd()">
                    <img alt="" src="images/TopMenu06.gif" onMouseOver="this.src='images/TopMenu06a.gif'" onMouseOut="this.src='images/TopMenu06.gif'" /></li>
                <li onClick="Logout();" >
                    <img alt="" src="images/TopMenu04.gif"  onMouseOver="this.src='images/TopMenu04a.gif'" onMouseOut="this.src='images/TopMenu04.gif'" /></li>
            </ul>
        </div>
        <div id="outDv" style="clear:both;padding-left:60px;">
            <ul class="menuUl" onClick="ShowMenu(0,'shortCutModule.do')">
            	<li><a href="shortCutModule.do" hidefocus="true" target="leftFrame" onFocus="this.blur()">首页</a></li>
            </ul>
            <s:iterator id="var_2" value="moduleList" status="vStatus">
            <ul class="menuUl" onClick="ShowMenu(<s:property value="#vStatus.count"/>,'module.do?moduleCode=<s:property value="moduleCode"/>')">
            	<li><a href="module.do?moduleCode=<s:property value="moduleCode"/>" hidefocus="true" target="leftFrame" onFocus="this.blur()"><s:property value="moduleName"/></a></li>
            </ul>
            </s:iterator>
        </div>
    </div>
    
    <div region="south" split="false" style="height:26px;line-height:26px;background: #efefef;padding:0px;text-align:center;overflow: hidden;">
        <img alt="" src="images/userHeader.gif" align="absmiddle" />
        <strong>登录用户：</strong><s:property value="#session.user.name" />
        &nbsp; &nbsp; <strong>单位：</strong><s:property value="#session.user.dept.unit.orgName" />
        &nbsp; &nbsp; <strong>部门：</strong><s:property value="#session.user.dept.name" />
        &nbsp; &nbsp; &nbsp; &nbsp; 技术支持：湖南家校圈科技有限公司-业务支撑部&nbsp; &nbsp; TEL：0731-88997919 8301
    </div>
    <div region="west" split="true" title="快捷菜单" style="width:168px; padding1:1px;overflow: auto;background:#F6F9FD;">
       <iframe frameborder="0" id="leftFrame" name="leftFrame" src="shortCutModule.do" height="100%" width="160px" style="width: 160px; height: 100%;"></iframe>
    </div>
    <div region="center" split="true" style="overflow: auto;">
        <div id="tt" class="easyui-tabs" fit="true" border="false">
            <div id="mainFrame" title="桌面"	 style="overflow: auto;" border="false">
                <iframe frameborder="0" id="mainFrame" name="mainFrame" src="mainInfo.do" width="100%" height="100%" style="width: 100%; height: 100%;"></iframe>
            </div>
        </div>
    </div>
    <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-addmenu">添加到快捷菜单</div>
        <div id="mm-delmenu">删除快捷菜单</div>
    </div>
</body>
</html>
