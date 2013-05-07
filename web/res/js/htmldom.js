/* ---------------------------- ҳ��Ԫ�ش������� ------------------------------------- */
/* ---------------------- ������Ա�������� ����ʱ�䣺2008-11-06 -------------------------- */

/*
 * ��̬��ʾ������ĳһ���͵ı�ǩ
 * ��prefix��Ϊ������������������ı�ǩ���أ�A����ǩ���Ƶ���tagName��B��ID��Ϊ���Ҳ�����prefix
 *
 * ����1��tagName ��ǩ����
 * ����2��prefix ��ǩID��ǰ׺
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-12-01
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
 * �ɲ��ϱ仯���ֶ�ֵ��̬�ı���һ�ֶε�ֵ
 *
 * ����1��Ҫ��ӻ�ɾ�����ֶε�ֵ
 * ����2��Ҫ������ӻ�ɾ���Ӵ����ֶ�
 * ����3���Ӵ��ָ���
 *
 * ��д��Ա��������
 * ��дʱ�䣺2006-12-14
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

    //���������������ָ�����ϲ�Ϊһ��
    var twoSeparator = separator + separator;
    if(secondFieldValue.indexOf(twoSeparator) != -1)
    {
        secondFieldValue = secondFieldValue.replace(twoSeparator,separator);
    }

    //���ַ�����ͷ���ڷָ���������÷ָ���
    if(secondFieldValue.indexOf(separator) == 0)
    {
        secondFieldValue = secondFieldValue.replace(separator,"");
    }

    //���ַ�����β���ڷָ���������÷ָ���
    if(secondFieldValue.lastIndexOf(separator) == (secondFieldValue.length - 1))
    {
        secondFieldValue = secondFieldValue.replace(separator,"");
    }

    secondFieldName.value = secondFieldValue;
}

/*
 * ʹָ�����е����и�ѡ��Ĺ�ѡ����渴ѡ��ȫѡ����Ĺ�ѡ�仯���仯
 *
 * ����1��������
 * ����2����ѡ��ȫѡ����
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-10-22
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
 * ����ID��������ʹָ�����е���ظ�ѡ���״̬��ָ���ĸ�ѡ���״̬�仯���仯
 *
 * ����һ����
 * ����������ѡ��
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-11-27
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
 * ����ָ�����е����и�ѡ���ȡ�����и�ѡ��Ĺ�
 *
 * ����1��������
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-10-22
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

//ѡȡָ�������б��е�����Ԫ��
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

//�ƶ������˵�����
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

//��Դ�����б��е�Ԫ������Ŀ�������б�
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

//ɾ�������б�����
function removeAll(selectElement)
{
    while(selectElement.options[0]!=null && selectElement.options[0]!="")
    {
        selectElement.remove(0);
        removeAll(selectElement);
    }
}

/*
 * ��Ҫ�û��Ƚ���ȷ�ϵĲ���
 *
 * ����1����
 * ����2����������
 * ����3����ʾ�û�ȷ�ϵ���Ϣ
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-10-22
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
 * ɾ������
 *
 * ����1����
 * ����2����������
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-10-22
 */
function deleteAction(thisForm,strURL)
{
    if (confirm("��ȷ��Ҫɾ����ѡ��ļ�¼��"))
    {
    	thisForm.action = strURL;
    	thisForm.submit();
    }
}

/*
 * ɾ������
 *
 * ��������������
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-10-22
 */
function deleteHref(strURL)
{
    if (confirm("��ȷ��Ҫɾ����ѡ��ļ�¼��"))
    {
    	location.href = strURL;
    }
}

/*
 * �ѱ��е���������ֵ����Ϊ��
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-11-03
 */
function formReset(thisForm)
{
    thisForm.reset();
    return false;
}

/*
 * �������ӵ�ַ��ҳ����ת����Ӧ��ҳ��
 *
 * ����1����
 * ����2�����ӵ�ַ
 * ����3��ҳ��
 * ����4����ҳ��
 *
 * ��д��Ա��������
 * ��дʱ�䣺2008-11-06
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

//���´��� 
function openNewWindow(strPath,strWindowName,intWindowWidth,intWindowHeight)
{
   var leftMargin=(screen.width-intWindowWidth)/2;
   var topMarign=(screen.height-intWindowHeight)/2;
   var strFeatures="width="+intWindowWidth+",height="+intWindowHeight+",borderSize=thin,location=no,toolbar=no,status=yes,scrollbars=yes,resizable=yes,center=yes,top="+topMarign+",left="+leftMargin;
   var newWorkshop = window.open(strPath,strWindowName,strFeatures);
   newWorkshop.focus();
}
