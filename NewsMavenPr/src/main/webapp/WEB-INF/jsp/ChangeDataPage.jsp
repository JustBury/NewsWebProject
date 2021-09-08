<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/CommonStyles.css">
<link rel="stylesheet" href="resources/css/ChangeDataPageStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button" />
<fmt:message bundle="${loc}" key="local.text.common.created" var="created"/>
<fmt:message bundle="${loc}" key="local.button.create_news" var="creat_news"/>
<fmt:message bundle="${loc}" key="local.button.profile" var="profile"/>
<fmt:message bundle="${loc}" key="local.button.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.button.main" var="home_page"/>


</head>
<body>
	<div class="main-block-header">
		
		<div class="separating-block-header">
			<h1 class="siteName">News</h1>
		</div>
		
		<div class="separating-block-header">
			<div class="button_place" >
				<div class="menu_place">
				<form style="width: 33.3%" action="Controller" method="post">
					 <input	type="hidden" name="command" value="go_to_main_page" /> 
					 <input	type="submit" class="button_Menu" value="${home_page}" /><br />
				</form>
				<form style="width: 33.3%" action="Controller" method="post">
					 <input	type="hidden" name="command" value="go_to_main_page" /> 
					 <input	type="submit" class="button_Menu" value="${home_page}" /><br />
				</form>
				<form style="width: 33.3%" action="Controller" method="post">
					 <input	type="hidden" name="command" value="go_to_main_page" /> 
					 <input	type="submit" class="button_Menu" value="${home_page}" /><br />
				</form>
				</div>
					
			</div>
			<div class="button_place" >
				<form action="Controller" method="post">
					 <input	type="hidden" name="command" value="GO_TO_CREATE_NEWS_PAGE" /> 
					 <input	type="submit" class="button_create_news" value="${creat_news}" /><br />
				</form>
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
					 <input type="hidden" name="command" value="LOGOUT_USER" /> 
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
	<h1 class="text_change">Изменение данных</h1>
	<div class=form>
				
		<form action="Controller" method="post">
		
			<input type="hidden" name="command" value="CHANGE_DATA" /> 
			 <c:if test="${sessionScope['change_data'].equals('password')}">
            	<input type="password" name="${sessionScope['change_data']}" placeholder="Enter new ${sessionScope['change_data']}" value="" />
            	<input type="password" name="secondPassword" placeholder="Repiat new password" value="" /> 
      		 </c:if>	
			 <c:if test="${!sessionScope['change_data'].equals('password')}">
            	<input type="text" name="${sessionScope['change_data']}" placeholder="Enter new ${sessionScope['change_data']}" value="" /> 
      		 </c:if>		
			<input type="password" name="passwordConfirm" placeholder="Введите пороль для подтверждения" value="" />
			<input style="background: #324AB2; width: 50%;" type="submit"
				class="button" value="Change ${sessionScope['change_data']}" />
		</form>
	</div>

	<p class="text_end" style="font-size: 20px">
		<c:out value="${created}" />
	</p>

</body>
</html>