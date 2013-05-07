var FormValid = function(frm) {
    this.frm = frm;
    this.errMsg = new Array();
	this.errName = new Array();
   
    this.required = function(inputObj) {
        if (typeof(inputObj) == "undefined" || inputObj.value.trim() == "") {
            return false;
        }
		return true;
    }
    
    this.eqaul = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('eqaulName')];
		
        if (fstObj != null && sndObj != null) {
            if (fstObj.value != sndObj.value) {
               return false;
            }
        }
		return true;
    }

    this.gt = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('eqaulName')];
		
        if (fstObj != null && sndObj != null && fstObj.value.trim()!='' && sndObj.value.trim()!='') {
            if (fstObj.value <= sndObj.value) {
                 return false;
            }
        }
		return true;
    }

	this.compare = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('objectName')];
        if (fstObj != null && sndObj != null && fstObj.value.trim()!='' && sndObj.value.trim()!='') {
            if (!eval('fstObj.value' + inputObj.getAttribute('operate') + 'sndObj.value')) {
                 return false;
            }
        }
		return true;
	}
	
	this.limit = function (inputObj) {
		var len = inputObj.value.length;
		if (len) {
			var minv = inputObj.getAttribute('min');
			var maxv = inputObj.getAttribute('max');
			minv = minv || 0;
			maxv = maxv || Number.MAX_VALUE;
			return minv <= len && len <= maxv;
		}
		return true;
	}
	
	this.range = function (inputObj) {
		var val = parseInt(inputObj.value);
		if (inputObj.value) {
			var minv = inputObj.getAttribute('min');
			var maxv = inputObj.getAttribute('max');
			minv = minv || 0;
			maxv = maxv || Number.MAX_VALUE;
		
			return minv <= val && val <= maxv;
		}
		return true;
	}
	
	this.requireChecked = function (inputObj) {
		var minv = inputObj.getAttribute('min');
		var maxv = inputObj.getAttribute('max');
		minv = minv || 1;
		maxv = maxv || Number.MAX_VALUE;
	
		var checked = 0;
		var groups = document.getElementsByName(inputObj.name);
		
		for(var i=0;i<groups.length;i++) {
			if(groups[i].checked) checked++;
			
		}
		return minv <= checked && checked <= maxv;
	}
	
	this.filter = function (inputObj) {
		var value = inputObj.value;
		var allow = inputObj.getAttribute('allow');
		if (value.trim()) {
			return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, allow.split(/\s*,\s*/).join("|")), "gi").test(value);
		}
		return true;
	}
	
	this.isNo = function (inputObj) {
		var value = inputObj.value;
		var noValue = inputObj.getAttribute('noValue');
		return value!=noValue;
	}
    this.checkReg = function(inputObj, reg, msg) {
        inputObj.value = inputObj.value.trim();

        if (inputObj.value == '') {
            return;
        } else {
            if (!reg.test(inputObj.value)) {
				this.addErrorMsg(inputObj.name,msg);
			}
        }
    }

    this.passed = function() {
        if (this.errMsg.length > 0) {
            FormValid.showError(this.errMsg,this.errName);
            frt = document.getElementsByName(this.errName[0])[0];
			
			if (frt.type!='radio' && frt.type!='checkbox') {
				frt.focus();
			}
            return false;
        } else {
          return true;
        }
    }

    this.addErrorMsg = function(name,str) {
        this.errMsg.push(str);
		this.errName.push(name);
    }
	
    this.addAllName = function(name) {
    	//FormValid.allName.push(name);
    }
	
}

FormValid.allName = new Array();
FormValid.showError = function(errMsg) {
	var msg = "";
	for (i = 0; i < errMsg.length; i++) {
		msg += "- " + errMsg[i] + "\n";
	}
	$.messager.alert('信息输入有误',msg,'error');
}
function objectValid(frm,fv,obj){
	var formElements = frm.elements;
	var validType = obj.getAttribute('valid');
	var errorMsg = obj.getAttribute('errmsg');
	if (validType==null) return fv;
	fv.addAllName(obj.name);
	
	var vts = validType.split('|');
	var ems = errorMsg.split('|');
	for (var j=0; j<vts.length; j++) {
		var curValidType = vts[j];
		var curErrorMsg = ems[j];
		switch (curValidType) {
		case 'isNumber':
		case 'isZNumber':
		case 'isEmail':
		case 'isPhone':
		case 'isMobile':
		case 'isIdCard':
		case 'isMoney':
		case 'isZip':
		case 'isQQ':
		case 'isInt':
		case 'isEnglish':
		case 'isChinese':
		case 'isUrl':
		case 'isDate':
		case 'isTime':
			fv.checkReg(obj,RegExps[curValidType],curErrorMsg);
			break;
		case 'regexp':
			fv.checkReg(obj,new RegExp(obj.getAttribute('regexp'),"g"),curErrorMsg);
			break;
		case 'custom':
			if (!eval(obj.getAttribute('custom')+'(obj,formElements)')) {
				fv.addErrorMsg(obj.name,curErrorMsg);
			}
			break;
		default :
			if (!eval('fv.'+curValidType+'(obj,formElements)')) {
				fv.addErrorMsg(obj.name,curErrorMsg);
			}
			break;
		}
	}
	return fv;
}
function validatorobject(frm,obj){
	var fv = new FormValid(frm);
	
	fv = objectValid(frm,fv,obj);
	
	return fv.passed();
}
function validatorobject2(frm,obj){
	document.getElementById('errMsg_'+obj.name).innerHTML = '';
	var fv = new FormValid(frm);
	
	fv = objectValid(frm,fv,obj);
	
	vflag = true;
	for (key in fv.errMsg) {
		document.getElementById('errMsg_'+fv.errName[key]).innerHTML = fv.errMsg[key];
		vflag = false;
	}
	return vflag;
}
function validator(frm) {
	var ispass = true;
	var formElements = frm.elements;
	var fv = new FormValid(frm);
	for (var i=0; i<formElements.length;i++) {
		fv = objectValid(frm,fv,formElements[i]);
		ispass = fv.passed();
		if (!ispass) return ispass;
	}
	return ispass;
	//return fv.passed();
}
String.prototype.trim = function() {
	return this.replace(/^\s*|\s*$/g, "");
}
var RegExps = function(){};
RegExps.isNumber = /^[-\+]?\d+(\.\d+)?$/;
RegExps.isZNumber = /^[0-9]*[1-9][0-9]*$/;
RegExps.isEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;
RegExps.isPhone = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
RegExps.isMobile = /^((\(\d{2,3}\))|(\d{3}\-))?(13|15|18)\d{9}$/;
RegExps.isIdCard = /(^\d{15}$)|(^\d{17}[0-9Xx]$)/;
RegExps.isMoney = /^\d+(\.\d+)?$/;
RegExps.isZip = /^[1-9]\d{5}$/;
RegExps.isQQ = /^[1-9]\d{4,10}$/;
RegExps.isInt = /^[-\+]?\d+$/;
RegExps.isEnglish = /^[A-Za-z]+$/;
RegExps.isChinese =  /^[\u0391-\uFFE5]+$/;
RegExps.isUrl = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
RegExps.isDate = /^\d{4}-\d{1,2}-\d{1,2}$/;
RegExps.isTime = /^\d{4}-\d{1,2}-\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}$/;

RegExps.isPass = /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/; // 6-15 password

function isIntNumber(obj, _event, _allLen){
   return isNumber(obj, _event, _allLen, 0);
}
function isNumber(obj, _event, _allLen, _decimalLen){
   var allLen = _allLen;
   var decimalLen = _decimalLen;
   if(allLen==null) allLen = 20;
   if(allLen<0) allLen = 20;
   if(decimalLen==null) decimalLen = 0;
   if(decimalLen<0) allLen = 0;
   
   var flag = true;
   var objV = obj.value;
   var pos = getCursorPostion(obj);
   var eve;
   if(window.event){
      eve = window.event;
   }else{
      eve = _event;
   }
   if(eve.keyCode==190||eve.keyCode==110){
      if(decimalLen>0){
        var idx = obj.value.indexOf(".");
        if(idx>=0){
           flag = false;
        }
      }else{
         flag = false;
      }
   }else if(eve.keyCode==8||eve.keyCode==46||eve.keyCode==37||eve.keyCode==38||eve.keyCode==39||eve.keyCode==40){
      flag = true;
      eve.returnValue = flag;
      return flag;
   }else if(!isNumeric(eve.keyCode)){
      flag = false;
   }
   if(flag==true){
       if(isNumeric(eve.keyCode)){
           var t = eve.keyCode;
           if(t>=96) t = t-48;
           var afterValue = objV.substring(0,pos)+(t-48)+objV.substring(pos);
           var afterLength = afterValue.length;
           var idx = afterValue.indexOf(".");
           if(idx>0) {
              afterLength = afterLength-1;
           }
           if(afterLength>allLen) {
              flag = false;
           }else{
              if(idx>=0) {
                 if((afterLength-idx)>(decimalLen)){
                    flag = false;
                 }
              }
           }
           if(eve.keyCode==48||eve.keyCode==96){
                if(afterLength>1){
                   if(afterValue.substring(0,2)=='00'){
                      flag = false;
                   }
                }
           }
       }
   }
   eve.returnValue = flag;
   return flag;
}
function isNumeric(key){
   if(key>=48&&key<=57){
       return true;
   }
   if((key>=96)&&(key<=105)){
      return true;
   }
   return false;
}  
function getCursorPostion(obj){
   if(document.all){
      var workRange=document.selection.createRange();
      var vv = obj.createTextRange();
      workRange.setEndPoint("StartToStart",vv);
      return workRange.text.length;
   }else{
      return obj.selectionStart;  //fire fox
   }
}
function compareValue(obj, value1, message, _op){
   if(obj.value=="") return true;
   var vF = parseFloat(obj.value);
   var op = _op;
   if(op==null) op = ">";
   switch (op) {
	 case '>':
	     if(vF<=value1) {
	     $.messager.alert('信息输入有误',message);
	     	return false;
	     }
	     break;
	 case '>=':
	     if(vF<value1) {
	     	 $.messager.alert('信息输入有误',message);
	     	return false;
	     }
	     break;
	 case '<':
	     if(vF>=value1) {
	     	 $.messager.alert('信息输入有误',message);
	     	return false;
	     }
	     break;
	 case '<=':
	     if(vF>value1) {
	     	 $.messager.alert('信息输入有误',message);
	     	return false;
	     }
	     break;
	 default:
	     break;
   }
   return true;
}
function compareRangle(obj, value1, value2, message){
   if(obj.value=="") return true;
   var vF = parseFloat(obj.value);
   if(vF<value1||vF>value2){
	    $.messager.alert('信息输入有误',message);
	   return false;
   }
   return true;
}
function compareObject(obj1, obj2, message, message2){
   if(obj1.value=="") {
      if(obj2.value!=""){
        $.messager.alert('信息输入有误',message);
         return false;
      }
      return true;
   }
   if(obj2.value=="") {
      if(obj1.value!=""){
        $.messager.alert('信息输入有误',message);
         return false;
      }
      return true;
   }
   var vF1 = parseFloat(obj1.value);
   var vF2 = parseFloat(obj2.value);
   if(vF1>vF2){
	  $.messager.alert('信息输入有误',message);
	   return false;
   }
   return true;
}

  function beyondOpen(url, targetN){
     var submitForm = beyondCreateNewForm();
     var idx = url.indexOf("?");
     var rUrl = url;
     var parmstr = "";
     if(idx>0){
        rUrl = url.substring(0, idx);
        parmstr = url.substring(idx+1);
     }
     var parmsA = parmstr.split("&");
     var length = parmsA.length;
     for(i=0; i<length; i++){
        var tmpValue = parmsA[i];
        var tmpIdx = tmpValue.indexOf("=");
        if(tmpIdx>0){
           var attrName = tmpValue.substring(0, tmpIdx);
           var attrValue = tmpValue.substring(tmpIdx+1);
           var elementObj = beyondCreateNewElement(attrName, attrValue)
           submitForm.appendChild(elementObj);
        }
     }
     submitForm.action= rUrl;
     if(targetN!=null&&targetN!=""){
     	submitForm.target = targetN;
     }
     submitForm.submit();
  }
  function beyondCreateNewForm(){
     var submitForm = document.createElement("FORM");
     document.body.appendChild(submitForm);
     submitForm.method = "POST";
     return submitForm;
  }
  function beyondCreateNewElement(elementName, elementValue){
      var newElement = document.createElement("input");
      newElement.setAttribute("name",elementName);
      newElement.setAttribute("type","hidden");
      newElement.setAttribute("value",elementValue);
      return newElement;
  }