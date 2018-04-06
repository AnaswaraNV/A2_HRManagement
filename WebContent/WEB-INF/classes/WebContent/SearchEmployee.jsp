<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Search Employee</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>

		<h1>Search For Employee</h1>

		<p>Search for an employee by typing any part of name, email, address, phone number or department</p>

		<form action="SearchEmp" method="post">
			<div class="form-group">
				<input type="text" name="search" required class="form-control" /> <br />
				<br/> 
				<input type="submit" name="searchButton" value="Search" class="btn btn-secondary" />
				<br/> <br/> <br/>
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>