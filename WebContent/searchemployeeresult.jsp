<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="ca.myseneca.model.*"%>
    <%@page import="ca.myseneca.dataaccess.*"%>
    <%@page import="java.util.*" %>
    <%@page import="javax.servlet.*" %>
<!DOCTYPE html">
<html>
<head>
<title>Search Employee Page</title>
<jsp:include page="/header.jsp" />
</head>
<body>            
     <div class="container">
	 	<div class="logout_container">
	 	<%  
		   //Retrieving session attribute
		   String userId = null;
		   HttpSession Session_UserId=request.getSession(false);  
        if(Session_UserId!=null){  
        	userId=(String)Session_UserId.getAttribute("userId");  
        } %>
		<Label style="background-color: lightgrey;">
    			<%out.print(userId);%>
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
  <h2>Employee List</h2>  
  <br/><br/>        
  <table class="table table-striped">
	<tr>
			<th>Full Name</th>
			<th>Department</th>
			<th>Job Id</th>
			<th>Salary</th>
			<th>Email</th>
			<th>Phone Number</th>
		</tr>
    <%   		@SuppressWarnings("unchecked")
			List<Employee> employeeList = (List<Employee>) request
					.getAttribute("employeeList");
			if (employeeList != null) {
				for (Employee employee : employeeList) { %>
		<tr>
			<td><%
				String firstName = employee.getFirstName();
				String lastName = employee.getLastName();
				String fullName = firstName + " " + lastName;
				out.print(fullName);				
			%></td>
				<td><%
					if(employee.getDepartment() != null) {
						out.print(employee.getDepartment().getDepartmentName()); 
					}
				%>
			</td>	
			<td><%=employee.getJobId()%></td>
			<td><%=employee.getSalary()%></td>
			<td><a href="mailto:<%=employee.getEmail().toLowerCase()+ "@myseneca.ca" %>?Subject=I%20thought%20you%20might%20like%20to%20read%20this"><%=employee.getEmail().toLowerCase()+ "@myseneca.ca" %></a></td>
			<td><%=employee.getPhoneNumber()%></td>
		
		</tr>
		<% } } %>
	</table>
  <br/>
  			<form action="SearchEmployee.jsp" method="get">
				<input type="submit" value="Return" class="btn btn-secondary">
			</form>
  		
	 
</div>
</body>
</html>