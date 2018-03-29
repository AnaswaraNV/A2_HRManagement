<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="ca.myseneca.model.*"%>
<%@page import="javax.servlet.*" %>
<%@page errorPage="errorpage.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link href="styles/main.css" rel="stylesheet" type="text/css">
<title>EmployeeListPage</title>
</head>
<body>
	<div class="container">
	<div class="logout_container">
		<% 
		   //Retrieving session attribute
		   String userId = null;
		   HttpSession Session_UserId1=request.getSession(false);  
        	if(Session_UserId1!=null){  
        		userId=(String)Session_UserId1.getAttribute("userId");  
         } %>
		<Label style="background-color: lightgrey;">
    			<%out.print("\t" + userId);%>
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
	
	<h1>Employee List Page</h1>
	
	<p>Show employee list by entering department id or all the employees in the company</p>
	
	<form action="ShowEmpListPage" method="post">
		<div class="form-group">
    		<label>Department Id </label> <br/>
        	<input type="text" name="DepId" class="form-control"/> <br/> <br/>
	  		<input type="submit" name="empByIDButton" value="Show Department Employees" class="btn btn-secondary" /><br/>
	  		<br/> <br/>
    		<input type="submit" name="allEmpButton" value="Show All Employees" class="btn btn-secondary"/> 
       </div>
</form>
	<jsp:include page="/footer.jsp" />
</div>
</body>
</html>