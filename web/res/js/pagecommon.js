//页面公用js库，包括增删改查等等公用函数


/**
*编辑信息
*@param action 编辑请求地址
*@param id	主键id名
*/
function editInfo(action,id) {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要修改的信息。');
        return;
    }
    
    location = action+"?"+id+"="+v[0];
}

/**
*删除信息
*@param action 编辑请求地址
*@param ids	主键数组名
*/
function deleteInfo(action,ids) {
	var v = GetGridSelect('grid1');
 	if (v.length == 0 || v[0] == "0") {
 		$.messager.alert('未选中记录','请先选中需要删除的信息。');
        return;
    } 
    $.messager.confirm('确定操作', '你确定要删除该条数据？', function(r){
    	if(r){
    		var i=0
    		var tmp=action+"?";
    		for(;i<v.length;i++){
    			tmp +=ids+"="+v[i];
    			if(i!=v.length-1){
    				tmp+="&";
    			}
    		}
    		location=tmp;
    	}
    });	
}
//跳转页面
function goPage(form,suffix) {
	form.action = form.action+"?"+suffix;
	form.submit();
}

//跳转到指定页面
function turnTo(form,pageNoId,totalPages,currentPage){
	var flag=true;
	var pageNo=$("#"+pageNoId).val();
	var req=/^[0-9]*[1-9][0-9]*$/;
	if(req.test(pageNo)){
		var t=parseInt(totalPages,0);
		var p=parseInt(pageNo,0);
		if(p>t){
			flag=false;
			$.messager.alert('页码输入错误','请页码过大。');
	 	}
	} else {
		$.messager.alert('页码输入错误','请输入正确的页码数字。');
		flag=false;
	}
	if(currentPage==pageNo){
		flag=false;
	}
	if(flag){
		form.action = form.action+"?redirectSign=0&pageNo="+pageNo;
		form.submit();
	}
}

//跳转到指定页面
function turnToGrid(pageNo,totalPages){
	var flag=true;
	var req=/^[0-9]*[1-9][0-9]*$/;
	if(req.test(pageNo)){
		var t=parseInt(totalPages,0);
		var p=parseInt(pageNo,0);
		if(p>t){
			flag=false;
			$.messager.alert('页码输入错误','页码过大。');
	 	}
	} else {
		$.messager.alert('页码输入错误','请输入正确的页码数字。');
		flag=false;
	}
	if(flag){
		$("#pageNo").val(pageNo);
		doSearch();
	}
}