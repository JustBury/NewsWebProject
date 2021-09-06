<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>LIST USERS</title>


<link rel="stylesheet" href="resources/css/CommonStyles.css">
<link rel="stylesheet" href="resources/css/ListUsersStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site" />
<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button" />
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button" />
<fmt:message bundle="${loc}" key="local.button.login" var="Login_button" />
<fmt:message bundle="${loc}" key="local.button.register" var="Register_button" />
<fmt:message bundle="${loc}" key="local.text.mainpage.welcom" var="welcom" />
<fmt:message bundle="${loc}" key="local.text.common.created" var="created" />
<fmt:message bundle="${loc}" key="local.button.main" var="home_page"/>
<fmt:message bundle="${loc}" key="local.button.profile" var="profile"/>
<fmt:message bundle="${loc}" key="local.button.logout" var="logout"/>

<fmt:message bundle="${loc}" key="local.text.listusers.email" var="email"/>
<fmt:message bundle="${loc}" key="local.text.listusers.iduser" var="iduser"/>
<fmt:message bundle="${loc}" key="local.text.listusers.name" var="name"/>
<fmt:message bundle="${loc}" key="local.text.listusers.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.text.listusers.role" var="role"/>
<fmt:message bundle="${loc}" key="local.text.listusers.date" var="date"/>
<fmt:message bundle="${loc}" key="local.text.listusers.listusers" var="listusers"/>
<fmt:message bundle="${loc}" key="local.text.listusers.delete" var="delete"/>

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

	<div class="div_table">
	
	<table>
		<caption>${listusers}</caption>
		<tr><th>${iduser}</th><th>${name}</th><th>${surname}</th><th>${email}</th><th>${role}</th><th>${date}</th><th></th></tr> 
		<c:forEach items="${listUsers}" var="listUsers">
		<tr>
		<td><c:out value="${listUsers.getId()}"/></td>
		<td><c:out value="${listUsers.getName()}"/></td>
		<td><c:out value="${listUsers.getSurname()}"/></td>
		<td><c:out value="${listUsers.getEmail()}"/></td>
		<td><c:out value="${listUsers.getRole()}"/></td>
		<td><c:out value="${listUsers.getDateRegistration()}"/></td>
		<td><form action="Controller" method="post">
				 <input type="hidden" name="idUser" value="${listUsers.getId()}" /> <input
						type="hidden" name="command" value="DELETE_USER" /> <input
						type="submit" class="button_delete" value="${delete}" /><br />
				</form></td></tr>
		</c:forEach>
		
	</table>
	</div>
	
	<p class="text_end" style="font-size: 20px">
		<c:out value="${created}" />
	</p>
</body>
</html>

