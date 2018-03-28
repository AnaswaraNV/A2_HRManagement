<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="ca.myseneca.model.*"%>
    <%@page import="ca.myseneca.dataaccess.*"%>
    <%@page import="java.util.*" %>
    <%@page import="javax.servlet.*" %>
<!DOCTYPE html">
<html>
<head>
<title>Employee List</title>
<jsp:include page="/header.jsp" />
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
		<Label style="background-color: lightgrey;">
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
  <h2>Striped Rows</h2>
  <p>The .table-striped class adds zebra-stripes to a table:</p>            
  <table class="table table-striped">
	<tr>
			<th>Employee ID</th>
			<th>first Name</th>
			<th>last Name</th>
			<th>Email</th>
			<th>Phone Number</th>
			<th>Hire Date</th>
			<th>Job Id</th>
			<th>Salary</th>
			<th>Commision pct</th>
			<th>Manager Id</th>
			<th>Department Id</th>
		</tr>
    <%   		@SuppressWarnings("unchecked")
			List<Employee> employeeList = (List<Employee>) request
					.getAttribute("employeeList");
			if (employeeList != null) {
				for (Employee employee : employeeList) { %>
		<tr>
			<td><%=employee.getEmployeeId()%></td>
			<td><%=employee.getFirstName()%></td>
			<td><%=employee.getLastName()%></td>
			<td><%=employee.getEmail()%></td>
			<td><%=employee.getPhoneNumber()%></td>
			<td><%=employee.getHireDate()%></td>
			<td><%=employee.getJobId()%></td>
			<td><%=employee.getSalary()%></td>
			<td><%=employee.getCommissionPct()%></td>
			<td><%=employee.getManagerId()%></td>
			<td><%
					if(employee.getDepartment() != null) {
						out.print(employee.getDepartment().getDepartmentId()); 
					}
				%>
			</td>			
		</tr>
		<% } } %>
	</table>
  <br/>
  			<form action="EmployeeList.jsp" method="get">
				<input type="submit" value="Return" class="btn btn-secondary">
			</form>
  		
	 
</div>
</body>
</html>