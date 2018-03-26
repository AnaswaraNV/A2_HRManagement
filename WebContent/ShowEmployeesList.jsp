<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="ca.myseneca.model.*"%>
    <%@page import="ca.myseneca.dataaccess.*"%>
    <%@page import="java.util.*" %>
    <%@page import="javax.servlet.*" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
</head>
<body>            
     <div class="container">
	 	<div class="logout_container">
				<% //receiving the global user Id
				   //application = getServletConfig().getServletContext(); 
				   //*check this in future name of application
				   String userId= (String) application.getAttribute("userId"); 
				 %>	
				<% out.print(userId); %> 
				<a href="Logout.jsp"> <img border="0" alt="logout"
					src="images/logout.jpg" width="20" height="20"> </a>
			 </div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="EmployeeList.jsp">
    			<img src="images/logo.jpg" alt="Logo" style="width:50px;">
  		</a>
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="EmployeeList.jsp">Employee
					List</a></li>
			<li class="nav-item"><a class="nav-link" href="#">New
					Employee</a></li>
			<li class="nav-item"><a class="nav-link" href="SearchEmployee.jsp">Search
					Employee</a></li>
		</ul>
	</nav>
  <h2>Striped Rows</h2>
  <p>The .table-striped class adds zebra-stripes to a table:</p>            
  <table class="table table-striped">

    <%  
// retrieve your list from the request, with casting
List<Employee> empList = (List<Employee>) request.getAttribute("employeeList"); %>
<c:forEach items="${empList}" var="employee">
    <tr>
      <td><c:out value="${employee.employeeId}" /></td>
      <td><c:out value="${employee.firstName}" /></td>
    </tr>
  </c:forEach>
  </table>
  
  <br/>
  	<form action="" method="get">
		<input type="submit" value="Return" class="btn btn-secondary">
	</form>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-bottom">
		<p class="text-white" align="center">CJV805 - A2 HRManagement</p>
	</nav>
</div>
	
</body>
</html>