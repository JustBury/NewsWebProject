<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/userPageCss.css">


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.login"
	var="Login_button" />
<fmt:message bundle="${loc}" key="local.text.created" var="created" />
<fmt:message bundle="${loc}" key="local.text.hello" var="hello" />


</head>
<body>
	<div class="main-block-header">
		<div class="separating-block-header">
			<h1 class="siteName">News</h1>
		</div>
		<div class="separating-block-header"></div>
		<div class="separating-block-header ">
			<div class="button_place">
				<p class="text_user">Login</p>
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="exit" /> <input
						type="hidden" name="command" value="EXIT" /> <input type="submit"
						class="button_exit" value="EXIT" /><br />
				</form>
			</div>

			<div class="button_place" style="margin: left">
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="ru" /> <input
						type="hidden" name="command" value="CHANGE_LOCAL" /> <input
						type="submit" class="button_local" value="RUS" /><br />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="en" /> <input
						type="hidden" name="command" value="CHANGE_LOCAL" /> <input
						type="submit" class="button_local" value="ENG" /><br />
				</form>
			</div>
		</div>
	</div>


	<p class="text" style="font-size: 20px">
		<c:out value="${created}" />
	</p>
</body>
</html>