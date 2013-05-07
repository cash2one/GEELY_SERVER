
/* ---------------------------- 通用JS函数库 ------------------------------------- */
/* ------------------- 重建人员：龙湘勇 重建时间：2008-11-06 ------------------------ */
/*
 * 根据文件路径包含JS文件
 *
 * 参数1：JS文件路径
 *
 * 编写人员：龙湘勇
 * 编写时间：2008-11-06
 */
function include(path) {
	var sobj = document.createElement("script");
	sobj.type = "text/javascript";
	sobj.src = path;
	var headobj = document.getElementsByTagName("head")[0];
	headobj.appendChild(sobj);
}
function getElesByName(tag, name) {
	var eles = document.getElementsByTagName(tag);
	var arr = new Array();
	var j = 0;
	for (var i = 0; i < eles.length; i++) {
		if (eles[i].getAttribute("name") == name) {
			arr[j] = eles[i];
			j++;
		}
	}
	return arr;
}
function showEle(eles, id, attr) {
	attr = attr ? attr : "id";
	for (var i = 0; i < eles.length; i++) {
		if (eles[i].getAttribute(attr) == id) {
			eles[i].style.display = "block";
		} else {
			eles[i].style.display = "none";
		}
	}
}
function getFCKHtml(iname) {
	var e_iname = FCKeditorAPI.GetInstance(iname);
	return e_iname.GetHTML();
}
/**
 * parse url to get params
 * @return
 */
function getargs(queryString) {
	var args = new Object();
	var query = !queryString || queryString == null ? location.search.substring(1) : queryString;    // get query string
	var pairs = query.split("&");                 // break at ampersand
	for (var i = 0; i < pairs.length; i++) {
		var pos = pairs[i].indexOf("=");          // look for "name=value"
		if (pos == -1) {
			continue;
		}                  // if not found, skip
		var start = pairs[i].indexOf("?") == -1 ? 0 : pairs[i].indexOf("?") + 1;
		var argname = pairs[i].substring(start, pos); // extract the name
		var value = pairs[i].substring(pos + 1);        // extract the value
		value = decodeURIComponent(value);       // decode it, if needed
		args[argname] = value; //alert(argname+"  "+value);                   // store as a property
	}
	return args;                                  // return the object
}
function delargs(url, ref) {
	var str = "";
	if (url.indexOf("?") != -1) {
		str = url.substr(url.indexOf("?") + 1);
	} else {
		return url;
	}
	var arr = "";
	var returnurl = "";
	var setparam = "";
	if (str.indexOf("&") != -1) {
		arr = str.split("&");
		for (i in arr) {
			if (arr[i].split("=")[0] != ref) {
				returnurl = returnurl + arr[i].split("=")[0] + "=" + arr[i].split("=")[1] + "&";
			}
		}
		return url.substr(0, url.indexOf("?")) + "?" + returnurl.substr(0, returnurl.length - 1);
	} else {
		arr = str.split("=");
		if (arr[0] == ref) {
			return url.substr(0, url.indexOf("?"));
		} else {
			return url;
		}
	}
}
function getEwEditor(id) {
	return document.getElementById(id).contentWindow;
}
function getEwHtml(id) {
	return getEwEditor(id).getHTML();
}
function UpdateEditorFormValue() {
	var framesArr = document.getElementsByTagName("iframe");//alert(framesArr.length);
	for (var i = 0; i < framesArr.length; ++i) {//alert(framesArr[i].FCK);
		if (framesArr[i].FCK) {
			framesArr[i].FCK.UpdateLinkedField();
		} else {
			if (framesArr[i].contentWindow.getHTML && framesArr[i].id) {
				var id = framesArr[i].id;//alert(id);
				var psrc = framesArr[i].src;//alert(psrc);
				var eid = getargs(psrc)["id"];//alert(eid);
				if (eid) {
					var input = document.getElementsByName(eid)[0];
					var editor = getEwEditor(id);
					input.value = editor.getHTML();
	        	//alert(input.value);
				}
			}
		}
	}
}
function getAllewEditors() {
	var framesArr = document.getElementsByTagName("iframe");
	var j = 0;
	var ew_frames = new Array();
	for (var i = 0; i < framesArr.length; ++i) {
		if (framesArr[i].src && framesArr[i].src.indexOf("ewebeditor.htm") != -1) {
			ew_frames[j] = framesArr[i].contentWindow;
			j++;
		}
	}
	return ew_frames;
}
function initEditorStyle(emode){
	var editors=getAllewEditors();
	for (var i = 0; i < editors.length; ++i) {
		editors[i].setMode(emode);
	}
}

function isPost() {
	for (var z = 0; z < ew_post.length; ++z) {
		if (ew_post[z] == false) {
			return false;
		}
	}
	return true;
}
var ew_post;
var ew_strEventUploadFinal;
function ewremoteUpload(strEventUploadFinal) {//alert("ewr");
	var all_ew = getAllewEditors();//alert(all_ew.length);
	ew_post = new Array(all_ew.length);
	for (var z = 0; z < ew_post.length; ++z) {
		ew_post[z] = false;
	}
	ew_strEventUploadFinal = strEventUploadFinal;
	for (var i = 0; i < all_ew.length; ++i) {
		all_ew[i].remoteUpload("doEwSubmit(" + i + ")");
	}
}
function doEwSubmit(index) {
	ew_post[index] = true;
	//alert(index);alert(ew_post[0]+"  "+ew_post[1]+"  "+ew_post[2]);
	if (isPost()) {
		eval(ew_strEventUploadFinal);
	}
}
String.prototype.UrlEncode = function () {
	var str = this;
	str = str.replace(/./g, function (sHex) {
		window.EnCodeStr = "";
		window.sHex = sHex;
		window.execScript("window.EnCodeStr=Hex(Asc(window.sHex))", "vbscript");
		return window.EnCodeStr.replace(/../g, "%$&");
	});
	return str;
};

//包含字符串函数库文件
include("res/js/string.js"); 

//包含数学函数库文件
include("res/js/math.js"); 

//包含时间函数库文件
include("res/js/date.js"); 

//包含页面元素处理函数库文件
include("res/js/htmldom.js"); 

//包含输入检验函数库文件
include("res/js/inputValidate.js");

