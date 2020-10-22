<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="login" method="POST">
			<p>
				<input type="text" name="userEmail" placeholder="email" />
			</p>
			<p>
				<input type="password" name="userPassword" placeholder="password" />
			</p>
			<p>
				<input type="submit" value="Send" />
			</p>
		</form>
		<a href="/registration"></a>
	</div>
</body>
</html>