<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.epam.jewelry_shop.model.User"%>
<%@taglib prefix="capcha" uri="WEB-INF/custom.tld"%>
<%@taglib prefix="chooseLocale" uri="WEB-INF/custom.tld"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="img/core-img/favicon.ico">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
form label {
	display: inline-block;
	width: 100px;
}

form div {
	margin-bottom: 10px;
}

.error {
	color: red;
	margin-left: 5px;
}

label.error {
	display: inline;
}
</style>
</head>
<body>
<chooseLocale:locale />

	<c:set var="locale" value="${sessionScope.locale}" />
	<c:choose>
		<c:when test="${locale != null}">
			<fmt:setLocale value="${sessionScope.locale}" />
		</c:when>
		<c:otherwise>
			<c:forEach items="${cookie}" var="currentCookie">
				<c:if test="${currentCookie.key eq 'userlocale'}">
					<fmt:setLocale value="${currentCookie.value.value}" />
    <br />
				</c:if>
			</c:forEach>

		</c:otherwise>
	</c:choose>


	<fmt:setBundle basename="resources.pagecontent" />
	
	
	<%
		String firstName = "";
	String lastName = "";
	String email = "";
	String password = "";
	if (request.getParameter("first_name") != null) {
		firstName = request.getParameter("first_name");
	}
	if (request.getParameter("last_name") != null) {
		lastName = request.getParameter("last_name");
	}
	if (request.getParameter("email") != null) {
		email = request.getParameter("email");
	}
	if (request.getParameter("password") != null) {
		password = request.getParameter("password");
	}
	%>

	<div class="container">

		<form id="form" action="registration" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="first_name"><fmt:message
						key="registration_jsp.label.fName" /> </label> <input type="text"
					class="form-control" id="first_name" name="first_name"
					value="<%=firstName%>">

			</div>
			<div class="form-group">
				<label for="last_name"><fmt:message
						key="registration_jsp.label.lName" /> </label> <input type="text"
					class="form-control" id="last_name" name="last_name"
					value="<%=lastName%>">

			</div>
			<div class="form-group">
				<label for="email"><fmt:message
						key="registration_jsp.label.emailAddress" /> </label> <input type="email"
					class="form-control" id="email" name="email" value="<%=email%>">
				<small id="emailHelp" class="form-text text-muted">We'll
					never share your email with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="password"><fmt:message
						key="registration_jsp.label.password" /> </label> <input type="password"
					class="form-control" id="password" name="password"
					value="<%=password%>">
			</div>
			<div class="form-group">
				<label for="confirm_password"><fmt:message
						key="registration_jsp.label.confirmPassword" /></label> <input
					type="password" class="form-control" id="confirm_password">
			</div>
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="check">
				<label class="form-check-label" for="check"><fmt:message
						key="registration_jsp.label.checkMe" /></label>
			</div>
			<div class="form-check" style="margin-bottom: 80px;">
				<label class="form-check-label" for="check"><fmt:message
						key="registration_jsp.label.loadAva" /></label></br> <input type="file"
					name="file" id="file" class="form-check-input">

			</div>
			<capcha:imageCapcha />
			<input id="submit" type="submit"
				value="<fmt:message 
			key="value.submit" />">
		</form>
		<%
			if (request.getAttribute("unvalidEmail") != null) {

			String userEmail = (String) request.getAttribute("unvalidEmail");
			out.println("<p>User with email:  '" + userEmail + "' already exists!</p>");
		}
		if (request.getAttribute("validCapcha") != null) {

			out.println("<p>User with email:  '" + request.getAttribute("validCapcha") + "</p>");
		}
		%>

	</div>

	<!--<script src="js/jquery/jquery-2.2.4.min.js"></script>-->
	<!--<script src="js/validJquery.js"></script>-->
	<script src="js/validJs.js"></script>
</body>
</html>
