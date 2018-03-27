<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Search For Employee Page</title>
</head>
<body>
	<div class="container">
	<div class="logout_container">
		<% //receiving the global user Id
		   //application = getServletConfig().getServletContext(); 
		   //Retrieving session attribute
		   String userId = null;
		   HttpSession Session_UserId=request.getSession(false);  
        if(Session_UserId!=null){  
        userId=(String)Session_UserId.getAttribute("userId");  
        } %>
		<Label style="border-bottom: 6px solid blue;
    				background-color: lightgrey;">
    			<%out.print(     userId);%>
    	</Label> 
		<a href="Logout.jsp"> <img border="0" alt="logout"
			src="images/logout.jpg" width="20" height="20"> </a>
	</div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="EmployeeList.jsp">
    			<img src="images/hrmlogo.png" alt="Logo" style="width:50px;">
  			</a>
		<ul class="navbar-nav">		
			<li class="nav-item active"><a class="nav-link" href="EmployeeList.jsp">Employee
					List</a></li>
			<li class="nav-item"><a class="nav-link" href="NewEmployee.jsp">New
					Employee</a></li>
			<li class="nav-item"><a class="nav-link" href="SearchEmployee.jsp">Search
					Employee</a></li>
		</ul>
	</nav>
	<br/>
	<br/>
	
	<h1>Search For Employee Page</h1>
	
		<p>Search For an employee by typing any part of name, email address, phone number or department</p>
	
	<form action="SearchEmployeePage" method="post">
		<div class="form-group">
  
        	<input type="text" name="search" required class="form-control"/> <br/> <br/>
	  		<input type="submit" name="searchButton" value="Go" class="btn btn-secondary" /><br/>
	  		<br/> <br/>
       </div>
	</form>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-bottom">
		<p class="text-white" align="center">CJV805 - A2 HRManagement</p>
	</nav>
</div>
</body>
</html>