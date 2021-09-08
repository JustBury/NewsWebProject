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
<link rel="stylesheet" href="resources/css/NewsContentPageStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site" />
<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button" />
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.login"
	var="Login_button" />
<fmt:message bundle="${loc}" key="local.text.common.created"
	var="created" />
<fmt:message bundle="${loc}" key="local.text.hello" var="hello" />
<fmt:message bundle="${loc}" key="local.button.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.button.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.button.main" var="home_page"/>



</head>
<body>
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
						<input type="hidden" name="command" value="go_to_main_page" /> <input
							type="submit" class="button_Menu" value="${home_page}" /><br />
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
					<c:out value="${sessionScope['user'].getName()}" />
					&nbsp
					<c:out value="${sessionScope['user'].getSurname()}" />
				</p>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="GO_TO_USER_SETUP_PAGE" />
					<input type="submit" class="button_profile" value="${profile}" /><br />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="LOGOUT_USER" /> <input
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
	<font color="green" size="3"> <c:out value='${param.messageComment}' />
	</font>

	<div class="news_field">
		<div class="news_field_brief">
			<p class="text_brief">
				<c:out value='${requestScope["news"].getBrief()}' />
			</p>
		</div>
		<HR WIDTH="90%" ALIGN="center" SIZE="2">
		<div class="news_field_content">
			<p class="text_content">
				<c:out value='${requestScope["news"].getContent()}' />
			</p>
		</div>
	</div>

	<div class="news_field">
		<div class="new_coment">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="ADD_COMMENT_NEWS" /> <input
					type="hidden" name="newsId"
					value='${requestScope["news"].getNewsId()}' />
				<textarea rows="5" cols="90" name="content"> </textarea>
				<input type="submit" class="button_create_news" value="Add comment" /><br />
			</form>
		</div>
		<HR WIDTH="90%" ALIGN="center" SIZE="2">





		<div class="news_field">
			<c:forEach items="${comments}" var="comment">
				<div class="comment_area">
					<div class="news_field_brief">
						<p class="text_comment">
							<c:out value="${comment.getContent()}" />
						</p>
					</div>
					<div class="news_field_content">
						<p class="text_author_comment">
							<c:out value="${comment.getAuthorComment().getName() }" />
							&nbsp
							<c:out value="${comment.getAuthorComment().getSurname()}" />
							&nbsp
							<c:out value="${comment.getDatePublication()}" />
						</p>
					</div>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="DELETE_COMMENT" /> 
						<input type="hidden" name="idComment"	value='${comment.getIdComment()}' />
						<input type="hidden" name="newsId"	value='${requestScope["news"].getNewsId()}' />
						<input type="submit" class="button_create_news"
							value="DELETE_COMMENT" /><br />
					</form>
				</div>
			</c:forEach>
		</div>



	</div>



	<p class="text_end" style="font-size: 20px">
		<c:out value="${created}" />
	</p>
</body>
</html>