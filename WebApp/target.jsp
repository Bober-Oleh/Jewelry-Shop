<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.epam.jewelry_shop.model.Product"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
td {
	width: 80px;
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

	<%
		if (session.getAttribute("order") != null) {
		session.setAttribute("order", null);
	%>
	<h3>Your order is accepted</h3>
	<%
		}
	%>
	<%
		if (request.getAttribute("listTarget") == null
			|| ((Map<Product, Integer>) request.getAttribute("listTarget")).size() == 0) {
	%>

	<h3>
		<fmt:message key="target_jsp.target" />
	</h3>

	<%
		}

	else {
	%>

	<%
		Map<Product, Integer> target = (Map<Product, Integer>) request.getAttribute("listTarget");
	%>
	<table>
		<tr>
			<td><fmt:message key="target_jsp.id" /></td>
			<td><fmt:message key="target_jsp.name" /></td>
			<td><fmt:message key="target_jsp.price" /></td>
			<td><fmt:message key="target_jsp.number" /></td>
			<td><fmt:message key="target_jsp.delete" /></td>
		</tr>
	</table>

	<%
		for (Product p : target.keySet()) {
	%>
	<form action="ServiceTargetServlet">
		<table>
			<tr>
				<input type="text" style="display: none" name="id"
					value="<%=p.getIdProduct()%>" />
				<td><%=p.getIdProduct()%></td>
				<td><%=p.getName()%></td>
				<td><%=p.getPrice()%></td>
				<td><select name="number">
						<%
							for (int i = 1; i <= 10; i++) {
						%>
						<option <%if (i == target.get(p)) {%> selected <%}%>
							value="<%=i%>"><%=i%></option>
						<%
							}
						%>
				</select></td>
				<td><input type="checkbox" value="delete" name="delete"></td>
				<td><input type="submit" value="<fmt:message key="value.apply"/>"></td>
			</tr>
		</table>
	</form>

	<%
		}
	%>
	<h3>
	<fmt:message key="target_jsp.totalPrice"/>:
		<%=(double) request.getAttribute("totalPrice")%><h3>
			<form action="OrderServlet">
				<input type="submit" value="<fmt:message key="target_jsp.arrangeOrder" />" name="order">
			</form>

			<%
				}
			%>
			<a href="/JewelryShop"><fmt:message key="button.back" /></a>
</body>
</html>