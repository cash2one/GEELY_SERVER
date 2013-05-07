FormValid 0.3 完整功能版

简介：完全通过html不需要写JS验证程序就可以实现表单验证功能，可支持一个表单元件多条件验证，
同一页面多表单可同时使用，和服务端程序无关系，完全客户端方式。

特性：
	23种常用验证规则
	一个表单元件支持同时多种验证规则
	可自定义正则规则
	可自定义函数规则
	可自定义错误显示方式

使用方法：在要验证的表单(form)中加上 onsubmit="return validator(this)"
在要验证的表单元件中设置元件，valid(验证规则),errmsg（提示消息），多个验证使用|作为分隔！


验证规则参数：
	required 必填
	eqaul    和另一元件对比值是否相同,对比的元件名在元件加上属性eqaulName
	gt 	 和另一元件对比值是否大于另一元件值,对比的元件名在元件加上属性eqaulName
	isNumber 是否为数字
	isInt    是否为整形
	isTime   是否为时间格式
	isDate   是否为日期格式
	isEmail  必须是Email格式
	
	isPhone  是否为电话号码
	isMobile 是否为手机号码
	isIdCard 是否为身份证号码
	isMoney  是否为货币值
	isZip    是否是邮件编码
	isQQ     是否是QQ
	isEnglish 是否是english
	isChinese 是否是中文
	isUrl     是否是url
	compare   对比两个值
	limit     长度限制
	range     值范围限制
	requireChecked 单选、复选框必须选择
	filter         扩展名限制
        isNo               值不等于

	regexp    自定义正则判断
	custom    自定义函数判断

更新历史：
0.3 
加入十几种验证规则
加入自定义正则判断
加入自定义函数判断
加入将焦点移到第一个错误元件

0.2
加入自定义错误显示方式
