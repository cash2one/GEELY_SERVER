/*
  ����:ȥ��HTML�ؼ����ݵ����ҿո�

  Control:���������ݵ�HTML�ؼ���

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
  ����:���ñ���

*/
function ResetForm()
{
	document.forms(0).reset();window.event.returnValue=false;
}
/*
  ����:������һҳ��

*/
function GoLastPage()
{
	history.go(-1);window.event.returnValue=false;
}


String.prototype.endWith = function(oString) {
    var reg = new RegExp(oString + "$");
    return reg.test(this);
}
