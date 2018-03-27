<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
		//invalidating the session
		   HttpSession session1=request.getSession();  
           session1.invalidate();  
	 %>
	<h1>You have successfully Logged out!</h1>
	<div class="form-group">
  		<label>Login again</label>
  		<br/><br/>
  		<div>
  			<form action="index.html" method="get">
				<input type="submit" value="Login" class="btn btn-secondary">
			</form>
  		</div>
  		<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-bottom">
		<p class="text-white" align="center">CJV805 - A2 HRManagement</p>
	</nav>
  	</div>
  	
  	<br/>
  	<br/>
  	<br/>
</body>
</html>