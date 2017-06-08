<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/top.css" />
<link rel="stylesheet" type="text/css" href="css/diary.css" />

<div id="top_box">
	<div id="header">
        <div id="header1">
            <c:if test="${!empty sessionScope.userName}">
                <ul id="content">
                    <li>
                         <a href="#">欢迎${sessionScope.userName}登录Panda日记</a>
                    </li>	
                </ul>
            </c:if>
            <c:if test="${empty sessionScope.userName}">
                <ul id="content">
                    <li>
                         <a href="#">欢迎光临Panda日记</a>
                    </li>
                </ul>
            </c:if>
        </div>
        <div id="header2">
            <ul id="content">
                <li>
                     <a href="DiaryServlet?action=listAllDiary">首页</a>
                </li>
                <c:if test="${empty sessionScope.userName}">
                <li>
                     <a href="login.html">登录</a>
                </li>
                <li>
                     <a href="register.html">注册</a>
                </li>
                <li>
                     <a href="getAnswer.html">找回密码</a>
                </li>
                </c:if>
                <c:if test="${!empty sessionScope.userName}">
                <li>
                     <a href="DiaryServlet?action=listMyDiary">我的日记</a>
                </li>
                <li>
                     <a href="writeDiary.jsp">写日记</a>
                </li>
                <li>
                     <a href="UserServlet?action=exit">退出登录</a>
                </li>
                </c:if>
            </ul>  	
        </div>
    </div>
    <div id="banner">
    	<div id="wrapper">
            <h1>
                Panda 日记
            </h1>
            <p>
                涓 涓 细 流 ， 终 为 江 河
            </p><br />
            <p>
                 <a href="#" id="button">了解Panda</a>
            </p>
        </div>
    </div>
    <div id="mytext">
    	<b>每一天，都是不一样的自己。</b>
    </div>
</div>
