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
<link rel="stylesheet" href="resources/css/UserPageStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button" />
<fmt:message bundle="${loc}" key="local.text.common.created" var="created"/>
<fmt:message bundle="${loc}" key="local.button.create_news" var="creat_news"/>
<fmt:message bundle="${loc}" key="local.button.profile" var="profile"/>
<fmt:message bundle="${loc}" key="local.button.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.button.userpage.adminpage" var="admin_page"/>


</head>
<body>
	<div class="main-block-header">
		
		<div class="separating-block-header">
			<h1 class="siteName">News</h1>
		</div>
		
		<div class="separating-block-header">
			<div class="button_place" >
				<form action="Controller" method="post">
					 <input	type="hidden" name="command" value="GO_TO_CREATE_NEWS_PAGE" /> 
					 <input	type="submit" class="button_create_news" value="${creat_news}" /><br />
				</form>
				<c:if test="${sessionScope['user'].getRole().equals('admin')}">
					<form action="Controller" method="post">
						 <input	type="hidden" name="command" value="GO_TO_ADMIN_PAGE" /> 
						 <input	type="submit" class="button_create_news" value="${admin_page}" /><br />
					</form>
				</c:if>
				
			</div>
		</div>
		
		<div class="separating-block-header ">
				<div class="button_place" >
					<p class="text_user"><c:out value="${sessionScope['user'].getName()}" />&nbsp<c:out value="${sessionScope['user'].getSurname()}" /></p>
				<form action="Controller" method="post">
					 <input	type="hidden" name="command" value="GO_TO_USER_SETUP_PAGE" /> 
					 <input	type="submit" class="button_profile" value="${profile}" /><br />
				</form>
				<form action="Controller" method="post">
					 <input type="hidden" name="command" value="Logout" /> 
					 <input	type="submit" class="button_logout" value="${logout}" /><br />
				</form>
				</div >
				
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
	
	<!-- Здесь начинается вывод новостей -->

	<table align="center">
		<c:forEach items="${newses}" var="clip">
			<tr>
				<td style="color: black">
					<div class="newsSt">
   						 <img src=<c:out value="${clip.getLinkImage()}" /> width="600" height="400">
   						 <div class="containerNewsSt">
   							 <form action="Controller" method="post">
									<input type="hidden" name="command" value="GO_TO_CONTENT_NEWS_PAGE" />
									<input type="hidden" name="newsId" value="${clip.getNewsId()}" />
									<input type="submit" class="btn" value="${clip.getTitle()}" />
							</form>
   											
					    </div>
					</div>
					<HR WIDTH="90%" ALIGN="center" SIZE="2">
				</td>
			</tr>
		</c:forEach>
	</table>

	
	<p class="text_end" style="font-size: 20px">
		<c:out value="${created}" />
	</p>
</body>
</html>