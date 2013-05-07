function CheckAll(me, RowClassName, AlternatingRowClassName, SelectedCssClass, ClientSelectChangeEvent) {
    var isFirst = true;
    var index = me.name.indexOf('_');
    var prefix = me.name.substr(0, index);
    var controlName = me.name.replace('_HeaderButton', '');
    var checkstatus = me.checked;
    for (var i = 0; i < me.form.length; i++) {
        var o = me.form[i];
        if (o.type == 'checkbox') {
            if (me.name != o.name) {
                if (o.id.indexOf(controlName) != -1) {
                    if (o.name.substring(0, prefix.length) == prefix) {
                        if (!o.disabled) {
                            if (SelectedCssClass || !isFirst) {
                                o.checked = me.checked;
                                if (SelectedCssClass)
                                    o.checked ? HighLight(o, SelectedCssClass) : LowLight(o, i % 2 == 0 ? AlternatingRowClassName : RowClassName);

                            } else {
                                o.click(); //页面上有两个以上的全选复选框的话,这个有可能触发的是CheckAll().
                                isFirst = false;
                                o.checked = me.checked;
                            }
                        }
                    }
                }
            }
        }
    }
    if (ClientSelectChangeEvent)
        ClientSelectChangeEvent();
}

function CheckItem(me, rowIndex, HeaderID, RowClassName, AlternatingRowClassName, SelectedCssClass, MultipleChoice, count, ClientSelectChangeEvent, arg) {
    var clickRow = arg.length > 0;

    if (me.checked) {
        HighLight(me, SelectedCssClass);
    }
    else {
        LowLight(me, rowIndex % 2 == 0 ? RowClassName : AlternatingRowClassName, SelectedCssClass);
    }

    var headerchk = document.getElementById(HeaderID)
    var index = headerchk.name.indexOf('_');
    var prefix = headerchk.name.substr(0, index);
    var totalnumber = 0;
    var totalchecked = 0;
    if (headerchk.checked) {
        headerchk.checked = headerchk.checked & 0;
    }
    for (var i = 0; i < document.forms[0].length; i++) {
        var o = document.forms[0][i];
        if (o.type == 'checkbox') {
            if (o.name != headerchk.name) {
                if ((o.name.substring(0, prefix.length) == prefix) && (o.name.substring(o.name.lastIndexOf('$') + 1) == 'CheckBoxButton')) {
                    totalnumber++;
                    if (o.checked) totalchecked++;

                    //zhouyou96补充
                    if (MultipleChoice == false || clickRow == true)
                        if (o != me && o.checked) {
                        totalchecked--;
                        o.checked = false;
                        LowLight(o, totalnumber % 2 == 1 ? AlternatingRowClassName : RowClassName, SelectedCssClass);
                    }
                }
            }
        }
    }
    if (totalnumber == totalchecked) {
        headerchk.checked = true;
    }
    if (ClientSelectChangeEvent)
        ClientSelectChangeEvent();
}

function HighLight(Element, SelectedCssClass) {
    while (Element.tagName != "TR") { Element = Element.parentNode; }
    $(Element).addClass(SelectedCssClass);
    //Element.className = SelectedCssClass;
    CurrentClassName = Element.className;
}

function LowLight(Element, RowClassName, SelectedCssClass) {
    while (Element.tagName != "TR") { Element = Element.parentNode; }
    $(Element).removeClass(SelectedCssClass);
    $(Element).addClass(RowClassName);
    CurrentClassName = Element.className;
}

function RowMouseOver(me, MouseOverCssClass) {
    CurrentClassName = me.className;
    me.className = MouseOverCssClass;
}

function RowMouseOut(me) {
    me.className = CurrentClassName;
}

//Grid行双击事件。
function RowDblclick(dblclickURL) {
    window.location.href = dblclickURL;
}

function CheckInputValue(obj) {
    if (obj.value == '') {
        alert("转到页数值不能为空！");
        return false;
    }
    return true;
}

function RowMouseClick(row, chkid) {
    var chk = document.getElementById(chkid);

    if (event.srcElement != chk) {
        chk.checked = !chk.checked;
        chk.onclick('single');
    }
}
function RowMouseClickMulti(row, chkid) {
    var chk = document.getElementById(chkid);
    if (event.srcElement != chk) {
        chk.checked = !chk.checked;
    }
}
//获取选择的主键值。
function GetGridSelect(controlName, form) {
    var checkEle = $("input:checked");
    var v = [];
    if (checkEle != null && checkEle.length > 0) {
        for (var i = 0; i < checkEle.length; i++) {
            var o = checkEle[i];
            var keyField = $(o.parentNode).attr('KeyField');
            if (keyField != null && keyField != '' && o.name.substring(0, controlName.length) == controlName) {
                if ((!o.disabled) && o.checked) {
                    v[v.length] = keyField;
                }
            }
        }
    }
    return v;
}

//带隐藏字段的获取选择值。
function GetGridSelectExt(controlName, form) {
    var checkEle = $("input:checked");
    var v = [];
    if (checkEle != null && checkEle.length > 0) {
        for (var i = 0; i < checkEle.length; i++) {
            var o = checkEle[i];
            var keyField = $(o.parentNode).attr('KeyField');
            var hiddedField = $(o.parentNode).attr('HiddedField');
            if (o.checked && (!o.disabled) && o.name.substring(0, controlName.length) == controlName) {
                var r = {};
                if (keyField != null && keyField != '') {
                    r.keyField = keyField;
                }
                if (hiddedField != null && hiddedField != '') {
                    r.hiddedField = hiddedField;
                }
                v[v.length] = r;
            }
        }
    }
    return v;
}