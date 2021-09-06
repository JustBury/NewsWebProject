<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/CommonStyles.css">
<link rel="stylesheet" href="resources/css/AdminPageStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site" />
<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button" />
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button" />
<fmt:message bundle="${loc}" key="local.button.login" var="Login_button" />
<fmt:message bundle="${loc}" key="local.button.register"
	var="Register_button" />
<fmt:message bundle="${loc}" key="local.text.mainpage.welcom"
	var="welcom" />
<fmt:message bundle="${loc}" key="local.text.common.created"
	var="created" />
<fmt:message bundle="${loc}" key="local.button.main" var="home_page" />
<fmt:message bundle="${loc}" key="local.button.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.button.logout" var="logout" />

</head>
<body link="white" vlink="white" alink="white" bgcolor="black">
	<div class="main-block-header">
		<div class="separating-block-header">
			<h1 class="siteName">News</h1>
		</div>
		<div class="separating-block-header">
			<div class="button_place">
				<div class="menu_place">
					<form style="width: 33.3%" action="Controller" method="post">
						<input type="hidden" name="command" value="go_to_main_page" /> <input
							type="submit" class="button_Menu" value="${home_page}" /><br />
					</form>
					<form style="width: 33.3%" action="Controller" method="post">
						<input type="hidden" name="command" value="GO_TO_LIST_USERS_PAGE" />
						<input type="submit" class="button_Menu" value="LIST USERS" /><br />
					</form>
					<form style="width: 33.3%" action="Controller" method="post">
						<input type="hidden" name="command" value="go_to_main_page" /> <input
							type="submit" class="button_Menu" value="${home_page}" /><br />
					</form>
				</div>
			</div>
		</div>
		<div class="separating-block-header ">
			<div class="button_place">
				<p class="text_user">
					<c:out
						value="${sessionScope['user'].getName()} ${sessionScope['user'].getSurname()}" />
				</p>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="GO_TO_USER_SETUP_PAGE" />
					<input type="submit" class="button_profile" value="${profile}" /><br />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="Logout" /> <input
						type="submit" class="button_logout" value="${logout}" /><br />
				</form>
			</div>
			<div class="button_place" style="margin: left">
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="ru" /> <input
						type="hidden" name="command" value="CHANGE_LOCAL" /> <input
						type="submit" class="button_local" value="${rus_button}" /><br />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="en" /> <input
						type="hidden" name="command" value="CHANGE_LOCAL" /> <input
						type="submit" class="button_local" value="${eng_button}" /><br />
				</form>
			</div>

		</div>
	</div>

	<p class="text">OFFICE ADMIN</p>

	<HR WIDTH="90%" ALIGN="center" SIZE="2">

	<!-- Здесь начинается вывод новостей -->



	<font color="green" size="3"> <c:out value='${param.message}' />
	</font>

	<div class="div_table">
		<table>
			<caption>${listusers}</caption>
			<tr>
				<th>Id NEWS</th>
				<th>TITLE</th>
				<th>AUTHOR</th>
				<th>STATUS</th>
				<th>DATA</th>
				<th>${date}</th>
				<th></th>
			</tr>
			<c:forEach items="${newses}" var="news">
				<tr>
					<td><c:out value="${news.getNewsId()}" /></td>
					<td><c:out value="${news.getTitle()}" /></td>
					<td><c:out
							value="${news.getAuthor().getSurname()} ${news.getAuthor().getName()}" /></td>
					<td><c:out value="${news.getStatus()}" /></td>
					<td><c:out value="${news.getDataPublication()}" /></td>
					<td><form action="Controller" method="post">
							<input type="hidden" name="newsId" value="${news.getNewsId()}" />
							<input type="hidden" name="command"
								value="GO_TO_CORRECTION_NEWS_PAGE" /> <input type="submit"
								class="button_view" value="VIEW" /><br />
						</form></td>
				</tr>

			</c:forEach>

		</table>
	</div>
	<p class="text" style="font-size: 20px">
		<c:out value="${created}" />
	</p>
</body>
</html>