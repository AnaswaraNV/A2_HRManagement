<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link href="styles/main.css" rel="stylesheet" type="text/css">
<title>New Employee Page</title>
</head>
<body>
	<div class="container">
	<div class="logout_container">
		<% //receiving the global user Id
		   //application = getServletConfig().getServletContext(); 
		   //*check this in future name of application
		   String userId= (String) application.getAttribute("userId"); 
		 %>	
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
	
		<h1>New Employee Page</h1>
	
		<p>Create a new employee</p>
	
	<form action="NewEmployeePage" method="post">
		<div class="form-group">
  			<Label>Employee Id</Label>
        	<input type="text" name="EmployeeId" class="form-control" readonly/> <br/>
        	<Label>First Name</Label>
        	<input type="text" name="firstName" class="form-control" /> <br/>
        	<Label>Last Name Name</Label>
        	<input type="text" name="lastName" class="form-control" /> <br/>
        	<Label>Department Id</Label>
        	<input type="text" name="dep Id" class="form-control" /> <br/>
        	<Label>Email</Label>
        	<input type="text" name="email" class="form-control" /> <br/>
        	<Label>Phone Number</Label>
        	<input type="text" name="phonenumber" class="form-control" /> <br/>
        	<Label>Hire Date</Label>
        	<input type="text" name="hdate" class="form-control" /> <br/>
        	<Label>Salary</Label>
        	<input type="text" name="salary" class="form-control" /> <br/>
        	<Label>Comm pct</Label>
        	<input type="text" name="cmpct" class="form-control" /> <br/>
        	
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