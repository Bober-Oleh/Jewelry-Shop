<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="userName"%>
<%
	String n = userName;
if (n.equals("guest")) {
%>
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
</div>
<%
	} else {
%>
<div>
	<p
		style="color: green; font-size: 20px; font-weight: bold; margin-right: 25px;">
		Hello,
		<%=userName%></p>
	<a href="logout">logout</a>
</div>
<%
	}
%>