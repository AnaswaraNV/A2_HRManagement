<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="ca.myseneca.model.*" %>
<%@ page import="ca.myseneca.dataaccess.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page errorPage="errorpage.jsp" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<title>Search Employee Page</title>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp" %>
		<br/> <br/>
		
		<h1>Employee List</h1>
		<br/> <br/>
		
		<table class="table table-striped">
			<tr>
				<th>Full Name</th>
				<th>Department</th>
				<th>Job Id</th>
				<th>Salary</th>
				<th>Email</th>
				<th>Phone Number</th>
			</tr>
			<%
				@SuppressWarnings("unchecked")
				List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
				if (employeeList != null) {
					for (Employee employee : employeeList) {
			%>
			<tr>
				<td>
					<%
						String firstName = employee.getFirstName();
						String lastName = employee.getLastName();
						String fullName = firstName + " " + lastName;
						out.print(fullName);
					%>
				</td>
				<td>
					<%
						if (employee.getDepartment() != null) {
							out.print(employee.getDepartment().getDepartmentName());
						}
					%>
				</td>
				<td><%=employee.getJobId()%></td>
				<td><%=employee.getSalary()%></td>
				<td>
					<a href="mailto:<%=employee.getEmail().toLowerCase() + "@myseneca.ca"%>?Subject=I%20thought%20you%20might%20like%20to%20read%20this"><%=employee.getEmail().toLowerCase() + "@myseneca.ca"%></a>
				</td>
				<td><%=employee.getPhoneNumber()%></td>

			</tr>
			<%
					}
				}
			%>
		</table>
		<br/> <br/>
		<input class="btn btn-secondary" type="button" value="Return" onclick="window.history.back()">
		<br/> <br/> <br/>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>