/* ---------------------------- ������麯���� ------------------------------------- */
/* ---------------------- ������Ա�������� ����ʱ�䣺2008-11-06 ------------------------ */

/*
 * �ж�������ַ����Ƿ�Ϊ���ֺ���ĸ
 *
 * �������ַ���
 *
 * ���أ�true-�ǣ�false-��
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-12-22
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
 * �ж������Ƿ�Ϊһ����Ч������
 *
 * ����������
 *
 * ���أ�true-��Ч�ģ�false-��Ч��
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-12-22
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
 * �ж��Ƿ�Ϊ�Ƿ����ݣ����������ַ���'��"��;��
 *
 * ������Ҫ�жϵ�����
 *
 * ����ֵ��trueΪ�Ƿ����ݣ�falseΪ�Ϸ�����
 *
 * ��д��Ա��������
 * ��дʱ�䣺2006-09-14
 */
function isInvalidData(str)
{
    if(str.indexOf("\'") != -1)
    {
        alert("��Ч���ݣ����ܺ��а��/Ӣ�ĵ����ţ�\'����");
        return true;
    }
    if(str.indexOf("\"") != -1)
    {
        alert("��Ч���ݣ����ܺ��а��/Ӣ��˫���ţ�\"����");
        return true;
    }
    if(str.indexOf("\\") != -1)
    {
        alert("��Ч���ݣ����ܺ��з�б�ܣ�\\����");
        return true;
    }
    if(str.indexOf(";") != -1)
    {
        alert("��Ч���ݣ����ܺ��а��/Ӣ�ķֺţ�;����");
        return true;
    }
    if(str.indexOf("(") != -1 || str.indexOf(")") != -1)
    {
        alert("��Ч���ݣ����ܺ��а��/Ӣ�����ţ�()����");
        return true;
    }

    return false;
}

/*
 * ���IP��ַ�Ƿ���Ч
 *
 * ������IP��ַ
 *
 * ����ֵ��true-��Ч�ģ�false-��Ч��
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

