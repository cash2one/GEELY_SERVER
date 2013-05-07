/* ---------------------------- 输入检验函数库 ------------------------------------- */
/* ---------------------- 创建人员：龙湘勇 创建时间：2008-11-06 ------------------------ */

/*
 * 判断输入的字符串是否为数字和字母
 *
 * 参数：字符串
 *
 * 返回：true-是，false-否
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-12-22
 */
function ifValidStr(str)
{
	if (!/^\w*$/.test(str))
	{
		return false;
	}
	else
	{
		return true;
	}
}

/*
 * 判断年龄是否为一个有效的年龄
 *
 * 参数：年龄
 *
 * 返回：true-有效的，false-无效的
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-12-22
 */
function ifValidAge(age)
{
	if (!/^[0-1]?[0-9]?[0-9]$/.test(age))
	{
		return false;
	}
	else
	{
		return true;
	}
}

/*
 * 判断是否为非法数据（含有特殊字符：'或"或;）
 *
 * 参数：要判断的数据
 *
 * 返回值：true为非法数据，false为合法数据
 *
 * 编写人员：龙湘勇
 * 编写时间：2006-09-14
 */
function isInvalidData(str)
{
    if(str.indexOf("\'") != -1)
    {
        alert("无效数据，不能含有半角/英文单引号（\'）！");
        return true;
    }
    if(str.indexOf("\"") != -1)
    {
        alert("无效数据，不能含有半角/英文双引号（\"）！");
        return true;
    }
    if(str.indexOf("\\") != -1)
    {
        alert("无效数据，不能含有反斜杠（\\）！");
        return true;
    }
    if(str.indexOf(";") != -1)
    {
        alert("无效数据，不能含有半角/英文分号（;）！");
        return true;
    }
    if(str.indexOf("(") != -1 || str.indexOf(")") != -1)
    {
        alert("无效数据，不能含有半角/英文括号（()）！");
        return true;
    }

    return false;
}

/*
 * 检查IP地址是否有效
 *
 * 参数：IP地址
 *
 * 返回值：true-有效的，false-无效的
 *
 */
function checkIP(ipAddress)
{
	var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	var reg = ipAddress.match(exp);
	
	if(reg == null)
	{
		return false;
	}
	else
	{
		return true;
	}
}

