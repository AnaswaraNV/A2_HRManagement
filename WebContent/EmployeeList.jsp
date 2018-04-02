<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ca.myseneca.model.*" %>
<%@page import="javax.servlet.*" %>
<%@page errorPage="errorpage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Employee List</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>

		<h1>Employee List</h1>

		<p>Show the employee list by entering department id or all the employees in the company</p>

		<form action="ShowEmpList" method="post">
			<div class="form-group">
				<label class="emp-list-label">Department Id:</label> 
				<br/> 
				<input class="form-control" name="depId" type="text"/> 
				<br/> <br/> 
				<input class="btn btn-secondary" name="empByIDButton" type="submit" value="Show Department Employees"/>
				<br/> <br/>
				<input class="btn btn-secondary" name="allEmpButton" type="submit" value="Show All Employees"/>
				<br/> <br/> <br/> 
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>