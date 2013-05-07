/* ---------------------------- 字符串处理函数库 ------------------------------------- */
/* ---------------------- 创建人员：龙湘勇 创建时间：2008-11-06 ------------------------ */

//字符串两端去空格函数
function trim(str)
{
    var i = 0;
    var len = str.length;
    if(str == "")
    {
        return str ;
    }
    j = len -1;
    flagbegin = true;
    flagend = true;

    while(flagbegin == true && i < len)
    {
        if(str.charAt(i) == " ")
        {
            i = i+1;
            flagbegin = true;
        }
        else
        {
            flagbegin = false;
        }
    }

    while(flagend == true && j >= 0)
    {
        if(str.charAt(j) ==" ")
        {
            j = j-1;
            flagend = true;
        }
        else
        {
            flagend = false;
        }
    }

    if(i > j)
    {
        return "";
    }

    trimstr = str.substring(i,j+1);
    return trimstr;
}

//去掉字符串左边的空格
function leftTrim(str)
{
    var i = 0;
    var len = str.length;

    if(str == "")
    {
        return str ;
    }

    flagbegin = true;

    while(flagbegin == true && i < len)
    {
        if(str.charAt(i) == " ")
        {
            i = i+1;
            flagbegin = true;
        }
        else
        {
            flagbegin = false;
        }
    }

    trimstr = str.substring(i,len);
    return trimstr;
}

//得到IP地址数字形式
function getIPNum(ipAddress)
{
	var scount=0;
	var ip = ipAddress;
	var iplength = ip.length;
	var Letters = "1234567890.";
	for (var i=0; i < iplength; i++)
  	{
   		var CheckChar = ip.charAt(i);
   		if (Letters.indexOf(CheckChar) == -1)
   		{
    		return "0";
   		}
  	}

	for (var i = 0;i<iplength;i++)
  	(ip.substr(i,1)==".")?scount++:scount;

	if(scount!=3)
	{
		return "0";
	}

	first = ip.indexOf(".");
	last = ip.lastIndexOf(".");
	str1 = ip.substring(0,first);
	subip = ip.substring(0,last);
	sublength = subip.length;
	second = subip.lastIndexOf(".");
	str2 = subip.substring(first+1,second);
	str3 = subip.substring(second+1,sublength);
	str4 = ip.substring(last+1,iplength);

	for(var i=str1.length;i<3;i++)
	{
	   str1 = "0"+str1;
	}

	for(var i=str2.length;i<3;i++)
	{
	   str2 = "0"+str2;
	}

	for(var i=str3.length;i<3;i++)
	{
	   str3 = "0"+str3;
	}

	for(var i=str4.length;i<3;i++)
	{
	   str4 = "0"+str4;
	}

  	return str1+str2+str3+str4;
}
