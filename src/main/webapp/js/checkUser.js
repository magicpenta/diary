// JavaScript Document

function checkStr(str,digit){
	var n=0;
	for(i=0;i<str.length;i++){
		var leg = str.charCodeAt(i);
		if (leg>255){
			n+=2;
		} else {
			n+=1;
		}
	}
	if (n>digit){
		return true;
	} else {
		return false;
	}
}

// 判断用户名是否超过20个字符
function checkUserName(userName){
	if(checkStr(userName,20)){
		return false;
	} else {
		return true;
	}
}

// 检查密码
function checkPassword(pwd){
	var str = pwd;
	var Expression = /^[A-Za-z]{1}([A-Za-z0-9]|[._]){5,29}$/;
	var objExp = new RegExp(Expression);
	if (objExp.test(str)==true)
	{
		return true;
	} else {
		return false;
	}
}
	
// 检查邮箱地址
function checkEmailAdd(email){
	var str=email;
	 //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression=/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/; 
	var objExp=new RegExp(Expression);		//创建正则表达式对象
	if(objExp.test(str)==true){				//通过正则表达式进行验证
		return true;
	}else{
		return false;
	}
}