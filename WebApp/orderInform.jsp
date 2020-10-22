<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
h3 {
	margin-top: 20px;
}

.btn {
	margin-top: 20px;
}
</style>
</head>
<body>

	<!--/.Carousel Wrapper-->
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

	<form action="/JewelryShop/OrderServlet">
		<h3>
			<fmt:message key="order_jsp.delivery" />
		</h3>
		<select name="delivery">
			<option value="Pickup">Pickup</option>
			<option value="Home delivery">Home delivery</option>
			<option value="Post delivery">Post delivery</option>
		</select></br>
		<h3>
			<fmt:message key="order_jsp.payment" />
		</h3>
		<select name="payment">
			<option value="Card">Card</option>
			<option value="Cash">Cash</option>
			<option value="C.O.D.">C.O.D.</option>
		</select></br>
		<h3>
			<fmt:message key="order_jsp.details" />
		</h3>
		<label><fmt:message key="order_jsp.fio" /></label><input type="text"
			name="name" /> <label><fmt:message key="order_jsp.address" />
		</label> <input type="text" name="adress" /> <input class="btn" type="submit"
			value="<fmt:message key="value.apply" />" name="recvisits">
	</form>
</body>
</html>