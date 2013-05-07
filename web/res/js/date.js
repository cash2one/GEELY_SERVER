/* ---------------------------- 时间处理函数库 ------------------------------------- */
/* -------------------- 创建人员：龙湘勇 创建时间：2008-11-06 ------------------------ */

/* 
 * 比较时间大小，
 *
 * 返回0-相等,1-第一个大,2-第二个大
 *
 * author:lxy 2005-11-13
 */
function compareDate(firstDate,secondDate)
{
    if(firstDate == secondDate)
    {
        return 0;
    }

    var a=firstDate.split("-");
    var b=secondDate.split("-");
    if(a[0]==b[0] && a[1]==b[1])
    {
        if(parseInt(a[2]) > parseInt(b[2]))
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
    if(a[0]==b[0])
    {
        if(parseInt(a[1]) > parseInt(b[1]))
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
    if(parseInt(a[0]) > parseInt(b[0]))
    {
        return 1;
    }
    else
    {
        return 2;
    }
}

//判断是否为有效日期
function isValidDate( year, month, day )
{
    if (isInteger(year)==false)
    {
        return false;
    }

    if (isInteger(month)==false)
    {
        return false;
    }

    if (isInteger(day)==false)
    {
	return false;
    }

    year  = parseInt(year,10);
    month = parseInt(month,10);
    day   = parseInt(day,10);

    if (year<1)
    {
        return false;
    }

    if (month<1 || month>12)
    {
        return false;
    }

    if ( month==4 || month==6 || month==9 || month==11 )
    {
        if ( day < 1 || day > 30 )
        {
            return false;
        }
    }
    else
    {
        if ( month!=2 )
        {
            if ( day < 1 || day > 31 )
            {
                return false;
            }
        }
        else
        {
            if ( ( year % 100 ) != 0 && (year % 4 == 0) || ( year % 100 ) == 0 && ( year % 400) == 0 )
            {
                if ( day > 29 )
                {
                    return false;
                }
            }
            else
            {
                if ( day > 28 )
                {
                    return false;
                }
            }
        }
    }
    return true;
}

//判断是否为有效时间
function isValidTime( hour, minute, second )
{
    if (isInteger(hour)==false)
    {
        return false;
    }

    if (isInteger(minute)==false)
    {
        return false;
    }

    if (isInteger(second)==false)
    {
        return false;
    }

    hour  = parseInt(hour,10);
    minute = parseInt(minute,10);
    second   = parseInt(second,10);

    if (hour<0 || hour >= 24)
    {
        return false;
    }

    if (minute<0 || minute>59)
    {
        return false;
    }

    if (second<0 || second>59)
    {
        return false;
    }

    return true;
}

//效验日期数据
function checkDate()
{
    argDate = checkDate.arguments[0];
    year = argDate.substring(0,4);
    f_index=argDate.indexOf("-");
    l_index=argDate.lastIndexOf("-");
    month = argDate.substring(f_index+1,l_index);
    Date = argDate.substring(l_index+1,argDate.length);

    return isValidDate(year,month,Date);
}

//效验时间数据
function checkTime()
{
    argTime = checkTime.arguments[0];
    hour = argTime.substring(0,2);
    f_index=argTime.indexOf(":");
    l_index=argTime.lastIndexOf(":");
    minute = argTime.substring(f_index+1,l_index);
    second = argTime.substring(l_index+1,argTime.length);

    return isValidTime(hour,minute,second);
}

//给日期赋值
function setDate(fieldname)
{
    myleft=document.body.scrollLeft+event.clientX-event.offsetX-80;
    mytop=document.body.scrollTop+event.clientY-event.offsetY+140;

    window.showModalDialog("res/datetime/Date.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:280px;dialogHeight:205px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px");
}

//给日期后时间赋值
function setTime(fieldname)
{
    myleft=document.body.scrollLeft+event.clientX-event.offsetX-80;
    mytop=document.body.scrollTop+event.clientY-event.offsetY+140;

    window.showModalDialog("res/datetime/Time.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:280px;dialogHeight:120px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px");
}

//给时间赋值()
function setTimeOnly(fieldname)
{
    myleft=document.body.scrollLeft+event.clientX-event.offsetX-80;
    mytop=document.body.scrollTop+event.clientY-event.offsetY+140;

    window.showModalDialog("res/datetime/TimeOnly.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:280px;dialogHeight:120px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px");
}

