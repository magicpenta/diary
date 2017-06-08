<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>写日记</title>
<script language="javascript">
function setTemplate(style){
	if (style=="极简主义"){
		document.getElementById("diarybg").style.backgroundImage="url(images/diaryBG_1.jpg)";
		document.getElementById("template").value="极简主义";		
	} else if (style=="高冷风范"){
		document.getElementById("diarybg").style.backgroundImage="url(images/diaryBG_2.jpg)";
		document.getElementById("template").value="高冷风范";	
	}
}
</script>
</head>

<body>

<%@ include file="top.jsp" %>

<c:if test="${empty sessionScope.userName}">
    <c:redirect url="index.jsp"/>
</c:if>

<div id="box">
	<form id="writePanel" name="writePanel" action="DiaryServlet?action=preview" method="post">
		<div id="control">
	    	<div id="model">
	        	<span>请选择模板：</span>
	            <a href="###" onclick="setTemplate('极简主义')">极简主义</a>
	            <a href="###" onclick="setTemplate('高冷风范')">高冷风范</a>
	            <input id="template" name="template" type="hidden" value="极简主义">
	        </div>
	        <div id="title">
	        	请输入日记标题：
	            <input type="text" id="div_title" name="div_title" maxlength="30" placeholder="请在此输入标题" />
	        </div>
	    </div>
        <div id="diarybg">
            <div id="girdLayout">
                <ul id="opt">
                	<li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字" onfocus="this.select()"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[0].value='搞定技术难题了'">搞定技术难题了！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[0].value='撩到女神~'">撩到女神~</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[0].value='拿到五杀了！'">拿到五杀了！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[0].value='BUG解决了！。'">BUG解决了！</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[1].value='逛一逛github。'">逛一逛github。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[1].value='刷一刷知乎。'">刷一刷知乎。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[1].value='撸代码撸代码撸代码。'">撸代码撸代码撸代码。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[1].value='改BUG改BUG改BUG。'">改BUG改BUG改BUG。</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[2].value='准备好和产品撕逼。'">准备好和产品撕逼。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[2].value='做好本周技术总结。'">做好本周技术总结。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[2].value='屯点零食好加班。'">屯点零食好加班。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[2].value='下班前多看服务器几眼。'">下班前多看服务器几眼。</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[3].value='劲爆小龙虾！'">劲爆小龙虾！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[3].value='麻辣烫！'">麻辣烫！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[3].value='飘香榴莲酥！'">飘香榴莲酥！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[3].value='经典炒拉面！'">经典炒拉面！</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[4].value='100分！完美的一天！'">100分！完美的一天！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[4].value='80分！生活还有期待！'">80分！生活还有期待！</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[4].value='60分。平平淡淡。'">60分。平平淡淡。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[4].value='0分！不能更糟了！'">0分！不能更糟了！</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[5].value='startup'">startup</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[5].value='mvn tomcat7:run'">mvn tomcat7:run</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[5].value='mvn tomcat7:deploy'">mvn tomcat7:deploy</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[5].value='shutdown'">shutdown</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[6].value='和基友看场电影。'">和基友看场电影。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[6].value='和基友五黑。'">和基友五黑。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[6].value='和基友吹牛逼。'">和基友吹牛逼。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[6].value='和基友吃路边摊。'">和基友吃路边摊。</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[7].value='慢跑。'">慢跑。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[7].value='游泳。'">游泳。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[7].value='球类运动。'">球类运动。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[7].value='健身房举铁。'">健身房举铁。</a>
                    </li>
                </ul>
            </div>
            <div id="girdLayout">
                <ul id="opt">
                    <li>
                    	<input name="content" type="text" maxlength="30" placeholder="请在此输入文字"/>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[8].value='给爸妈打个电话。'">给爸妈打个电话。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[8].value='给女票买点礼物。'">给女票买点礼物。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[8].value='尝试做一道菜。'">尝试做一道菜。</a>
                    </li>
                    <li>
                        <a href="###" onclick="document.getElementsByName('content')[8].value='做点家务活。'">做点家务活。</a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="preview">
        	<button>预览</button>
        </div>
    </form>
</div>
<%@ include file="bottom.jsp" %>
</body>
</html>
