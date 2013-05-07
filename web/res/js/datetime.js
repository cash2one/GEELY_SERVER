
//¸øÈÕÆÚ¸³Öµ
function td_calendar(fieldname)
{
  myleft=(screen.width-80)/2;
  mytop=(screen.height-120)/2;
  window.showModalDialog("./res/datetime/Date.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:280px;dialogHeight:205px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px"); 
}
 
//¸øÊ±¼ä¸³Öµ(¾«È·µ½Ãë)
function td_clock(fieldname)
{
  myleft=document.body.scrollLeft+event.clientX-event.offsetX-80;
  mytop=document.body.scrollTop+event.clientY-event.offsetY;

  window.showModalDialog("./res/datetime/Time.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:280px;dialogHeight:130px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px");
}

//¸øÊ±¼ä¸³Öµ(¾«È·µ½·Ö)
function td_clock_minute(fieldname)
{
  myleft=(screen.width-80)/2;
  mytop=(screen.height-120)/2;

  window.showModalDialog("./res/datetime/TimeMinute.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:230px;dialogHeight:120px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px");
}

//¸øÊ±¼ä¸³Öµ(¾«È·µ½Ê±)
function td_clock_hour(fieldname)
{
  myleft=document.body.scrollLeft+event.clientX-event.offsetX-80;
  mytop=document.body.scrollTop+event.clientY-event.offsetY;

  window.showModalDialog("./res/datetime/TimeHour.jsp?FIELDNAME="+fieldname,self,"edge:raised;scroll:0;status:0;help:0;resizable:1;dialogWidth:200px;dialogHeight:120px;dialogTop:"+mytop+"px;dialogLeft:"+myleft+"px");
}


//¼ì²éÊÇ·ñÎªÊý×Ö---·µ»ØÖµ£º1ÎªÊÇÊý×Ö£¬0Îª²»ÊÇÊý×Ö
function fucCheckNUM(NUM)
{
	 var i,j,strTemp;
	 strTemp=".0123456789";

	 if ( NUM.length== 0)
         {
	    return 0;
         }

	 for (i=0;i<NUM.length;i++)
	 {
	  j=strTemp.indexOf(NUM.charAt(i));
	  if (j==-1)
	  {	  
	   return 0;
	  }
	 }	
	 return 1;
}

//ÅÐ¶ÏÊÇ·ñÎªÓÐÐ§ÈÕÆÚ
function isValidDate( year, month, day )
{
    //ÅÐ¶ÏÄêÊÇ·ñÎªÊý×Ö
    if (fucCheckNUM(year)==0)
    {
	return false;
    }

    //ÅÐ¶ÏÔÂÊÇ·ñÎªÊý×Ö
    if (fucCheckNUM(month)==0)
    {
	return false;
    }

    //ÅÐ¶ÏÌìÊÇ·ñÎªÊý×Ö
    if (fucCheckNUM(day)==0)
    {
	return false;
    }

   year  = parseInt(year,10);
   month = parseInt(month,10);
   day   = parseInt(day,10);

   //ÅÐ¶ÏÊÇ·ñÎªÓÐÐ§ÄêÊý
   if (year<1)
   {  
       return false;
   }

   //ÅÐ¶ÏÊÇ·ñÎªÓÐÐ§ÔÂ·Ý
   if (month<1 || month>12)
   {  
       return false;
   }

   //ÅÐ¶ÏÊÇ·ñÎªÓÐÐ§ÌìÊý
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
 return (true);
}

//Ð§ÑéÈÕÆÚÊý¾Ý
function CheckDate()
{
  argDate = CheckDate.arguments[0];
  f_index=argDate.indexOf("-");
  l_index=argDate.lastIndexOf("-");

  year = argDate.substring(0,4);
  
  month = argDate.substring(f_index+1,l_index);
  
  Date = argDate.substring(l_index+1,argDate.length);
  
  return isValidDate(year,month,Date);
}

//»ñµÃÈÕÆÚÊý×ÖÖµ
function getNumber()
{
  argDate = getNumber.arguments[0];
  f_index=argDate.indexOf("-");
  l_index=argDate.lastIndexOf("-");

  year = argDate.substring(0,4);  
  month = argDate.substring(f_index+1,l_index);  
  Date = argDate.substring(l_index+1,argDate.length);
  return (year+month+Date);
}

//¸ñÊ½»¯ÊäÈëµÄÈÕÆÚ´®£¬ÊäÈëµÄÈç£º"2003-9-12" Êä³ö£º"2003-09-12"
function formatDateStr(inDate)
{
  if (inDate==null||inDate=="") return "";
  var beginDate = inDate.split("-");
  var mYear=beginDate[0];
  var mMonth=beginDate[1];
  var mDay=beginDate[2];
  mMonth=((mMonth.length==1)?("0"+mMonth):mMonth);
  mDay=((mDay.length==1)?("0"+mDay):mDay);
  return mYear+"-"+mMonth+"-"+mDay;
}

//¸ñÊ½»¯ÊäÈëµÄÊ±¼ä´®£¬ÊäÈëµÄÈç£º"9:9:9" Êä³ö£º"09:09:09"
function formatDateStr(inTime)
{
  if (inTime==null||inTime=="") return "";
  var beginTime = inDate.split("-");
  var mHour=beginTime[0];
  var mMinute=beginTime[1];
  var mSecond=beginTime[2];
  mHour=((mHour.length==1)?("0"+mHour):mHour);
  mMinute=((mMinute.length==1)?("0"+mMinute):mMinute);
  mSecond=((mSecond.length==1)?("0"+mSecond):mSecond);
  return mHour+":"+mMinute+":"+mSecond;
}


//º¯ÊýÃû£ºcompareTimes()
//º¯ÊýÃû£º±È½ÏÊ±¼ä´óÐ¡
//²ÎÊýËµÃ÷£ºbeginTime(2005-10-11 10:09:10),endTime(2005-10-12 10:09:10)
//·µ»ØÖµ£º1±íÊ¾¿ªÊ¼Ê±¼ä´óÓÚ½áÊøÊ±¼ä£¬0±íÊ¾¿ªÊ¼Ê±¼äµÈÓÚ½áÊøÊ±¼ä£¬ -1±íÊ¾¿ªÊ¼Ê±¼äÐ¡ÓÚ½áÊøÊ±¼ä
function compareTimes(beginTime,endTime)
{
	var beginYear = beginTime.substring(0,4);
	var beginMonth = beginTime.substring(5,7);
	var beginDay = beginTime.substring(8,10);
	var beginH = beginTime.substring(11,13);
	var beginM = beginTime.substring(14,16);
	var beginS = beginTime.substring(17,19);
	var endYear = endTime.substring(0,4);
	var endMonth = endTime.substring(5,7);
	var endDay = endTime.substring(8,10);
	var endH = endTime.substring(11,13);
	var endM = endTime.substring(14,16);
	var endS = endTime.substring(17,19);
	return compareTime(beginYear,beginMonth,beginDay,beginH,beginM,beginS,endYear,endMonth,endDay,endH,endM,endS);
}

//º¯ÊýÃû£ºcompareTime()
//¹¦ÄÜ½éÉÜ£º ±È½ÏÊ±¼ä´óÐ¡
//²ÎÊýËµÃ÷£ºbeginYear¿ªÊ¼Äê£¬beginMonth¿ªÊ¼ÔÂ,benginDay¿ªÊ¼ÈÕ,beginH¿ªÊ¼Ð¡Ê±£¬beginM¿ªÊ¼·ÖÖÓ£¬beginS¿ªÊ¼Ãë
//          endYear½áÊøÄê£¬endMonth½áÊøÔÂ£¬endMonth½áÊøÈÕ,endH½áÊøÐ¡Ê±£¬endM½áÊø·ÖÖÓ, endS½áÊøÃë
//·µ»ØÖµ£º1±íÊ¾¿ªÊ¼Ê±¼ä´óÓÚ½áÊøÊ±¼ä£¬0±íÊ¾¿ªÊ¼Ê±¼äµÈÓÚ½áÊøÊ±¼ä£¬ -1±íÊ¾¿ªÊ¼Ê±¼äÐ¡ÓÚ½áÊøÊ±¼ä

function compareTime(beginYear,beginMonth,beginDay,beginH,beginM,beginS,endYear,endMonth,endDay,endH,endM,endS)
{ 
  var date1 = new Date(beginYear,beginMonth-1,beginDay,beginH,beginM,beginS);
  var date2 = new Date(endYear,endMonth-1,endDay,endH,endM,endS);
  if(date1.getTime()>date2.getTime())
  {
    return 1;//´óÓÚ
  }
  else if(date1.getTime()==date2.getTime())
  {
  	return 0;//µÈÓÚ
  }
  else
  {
  	return -1;//Ð¡ÓÚ
  }
}

//º¯ÊýÃû£ºcompareDate()
//¹¦ÄÜ½éÉÜ£º ±È½ÏÈÕÆÚ´óÐ¡
//²ÎÊýËµÃ÷£ºbeginYear¿ªÊ¼Äê£¬beginMonth¿ªÊ¼ÔÂ,benginDay¿ªÊ¼ÈÕ
//          endYear½áÊøÄê£¬endMonth½áÊøÔÂ£¬endMonth½áÊøÈÕ
//·µ»ØÖµ£º1±íÊ¾¿ªÊ¼Ê±¼ä´óÓÚ½áÊøÊ±¼ä£¬0±íÊ¾¿ªÊ¼Ê±¼äµÈÓÚ½áÊøÊ±¼ä£¬ -1±íÊ¾¿ªÊ¼Ê±¼äÐ¡ÓÚ½áÊøÊ±¼ä

function compareDate(beginYear,beginMonth,beginDay,endYear,endMonth,endDay)
{
  var date1 = new Date(beginYear,beginMonth-1,beginDay);
  var date2 = new Date(endYear,endMonth-1,endDay);
  if(date1.getTime()>date2.getTime())
  {
    return 1;//´óÓÚ
  }
  else if(date1.getTime()==date2.getTime())
  {
  	return 0;//µÈÓÚ
  }
  else
  {
  	return -1;//Ð¡ÓÚ
  }
}


//»ñµÃµ±Ç°ÈÕÆÚ
function getCurDate()
{
   var today = new Date();
   var year = today.getYear();
   var month = today.getMonth()+1;
   var day = today.getDate();
   if(month<10) month = "0" + month;
   var date = year + "-" + month + "-" + day;
   return date;
}

//»ñµÃµ±Ç°Ê±¼ä
function getCurTime()
{
   var today = new Date();
   var hours = today.getHours();
   var minutes = today.getMinutes();
   var seconds = today.getSeconds();
   
   if(hours<10) hours = "0" + hours;
   if(minutes<10) minutes = "0" + minutes;
   if(seconds<10) seconds = "0" + seconds;

   var time = hours + ":" + minutes + ":" + seconds;
   return time;
}

//»ñµÃµ±Ç°ÈÕÆÚÊ±¼ä
function getCurDateTime()
{
   return getCurDate() + " " +getCurTime();
}

