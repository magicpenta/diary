<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日记预览</title>

<script language="javascript">
window.onload=function(){								//当页面载入后
	document.getElementById("diaryImg").src="CreateImg";
}
function is_Submit(){
	myform.action="DiaryServlet?action=save";
	myform.submit();
}
</script>

<style>
#box {
	font-family: "Microsoft YaHei UI";
}
#box ul {
	list-style: none;
	text-align: center;
}
#box li {
	margin-bottom: 30px;
}
#saveButton {
	text-decoration: none;
	background: #2C91ED;
	color: #FFF;
	padding: 10px 33px 10px 33px;
	border-radius: 3px;
	/* transition */
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
}

#saveButton:hover {
	background: #2984D9;
}
#correct {
	text-decoration: none;
	background: #0C0;
	color: #FFF;
	padding: 10px 33px 10px 33px;
	border-radius: 3px;
	/* transition */
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
}

#correct:hover {
	background: #0C9;
}

</style>

</head>

<body>
<%@ include file="top.jsp" %>
<div id="box">
    <div>
        <ul>
            <li>标题：${sessionScope.title }</li>
            <li><img src="images/loading.gif" name="diaryImg" id="diaryImg"/></li>
            <li>
				<a id="correct"href="#" style="" onclick="history.back();">再改改</a> &nbsp;&nbsp;
				<a id="saveButton" href="DiaryServlet?action=save">保存</a>
            </li>
        </ul>
	</div>
    <c:if test="${empty sessionScope.userName}">
		<c:redirect url="index.jsp"/>
	</c:if>
</div>
<%@ include file="bottom.jsp" %>
</body>
</html>