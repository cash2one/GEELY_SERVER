/* -------------------------------- ��ѧ������ --------------------------------------- */
/* ---------------------- ������Ա�������� ����ʱ�䣺2008-11-06 ------------------------- */

/*
 * �ж��Ƿ�Ϊ������
 *
 * ����˵����Ҫ��������
 *
 * ����ֵ��trueΪ��������falseΪ��������
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
 * ForDight(Dight,How):��ֵ��ʽ��������
 * ����1��Dight��Ҫ��ʽ��������
 * ����2��How��Ҫ������С��λ����  
 */  
function ForDight(Dight,How)  
{  
    Dight = Math.round(Dight*Math.pow(10,How))/Math.pow(10,How);  
    return Dight;  
}  
