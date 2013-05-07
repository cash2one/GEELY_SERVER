/* ---------------------------- 页面元素处理函数库 ------------------------------------- */
/* ---------------------- 创建人员：龙湘勇 创建时间：2008-11-06 -------------------------- */

/*
 * 动态显示或隐藏某一类型的标签
 * 若prefix不为空则把满足如下条件的标签隐藏：A、标签名称等于tagName，B、ID不为空且不包含prefix
 *
 * 参数1：tagName 标签名称
 * 参数2：prefix 标签ID的前缀
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-12-01
 */
function dynamicDisplayTag(tagName,prefix)
{
    if (trim(prefix) != "")
    {
        var e;
    
        for (var i = 0; i < document.getElementsByTagName(tagName).length; i++)
        {
            e = document.getElementsByTagName(tagName)[i]; 
        
            if (e.id != "" && e.id.indexOf(prefix) < 0)
            {
                e.style.display = "none";
            }
            else
            {
                e.style.display = "block";
            }
        }
    }
}

/*
 * 由不断变化的字段值动态改变另一字段的值
 *
 * 参数1：要添加或删除的字段的值
 * 参数2：要从中添加或删除子串的字段
 * 参数3：子串分隔符
 *
 * 编写人员：龙湘勇
 * 编写时间：2006-12-14
 */
function changeFieldValue(firstFieldValue,secondFieldName,separator)
{
    var secondFieldValue = secondFieldName.value;
    if(secondFieldValue.indexOf(firstFieldValue) == -1)
    {
        secondFieldValue = secondFieldValue + separator + firstFieldValue;
    }
    else
    {
        secondFieldValue = secondFieldValue.replace(firstFieldValue,"");
    }

    //若连续存在两个分隔符则合并为一个
    var twoSeparator = separator + separator;
    if(secondFieldValue.indexOf(twoSeparator) != -1)
    {
        secondFieldValue = secondFieldValue.replace(twoSeparator,separator);
    }

    //若字符串开头存在分隔符则清除该分隔符
    if(secondFieldValue.indexOf(separator) == 0)
    {
        secondFieldValue = secondFieldValue.replace(separator,"");
    }

    //若字符串结尾存在分隔符则清除该分隔符
    if(secondFieldValue.lastIndexOf(separator) == (secondFieldValue.length - 1))
    {
        secondFieldValue = secondFieldValue.replace(separator,"");
    }

    secondFieldName.value = secondFieldValue;
}

/*
 * 使指定表单中的所有复选框的勾选情况随复选框全选对象的勾选变化而变化
 *
 * 参数1：表单对象
 * 参数2：复选框全选对象
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-10-22
 */
function check(thisForm,thisCheckbox) 
{
	var e;
	
    for(var i = 0; i < thisForm.elements.length; i++)
    {
        e = thisForm.elements[i];
        
        if (e.type == "checkbox")
        {
            e.checked = thisCheckbox.checked;
        }
    }
}

/*
 * 根据ID的相似性使指定表单中的相关复选框的状态随指定的复选框的状态变化而变化
 *
 * 参数一：表单
 * 参数二：复选框
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-11-27
 */
function checkById(thisForm,thisCheckbox)
{
    var e;
    
    for (var i = 0 ; i < thisForm.elements.length; i++)
    {
        e = thisForm.elements[i];
        
        if (e.type == "checkbox")
        {
            if(e.id.indexOf(thisCheckbox.id) == 0)
            {
                e.checked = thisCheckbox.checked;
            }
        }
    }
}

/*
 * 勾上指定表单中的所有复选框或取消所有复选框的勾
 *
 * 参数1：表单对象
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-10-22
 */
function checkAll(form)
{
    var e;
    
    for (var i = 0 ; i < form.elements.length; i++)
    {
        e = form.elements[i];
        if (e.type == "checkbox")
        {
            if(e.checked == false)
            {
                e.checked = true;
            }
            else
            {
                e.checked = false;
            }
        }
    }
}

//选取指定下拉列表中的所有元素
function selectAll(select)
{
    if(select.options.length==0)
    {
        return false;
    }
    for (i=0; i<select.options.length; i++)
    {
        select.options(i).selected = true;
    }
    return true;
}

//移动下拉菜单数据
function sourceTarget(source,target)
{
    var l_opts = source.options;
    var r_opts = target.options;
    var flag = false;
    for(var i=0;i<l_opts.length;i++)
    {
        if(l_opts[i].selected==true)
	    {
	        for(var j=0;j<r_opts.length;j++)
	        {
	            if(r_opts[j].value==l_opts[i].value)
		        {
		            flag = true;
		            break;
		        }
	        }

	        if(flag==false)
	        {
	            var opt = document.createElement('option');
	            opt.value = l_opts[i].value;
	            opt.text = l_opts[i].text;
	            target.add(opt);
	            source.remove(i);
	            i=-1;
	        }
	        flag=false;
	    }
    }
}

//把源下拉列表中的元素移至目标下拉列表
function moveSelect(source,target)
{
    for (i=target.options.length-1; i>=0; i--)
    {
        if(target.options(i).selected)
        {
           option_text=target.options(i).text;
           option_value=target.options(i).value;
           option_style_color=target.options(i).style.color;

           var my_option = document.createElement("OPTION");
           my_option.text=option_text;
           my_option.value=option_value;
           my_option.style.color=option_style_color;

           source.add(my_option);
           target.remove(i);
        }
    }
}

//删除下拉列表数据
function removeAll(selectElement)
{
    while(selectElement.options[0]!=null && selectElement.options[0]!="")
    {
        selectElement.remove(0);
        removeAll(selectElement);
    }
}

/*
 * 需要用户先进行确认的操作
 *
 * 参数1：表单
 * 参数2：操作链接
 * 参数3：提示用户确认的消息
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-10-22
 */
function confirmAction(thisForm,strURL,message)
{
    if (confirm(message))
    {
    	thisForm.action = strURL;
    	thisForm.submit();
    }
}

/*
 * 删除操作
 *
 * 参数1：表单
 * 参数2：操作链接
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-10-22
 */
function deleteAction(thisForm,strURL)
{
    if (confirm("您确认要删除所选择的记录吗？"))
    {
    	thisForm.action = strURL;
    	thisForm.submit();
    }
}

/*
 * 删除操作
 *
 * 参数：操作链接
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-10-22
 */
function deleteHref(strURL)
{
    if (confirm("您确认要删除所选择的记录吗？"))
    {
    	location.href = strURL;
    }
}

/*
 * 把表单中的所有属性值重置为空
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-11-03
 */
function formReset(thisForm)
{
    thisForm.reset();
    return false;
}

/*
 * 根据链接地址和页码跳转到相应的页面
 *
 * 参数1：表单
 * 参数2：链接地址
 * 参数3：页码
 * 参数4：总页数
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-11-06
 */
function gotoPage(thisForm,url,pageNo,totalPages)
{
    if (isInteger(pageNo) == false)
    {
        pageNo = 1;
    }
    else
    {
        if (parseInt(pageNo) <= 0)
        {
            pageNo = 1;
        }
        
        if (parseInt(pageNo) > parseInt(totalPages))
        {
            pageNo = totalPages;
        }
    }
    
    thisForm.action = url + pageNo;
    thisForm.submit();
}

//打开新窗口 
function openNewWindow(strPath,strWindowName,intWindowWidth,intWindowHeight)
{
   var leftMargin=(screen.width-intWindowWidth)/2;
   var topMarign=(screen.height-intWindowHeight)/2;
   var strFeatures="width="+intWindowWidth+",height="+intWindowHeight+",borderSize=thin,location=no,toolbar=no,status=yes,scrollbars=yes,resizable=yes,center=yes,top="+topMarign+",left="+leftMargin;
   var newWorkshop = window.open(strPath,strWindowName,strFeatures);
   newWorkshop.focus();
}
