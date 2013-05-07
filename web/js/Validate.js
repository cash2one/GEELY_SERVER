/*
  功能:去除HTML控件内容的左右空格。

  Control:可输入数据的HTML控件。

*/
function TrimControl(Control)
{
    if(Control==null)return;
	var str=Control.value;
	while (str.charAt(0) == ' ')
	{
		str = str.substring(1);
	}
	while (str.charAt(str.length - 1) == ' ')
	{
		str = str.substring(0,str.length - 1);
	}
	Control.value=str;
}


/*
  功能:重置表单。

*/
function ResetForm()
{
	document.forms(0).reset();window.event.returnValue=false;
}
/*
  功能:返回上一页。

*/
function GoLastPage()
{
	history.go(-1);window.event.returnValue=false;
}


String.prototype.endWith = function(oString) {
    var reg = new RegExp(oString + "$");
    return reg.test(this);
}
