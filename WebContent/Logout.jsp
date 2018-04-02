<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Logout</title>
</head>
<body>
	<div class="container">
		<%
			//invalidating the session
			HttpSession session1 = request.getSession();
			session1.invalidate();
		%>
		<h1>You have successfully Logged out!</h1>
		<img src="images/user.png"/> 
		<br/> <br/>
		<div class="form-group">
			<h2>Click to Login</h2>
			<div>
				<form action="index.html" method="get">
					<input class="btn btn-secondary" type="submit" value="Login">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>