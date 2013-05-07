/* -------------------------------- 数学函数库 --------------------------------------- */
/* ---------------------- 创建人员：龙湘勇 创建时间：2008-11-06 ------------------------- */

/*
 * 判断是否为正整数
 *
 * 参数说明：要检查的数字
 *
 * 返回值：true为正整数，false为非正整数
 */
function isInteger(num)
{
    var i,j,strTemp;
    strTemp = "0123456789";

    if (num.length == 0)
    {
        return false;
    }

    for (i=0;i<num.length;i++)
    {
        j = strTemp.indexOf(num.charAt(i));
        if (j==-1)
        {
	        return false;
        }
    }

    return true;
}

/*  
 * ForDight(Dight,How):数值格式化函数，
 * 参数1：Dight，要格式化的数字
 * 参数2：How，要保留的小数位数。  
 */  
function ForDight(Dight,How)  
{  
    Dight = Math.round(Dight*Math.pow(10,How))/Math.pow(10,How);  
    return Dight;  
}  
