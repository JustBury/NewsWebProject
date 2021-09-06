<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link  rel="stylesheet" type="text/css" href="resources/css/CommonStyles.css">
<link  rel="stylesheet" type="text/css" href="resources/css/RegistrationPageStyles.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.rus" var="rus_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.eng" var="eng_button" />
<fmt:message bundle="${loc}" key="local.button.register" var="Register_button" />
<fmt:message bundle="${loc}" key="local.text.common.created" var="created"/>
<fmt:message bundle="${loc}" key="local.button.main" var="home_page"/>

<fmt:message bundle="${loc}" key="local.user.name" var="name"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.user.email" var="email"/>
<fmt:message bundle="${loc}" key="local.user.firstPassword" var="firstPassword"/>
<fmt:message bundle="${loc}" key="local.user.secondPassword" var="secondPassword"/>


<fmt:message bundle="${loc}" key="local.text.ar.password_hint" var="password_hint"/>
<fmt:message bundle="${loc}" key="local.text.ar.incorrect_data" var="incorrect_data_message"/>
<fmt:message bundle="${loc}" key="local.text.ar.already_exist" var="already_exist"/>

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
	
	
	
	<div class=form>
	
	<!-- Вывод надписи при некорректных данных -->
	
		<font color="red" size="3">
			<c:out value="${param.incorrect_data_message}"/>
			<c:out value="${param.email_is_busy}"/>
		</font>
		
		<!-- Поля для ввода имени, почты и пороля -->
		
		
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="Registration_new_user" />


			<input type="text" name="name" placeholder="${name}" value="" /> 
			<input type="text" name="surname" placeholder="${surname}" value="" />
			<input type="text" name="email" placeholder="${email}" value="" /> 
			<input type="password" name="firstPassword" placeholder="${firstPassword}" value="" />
			<input type="password" name="secondPassword" placeholder="${secondPassword}" value="" />
			
			<p><c:out value="${password_hint}"/></p>

			<input style="background: #324AB2; width: 50%;" type="submit"
				class="button" value="${Register_button}" />
		</form>
	</div>


<p class="text_end" style="font-size: 20px"><c:out value="${created}"/></p>
</body>
</html>