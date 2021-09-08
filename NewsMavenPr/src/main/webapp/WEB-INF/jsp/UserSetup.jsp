<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link  rel="stylesheet" type="text/css" href="resources/css/CommonStyles.css">
<link  rel="stylesheet" type="text/css" href="resources/css/UserSetupStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button"/>
<fmt:message bundle="${loc}" key="local.text.common.created" var="created"/>
<fmt:message bundle="${loc}" key="local.button.create_news" var="creat_news"/>
<fmt:message bundle="${loc}" key="local.button.main" var="home_page"/>

<fmt:message bundle="${loc}" key="local.text.usersetub.email" var="email"/>
<fmt:message bundle="${loc}" key="local.text.usersetub.name" var="name"/>
<fmt:message bundle="${loc}" key="local.text.usersetub.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.text.usersetub.RegistrationDate" var="RDATE"/>
<fmt:message bundle="${loc}" key="local.text.usersetub.password" var="password"/>
<fmt:message bundle="${loc}" key="local.button.usersetab.change" var="change"/>
<fmt:message bundle="${loc}" key="local.text.usersetub.myprofile" var="profile"/>
<fmt:message bundle="${loc}" key="local.button.creatnews.role" var="role"/>

<fmt:message bundle="${loc}" key="local.button.logout" var="logout"/>

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
	<h1 class="myProfile"><c:out value="${profile}"/></h1>
	
	
	<div class="profile">
		
		<div style="padding-top: 10px;">
			
		<form action="Controller" method="post">
			<font color="red" size="3">
				<c:out value="${param.not_valid_data}"/>		
			</font>
			<p class="text_profile"> <input type="hidden" name="command" value="GO_TO_CHANGE_DATA_PAGE" /> 
									<input type="hidden" name="change_data" value="CHANGE_EMAIL"/>
			<c:out value="${email}"/> <font class = "text_data">
						<c:out value="${sessionScope['user'].getEmail()}"/>
					</font>  
				 <input type="submit" class="button_change" value="${change}" /></p>
		</form>
		</div>
		
	<div style="padding-top: 10px;">
		<form action="Controller" method="post">
			<p class="text_profile"> <input type="hidden" name="command" value="GO_TO_CHANGE_DATA_PAGE" /> 
									<input type="hidden" name="change_data" value="CHANGE_NAME"/>
			<c:out value="${name}"/> <font class = "text_data">
						<c:out value="${sessionScope['user'].getName()}"/>
					</font>  
				 <input type="submit" class="button_change" value="${change}" /></p>
		</form>
		</div>
		<div style="padding-top: 10px;">
		<form action="Controller" method="post">
			<p class="text_profile"> 
			<input type="hidden" name="command" value="GO_TO_CHANGE_DATA_PAGE" /> 
			<input type="hidden" name="change_data" value="CHANGE_SURNAME" />
			<c:out value="${surname}"/> <font class = "text_data">
						<c:out value="${sessionScope['user'].getSurname()}"/>
					</font> 
			<input type="submit" class="button_change" value="${change}" /></p>
		</form>
		</div>
		<div style="padding-top: 10px;">
			<p class="text_profile"> 
			<c:out value="${role}"/> <font class = "text_data">
						<c:out value="${sessionScope['user'].getRole()}"/>
					</font> 
			</p>	
		</div>
			<div style="padding-top: 10px;">
			<p class="text_profile"> 
			<c:out value="${RDATE}"/> <font class = "text_data">
						<c:out value="${sessionScope['user'].getDateRegistration()}"/>
					</font> 
			</p>	
		</div>
		<div style="padding-top: 10px;">
		<form action="Controller" method="post">
			<p class="text_profile"> <input type="hidden" name="command" value="GO_TO_CHANGE_DATA_PAGE" /> 
									 <input type="hidden" name="change_data" value="CHANGE_PASSWORD"/>
			<c:out value="${password}"/>  <input type="submit" class="button_change" value="${change}" /></p>
		</form>
		</div>
	</div>
	<p class="text_end" style="font-size: 20px">
		<c:out value="${created}" />
	</p>
</body>
</html>