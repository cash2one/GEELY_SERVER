/**
 * jQuery EasyUI Library
 *
 * @author	YB
 * @Created Date	2010-09-20
 */

//建立tab
function addTab(tit, url, priNum, moduleId) {
    if ($('#tt').tabs('exists', tit)) {
        $('#tt').tabs('select', tit);
    } else {
        $('#tt').tabs('add', {
            id: priNum,
            title: tit,
            content: "<iframe fit='true' name='" + priNum + "' frameborder='0' src='" + url + "' width='100%' height='100%'></iframe>",
			//href:url,
            closable: true,
            selected: true,
            menuId: moduleId
        });
        //$('#NewWork').html("当前工作：" + tit);
    }
    tabClose();
}


function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function() {
        var subtitle = $(this).children("span").text();
        if (subtitle == "桌面") {
            //alert("桌面窗口不能关闭！");
        } else {
            $('#tt').tabs('close', subtitle);
        }
    })

    $(".tabs-inner").bind('contextmenu', function(e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children("span").text();
        $('#mm').data("currtab", subtitle);
        return false;
    });
}
//绑定右键菜单事件
function tabCloseEven() {
    //关闭当前
    $('#mm-tabclose').click(function() {
        var currtab_title = $('#mm').data("currtab");
        if (currtab_title == "桌面") {
            alert("桌面窗口不能关闭！");
        } else {
            $('#tt').tabs('close', currtab_title);
        }
    })
    //全部关闭
    $('#mm-tabcloseall').click(function() {
        $('.tabs-inner span').each(function(i, n) {
            var t = $(n).text();
            if (t != "桌面") {
                $('#tt').tabs('close', t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function() {
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function(i, n) {
            var t = $(n).text();
            if (t != currtab_title && t != "桌面")
                $('#tt').tabs('close', t);
        });
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function() {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {            
            alert('当前窗口右边没有窗口！');
            return false;
        }
        nextall.each(function(i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tt').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function() {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('当前窗口左边没有窗口！');
            return false;
        }
        prevall.each(function(i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            if (t != "桌面") {
                $('#tt').tabs('close', t);
            }
        });
        return false;
    });

    //添加到快捷菜单
    $("#mm-addmenu").click(function() {
    	var tab = $('#tt').tabs('getSelected');
		var menuId = tab.panel('options').menuId;
        var currtab_title = $('#mm').data("currtab");
        var priNum = $("a span:contains('" + currtab_title + "')");
        //alert(menuId);
        $.ajax({
   			type: "POST",
   			url: "shortCut_add.do",
   			data: "moduleId="+menuId,
   			success: function(msg){
   				if (msg==1) {
   					$.messager.alert('信息提示','添加快捷菜单成功!');
   				} else {
   					$.messager.alert('信息提示','添加快捷菜单失败!');
   				}
   				ShowMenu(0,'shortCutModule.do');
   			}
   		});
    });
    
    //删除快捷菜单
    $("#mm-delmenu").click(function() {
    	var tab = $('#tt').tabs('getSelected');
		var menuId = tab.panel('options').menuId;
        var currtab_title = $('#mm').data("currtab");
        var priNum = $("a span:contains(" + currtab_title + ")").attr("alt");
        //alert(menuId);
        $.ajax({
   			type: "POST",
   			url: "shortCut_delete.do",
   			data: "moduleId="+menuId,
   			success: function(msg){
   				if (msg==1) {
   					$.messager.alert('信息提示','删除快捷菜单成功!');
   				} else {
   					$.messager.alert('信息提示','删除快捷菜单失败!');
   				}
   				ShowMenu(0,'shortCutModule.do');
   			}
   		});
    });
}