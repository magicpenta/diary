<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>找回密码</title>

<link rel="stylesheet" href="css/style.css" />
</head>

<body>
<script language="javascript">
function checkForm(form){
	if (form.answer.value==""){
		document.getElementById("div_answer").style.display='block';	
	} else {
		document.getElementById("div_answer").style.display='none';
	}
}
</script>
    <div class="container">
    	<h1>找回密码</h1>
        <form action="UserServlet?action=getPwd" method="post" name="myform" id="myform">
        	<input type="hidden" name="userName" id="userName" value="${requestScope.userName}">
            <input type="text" name="question" id="question" value="${requestScope.question}" />
            <input type="text" name="answer" id="answer" placeholder="请输入提示问题答案" />
            <button type="submit" name="finish" id="finish" style="background:#09F; border: 1px solid #0CF; width:300px" onclick="checkForm(this.form)"/>完 成</button>
            <div id="div_answer" class="errorPane_2">
            	<span>请输入提示问题答案</span>
            </div>
        </form>
    </div>
</body>
</html>
