/**
 * jQuery Library
 *
 * @author	YB
 * @Created Date	2010-09-20
 */
 
var MSGERROR = "操作失败";
//jQuery.fx.off = true; //关闭所有jquery动画效果



//IE8及chrome框架高度，在***Manage.aspx文件底部调用
function noieFrameHeight() {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    if (window.ActiveXObject)
        Sys.ie = ua.match(/msie ([\d.]+)/)[1]
    else if (document.getBoxObjectFor)
        Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]
    else if (window.MessageEvent && !document.getBoxObjectFor)
        Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1]
    else if (window.opera)
        Sys.opera = ua.match(/opera.([\d.]+)/)[1]
    else if (window.openDatabase)
        Sys.safari = ua.match(/version\/([\d.]+)/)[1];
    //以下进行测试
    if (Sys.ie == "8.0") { $(".easyui-layout").css("height", document.body.clientHeight + "px"); }
    if (Sys.firefox) { $(".easyui-layout").css("height", document.body.clientHeight + "px"); $(".easyui-layout").css("width", document.body.clientWidth + "px"); }
    if (Sys.chrome) { $(".easyui-layout").css("height", document.body.clientHeight + "px"); }
    if (Sys.opera) $(".easyui-layout").css("height", document.body.clientHeight + "px");
    if (Sys.safari) $(".easyui-layout").css("height", document.body.clientHeight + "px");
}

//设置父框架fraRBottom高度
function autoFrameHeight(frameID) {
    parent.document.getElementById(frameID).style.height = document.body.scrollHeight + "px";
}

//根据本页面高度，设置父框架高度+20
function frameHeight(frameID) {
    var ifHeight = document.body.scrollHeight;
    if (document.body.scrollHeight < 100) { ifHeight = 100 };
    if (parent.document.getElementById(frameID).style.height <= ifHeight) {
        parent.document.getElementById(frameID).style.height = ifHeight + 20 + "px";
    }
}

//搜索栏控制函数
function searchPanel() {
    var i = 1;
    $(".searchPanel-header").click(function(event) {
        if (i % 2 == 1) {
			$(".searchPanel-tools>div").addClass("searchPanel-collapse");
			$(".searchPanel-tools>div").removeClass("searchPanel-expand");
        }
        else {
			$(".searchPanel-tools>div").addClass("searchPanel-expand");
            $(".searchPanel-tools>div").removeClass("searchPanel-collapse");
        }
        $(".searchPanel-body").toggle("fast");
        i++;
    })
	$(".searchPanel-tools>div").addClass("searchPanel-btn");
	$(".searchPanel-tools>div").bind("mouseover",function(){
															$(".searchPanel-tools>div").removeClass("searchPanel-btn");
															$(".searchPanel-tools>div").addClass("searchPanel-btnHover");
															})
	$(".searchPanel-tools>div").bind("mouseout",function(){
															$(".searchPanel-tools>div").removeClass("searchPanel-btnHover");
															$(".searchPanel-tools>div").addClass("searchPanel-btn");
															})
}

//Grid表格隔行加颜色
function tableTrColor(){
	$(".infoList tbody>tr:odd").addClass("odd"); //先排除第一行,然后给奇数行添加样式
		$(".infoList tbody>tr:even").addClass("even"); //先排除第一行,然后给偶数行添加样式
		$('.infoList tbody>tr').mouseover(function() {
			$(this)
				.addClass('trhover')
				.siblings().removeClass('trhover')
				.end()
		});
		$('.infoList tbody').mouseout(function(){$('.infoList tbody>tr').siblings().removeClass('trhover').end()})
}

//退出
function Logout() {
	$.messager.confirm('确定操作', '您确认要退出当前的登陆?', function(r){
   		 if(r){
    	    location.href = "logout.do";
    		}
    });	
}

//查看日志
function ViewLog() {
    showSuperDialog({ url: "ViewLog.html", width: 900, height: 480, model: true });
}

//修改密码
function ChangePwd() {
 var winPar= showSuperDialog({ url: 'ChangePwd.do', title: '密码修改', width: 500, height:325, model: true });
  if(winPar == "login")
     window.location='login.jsp';
}
function SoftDown(url) {
    location.href = url;
}

//对话框中国的帮助按钮
function help(){
	$.messager.alert('信息提示','帮助文档正在制作中……','info');
}

//全选/取消
function CheckAll(obj) {
	if ($(obj).attr("checked") == true) { // 全选			   
		$("input[type='checkbox']",$(obj).closest('table')).each(function() {
			$(this).attr("checked", true);
		});
	} else { // 取消全选
		$("input[type='checkbox']",$(obj).closest('table')).each(function() {
			$(this).attr("checked", false);
		});
	}
}

//显示对话框
//showSuperDialog({url:'/dialog.html',title:'对话框',width:100,height:100,model:true});
function showSuperDialog(options) {
    var opt = options || {};
    opt.url = opt.url || "javascript:document.write('未指定URL');";
    opt.title = opt.title || "对话框";
    opt.width = opt.width || 800;
    opt.height = opt.height || 600;
    //if($.browser.version=='6.0')opt.height=opt.height+(0.2*opt.height); //2009-08-14 code by zhouyou.
    opt.model = typeof opt.model == 'undefined' || opt.model;
    opt.debug = false;
    if (opt.debug) {
        var w = window.open("DialogFrame.html", "_blank", "");
        if (w.loaded) { w.init([window, opt], false); } else { w.startup = function() { w.init([window, opt], true); } };
        return w;
    }

    if (opt.model) {
        return window.showModalDialog("DialogFrame.html", [window, opt]
            , "dialogHeight:" + opt.height + "px;dialogWidth:" + opt.width + "px;");
    } else {
        return window.showModelessDialog("DialogFrame.html", [window, opt]
            , "dialogHeight:" + opt.height + "px;dialogWidth:" + opt.width + "px;");
    }
}


//Flash显示
function jsFlash(file_path,width,height){
	document.write("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\""+width+"\" height=\""+height+"\">");
	document.write("<param name=\"movie\" value=\""+file_path+"\">");
	document.write("<param name=\"quality\" value=\"high\">");
	document.write("<param name=\"wmode\" value=\"transparent\">");
	document.write("<embed src=\""+file_path+"\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\""+width+"\" height=\""+height+"\">");
	document.write("</embed>");
	document.write("</object>");
}



//左边菜单
$(document).ready(function() {
    $('.subLeftMenu').hide();
	$('.LeftMenu a').addClass('linkBg');
	$(".LeftMenu>li").click(function() {		
		$('.LeftMenu a').removeClass('HoverBg');
		var checkElement = $(this).children('ul');
		$('.subLeftMenu').hide();
		if(checkElement.is(":hidden")){
			checkElement.show();
			$(this).children('a').addClass('HoverBg');
		}else{
			checkElement.hide();
			$(this).children('a').addClass('linkBg');
		}
	});
});



// alert 适配器
$.extend({
    msg: function(message) {
        window.alert(message);
    }
});

/*
===========================================
//得到左边的字符串
===========================================
*/
String.prototype.Left = function(len) {

    if (isNaN(len) || len == null) {
        len = this.length;
    }
    else {
        if (parseInt(len) < 0 || parseInt(len) > this.length) {
            len = this.length;
        }
    }

    return this.substr(0, len);
}


/*
===========================================
//得到右边的字符串
===========================================
*/
String.prototype.Right = function(len) {

    if (isNaN(len) || len == null) {
        len = this.length;
    }
    else {
        if (parseInt(len) < 0 || parseInt(len) > this.length) {
            len = this.length;
        }
    }

    return this.substring(this.length - len, this.length);
}

/*
===========================================
//验证字符串是否为数字。
===========================================
*/
function IsDigit(s) {
    var patrn = /^[0-9]{1,20}$/;
    if (!patrn.exec(s)) return false
    return true
}  