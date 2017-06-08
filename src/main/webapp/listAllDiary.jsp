<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>panda日记</title>
</head>

<body>
<style>
#container {
	font-family: "Microsoft YaHei UI";
	width: 70%;
	border-top: 3px solid #343434;
	border-bottom: 3px solid #343434;
	margin: 15px auto 30px auto;
	padding-top: 20px;
	padding-bottom: 20px;
}
.diarybox .cover {
	cursor: pointer;
	width: 200px;
	height: 200px;
	background: rgba(0, 0, 0, 0.7);
	padding-top: 90px;
	position: absolute;
	left: 0;
	top: 0;
	text-align: center;
	color: #ffffff;
	transform-origin: bottom;
	-webkit-transform-origin: bottom;
	-moz-transform-origin: bottom;
	transform: translateY(200px);
	-webkit-transform: translateY(200px);
	-moz-transform: translateY(200px);
	transition: all 0.35s;
	-webkit-transition: all 0.35s;
	-moz-transition: all 0.35s;	
}

.diarybox:hover .cover {
	transform: translateY(0px);
	-webkit-transform: translateY(0px);
	-moz-transform: translateY(0px);	
}

.diarybox .cover a {
	width: 60px;
	height: 30px;
	border: solid 1px #FFFFFF;
	padding: 10px 30px 10px 30px;
	color: #FFF;
	text-decoration: none;	
}
#writeTime {
	text-align: right;
}
#empty {
	text-align: center;
}
#pageBottom {
	margin-top: 20px;
}
</style>
<script language="javascript">
var i = 0;	//标记变量，用于记录当前页共几条日记
function zoomIn(id, url){
	document.getElementById("diary"+id).style.display="";
	// 用于展开图片
	document.getElementById("diary"+id).src="images/diary/"+url+".png";
	document.getElementById("diaryImg"+id).style.width=1280;
	document.getElementById("diaryImg"+id).style.height=1280;
	document.getElementById("diary"+id).style.width=1280;
	document.getElementById("diary"+id).style.height=1280;
	document.getElementById("cover"+id).style.display="none";
}
function zoomOut(id,url){
	// 用于收缩图片
	document.getElementById("diary"+id).src="images/diary/"+url+"scale.jpg";
	document.getElementById("diaryImg"+id).style.width=200;
	document.getElementById("diaryImg"+id).style.height=200;
	document.getElementById("diary"+id).style.width=200;
	document.getElementById("diary"+id).style.height=200;
	document.getElementById("cover"+id).style.display="block";
}
</script>
<script type="javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="pagination" class="com.diary.tools.MyPagination"
	scope="session" />
<%@ include file="top.jsp"%>
<div id="container">
	<c:if test="${!empty requestScope.diaryList}">
		<c:forEach items="${requestScope.diaryList}" var="diaryList"
			varStatus="id">
			<script type="text/javascript">
				i++;	//标记变量，用于记录当前页共几条日记
			</script>
			<div id="diaryInfo">
				<font>${diaryList.userName}</font> &nbsp;&nbsp;发表日记： <b>${diaryList.title}</b>
			</div>
			<br />
			<div id="diaryImg${id.count}" class="diarybox" style="position:relative;overflow:hidden;">
				<img id="diary${id.count}" src="images/diary/${diaryList.address}scale.jpg" style="cursor: pointer;" onclick="zoomOut('${id.count}','${diaryList.address}')"/>
				    <div id="cover${id.count}" class="cover">
    					<a href="javascript:zoomIn('${id.count}','${diaryList.address}')">点击查看</a>
    				</div>
			</div>
			<br />
			<div id="writeTime">
				发表时间：
				<fmt:formatDate value="${diaryList.writeTime}"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<c:if test="${sessionScope.userName==diaryList.userName}">
					<a
						href="DiaryServlet?action=delDiary&id=${diaryList.id}&url=${requestScope.url}&imgName=${diaryList.address}">删除</a>
				</c:if>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.diaryList}">
	<div id="empty">
		暂无日记！
	</div>
	</c:if>
	<script type="text/javascript">
	</script>
	<div id="pageBottom">
		<%=pagination.printCtrl(Integer.parseInt(request.getAttribute("Page").toString()),"DiaryServlet?action="+request.getAttribute("url"),"")%>
	</div>
</div>
<%@ include file="bottom.jsp" %>
</body>
</html>