<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.epam.jewelry_shop.model.User"%>
<%@ page import="com.epam.jewelry_shop.model.Category"%>
<%@ page import="com.epam.jewelry_shop.model.Producer"%>
<%@ page import="com.epam.jewelry_shop.model.Product"%>
<%@ page import="com.epam.jewelry_shop.dao.ProductDAOImpl"%>
<%@ page import="com.epam.jewelry_shop.dao.ProductDAO"%>
<%@ page import="com.epam.jewelry_shop.beans.FilterFormBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="user" uri="WEB-INF/custom.tld"%>
<%@taglib prefix="chooseLocale" uri="WEB-INF/custom.tld"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>Material Design Bootstrap</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="css/mdb.min.css" rel="stylesheet">
<!-- Your custom styles (optional) -->
<link href="css/style.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
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


	<!--Main layout-->
	<main>

		<div class="container">
			<div class="row head">
				<div class=".col-6">
					<div class="brand">
						<h1>JewelryShop</h1>
					</div>
				</div>
				<div class=".col-4" style="margin-left: 45%;">
					<%
						String user = null;
					if (session.getAttribute("registeredUser") == null) {
						user = "guest";
					} else {
						user = ((User) session.getAttribute("registeredUser")).getFirstName();
					}
					%>

					<user:login userName="<%=user%>" />

				</div>

				<div class=".col-1">

					<img class="avatar" src="avatar">

				</div>
				<div class=".col-1 locale">

					<chooseLocale:locale />

				</div>


			</div>

			<!--Navbar-->
			<nav
				class="navbar navbar-expand-lg navbar-dark mdb-color lighten-3 mt-3 mb-5">


				<!-- Navbar brand -->


				<!-- Collapse button -->


				<!-- Collapsible content -->
				<div class="collapse navbar-collapse" id="basicExampleNav">

					<!-- Links -->
					<ul class="navbar-nav mr-auto">


						<li class="nav-item"><a class="nav-link" href="registration"><fmt:message
									key="home_jsp.registration" /></a></li>
						<li class="nav-item"><a class="nav-link" href="ShowTarget"><fmt:message
									key="home_jsp.target" /></a></li>

					</ul>
					<!-- Links -->

				</div>
				<!-- Collapsible content -->

			</nav>


			<div class="container">
				<div class="row">
					<div class=".col-5">
						<form id="filter" action="FilterServlet">
							<label><fmt:message key="home_jsp.label.chooseCategory" />
							</label> <select name="category">
								<option value="0">All categories</option>
								<%
									List<Category> catList = (List<Category>) request.getAttribute("categories");
								int selectCategoryId = 0;
								int selectProducerId = 0;
								ProductDAOImpl productDAO = (ProductDAOImpl) getServletContext().getAttribute("productDAO");
								double minPrice = productDAO.getMinPrice();
								double maxPrice = productDAO.getMaxPrice();
								String name = "";
								if (session.getAttribute("filters") != null) {
									FilterFormBean filterBean = (FilterFormBean) session.getAttribute("filters");
									selectCategoryId = filterBean.getCategoryId();
									selectProducerId = filterBean.getProducerId();
									name = filterBean.getName();
									minPrice = filterBean.getMinPrice();
									maxPrice = filterBean.getMaxPrice();
								}

								for (Category category : catList) {
								%>
								<%
									System.out.println("///////////////////////////////////////////" + selectCategoryId);
								%>
								<option value="<%=category.getIdCategory()%>"
									<%if (category.getIdCategory() == selectCategoryId) {%>
									<%="selected"%> <%}%>><%=category.getNameCategory()%></option>
								<%
									}
								%>

							</select></br> <label> <fmt:message
									key="home_jsp.option.selectProducer" />
							</label> <select name="producer">
								<option value="0">All producers</option>
								<%
									List<Producer> producersList = (List<Producer>) request.getAttribute("producers");

								for (Producer producer : producersList) {
								%>
								<option value="<%=producer.getIdProducer()%>"
									<%if (producer.getIdProducer() == selectProducerId) {%>
									<%="selected"%> <%}%>><%=producer.getNameProducer()%></option>
								<%
									}
								%>

							</select></br> <label><fmt:message key="home_jsp.label.productName" />
							</label> <input name="name" type="text" value="<%=name%>" /> </br> <label><fmt:message
									key="home_jsp.label.priceFrom" /> </label><input type="text"
								name="minPrice" value="<%=minPrice%>" value="0" /> </br> <label>
								<fmt:message key="home_jsp.label.priceTo" />
							</label><input type="text" name="maxPrice" value="<%=maxPrice%>" /></br> <input
								style="margin-left: 15px;" type="submit" name="filter"
								value="<fmt:message key="value.apply" />">
						</form>

					</div>
					<div class=".col-7">

						<form action="SorterServlet" id="sort">
							<label><fmt:message key="home_jsp.label.sortBy" /> </label> <select
								name="fieldSort">
								<option value="name"><fmt:message
										key="home_jsp.value.name" /></option>
								<option value="price"><fmt:message
										key="home_jsp.value.price" /></option>
							</select> <label><fmt:message key="home_jsp.label.typeOfSorting" />
							</label> <select name="typeSort">
								<option value="1"><fmt:message
										key="home_jsp.value.direct" /></option>
								<option value="2"><fmt:message
										key="home_jsp.value.reverse" /></option>
							</select> <input style="margin-left: 15px;" type="submit"
								value="<fmt:message key="value.apply" />">
						</form>
						<form id="numberPages" action="numberProductsServlet">
							<label><fmt:message
									key="home_jsp.label.chooseNumberProducts" /> </label> <select
								id="selectNumber" name="numberProducts">
								<%
									int numberPages = 0;
								if (session.getAttribute("numberProducts") != null) {
									numberPages = (int) session.getAttribute("numberProducts");
								}

								for (int i = 1; i <= 10; i++) {
								%>
								<option <%if (i == numberPages) {%> selected <%}%>
									value="<%=i%>"><%=i%></option>
								<%
									}
								%>
							</select>
						</form>

					</div>

				</div>
			</div>


			<!--/.Navbar-->

			<!--Section: Products v.3-->
			<section class="text-center mb-4">

				<!--Grid row-->
				<div class="row wow fadeIn">

					<!--Grid column-->

					<%
						List<Product> productList = (List<Product>) request.getAttribute("products");
					if (productList.size() == 0) {
					%>
					<h1>
						Products not found
						<h1>
							<%
								}
							%>
							<%
								for (Product product : productList) {
							%>

							<!--Card-->
							<div class="card">

								<!--Card image-->
								<div class="view overlay">
									<img src="<%=product.getImage()%>" class="card-img-top" alt="">
									<a>
										<div class="mask rgba-white-slight"></div>
									</a>
								</div>
								<!--Card image-->

								<!--Card content-->
								<div class="card-body text-center">
									<!--Category & Title-->
									<a href="" class="grey-text">
										<h5><%=product.getCategory().getNameCategory()%></h5>
									</a>
									<h5>
										<strong> <a href="" class="dark-grey-text"><%=product.getName()%>
												<span class="badge badge-pill danger-color">NEW</span> </a>
										</strong>
									</h5>

									<h4 class="font-weight-bold blue-text">
										<strong><%=product.getPrice()%></strong>
									</h4>
									<form action="AddTargetServlet">
										<input type="text" style="display: none;" class="prodId"
											value="<%=product.getIdProduct()%>" name="targetId">
										<input type="Submit" class="buy"
											value="<fmt:message key="value.buy" />">
									</form>
								</div>
								<!--Card content-->

							</div>

							<%
								}
							%>
						
				</div>

				<!--Card-->
		</div>

		</div>

		</div>


		<!-- SCRIPTS -->
		<!-- JQuery -->
		<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
		<!-- Bootstrap tooltips -->
		<script type="text/javascript" src="js/popper.min.js"></script>
		<!-- Bootstrap core JavaScript -->
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<!-- MDB core JavaScript -->
		<script type="text/javascript" src="js/mdb.min.js"></script>
		<!-- Initializations -->
		<script type="text/javascript">
		</script>
		<script src="js/serverSendJs.js"></script>
		</section>

		<%
			int numberPage = 1;
		if (session.getAttribute("numberPage") != null) {
			numberPage = (int) session.getAttribute("numberPage");
		}
		%>

		<div style="text-align: center;">
			<form action="PaginationServlet">
				<%
					for (int i = 1; i <= (int) request.getAttribute("pages"); i++) {
				%>
				<input class="btnPage" type="submit" <%if (i == numberPage) {%>
					<%="style=\"background:green;\""%> <%}%>  " name="numberPage"
					value="<%=i%>">
				<%
					}
				%>
			</form>
		</div>

		<!-- <div class="container" style="position: fixed; bottom: 20px;">
				<div style="text-align: center">
					<p style="text-align: center">
						© 2019 Copyright: <a
							href="https://mdbootstrap.com/education/bootstrap/"
							target="_blank"> MDBootstrap.com </a>
					</p>
				</div> -->
		</div>
</body>
</html>
